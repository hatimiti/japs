package org.japs.basic.array;

public class Main {
	public static void main(String[] args) {
		int[] a = { 1, 2 };
		int[] b = new int[] { 1, 2 };
		//NG hello({ 1, 2 });
		hello(new int[] { 1, 2 });
		world(1, 2);
	}
	public static void hello(int[] x) {}
	public static void world(int... x) {}
}
