package org.japs.basic.type._class;

import java.util.Arrays;
import java.util.Objects;

/**
 * 人間の情報を表現するクラス．
 * @author m-kakimi
 *
 */
public final class Person {
	
	public Person(int height, int weight) {
		this.height = height;
		this.weight = weight;
	}
	
	/** 身長 */
	private int height;
	/** 体重 */
	private int weight;
	
	public int getHeight() {
		return height;
	}
	
	public int getWeight() {
		return weight;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Person)) {
			return false;
		}
		Person another = (Person) obj;
		return this.height == another.height
			&& this.weight == another.weight;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		final int X = 31;
		result = result * X + this.height;
		result = result * X + this.weight;
		return result;
//		return java.util.Objects.hash(this.height, this.weight); // from 1.7
//		return java.util.Arrays.hashCode(
//			new Object[] { this.height, this.weight }); // from 1.5
	}
	
	@Override
	public String toString() {
		return new StringBuilder(Person.class.getName())
				.append(" { height: ").append(this.height).append(",")
				.append(" weight: ").append(this.weight)
				.append(" } ").toString();
	}
}