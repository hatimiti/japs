package org.japs.java8.optional;

import java.util.Optional;

public class Main {

	public static void main(String[] args) {
		// String s = Creator.createSomethingBy(1);
		// Processor.process(s);
		Optional<String> s = Creator.createSomethingBy(1);
		s.ifPresent(Processor::process);
	}

	public void testOptional() {

		Optional<String> a = func("Hello");
		Optional<String> b = func("Hello, World");

		System.out.println(a); // Optional[!Hello!]
		System.out.println(b); // Optional.empty

		func("hello").orElseThrow(() -> new RuntimeException("Optional Empty"));
		func("hello").orElse("HELLO");

		Optional<Optional<String>> map1 = func("hello").map(s -> func(s + "W"));
		Optional<String> map2 = func("hello").flatMap(s -> func(s + "W"));

		System.out.println(map1); // Optional[Optional[!!hello!W!]]
		System.out.println(map2); // Optional[!!hello!W!]

		func("Hello").ifPresent(v -> {
			System.out.println(v); // !Hello!
		});
	}

	public Optional<String> func(String value) {
		return Optional.ofNullable(value)
				.filter(v -> v.length() < 10)
				.map(v -> "!" + v + "!");
	}
}

class Creator {

	// /**
	// * 与えられた引数を基に文字列を生成して返します。
	// * i が偶数の場合は文字列{@code "x"}を返し、奇数の場合は {@code null}を返します。
	// * @param i 文字列生成シード
	// * @return 生成した文字列
	// */
	// public static String createSomethingBy(int i) {
	// return i % 2 == 0 ? "x" : null;
	// }
	/**
	 * 与えられた引数を基に文字列を生成して返します。 i が偶数の場合は文字列{@code Optional["x"]}を返し、 奇数の場合は
	 * {@code Optional.empty}を返します。
	 * 
	 * @param i
	 *            文字列生成シード
	 * @return 生成した文字列
	 */
	public static Optional<String> createSomethingBy(int i) {
		return i % 2 == 0 ? Optional.of("x") : Optional.empty();
	}
}

class Processor {

	public static void process(String s) {
		System.out.println(s.length());
	}
}