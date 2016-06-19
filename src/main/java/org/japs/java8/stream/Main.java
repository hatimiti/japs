package org.japs.java8.stream;

import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Integer> list
			= Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 });
		list.parallelStream().forEach(System.out::print);
		// 7681359402 -> 出力順は都度変わる
	}
}