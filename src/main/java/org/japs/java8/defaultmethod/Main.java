package org.japs.java8.defaultmethod;

public final class Main {
	public static void main(String[] args) {
		
	}
}

interface Movable {

	int walk();
	
	default int run() {
		return walk() * 2;
	}
}

class Person implements Movable {
	@Override
	public int walk() { return 10; }
}

//コンパイルエラー(The default method func() inherited from Intf2 conflicts with another method inherited from Intf)
//Intf2がdefaultメソッドで実装していても
//Xクラスで実装する必要がある
//class X implements Intf, Intf2 {
//
//}
interface Intf {
	void func();
}
interface Intf2 {
	default void func() {
		System.out.println("Intf2#func");
	}
}