package org.japs.java8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * [ルール]
 * ・ループ構文(for, while, iterateなど)禁止
 * ・Stream API を利用すること
 * ・
 */
public class HandsOnAnswer {

/* Products.all(): List<Product>
{ "name": "本", "value": 2980, "saleDay": "2012-02-12", "salesCompanies": [{ "name": "Jahoo", "telNo": "111-2222-3333" }, { "name": "Vamazon", "telNo": "222-5555-1234" }] }
{ "name": "ハサミ", "value": 300, "saleDay": "2012-06-22", "salesCompanies": [{ "name": "Jahoo", "telNo": "111-2222-3333" }, { "name": "Aooale", "telNo": "111-9393-8383" }, { "name": "Vamazon", "telNo": "222-5555-1234" }, { "name": "Acebook", "telNo": "333-9899-1111" }, { "name": "Peitter", "telNo": "444-1111-3355" }] }
{ "name": "ギター", "value": 89980, "saleDay": "2013-01-01", "salesCompanies": [{ "name": "Acebook", "telNo": "333-9899-1111" }, { "name": "Vamazon", "telNo": "222-5555-1234" }] }
{ "name": "扇風機", "value": 8000, "saleDay": "2012-02-12", "salesCompanies": [{ "name": "Vamazon", "telNo": "222-5555-1234" }] }
{ "name": "机", "value": 19980, "saleDay": "2015-09-02", "salesCompanies": [{ "name": "Vamazon", "telNo": "222-5555-1234" }, { "name": "Acebook", "telNo": "333-9899-1111" }] }
{ "name": "謎の物体", "value": 9, "saleDay": "2012-12-29", "salesCompanies": [{ "name": "Peitter", "telNo": "444-1111-3355" }, { "name": "Vamazon", "telNo": "222-5555-1234" }] }
{ "name": "赤ペン", "value": 120, "saleDay": "2013-09-08", "salesCompanies": [{ "name": "Aooale", "telNo": "111-9393-8383" }, { "name": "Vamazon", "telNo": "222-5555-1234" }] }
{ "name": "青ペン", "value": 120, "saleDay": "2014-06-28", "salesCompanies": [{ "name": "Jahoo", "telNo": "111-2222-3333" }, { "name": "Acebook", "telNo": "333-9899-1111" }] }
{ "name": "パン", "value": 150, "saleDay": "2016-08-31", "salesCompanies": [{ "name": "Aooale", "telNo": "111-9393-8383" }, { "name": "Peitter", "telNo": "444-1111-3355" }] }
{ "name": "水", "value": 300, "saleDay": "2016-08-01", "salesCompanies": [{ "name": "Peitter", "telNo": "444-1111-3355" }, { "name": "Vamazon", "telNo": "222-5555-1234" }] }
{ "name": "スーツ", "value": 32980, "saleDay": "2015-08-03", "salesCompanies": [{ "name": "Jahoo", "telNo": "111-2222-3333" }, { "name": "Vamazon", "telNo": "222-5555-1234" }] }
{ "name": "衣類", "value": 5980, "saleDay": "2015-10-20", "salesCompanies": [{ "name": "Aooale", "telNo": "111-9393-8383" }, { "name": "Peitter", "telNo": "444-1111-3355" }] }
{ "name": "時計", "value": 2980, "saleDay": "2016-06-02", "salesCompanies": [{ "name": "Aooale", "telNo": "111-9393-8383" }] }
{ "name": "PC", "value": 210000, "saleDay": "2016-01-31", "salesCompanies": [{ "name": "Acebook", "telNo": "333-9899-1111" }, { "name": "Peitter", "telNo": "444-1111-3355" }] }
{ "name": "車", "value": 1342980, "saleDay": "2015-08-22", "salesCompanies": [{ "name": "Vamazon", "telNo": "222-5555-1234" }] }
*/

	public static void problem_0() {
		System.out.println("-- problem_0 --");
		System.out.println("適切な関数型インターフェースで定義してください．");
		
		/* ラムダ式の構文
		 * (引数型1 引数名1, 引数型2 引数名2) -> {
		 *     式1;
		 *     式2;
		 *     return 式3;
		 * }
		 * ・引数が1つの場合は()や型を省略可能
		 * ・式が1つの場合は return を省略可能
		 * 例: 引数名1 -> 式1
		 * 
		 * ・引数が0個の場合は()は省略不可
		 * 例: () -> 式1
		 */
		
		UnaryOperator<Product> a = p -> p;
		Supplier<Product> b = () -> new Product("-", 0, null, null);
		Function<Product, String> c = (Product p) -> p.getName();
		Consumer<Product> d = p -> System.out.println(p);
		Predicate<Product> e = p -> 1000 < p.getValue();
		BiPredicate<Product, Product> f = (p1, p2) -> p1.equals(p2);
		BiFunction<Product, Product, Boolean> g = (p1, p2) -> p1.equals(p2);
		BinaryOperator<Product> h = (p1, p2) -> {
			int p1CompaniesSize = p1.getSalesCompanies().size();
			int p2CompaniesSize = p2.getSalesCompanies().size();
			return p1CompaniesSize < p2CompaniesSize ? p1 : p2;
		};
		ToIntFunction<Product> i = p -> p.getValue();
		ToDoubleBiFunction<Product, Product> j = (p1, p2) -> (p1.getValue() + p2.getValue()) / 2.0;
	}

	public static void problem_1() {
		System.out.println("-- problem_1 --");
		System.out.println("全商品情報を表示してください．");
		
		Products.all().stream()
			// ここに Stream API で処理
			.forEach(p -> System.out.println(p))
		;
//		Products.all()
//			.stream()
//			.forEach(System.out::println);
	}

