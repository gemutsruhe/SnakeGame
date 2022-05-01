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

public class GamePlay extends JPanel{
	StartGame startGame;
	JFrame frame;
	GamePlay gamePlay;
	Record record;
	
	private static int DELAY = 50;
	static int size = 12;
	private ArrayList<int[]> snake;
	int[] snakeHead = {300, 300};
	char direction;
	int[] apple = {0,0};
	Timer timer;
	Graphics g;
	MyKeyAdapter keyListener;
	
	boolean ranked;
	String user_name;
	
	GamePlay(StartGame startGame, JFrame frame, boolean save){
		this.startGame = startGame;
		this.frame = frame;
		this.gamePlay = this;
		ranked = false;
		user_name = "";
		if(save)
			try {
				loadGame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else{
			File file = new File("saveGame.data");
			if(file.exists()) {
				file.delete();
			}
			init();
		}
		keyListener = new MyKeyAdapter();
		frame.addKeyListener(keyListener);
		startGame();
	}
	
	public void startGame() {
		timer = new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ranked) { // user play get ranking
					if(record == null) record = new Record(gamePlay); // show frame can type user name
					else if(user_name.compareTo("") != 0) {
						try { // save rank and back to main menu
							saveRank();
							startGame.showMenu();
							timer.stop();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else if(direction != '0') { // not in InGameMenu
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
					
					if(isCollision() && ranked == false) {
						
						try {
							ranked = isRanked();
							
							if(ranked == false) {
								frame.removeKeyListener(new MyKeyAdapter());
								startGame.showMenu();
								timer.stop();
							}
						} catch (NumberFormatException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						repaint();
					}
				}
			}
		});
		timer.start();
		
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
	
	private boolean isRanked() throws NumberFormatException, IOException {

		File file = new File("Ranking.data");
		if(file.exists() == false) return true;
		
		FileReader fileReader = fileReader = new FileReader("Ranking.data");
		
		BufferedReader reader = new BufferedReader(fileReader);
		String read;
		int i;
		for(i = 0; (i < 5) && (read = reader.readLine()) != null; i++) {
			String []info = read.split(" ");
			if(Integer.parseInt(info[1]) < snake.size()) {
				return true;
			}
		}
		if(i < 5) return true;
		return false;
	}
	
	private void saveRank() throws IOException {
		File file = new File("Ranking.data");
		if(file.exists() == false) {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write((user_name + " " + snake.size()).getBytes());
			fos.close();
		} else {
			FileReader fileReader = new FileReader("Ranking.data");
			BufferedReader reader = new BufferedReader(fileReader);
			String read;
			
			ArrayList<String> name = new ArrayList<>();
			ArrayList<String> score = new ArrayList<>();
			int i;
			boolean inserted = false;
			for(i = 0; (i < 5) && (read = reader.readLine()) != null; i++) {
				String []info = read.split(" ");
				if(inserted == false && Integer.parseInt(info[1]) < snake.size()) {
					name.add(user_name);
					score.add(Integer.toString(snake.size()));
					inserted = true;
					i++;
					if(i < 5) {
						name.add(info[0]);
						score.add(info[1]);
					}
					inserted = true;
				} else {
					name.add(info[0]);
					score.add(info[1]);
				}
			}
			if(name.size() < 5 && inserted == false) {
				name.add(user_name);
				score.add(Integer.toString(snake.size()));
			}
			FileOutputStream fos = new FileOutputStream(file);
			
			for(i = 0; i < name.size(); i++) {
				fos.write((name.get(i) + " " + score.get(i)).getBytes());
				if(i < name.size() - 1) fos.write(("\n").getBytes());
			}
			
			fos.close();
		}
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public char getDirection() {
		return direction;
	}
	
	public void init() { // init snake, direction, location of apple
		snakeHead[0] = 300;
		snakeHead[1] = 300;
		snake = new ArrayList<int[]>();
		snake.add(snakeHead);
		direction = 'U';
		moveApple();
	}
	
	public void saveGame() throws IOException {
		File file = new File("saveGame.data");
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(((char)direction + "\n").getBytes()); // save snake head direction
		fos.write((apple[0] + " " + apple[1] + "\n").getBytes()); // save apple location
		for(int i = 0; i < snake.size(); i++) { // save snake location
			fos.write((snake.get(i)[0] + " " + snake.get(i)[1]).getBytes());
			if(i < snake.size() - 1) fos.write(("\n").getBytes());
		}
		fos.close();
	}
	
	public void loadGame() throws IOException {
		FileReader fileReader = new FileReader("saveGame.data");
		BufferedReader reader = new BufferedReader(fileReader);

		direction = reader.readLine().toCharArray()[0]; // first line : direction
		
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
		
		File file = new File("saveGame.data");
		file.delete();
	}
	
	public void setUserName(String name) {
		user_name = name;
	}
	
	public MyKeyAdapter getKeyListener() {
		return keyListener;
	}
	
	public String getHead() {
		return snakeHead[0] + " " + snakeHead[1];
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
				timer.stop();
				startGame.showInGameMenu();
			}
		}
	}
}