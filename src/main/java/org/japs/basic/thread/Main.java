package org.japs.basic.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		Thread m = Thread.currentThread();
		System.out.printf("priority of %s is %d.\n", m.getName(), m.getPriority());
		System.out.println("It's showing at the main thread.");

		Arrays.stream(m.getStackTrace()).forEach(System.out::println);

		// ExecutorService pool = Executors.newFixedThreadPool(3);
		// IntStream.range(0, 10).forEach(i -> {
		// pool.submit(() -> {
		// System.out.println(String.format("i: %s, th: %s", i,
		// Thread.currentThread().getName()));
		// });
		// });
		// pool.shutdown();

		// new AtomicTest().execute();
		//
		// BlockingQueue<Integer> q = new SynchronousQueue<>();
		// new Thread(new SQThreadA(q)).start();
		// new Thread(new SQThreadB(q)).start();

		// Share share = new Share();
		Share2 share = new Share2();
		Thread x = new ThreadA(share);
		Thread y = new ThreadB(share);
		x.setName("japs thread A");
		y.setName("japs thread B");
		x.start();
		y.start();
	}

}

////////////////////////////////////////////////////////
// AtomicInteger

class AtomicTest {
	// private int num1 = 0;
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
		this.queue = queue;
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
		this.queue = queue;
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

	public ThreadA(Share share) {
		this.share = share;
	}

	public void run() {
		for (;;) {
			for (int i = 0; i < 5; i++) {
				share.set();
			}
		}
	}
}

class ThreadB extends Thread {
	private Share share;

	public ThreadB(Share share) {
		this.share = share;
	}

	public void run() {
		for (;;) {
			for (int i = 0; i < 5; i++) {
				share.print();
			}
		}
	}
}

class Share {
	private int a = 0;
	private String b;

	public synchronized void set() {
		while (a != 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		a++;
		b = "data";
		notify();
		System.out.println("  set() a: " + a + " b: " + b);
	}

	public synchronized void print() {
		while (b == null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		a--;
		b = null;
		notify();
		System.out.println("print() a: " + a + " b: " + b);
	}
}

class Share2 extends Share {
	private int a = 0;
	private String b;

	private List<Object> objs = new ArrayList<>();

	@Override
	public synchronized void set() {
		a++;
		b = "data";
		System.out.println("  set() a: " + a + " b: " + b);

		try {
			objs.add(new Object());
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void print() {
		a--;
		b = null;
		System.out.println("print() a: " + a + " b: " + b);

		try {
			objs.remove(new Object());
			System.out.println("objs.size: " + objs.size());
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
