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

public class SoloPlay extends GamePlay{
	Record record;
	
	private ArrayList<int[]> snake;
	int[] snakeHead = {300, 300};
	char direction;
	int[] apple = {0,0};
	
	boolean ranked;
	String user_name;
	
	SoloPlay(StartGame startGame, JFrame frame, boolean save){
		super(startGame, frame);
		ranked = false;
		user_name = "";
		if(save) // selected load game on main menu
			try {
				loadGame(); // load game
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else{
			File file = new File("saveGame.data"); 
			if(file.exists()) { // discard save data
				file.delete();
			}
			init();
		}
	}
	
	private boolean isRanked() throws NumberFormatException, IOException {

		File file = new File("Ranking.data");
		if(file.exists() == false) return true;
		
		FileReader fileReader = fileReader = new FileReader("Ranking.data");
		
		BufferedReader reader = new BufferedReader(fileReader);
		String read;
		int i;
		for(i = 0; (i < 15) && (read = reader.readLine()) != null; i++) {
			String []info = read.split(" ");
			if(Integer.parseInt(info[1]) < snake.size()) { // ranked in 15
				return true;
			}
		}
		if(i < 15) return true; // saved rank number lower than 15
		return false;
	}
	
	private void saveRank() throws IOException {
		File file = new File("Ranking.data");
		if(file.exists() == false) { // first game
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
			for(i = 0; (i < 15) && (read = reader.readLine()) != null; i++) { // get ranked user name and score
				String []info = read.split(" ");
				if(inserted == false && Integer.parseInt(info[1]) < snake.size()) {
					name.add(user_name);
					score.add(Integer.toString(snake.size()));
					inserted = true;
					i++;
					if(i < 15) {
						name.add(info[0]);
						score.add(info[1]);
					}
					inserted = true;
				} else {
					name.add(info[0]);
					score.add(info[1]);
				}
			}
			if(name.size() < 15 && inserted == false) {
				name.add(user_name);
				score.add(Integer.toString(snake.size()));
			}
			FileOutputStream fos = new FileOutputStream(file);
			
			for(i = 0; i < name.size(); i++) { // save ranking
				fos.write((name.get(i) + " " + score.get(i)).getBytes());
				if(i < name.size() - 1) fos.write(("\n").getBytes());
			}
			
			fos.close();
		}
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
		moveApple(apple);
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
	
	public String getHead() {
		return snakeHead[0] + " " + snakeHead[1];
	}

	@Override
	protected void repaintPanel() {
		// TODO Auto-generated method stub
		if(ranked) { // user play get ranking
			if(record == null) record = new Record(gamePlay); // show frame can type user name
			else if(user_name.compareTo("") != 0) {
				try { // save rank and back to main menu
					saveRank();
					JOptionPane.showMessageDialog(null, "Score : " + snake.size(), "Game Result", JOptionPane.INFORMATION_MESSAGE);
					startGame.showMenu();
					timer.stop();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(direction != '0') { // not in InGameMenu
			if(isSnakeEatApple(snakeHead, apple)) {
				growSnake(snake);
				moveApple(apple);
			}
			
			for(int i = snake.size() - 1; i >= 1; i--) { // move snake trunk
				snake.get(i)[0] = snake.get(i-1)[0];
				snake.get(i)[1] = snake.get(i-1)[1];
			}
			
			switch(direction) { // move snake head
			case 'L':
				snakeHead[0] -= size; // move to left
				break;
			case 'R':
				snakeHead[0] += size; // move to right
				break;
			case 'D':
				snakeHead[1] += size; // move to down
				break;
			case 'U':
				snakeHead[1] -= size; // move to up
				break;
			}
			
			if(isCollision() && ranked == false) { // die snake, not ranked
				
				try {
					ranked = isRanked(); // determine ranking
					
					if(ranked == false) { // 
						JOptionPane.showMessageDialog(null, "score : " + snake.size(), "Game Result", JOptionPane.INFORMATION_MESSAGE);
						frame.removeKeyListener(new MyKeyAdapter());
						startGame.showMenu(); // back to menu
						timer.stop(); // stop game
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

	@Override
	protected void drawSnake() {
		// TODO Auto-generated method stub
		for(int i = 0; i < snake.size(); i++) {
			g.fillRect(snake.get(i)[0], snake.get(i)[1], size, size);
		}
	}

	@Override
	protected void drawApple() {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillOval(apple[0], apple[1], size, size);
	}

	@Override
	protected boolean isSnakeEatApple(int[] snakeHead, int[] apple) {
		// TODO Auto-generated method stub
		if(snakeHead[0] == apple[0] && snakeHead[1] == apple[1]) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void moveApple(int []apple) {
		// TODO Auto-generated method stub
		apple[0] = (int) (Math.random() * (600 / size)) * size;
		apple[1] = (int) (Math.random() * (600 / size)) * size;
	}

	@Override
	protected boolean isCollision() {
		// TODO Auto-generated method stub
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

	@Override
	protected void keyReact(KeyEvent e) {
		// TODO Auto-generated method stub
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
