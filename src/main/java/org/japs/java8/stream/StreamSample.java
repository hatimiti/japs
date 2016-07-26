package org.japs.java8.stream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSample {

	public static void main(String[] args) {
		List<Person> persons = new ArrayList<>();

		persons.add(new Person(22, 240_000, LocalDate.of(2000, 12, 22)));
		persons.add(new Person(42, 350_000, LocalDate.of(1988, 1, 10)));
		persons.add(new Person(30, 290_000, LocalDate.of(1999, 8, 18)));
		persons.add(new Person(22, 250_000, LocalDate.of(2001, 3, 1)));
		persons.add(new Person(31, 310_000, LocalDate.of(2002, 4, 4)));
		persons.add(new Person(42, 310_000, LocalDate.of(2000, 12, 10)));

		System.out.println("-- sample --");
		averageOfAge(persons);
		averageOfAgeByLoop(persons);
		
		checkSequentialFromParallelStream();
		checkFlatMap();
		checkReduce(persons);
		
		createStream();
		terminalOperation();
		
		System.out.println(new Random(System.currentTimeMillis()).nextInt(9));
	}

	private static void checkReduce(List<Person> persons) {
		System.out.println(
			persons.stream()
				.reduce(0, (i, p) -> i + p.getSalary(), (i, j) -> i + j)
		); // 1750000
		System.out.println(
			persons.stream()
				.mapToInt(Person::getSalary)
				.reduce(0, (i, j) -> i + j)
		); // 1750000
		System.out.println(
			persons.stream()
				.mapToInt(Person::getSalary)
				.reduce((i, j) -> i + j)
		); // OptionalInt[1750000]
	}

	private static void checkFlatMap() {
		String[] ary = Stream.of(Arrays.asList("a", "b"), Arrays.asList("c", "d"))
				.flatMap(l -> {
					List<String> r = new ArrayList<>(l);
					r.add("x");
					return r.stream();
				})
				.distinct()
				.sorted(Comparator.comparing(String::length))
//				.sorted((e1, e2) -> e1.length() - e2.length())
				.toArray(String[]::new);
		//.forEach(e -> System.out.println("" + count.incrementAndGet() + e));
		System.out.println(ary.length);
	}

	private static void checkSequentialFromParallelStream() {
		Stream<String> st = Stream.of("a");
		System.out.println(
			st.parallel().sequential().isParallel()
		);
	}

	private static double averageOfAge(List<Person> persons) {
		return persons.stream()
			.filter(p -> p.getAge() >= 30) 
			.mapToInt(p -> p.getSalary())
			.summaryStatistics().getAverage();
	}
	
	private static double averageOfAgeByLoop(List<Person> persons) {
		double result = 0.0;
		int sum = 0;
		int count = 0;
		for (Person p : persons) {
			if (p.getAge() >= 30) {
				sum += p.getSalary();
				count++;
			}
		}
		if (count != 0) {
			result = ((double) sum) / count;
		}
		return result;
	}
	
	@SuppressWarnings("unused")
	private static void createStream() {
		Stream<String> s1  = Stream.of("Hello Stream");
		Stream<String> s2  = Stream.of("Hello", "Stream");
		Stream<String> s3  = Stream.empty();
		IntStream      s4  = IntStream.iterate(1, n -> n + n); //1,2,4,8,16,...
		DoubleStream   s5  = DoubleStream.generate(() -> Math.random()); // 乱数の無限Stream
		IntStream      s6  = IntStream.range(1, 10); // 1〜9
		IntStream      s7  = IntStream.rangeClosed(1, 10); // 1〜10
		Stream<String> s8  = Arrays.asList("a", "b")/*List<String>*/.stream();
		Stream<String> s9  = Arrays.asList("a", "b")/*List<String>*/.parallelStream();
		Stream<String> s10 = Arrays.stream(new String[] { "Hello", "Stream"});
	}

	private static void terminalOperation() {
		/*#1*/
		Stream.of("a", "b", "c").forEach(e -> System.out.print(e)); //abc
		System.out.println();
		/*#2*/
		Stream.of("b", "c", "a").parallel().forEachOrdered(e -> System.out.print(e)); //bca
		System.out.println();
		/*#3*/
		Optional<Integer> x = Stream.of(3, 1, 2).min((i, j) -> i.compareTo(j));
		System.out.println(x); // Optional[1]
		/*#4*/
		Optional<Integer> y = Stream.of(3, 1, 2).max((i, j) -> i.compareTo(j));
		System.out.println(y); // Optional[3]
		/*#5*/
		long z = Stream.of(0, 0, 0).count();
		System.out.println(z); // 3
		/*#6*/
		boolean a = Stream.of(1, 2, 3).anyMatch(e -> e.equals(2));
		System.out.println(a); // true
		/*#7*/
		boolean b = Stream.of(2, 2, 2).allMatch(e -> e.equals(2));
		System.out.println(b); // true
		/*#8*/
		boolean c = Stream.of(2, 2, 2).noneMatch(e -> e.equals(3));
		System.out.println(c); // true
		/*#9*/
		Optional<Integer> d = Stream.of(2, 1, 3).findFirst();
		System.out.println(d); // Optional[2]
		/*#10*/
		Optional<Integer> e = Stream.of(2, 1, 3).findAny();
		System.out.println(e); // Optional[2]
		/*#11*/
		Object[] f = Stream.of(2, 1, 3).toArray();
		System.out.println(f); // [Ljava.lang.Object;@17f052a3 
		/*#12*/
		Integer[] g = Stream.of(2, 1, 3).toArray(i -> new Integer[i]);
		System.out.println(g); // [Ljava.lang.Integer;@685f4c2e
	}
}

class Person {

	private int age;
	private int salary;
	private LocalDate enteredDate;

	public Person(int age, int salary, LocalDate enteredDate) {
		this.age = age;
		this.salary = salary;
		this.enteredDate = enteredDate;
	}

	public int getAge() {
		return age;
	}

	public int getSalary() {
		return salary;
	}

	public LocalDate getEnteredDate() {
		return enteredDate;
	}
}
