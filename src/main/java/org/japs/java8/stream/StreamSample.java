package org.japs.java8.stream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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

		System.out.println(
				persons.stream()
				.filter(p -> p.getAge() >= 30) 
				.mapToInt(p -> p.getSalary())
				.summaryStatistics().getAverage()
				);

		Stream<String> st = Stream.of("a");
		System.out.println(
				st.parallel().sequential().isParallel()
				);

		AtomicInteger count = new AtomicInteger(0);
		String[] ary = Stream.of(Arrays.asList("a", "b"), Arrays.asList("c", "d"))
				.flatMap(l -> {
					List<String> r = new ArrayList<>(l);
					r.add("x");
					return r.stream();
				})
				.distinct()
				.sorted((e1, e2) -> e1.length() - e2.length())
				.toArray(String[]::new);
		//.forEach(e -> System.out.println("" + count.incrementAndGet() + e));
		System.out.println(ary);

		System.out.println();
		count.set(0);
		Stream.of(Arrays.asList("a", "b"), Arrays.asList("c", "d"))
		.flatMap(List::stream)
		.forEach(e -> System.out.println("" + count.incrementAndGet() + e));
		/*
        System.out.println(
            persons.stream()
                .collect(Collectors.maxBy((Person a, Person b) -> a.getAge() - b.getAge()))
                .entrySet()
                .stream()
                .mapToObj(p -> p.getEnteredDate())
        );
		 */
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
