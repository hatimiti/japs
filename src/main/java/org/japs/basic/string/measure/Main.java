package org.japs.basic.string.measure;

import static org.japs.util.JapsUtil.jfor;
import static org.japs.util.JapsUtil.timer;

import java.util.Random;

public class Main {
	
	public static final int COUNT = 90000000;
	public static final int[] RANDS = new int[COUNT];
	
	public static final String A = "AAA";
	public static final String B = "BBB";
	public static final String C = "CCC";
	public static final String D = "DDD";
	public static final String E = "EEE";
	
	public static volatile String result;
	
	public static void main(String[] args) {
		
		Random random = new Random();
		jfor(COUNT, i -> {
			RANDS[i] = random.nextInt(i);
		});
		
		System.out.println(plus(0).equals(sb(0)));
		System.out.println(sb(0).equals(fmt(0)));
		System.out.println(plus(0));
		
		jfor(COUNT, i -> { result = plus(RANDS[i]); });
		jfor(COUNT, i -> { result = sb(RANDS[i]); });
//		jfor(COUNT, i -> { result = fmt(RANDS[i]); });

		timer("plus: ", jfor(COUNT, i -> { result = plus(RANDS[i]); }));
		timer("sb: ", jfor(COUNT, i -> { result = sb(RANDS[i]); }));
//		timer("fmt: ", jfor(COUNT, i -> { result = fmt(RANDS[i]); }));
		
		new P();
	}
	
	public static String plus(int rand) {
		return "create table " + (A + B) + " ("
				+ C + " integer not null, "
				+ D + " real not null, "
				+ E + " real not null, "
				+ rand + " real not null)";
	}

	public static String fmt(int rand) {
		return String.format("create table %s%s (%s integer not null, %s real not null, %s real not null, %s real not null)",
				A, B, C, D, E, rand);
	}
	
	public static String sb(int rand) {
		return new StringBuilder("create table ")
				.append(A)
				.append(B)
				.append(" (")
				.append(C)
				.append(" integer not null, ")
				.append(D)
				.append(" real not null, ")
				.append(E)
				.append(" real not null, ")
				.append(rand)
				.append(" real not null)").toString();
	}
	
	public static class P {
		private P() {
			
		}
	}
}