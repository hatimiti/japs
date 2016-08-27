package org.japs.java8.defaultmethod;

import java.io.Serializable;

/**
 * 
 */
public final class HandsOn2 {
	public static void main(String[] args) throws Exception {
		System.out.println("-- HandsOn2 --");
		new HandsOn2().start();
	}
	
	private void start() {
		
	}
	
	abstract class Vehicle {
		protected int speed;
	}
	
	class Car extends Vehicle {
		
		public void run() {
			System.out.println("Driving speed: " + speed);
		}
		
		
	}
	class Bike extends Vehicle {
		
		public void run() {
			System.out.println("Driving speed: " + speed);
		}
	}

}