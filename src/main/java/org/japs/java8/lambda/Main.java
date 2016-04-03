package org.japs.java8.lambda;

import java.util.function.Function;

public class Main {
	public static void main(String[] args) {
		// Java8
		中級者 になるための = Java勉強会(学習->学習.日々学習());
		
		// Java7
		中級者 になるための_= Java勉強会(new Function<初級者, 中級者>() {
			@Override
			public 中級者 apply(初級者 学習) {
				return 学習.日々学習();
			}
		});
	}
	
	public static 中級者 Java勉強会(Function<初級者, 中級者> c) {
		return c.apply(new 初級者());
	}
}
interface 級 { 級 日々学習(); }
class 初級者 implements 級 { public 中級者 日々学習() { return new 中級者(); } }
class 中級者 implements 級 { public 上級者 日々学習() { return new 上級者(); } }
class 上級者 implements 級 { public 上級者 日々学習() { return new 上級者(); } }