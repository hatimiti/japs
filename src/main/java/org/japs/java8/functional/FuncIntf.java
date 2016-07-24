package org.japs.java8.functional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public interface FuncIntf<T> {
	
	void x(T a);
	
	public static void main(String... args) {
		(new FuncIntf<String>() { public void x(String a) { System.out.println(a); }}).x("Hello");
		((FuncIntf<String>) a -> System.out.println(a)).x("Hello");
		
		List<String> strings = Arrays.asList("a", "b", "c");
		
		// Java 7
		Collections.sort(strings, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		// Java 8
		Collections.sort(strings, (o1, o2) -> o1.compareTo(o2));
		Collections.sort(strings, (String o1, String o2) -> { return o1.compareTo(o2); });
	}
	
}

@FunctionalInterface
interface SampleFuncIntf {
	int calc(int a, int b);
}