	public static void problem_2() {
		System.out.println("-- problem_2 --");
		System.out.println("1,000円以下の商品情報を表示してください．");
		
		Products.all().stream()
			// ここに Stream API で処理
			.filter(p -> p.getValue() <= 1000)
			.forEach(System.out::println)
		;
		// [出力例]
		// { "name": "ハサミ", "value": 300, "saleDay": "2012-06-22", "salesCompanies": [{ "name": "Jahoo", "telNo": "111-2222-3333" }, { "name": "Aooale", "telNo": "111-9393-8383" }, { "name": "Vamazon", "telNo": "222-5555-1234" }, { "name": "Acebook", "telNo": "333-9899-1111" }, { "name": "Peitter", "telNo": "444-1111-3355" }] }
		// { "name": "謎の物体", "value": 9, "saleDay": "2012-12-29", "salesCompanies": [{ "name": "Peitter", "telNo": "444-1111-3355" }, { "name": "Vamazon", "telNo": "222-5555-1234" }] }
		// { "name": "赤ペン", "value": 120, "saleDay": "2013-09-08", "salesCompanies": [{ "name": "Aooale", "telNo": "111-9393-8383" }, { "name": "Vamazon", "telNo": "222-5555-1234" }] }
		// { "name": "青ペン", "value": 120, "saleDay": "2014-06-28", "salesCompanies": [{ "name": "Jahoo", "telNo": "111-2222-3333" }, { "name": "Acebook", "telNo": "333-9899-1111" }] }
		// { "name": "パン", "value": 150, "saleDay": "2016-08-31", "salesCompanies": [{ "name": "Aooale", "telNo": "111-9393-8383" }, { "name": "Peitter", "telNo": "444-1111-3355" }] }
		// { "name": "水", "value": 300, "saleDay": "2016-08-01", "salesCompanies": [{ "name": "Peitter", "telNo": "444-1111-3355" }, { "name": "Vamazon", "telNo": "222-5555-1234" }] }
	}

	public static void problem_3() {
		System.out.println("-- problem_3 --");
		System.out.println("値段が最も高い商品を取得する．");
		
		System.out.println(
			Products.all().stream()
				.max((p1, p2) -> p1.getValue() - p2.getValue()).get()
		);
		System.out.println(
			Products.all().stream()
				// ここに Stream API で処理
				.max(Comparator.comparingInt(Product::getValue)).get()
		);
		// [出力例]
		// { "name": "車", "value": 1342980, "saleDay": "2015-08-22", "salesCompanies": [{ "name": "Vamazon", "telNo": "222-5555-1234" }] }
	}

	public static void problem_4() {
		System.out.println("-- problem_4 --");
		System.out.println("全商品の価格平均値を取得してください．");
		
		System.out.println(
			Products.all().stream()
				// ここに Stream API で処理
				.mapToInt(Product::getValue)
				.average().getAsDouble()
		);
		// [出力例]
		// 114457.26666666666 
	}

	public static void problem_5() {
		System.out.println("-- problem_5 --");
		System.out.println("発売年毎の商品数を表示してください．(発売年順に並び替え)");
		/*
		 * [参考]
		 * ・発売年の取得: Product#getSaleDay().getYear()
		 * ・グルーピング: Stream#collect(Collectors.groupingBy(Function<T, K>)) -> Map型で帰る
		 * ・Map型をStream化: Map#entrySet().stream()
		 * ・EntryからKey/Val取得: Entry#getKey(), Entry#getValue()
		 */
//		System.out.println(
//			Products.all().stream()
//				// ここに Stream API で処理
//				.collect(Collectors.groupingBy(p -> p.getSaleDay().getYear()))
//				.entrySet().stream()
//				.sorted(Comparator.comparingInt(Entry::getKey))
//				.map(e -> e.getKey() + ": " + e.getValue().size())
//				.collect(Collectors.joining("\n"))
//		);
		System.out.println(
			Products.all().stream()
				.sorted(Comparator.comparing(Product::getSaleDay))
				.collect(TreeMap<Integer, Integer>::new, (m, p) -> {
							int year = p.getSaleDay().getYear();
							m.put(year, m.getOrDefault(year, 0) + 1);
						}, (m1, m2) -> m1.putAll(m2))
				.entrySet().stream()
				.map(e -> e.getKey() + ": " + e.getValue())
				.collect(Collectors.joining("\n"))
		);
		// [出力例]
		// 2012: 4
		// 2013: 2
		// 2014: 1
		// 2015: 4
		// 2016: 4
	}
	
	public static void problem_6() {
		System.out.println("-- problem_6 --");
		System.out.println("int 型配列を Stream 化し、count()を取得してください．");
		
		final int[] ARY = { 1, 2, 3, 4, 5 };
		
		System.out.println(
			// ここに Stream API で処理
			// Stream.of(ARY).count() // NG
			// Arrays.asList(ARY).stream().count() // NG
			Arrays.stream(ARY).count()
		);
		// [出力例]
		// 5
	}

	public static void problem_() {
		System.out.println("-- problem_ --");
		System.out.println("");
		
		System.out.println(
			// ここに Stream API で処理
		);
		// [出力例]
	}	
	
	public static void main(String... args) {
		problem_1(); System.out.println();
		problem_2(); System.out.println();
		problem_3(); System.out.println();
		problem_4(); System.out.println();
		problem_5(); System.out.println();
		problem_6(); System.out.println();
	}

}