package snakegame;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePlay extends JPanel{
	StartGame startGame;
	JFrame frame;
	//Frame frame;
	//JPanel panel;
	static int size = 12;
	private ArrayList<int[]> snake;
	int[] snakeHead = {300,300};
	int[] apple = {0,0};
	Timer timer;
	private boolean running = true;
	Graphics g;
	private static int DELAY = 50;
	char direction = 'U';
	GamePlay(StartGame startGame, JFrame frame){
		this.startGame = startGame;
		this.frame = frame;
		snake = new ArrayList<int[]>();
		snake.add(snakeHead);
		moveApple();
		frame.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(isSnakeEatApple()) {
					growSnake();
					moveApple();
				}
				
				for(int i = snake.size() - 1; i >= 1; i--) {
					snake.get(i)[0] = snake.get(i-1)[0];
					snake.get(i)[1] = snake.get(i-1)[1];
				}
				
				switch(direction) {
				case 'L':
					snakeHead[0] -= size;
					break;
				case 'R':
					snakeHead[0] += size;
					break;
				case 'D':
					snakeHead[1] += size;
					break;
				case 'U':
					snakeHead[1] -= size;
					break;
				}
				
				if(isCollison()) {
					return ;
				} else {
					repaint();
				}
			}
		}).start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		this.g = g;
		drawSnake();
		drawApple();
	}

	private void drawSnake() {
		for(int i = 0; i < snake.size(); i++) {
			g.fillRect(snake.get(i)[0], snake.get(i)[1], size, size);
		}
	}
	
	private void drawApple() {
		g.setColor(Color.red);
		g.fillOval(apple[0], apple[1], size, size);
	}
	
	private boolean isSnakeEatApple() {
		if(snakeHead[0] == apple[0] && snakeHead[1] == apple[1]) {
			return true;
		} else {
			return false;
		}
	}
	
	private void moveApple() {
		
		apple[0] = (int) (1 + Math.random() * (600 / size)) * size;
		apple[1] = (int) (1 + Math.random() * (600 / size)) * size;
		System.out.println(apple[0] + " " + apple[1]);
	}
	
	private void growSnake() {
		int [] tail = new int[2];
		snake.add(tail);
	}
	
	private boolean isCollison() {
		for(int i = 1; i < snake.size(); i++) {
			if(snake.get(i)[0] == snakeHead[0] && snake.get(i)[1] == snakeHead[1]) {
				return true;
			}
		}
		if(snakeHead[0] < 0 || snakeHead[0] >= 600 || snakeHead[1] < 0 || snakeHead[1] >= 600) {
			return true;
		}
		return false;
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(e.getKeyCode());
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') direction = 'L';
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') direction = 'R';
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') direction = 'U';
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') direction = 'D';
				break;
			case KeyEvent.VK_ESCAPE:
				startGame.showInGameMenu();
				direction = '0';
			}
		}
	}
}