package org.japs.java8.defaultmethod;

/**
 * Movable メソッド内で ((GameEntity) this) を利用して、実装した例．
 * GameEntity 以外に Movable インターフェースを実装すると例外となります．
 */
public final class HandsOnAnswer2 {
	public static void main(String[] args) throws Exception {
		System.out.println("-- HandsOnAnswer1_1 --");
		new HandsOnAnswer2().startGame();
	}
	
	private void startGame() {
		
		Player p = new Player();
		Enemy e = new Enemy();
		
		p.moveForward();
		p.moveRight();
		System.out.println("player " + p);
		
		e.moveBack();
		e.moveLeft();
		System.out.println("enemy " + e);
		
	}
	
	class GameEntity {
		protected int x;
		protected int y;
		@Override
		public String toString() {
			return String.format("position: %d, %d", x, y);
		}
	}

	class Player extends GameEntity implements Movable {}
	class Enemy extends GameEntity implements Movable {}
	class Weapon extends GameEntity {}

	interface Movable {
		default void moveForward() {
			((GameEntity) this).y -= 1;
		}
		default void moveBack() {
			((GameEntity) this).y += 1;
		}
		default void moveLeft() {
			((GameEntity) this).x -= 1;
		}
		default void moveRight() {
			((GameEntity) this).x += 1;
			// GameEntity.class.cast(this).x += 1;
		}
	}
	
}