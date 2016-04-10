package org.japs.applied.jvm;

public class Main {
	static int x;
	public static void main(String[] args) {
		long s = System.currentTimeMillis();
		System.out.println(1);
		x = sum();
		System.out.println(2);
		long e = System.currentTimeMillis();
		System.out.println(e - s);
	}
	
	public static int sum() {
		int r = 0;
		for (int i = 0; i < 90000000; i++) {
			r += i;
		}
		return r;
	}
}