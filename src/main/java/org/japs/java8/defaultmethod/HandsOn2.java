package org.japs.java8.defaultmethod;

/**
 * mixin を利用して、複数の機能を組み合わせたクラスを作成してください．
 */
public final class HandsOn2 {
	public static void main(String[] args) throws Exception {
		System.out.println("-- HandsOn2 --");
		new HandsOn2().start();
	}
	
	private void start() {
		/*
		 * ここに実行テストコードを記述してください．
		 * 例:
		 * new X().execB();
		 * new Y().execC();
		 */
	}

	/*
	 * ここに mixin 用のクラス、インターフェースを定義してください．
	 * 例:
	 * interface A { default void execA() { System.out.println("A"); }
	 * interface B { default void execB() { System.out.println("B"); }
	 * interface C { default void execC() { System.out.println("C"); }
	 * class X implements A, B { } // 何も定義していないが execA(), execB() の機能を保持している
	 * class Y implements C, D { } // 何も定義していないが execB(), execC() の機能を保持している
	 */

}