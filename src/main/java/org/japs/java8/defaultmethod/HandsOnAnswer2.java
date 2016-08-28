package org.japs.java8.defaultmethod;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * mixin を利用して、複数の機能を組み合わせたクラスを作成してください．
 */
public final class HandsOnAnswer2 {
	public static void main(String[] args) throws Exception {
		System.out.println("-- HandsOn2 --");
		new HandsOnAnswer2().start();
	}
	
	private void start() {
		PC pc = new PC();
		pc.start("Chrome");
		pc.input("Hello");
		
		Tablet tb = new Tablet();
		tb.start("Java");
		tb.input("Hello");
	}

	abstract class Device implements Display, CPU, InputDataModifiable {
		private final BlockingQueue<String> in;
		public Device () {
			in = new ArrayBlockingQueue<>(3);
		}
		@Override
		public BlockingQueue<String> getQueue() { return in; }
		
		public void start(String application) {
			ExecutorService s = null;
			try {
				s = Executors.newSingleThreadExecutor();
				s.submit(() -> calc(application, this));
			} finally {
				if (s != null) {
					s.shutdown();
				}
			}
		}
	}
	
	class PC extends Device implements Keybord {}
	class Tablet extends Device implements TouchPanel {}

	/** 画面表示を可能にする機能を付加するインタフェース */
	interface Display {
		default void print(String message) {
			System.out.println(message);
		}
	}
	/** 演算処理を可能にする機能を付加するインタフェース */
	interface CPU extends InputDataModifiable {
		default void calc(String application, Display d) {
			try {
				d.print(application + "/" + getQueue().poll(3, TimeUnit.SECONDS));
			} catch (InterruptedException e) {
				System.out.println("end-of-input");
			}
		}
	}
	/** タッチ操作を可能にする機能を付加するインタフェース */
	interface TouchPanel extends InputDataModifiable {
		default void input(String word) {
			getQueue().add("By TouchPanel: " + word);
		}
	}
	/** キーボード入力を可能にする機能を付加するインタフェース */
	interface Keybord extends InputDataModifiable {
		default void input(String word) {
			getQueue().add("By Keybord: " + word);
		}
	}

	/**
	 * mixin ではインスタンス変数が保持できないため、当インタフェースで共有データをやり取りする．
	 */
	interface InputDataModifiable {
		BlockingQueue<String> getQueue();
	}
}