package org.japs.basic.thread.dump;

/**
 * 
 * @author m-kakimi
 * @see java.lang.Thread.State
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		ShareData sd = new ShareBuilderData();
		Thread ta = new Thread(new MyRunnerA(sd));
		Thread tb = new Thread(new MyRunnerB(sd));
		
		ta.setName("Japs Thread A");
		tb.setName("Japs Thread B");
		
		ta.start();
		tb.start();
		
		System.out.println(sd);
		
	}
}

interface ShareData {
	void add(String str);
	void reset();
}

class ShareBuilderData implements ShareData {
	private StringBuilder sb;
	
	public ShareBuilderData() {
		reset();
	}

	@Override
	public /*synchronized*/ void add(String str) {
		for (int i = 0; i < 10000; i++) {
			synchronized (sb) {
				sb.append(str);
			}
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("*");
	}
	
	@Override
	public synchronized void reset() {
		sb = new StringBuilder();
	}
	
	@Override
	public String toString() {
		return sb.toString();
	}
}

class MyRunnerA implements Runnable {
	
	ShareData sd;
	
	public MyRunnerA(ShareData sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		for (;;) {
			sd.add("XA ");
		}
	}
	
}

class MyRunnerB implements Runnable {
	
	ShareData sd;
	
	public MyRunnerB(ShareData sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		for (;;) {
			sd.add("XB ");
		}
	}
	
}