package org.japs.basic.type._class;

import java.util.HashMap;
import java.util.Map;

public final class Main {
	public static void main(String[] args) throws InterruptedException {
		Person p = new Person(170, 58);
		String s = "Person: " + p;
		System.out.println(s);
		// Person: org.japs.basic.type._class.Person@1edf1c96
		
		System.out.println(p.getClass() == Person.class); // true
		
		testPersonEquals();
		System.out.println("------------------------");
		testPersonHashCode();
		System.out.println("------------------------");
		testPersonToString();
	}
	
	public static void testPersonEquals() {
		Person x = new Person(170, 58);
		Person y = new Person(170, 58);
		Person z = new Person(170, 58);
		
		Person __ = new Person(171, 59);
		// 1. reflexive
		System.out.println("x eq x: " + x.equals(x)); // true
		// 2. symmetric
		System.out.println("x eq y: " + x.equals(y)); // true
		System.out.println("y eq x: " + y.equals(x)); // true
		System.out.println("_ eq x: " + __.equals(x)); // false
		// 3. transitive
		System.out.println("x eq y: " + x.equals(y)); // true
		System.out.println("y eq z: " + y.equals(z)); // true
		System.out.println("x eq z: " + x.equals(z)); // true
		// 4. consistent
		System.out.println("x eq y: " + x.equals(y)); // true
		System.out.println("x eq y: " + x.equals(y)); // true
		// 5. null
		System.out.println("x eq null: " + x.equals(null)); // false
	}
	
	public static void testPersonHashCode() {
		Map<Person, String> m = new HashMap<>();
		m.put(new Person(100, 20), "a");
		m.put(new Person(100, 21), "b");
		m.put(new Person(101, 20), "c");
		
		assert m.get(new Person(100, 20)).equals("a");
		assert m.get(new Person(100, 21)).equals("b");
		assert m.get(new Person(101, 20)).equals("c");
	}
	
	public static void testPersonToString() {
		System.out.println(new Person(170, 55));
	}
}