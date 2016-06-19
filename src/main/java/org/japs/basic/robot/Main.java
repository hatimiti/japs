package org.japs.basic.robot;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * 
 * @author m-kakimi
 * @see java.lang.Thread.State
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		MinecraftRobot r = new MinecraftRobot();

		r.changeWindow();
		r.keyEsc();

		r.dig(4);
		r.forward(1);

		r.oepnInventory();
		r.clickLItem1();
		r.clickLWork1();
		r.clickLCreated();

		r.clickRWork1();
		r.clickLWork2();
		r.clickLCreated();

		r.clickLItem1();
		r.keyEsc();

		r.keyF5();
		r.keyF5();

		r.dig(5);

		r.keyF5();
	}

}

class MinecraftRobot {

	private static final int OP_DELAY_MS = 200;

	private static final int ITEM1_X = 350;
	private static final int ITEM1_Y = 520;
	private static final int WORK1_X = 550;
	private static final int WORK1_Y = 280;
	private static final int WORK2_X = WORK1_X;
	private static final int WORK2_Y = WORK1_Y + 20;
	private static final int CREAT_X = WORK1_X + 100;
	private static final int CREAT_Y = WORK1_Y + 12;

	private Robot r;

	public MinecraftRobot() {
		try {
			r = new Robot();
		} catch (AWTException e) {
			new RuntimeException(e);
		}
	}

	public void changeWindow() {
		r.keyPress(KeyEvent.VK_META);
		key(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_META);
		delay();
	}
	
	public void attack(int num) {
		for (int i = 0; i < num; i++) {
			clickL();
			delay();
		}
	}
	
	public void dig(int second) {
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.delay(second * 1000);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		delay();
	}
	
	public void forward(int second) {
		r.keyPress(KeyEvent.VK_W);
		r.delay(second * 1000);
		r.keyRelease(KeyEvent.VK_W);
		delay();
	}
	
	public void oepnInventory() {
		key(KeyEvent.VK_E);
	}
	
	public void keyEsc() {
		key(KeyEvent.VK_ESCAPE);
	}
	
	public void keyF5() {
		key(KeyEvent.VK_F5);
	}

	public void clickLCreated() {
		move(CREAT_X, CREAT_Y);
		clickL();
	}
	
	public void clickLItem1() {
		move(ITEM1_X, ITEM1_Y);
		clickL();
	}

	public void clickLWork1() {
		move(WORK1_X, WORK1_Y);
		clickL();
	}

	public void clickRWork1() {
		move(WORK1_X, WORK1_Y);
		clickR();
	}

	public void clickLWork2() {
		move(WORK2_X, WORK2_Y);
		clickL();
	}
	
	public void clickRWork2() {
		move(WORK2_X, WORK2_Y);
		clickR();
	}

	private void key(int key) {
		r.keyPress(key);
		r.keyRelease(key);
		delay();
	}

	private void mouse(int key) {
		r.mousePress(key);
		r.mouseRelease(key);
		delay();
	}

	private void clickL() {
		mouse(InputEvent.BUTTON1_DOWN_MASK);
	}

	private void clickR() {
		mouse(InputEvent.BUTTON3_DOWN_MASK);
	}

	private void move(int x, int y) {
		Point cur = currentMousePoint();
		boolean isDoneX = false;
		boolean isDoneY = false;

		int cx = cur.x;
		int cy = cur.y;
		int dx = cx <= x ? 1 : -1;
		int dy = cy <= y ? 1 : -1;
		
		while (!(isDoneX && isDoneY)) {
			if (!isDoneX && cx + dx - x != 0) {
				cx += dx;
			} else {
				isDoneX = true;
			}
			
			if (!isDoneY && cy + dy - y != 0) {
				cy += dy;
			} else {
				isDoneY = true;
			}
			
			r.mouseMove(cx, cy);
			r.delay(3);
		}
	}

	private Point currentMousePoint() {
		PointerInfo mi = MouseInfo.getPointerInfo();
		Point mp = mi.getLocation();
		return mp;
	}

	private void delay() {
		r.delay(OP_DELAY_MS);
	}

}