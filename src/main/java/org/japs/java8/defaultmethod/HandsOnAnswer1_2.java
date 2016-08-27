package org.japs.java8.defaultmethod;

/**
 * Movable メソッド内で GameEntity getEntity(); を利用して、実装した例．
 * GameEntity 以外でも実装できますが、getEntity() が public のため
 * 外部へインスタンスを公開してしまうことになります．
 */
public final class HandsOnAnswer1_2 {
	public static void main(String[] args) throws Exception {
		System.out.println("-- HandsOnAnswer1_2 --");
		new HandsOnAnswer1_2().startGame();
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

	class Player extends GameEntity implements Movable {
		@Override
		public GameEntity getEntity() { return this; }
	}

	class Enemy extends GameEntity implements Movable {
		@Override
		public GameEntity getEntity() { return this; }
	}

	class Weapon extends GameEntity {}
	
	interface Movable {
		
		GameEntity getEntity();
		
		default void moveForward() {
			getEntity().y -= 1;
		}
		default void moveBack() {
			getEntity().y += 1;
		}
		default void moveLeft() {
			getEntity().x -= 1;
		}
		default void moveRight() {
			getEntity().x += 1;
		}
	}
}