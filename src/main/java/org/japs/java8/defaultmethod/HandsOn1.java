package org.japs.java8.defaultmethod;

/**
 * 下記に定義してある、StandardMovable クラスを削除し
 * その機能の代替として Movable インターフェースのメソッド定義を default メソッドを使って実装してください。
 * コード量を減らすことを目的とします。
 */
public final class HandsOn1 {
	public static void main(String[] args) throws Exception {
		System.out.println("-- HandsOn1 --");
		new HandsOn1().startGame();
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
		
		private Movable m = new StandardMovable(this);

		@Override
		public void moveForward() { m.moveForward(); }
		@Override
		public void moveBack() { m.moveBack(); }
		@Override
		public void moveLeft() { m.moveLeft(); }
		@Override
		public void moveRight() { m.moveRight(); }
	}

	class Enemy extends GameEntity implements Movable {

		private Movable m = new StandardMovable(this);

		@Override
		public void moveForward() { m.moveForward(); }
		@Override
		public void moveBack() { m.moveBack(); }
		@Override
		public void moveLeft() { m.moveLeft(); }
		@Override
		public void moveRight() { m.moveRight(); }
	}

	class Weapon extends GameEntity {
		
	}

	class StandardMovable implements Movable {

		private GameEntity entity;
		
		public StandardMovable(GameEntity ge) {
			this.entity = ge;
		}

		@Override
		public void moveForward() { entity.y -= 1; }
		@Override
		public void moveBack() { entity.y += 1; }
		@Override
		public void moveLeft() { entity.x -= 1; }
		@Override
		public void moveRight() { entity.x += 1; }
	}

	interface Movable {
		void moveForward();
		void moveBack();
		void moveLeft();
		void moveRight();
	}
}