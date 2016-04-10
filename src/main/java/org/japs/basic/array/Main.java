package org.japs.basic.array;

public class Main {
	public static void main(String[] args) {
		int[] a = { 1, 2 };
		int[] b = new int[] { 1, 2 };
		//NG hello({ 1, 2 });
		hello(a); // 2 1
		hello(a); // 2 9
		world(1, 2); // 2 1
	}
	public static void hello(int[] x) {
		System.out.print(x.length);
		System.out.print(" ");
		System.out.println(x[0]);
		x[0] = 9;
	}
	public static void world(int... x) {
		System.out.print(x.length);
		System.out.print(" ");
		System.out.println(x[0]);
	}
}
