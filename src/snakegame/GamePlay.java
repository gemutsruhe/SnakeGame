package snakegame;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

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
	Graphics g;
	private static int DELAY = 50;
	char direction = 'U';
	GamePlay(StartGame startGame, JFrame frame, boolean save){
		this.startGame = startGame;
		this.frame = frame;
		if(save)
			try {
				loadGame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else init();
		
		frame.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(direction != '0') { // not in InGameMenu
					if(isSnakeEatApple()) {
						growSnake();
						moveApple();
					}
					
					for(int i = snake.size() - 1; i >= 1; i--) { // move snake trunk
						snake.get(i)[0] = snake.get(i-1)[0];
						snake.get(i)[1] = snake.get(i-1)[1];
					}
					
					switch(direction) { // move snake head
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
					
					if(isCollision()) {
						return ;
					} else {
						repaint();
					}
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
		apple[0] = (int) (Math.random() * (600 / size)) * size;
		apple[1] = (int) (Math.random() * (600 / size)) * size;
	}
	
	private void growSnake() {
		int [] tail = new int[2];
		snake.add(tail);
	}
	
	private boolean isCollision() {
		for(int i = 1; i < snake.size(); i++) { // collide to trunk
			if(snake.get(i)[0] == snakeHead[0] && snake.get(i)[1] == snakeHead[1]) {
				return true;
			}
		} // collide to border
		if(snakeHead[0] < 0 || snakeHead[0] >= 600 || snakeHead[1] < 0 || snakeHead[1] >= 600) {
			return true;
		}
		return false;
	}
	
	private void dieSnake() throws FileNotFoundException {
		File file = new File("Ranking.data");
		if(file.exists()) {
			FileInputStream fis = new FileInputStream(file);
		} else {
			
		}
		startGame.showMenu();
	}
	
	public void setDirection(char direction) {
		this.direction = direction;
	}

	public void init() { // init snake, direction, location of apple
		snakeHead[0] = 300;
		snakeHead[1] = 300;
		snake = new ArrayList<int[]>();
		snake.add(snakeHead);
		direction = 'U';
		moveApple();
	}
	
	public void saveGame(char direction) throws IOException {
		File file = new File("saveGame.data");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(((int)direction + "\n").getBytes()); // save snake head direction
		fos.write((apple[0] + " " + apple[1] + "\n").getBytes()); // save apple location
		for(int i = 0; i < snake.size(); i++) { // save snake location
			fos.write((snake.get(i)[0] + " " + snake.get(i)[1] + "\n").getBytes());
		}
		fos.close();
	}
	
	public void loadGame() throws IOException {
		FileReader fileReader = new FileReader("saveGame.data");
		BufferedReader reader = new BufferedReader(fileReader);

		direction = (char) Integer.parseInt(reader.readLine()); // first line : direction
		
		String[] location = reader.readLine().split(" "); // second line : apple location
		apple[0] = (Integer.parseInt(location[0])); 
		apple[1] = (Integer.parseInt(location[1]));
		
		snake = new ArrayList<int[]>();
		String read;
		while((read = reader.readLine()) != null) { // third to last line : snake head, snake trunk
			location = read.split(" ");
			int[] temp = new int[2];
			temp[0] = Integer.parseInt(location[0]);
			temp[1] = Integer.parseInt(location[1]);  
			snake.add(temp);
		}
		snakeHead = snake.get(0); // first is snake head
		
		fileReader.close();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
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
				startGame.showInGameMenu(direction);
				direction = '0';
			}
		}
	}
}