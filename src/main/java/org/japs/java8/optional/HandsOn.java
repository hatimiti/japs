package org.japs.java8.optional;

import java.util.Optional;

public class HandsOn {
	public static void main(String[] args) {
		Person a = new Person(Optional.of(160), Optional.of(55), Optional.of(false));
		Double bmiA = a.calcBMI();
		if (bmiA != null) {
			System.out.println(bmiA);
		}
		
		Person b = new Person(Optional.of(170), Optional.of(60), Optional.of(true));
		Double bmiB = b.calcBMI();
		if (bmiB != null) {
			System.out.println(bmiB);
		} else {
			System.out.println("bmiB is null.");
		}

		Person c = new Person(Optional.of(180), Optional.of(65), Optional.of(true));
		Double bmiC = c.calcBMI();
		if (bmiC == null) {
			throw new RuntimeException("bmiC is null.");
		}
	}
	
	private static class Person {
		
		private Integer height;
		private Integer weight;
		private Optional<Boolean> isSecret;
		
		public Person(
				Optional<Integer> height,
				Optional<Integer> width,
				Optional<Boolean> isSecret) {

			this.height = height.get();
			this.weight = width.get();
			this.isSecret = isSecret;
		}
		
		public Double calcBMI() {
			return isEnabledCalc()
					? weight / (height / 100.0 * height / 100.0)
					: null;
		}
		
		private boolean isEnabledCalc() {
			return !isSecret.get()
					&& weight != null && weight >= 1
					&& height != null && height >= 1
			;
		}
	}
}