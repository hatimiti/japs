package org.japs.basic.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class Main {
	
	private static final int SIZE = 9_000_000;
	
	public static void main(String[] args) throws Exception {
		
		List<Integer> al = new ArrayList<>();
		timer("ArrayList: fill: ", () -> {
			fill(al);
		});
		
		List<Integer> ll = new LinkedList<>();
		timer("LinkedList: fill: ", () -> {
			fill(ll);
		});
		
		timer("ArrayList: removeFirst: ", () -> {
			al.remove(0);
		});
		
		timer("LinkedList: removeFirst: ", () -> {
			ll.remove(0);
		});
	}
	
	public static void fill(List<Integer> l) {
		for (int i = 0; i < SIZE; i++) {
			l.add(i);
		}
	}
	
	public static void timer(String title, Runnable r) {
		long s = System.currentTimeMillis();
		r.run();
		long e = System.currentTimeMillis();
		System.out.println(e - s);
	}
}