package org.japs.util;

import java.util.function.Consumer;

public final class JapsUtil {
	
	public static void timer(String title, Runnable r) {
		long s = System.currentTimeMillis();
		r.run();
		long e = System.currentTimeMillis();
		System.out.println(title + (e - s));
	}
	
	public static Runnable jfor(int count, Consumer<Integer> cons) {
		if (count <= 0) {
			return () -> { cons.accept(0); };
		}
		return () -> {
			for (int i = 0; i < count; i++) {
				cons.accept(i);
			}
		};
	}
}