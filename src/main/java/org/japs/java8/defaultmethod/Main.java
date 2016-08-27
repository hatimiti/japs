package org.japs.java8.defaultmethod;

public final class Main {
	public static void main(String[] args) throws Exception {
		new Main().execute(args);
	}
	
	private void execute(String[] args) {
		System.out.println("-- Parent#greet() --");
		System.out.println(new Parent(){}.greet());
		// Parent: hello()

		System.out.println("\n-- ChildA#greet() --");
		System.out.println(new ChildA(){}.greet());
		// ChildA: (Parent: hello())

		System.out.println("\n-- ChildB#greet() --");
		System.out.println(new ChildB(){}.greet());
		// ChildB: greet()

		System.out.println("\n-- Implementer#greet() --");
		System.out.println(new Implementer().greet());
		// ChildA: (ChildB: hello())
		
	}
	
	interface Parent {
		default String hello() {
			return "Parent: hello()";
		};
		
		default String greet() {
			return hello();
		}
	}
	interface ChildA extends Parent {
		@Override
		default String greet() {
			return "ChildA: (" + hello() + ")";
		}
	}
	interface ChildB extends Parent {
		@Override
		default String hello() {
			return "ChildB: hello()";
		}
		
		@Override
		default String greet() {
			return "ChildB: greet()";
		}
	}
	class Implementer implements ChildA, ChildB {
		@Override
		public String greet() {
			return ChildA.super.greet();
		}
	}
}

