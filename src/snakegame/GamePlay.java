package snakegame;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public abstract class GamePlay extends JPanel{
	StartGame startGame;
	JFrame frame;
	GamePlay gamePlay;
	
	private static int DELAY = 50;
	static int size = 12;
	Timer timer;
	Graphics g;
	MyKeyAdapter keyListener;
	
	GamePlay(StartGame startGame, JFrame frame){
		this.startGame = startGame;
		this.frame = frame;
		this.gamePlay = this;
		keyListener = new MyKeyAdapter();
		frame.addKeyListener(keyListener);
		startGame();
	}
	
	public void startGame() {
		timer = new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaintPanel();
			}
		});
		timer.start();
	}
	
	abstract protected void repaintPanel();
	
	public void paint(Graphics g) {
		super.paint(g);
		this.g = g;
		drawSnake();
		drawApple();
	}

	protected abstract void drawSnake();

	protected abstract void drawApple();
	
	protected abstract boolean isSnakeEatApple(int[] snakeHead, int[] apple);
	
	protected abstract void moveApple(int []apple); // move apple random location
	
	protected abstract boolean isCollision();
	
	protected void growSnake(ArrayList<int[]> snake) {
		int [] tail = new int[2];
		snake.add(tail);
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public abstract void init();
	
	public MyKeyAdapter getKeyListener() {
		return keyListener;
	}
	
	protected abstract void keyReact(KeyEvent e);
	
	protected class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			keyReact(e);
		}
	}
}