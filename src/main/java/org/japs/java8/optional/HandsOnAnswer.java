package org.japs.java8.optional;

import java.util.Optional;

public class HandsOnAnswer {
	public static void main(String[] args) {
		Person a = new Person(160, 55, false);
		a.calcBMI().ifPresent(System.out::println);
		
		Person b = new Person(170, 60, true);
		Optional<Double> bmiB = b.calcBMI();
		if (bmiB.isPresent()) {
			System.out.println(bmiB.get());
		} else {
			System.out.println("bmiB is null.");
		}
		
		Person c = new Person(180, 65, true);
		c.calcBMI().orElseThrow(() -> new RuntimeException("bmiC is null."));
	}

	private static class Person {
		
		private Integer height;
		private Integer weight;
		private boolean isSecret;
		
		public Person(
				Integer height,
				Integer width,
				boolean isSecret) {
	
			this.height = height;
			this.weight = width;
			this.isSecret = isSecret;
		}
		
		public Optional<Double> calcBMI() {
			return isEnabledCalc()
					? Optional.of(weight / (height / 100.0 * height / 100.0))
					: Optional.empty();
		}
		
		private boolean isEnabledCalc() {
			return !isSecret
					&& weight != null && weight >= 1
					&& height != null && height >= 1
			;
		}
	}
}
