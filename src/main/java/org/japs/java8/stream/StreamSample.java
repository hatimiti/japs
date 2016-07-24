package org.japs.java8.stream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

		averageOfAge(persons);
		checkSequentialFromParallelStream();
		checkFlatMap();

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
		
		Optional<String> firstName = Optional.of("a");
		Optional<String> lastName = Optional.of("b");
		
		Optional<String> r =
				firstName.flatMap(fn ->
					lastName.map(ln -> fn + ln));
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

	private static void averageOfAge(List<Person> persons) {
		System.out.println(
			persons.stream()
				.filter(p -> p.getAge() >= 30) 
				.mapToInt(p -> p.getSalary())
				.summaryStatistics().getAverage()
		);
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
