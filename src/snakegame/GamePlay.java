package snakegame;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class GamePlay extends JPanel{
	SnakeGameFrame frame;
	JPanel panel;
	private ArrayList<int[]> snake;
	int[] snakeHead = {290,290};
	Timer timer;
	private boolean running = true;
	Graphics g;
	private static int DELAY = 100;
	char direction = 'R';
	GamePlay(SnakeGameFrame frame){
		//paint(this.getGraphics());
		this.frame = frame;
		snake = new ArrayList<int[]>();
		snake.add(snakeHead);
		frame.addKeyListener(new MyKeyAdapter());
		//this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	public void startGame() {
		new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(direction) {
				case 'L':
					snakeHead[0] -= 20;
					break;
				case 'R':
					snakeHead[0] += 20;
					break;
				case 'D':
					snakeHead[1] += 20;
					break;
				case 'U':
					snakeHead[1] -= 20;
					break;
				}
				repaint();
			}
		}).start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		this.g = g;
		drawSnake();
	}

	private void drawSnake() {
		for(int i = 0; i < snake.size(); i++) {
			g.fillRect(snake.get(i)[0], snake.get(i)[1], 20, 20);
		}
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(e);
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				direction = 'L';
				break;
			case KeyEvent.VK_RIGHT:
				direction = 'R';
				break;
			case KeyEvent.VK_UP:
				direction = 'U';
				break;
			case KeyEvent.VK_DOWN:
				direction = 'D';
				break;
			}
		}
	}
}
