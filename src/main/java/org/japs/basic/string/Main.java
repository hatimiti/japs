package org.japs.basic.string;

public class Main {
	public static void main(String[] args) {
		System.out.println(1 + "x");     // 1x
		System.out.println(1 + 2 + "x"); // 3x
		System.out.println("x" + 1 + 2); // x12
		
		String s = "𩸽は魚";
		System.out.println(s.length()); // 4
		System.out.println(s.codePointCount(0, s.length())); // 3
		
		s.chars()
			.forEach(c -> System.out.println(c));
		System.out.println("--------");
		s.codePoints().forEach(i -> System.out.println(String.valueOf(Character.toChars(i))));
	}
}