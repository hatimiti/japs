package org.japs.basic.type._class;

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
		if (!(obj instanceof Person)) {
			return false;
		}
		Person another = (Person) obj;
		return this.height == another.height
			&& this.weight == another.weight;
	}
}