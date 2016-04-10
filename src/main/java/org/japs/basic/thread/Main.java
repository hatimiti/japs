package org.japs.basic.thread;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		Thread m = Thread.currentThread();
		System.out.printf("priority of %s is %d.\n", m.getName(), m.getPriority());
		System.out.println("It's showing at the main thread.");

		Arrays.stream(m.getStackTrace())
			.forEach(System.out::println);

		ExecutorService pool = Executors.newFixedThreadPool(3);
		IntStream.range(0, 10).forEach(i -> {
			pool.submit(() -> {
				System.out.println(String.format("i: %s, th: %s", i, Thread.currentThread().getName()));
			});
		});
		pool.shutdown();


//		new AtomicTest().execute();

//		BlockingQueue<Integer> q = new SynchronousQueue<>();
//		new Thread(new SQThreadA(q)).start();
//		new Thread(new SQThreadB(q)).start();

//		Share share = new Share();
//		ThreadA a = new ThreadA(share);
//		ThreadB b = new ThreadB(share);
//		a.start();
//		b.start();
	}

}

////////////////////////////////////////////////////////
// AtomicInteger

class AtomicTest {
//	private int num1 = 0;
	private AtomicInteger num1 = new AtomicInteger(0);
	
	Runnable r1 = () -> {
		IntStream.range(0, 10000).forEach((i) -> num1.incrementAndGet()); 
	};
	public void execute() {
		IntStream.range(0, 1000).forEach((i) -> {
			Thread t = new Thread(r1);
			t.start();
			num1.decrementAndGet();
		});
		System.out.println(num1);
	}
}


////////////////////////////////////////////////////////
// SynchronousQueue

class SQThreadA implements Runnable {
	
	BlockingQueue<Integer> queue;
	public SQThreadA(BlockingQueue<Integer> queue) {
		this.queue =queue;
	}

	@Override
	public void run() {
		int count = 0;
		while (count < 10) {
			try {
				this.queue.put(++count);
				System.out.println("put: " + count);
			} catch (InterruptedException e) {
			}
		}
	}
}

class SQThreadB implements Runnable {
	
	BlockingQueue<Integer> queue;
	public SQThreadB(BlockingQueue<Integer> queue) {
		this.queue =queue;
	}

	@Override
	public void run() {
		int count = 0;
		while (count < 10) {
			try {
				count = this.queue.take();
				System.out.println("take: " + count);
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
		}
	}
}
////////////////////////////////////////////////////////
class ThreadA extends Thread {
	private Share share;
	public ThreadA(Share share) { this.share = share; }
	public void run() {
		for (int i = 0; i < 5; i++) { share.set(); }
	}
}

class ThreadB extends Thread {
	private Share share;
	public ThreadB(Share share) { this.share = share; }
	public void run() {
		for (int i = 0; i < 5; i++) { share.print(); }
	}
}

class Share {
	private int a = 0;
	private String b;
	
	public synchronized void set() {
		while (a != 0) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		a++; b = "data";
		notify();
		System.out.println("  set() a: " + a + " b: " + b);
	}
	
	public synchronized void print() {
		while (b == null) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		a--; b = null;
		notify();
		System.out.println("print() a: " + a + " b: " + b);
	}
}