package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DualPlay extends GamePlay{

	private ArrayList<ArrayList<int[]>> snake;
	int[][] snakeHead = {{0, 0},{1200-size, 1200-size}};
	char[] direction = {'D','U'};
	int[][] apple = {{0,0},{0,0}};
	
	int victory = 0;
	
	DualPlay(StartGame startGame, JFrame frame) {
		super(startGame, frame);
		frame.setSize(new Dimension(1200, 600));
		frame.setLocationRelativeTo(null);
		init();
		frame.setVisible(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void repaintPanel() {
		// TODO Auto-generated method stub
		if(isSnakeEatApple(snakeHead[0], apple[0]) || isSnakeEatApple(snakeHead[0], apple[1]))
			growSnake(snake.get(0));
		if(isSnakeEatApple(snakeHead[1], apple[0]) || isSnakeEatApple(snakeHead[1], apple[1]))
			growSnake(snake.get(1));
		if(isSnakeEatApple(snakeHead[0], apple[0]) || isSnakeEatApple(snakeHead[1], apple[0])) {
			moveApple(apple[0]);
			while(apple[0][0] == apple[1][0] && apple[0][1] == apple[1][1]) moveApple(apple[0]);
		}
		if(isSnakeEatApple(snakeHead[0], apple[1]) || isSnakeEatApple(snakeHead[1], apple[1])) {
			moveApple(apple[1]);
			while(apple[0][0] == apple[1][0] && apple[0][1] == apple[1][1]) moveApple(apple[1]);
		}
		
		ArrayList<int[]> p_snake = snake.get(0);
		for(int i = p_snake.size() - 1; i >= 1; i--) { // move snake1 trunk
			p_snake.get(i)[0] = p_snake.get(i-1)[0];
			p_snake.get(i)[1] = p_snake.get(i-1)[1];
		}
		p_snake = snake.get(1);
		for(int i = p_snake.size() - 1; i >= 1; i--) { // move snake2 trunk
			p_snake.get(i)[0] = p_snake.get(i-1)[0];
			p_snake.get(i)[1] = p_snake.get(i-1)[1];
		}
		
		moveHead(snakeHead[0], direction[0]);
		moveHead(snakeHead[1], direction[1]);

		if(isCollision()) { // die snake
			if(victory != 3) {
				JOptionPane.showMessageDialog(null, "player" + victory + " won the game", "game result", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "draw a game", "Game Result", JOptionPane.INFORMATION_MESSAGE);
			}
			
			frame.removeKeyListener(new MyKeyAdapter());
			frame.setSize(new Dimension(600, 600));
			frame.setLocationRelativeTo(null);
			startGame.showMenu(); // back to menu
			timer.stop(); // stop game
			
		} else {
			repaint();
		}
	}
	
	private void moveHead(int []snakeHead, char direction) {
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
	}

	@Override
	protected void drawSnake() {
		// TODO Auto-generated method stub
		for(int i = 0; i < snake.get(0).size(); i++) {
			g.fillRect(snake.get(0).get(i)[0], snake.get(0).get(i)[1], size, size);
		}
		g.setColor(Color.orange);
		for(int i = 0; i < snake.get(1).size(); i++) {
			g.fillRect(snake.get(1).get(i)[0], snake.get(1).get(i)[1], size, size);
		}
		
	}

	@Override
	protected void drawApple() {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillOval(apple[0][0], apple[0][1], size, size);
		g.fillOval(apple[1][0], apple[1][1], size, size);
	}

	@Override
	protected boolean isSnakeEatApple(int[] snakeHead, int[] apple) {
		// TODO Auto-generated method stub
		if(snakeHead[0] == apple[0] && snakeHead[1] == apple[1]) return true;
		return false;
	}
	
	@Override
	protected void moveApple(int[] apple) {
		// TODO Auto-generated method stub
		apple[0] = (int) (Math.random() * (1200 / size)) * size;
		apple[1] = (int) (Math.random() * (600 / size)) * size;
	}

	@Override
	protected boolean isCollision() {
		// TODO Auto-generated method stub
		for(int i = 1; i < snake.get(0).size(); i++) {
			if((snake.get(0).get(i)[0] == snakeHead[0][0] && snake.get(0).get(i)[1] == snakeHead[0][1])) { // snake1 collide to snake1 trunk
				victory = 1;
			}
			if((snake.get(0).get(i)[0] == snakeHead[1][0] && snake.get(0).get(i)[1] == snakeHead[1][1])) { // snake2 collide to snake1 trunk
				victory = 1;
			}
		}
		for(int i = 1; i < snake.get(1).size(); i++) {
			if((snake.get(1).get(i)[0] == snakeHead[0][0] && snake.get(1).get(i)[1] == snakeHead[0][1])) { // snake1 collide to snake1 trunk
				if(victory == 1) victory = 3;
				else victory = 2;
				
			}
			if((snake.get(1).get(i)[0] == snakeHead[1][0] && snake.get(1).get(i)[1] == snakeHead[1][1])) { // snake2 collide to snake1 trunk
				if(victory == 1) victory = 3;
				else victory = 2;
			}
		}
		
		if(snakeHead[0][0] == snakeHead[1][0] && snakeHead[0][1] == snakeHead[1][1]) {
			victory = 3;
		}
		
		if(snakeHead[0][0] < 0 || snakeHead[0][0] >= 1200 || snakeHead[0][1] < 0 || snakeHead[0][1] >= 600) {
			if(victory == 0) victory = 1;
		}
		if(snakeHead[1][0] < 0 || snakeHead[1][0] >= 1200 || snakeHead[1][1] < 0 || snakeHead[1][1] >= 600) {
			if(victory == 0) victory = 2;
			else victory = 3;
		}
		if(victory != 0) return true;
		else return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		snake = new ArrayList<ArrayList<int[]>>();
		
		snakeHead[0][0] = 0;
		snakeHead[0][1] = 0;
		snake.add(new ArrayList<int[]>());
		snake.get(0).add(snakeHead[0]);
		direction[0] = 'D';
		
		snakeHead[1][0] = 1200-size;
		snakeHead[1][1] = 600-size;
		snake.add(new ArrayList<int[]>());
		snake.get(1).add(snakeHead[1]);
		direction[1] = 'U';
		moveApple(apple[0]);
		moveApple(apple[1]);
		
		while(apple[0][0] == apple[1][0] && apple[0][1] == apple[1][1]) moveApple(apple[1]);
	}

	@Override
	protected void keyReact(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:
			if(direction[0] != 'R') direction[0] = 'L';
			break;
		case KeyEvent.VK_D:
			if(direction[0] != 'L') direction[0] = 'R';
			break;
		case KeyEvent.VK_W:
			if(direction[0] != 'D') direction[0] = 'U';
			break;
		case KeyEvent.VK_S:
			if(direction[0] != 'U') direction[0] = 'D';
			break;
		case KeyEvent.VK_LEFT:
			if(direction[1] != 'R') direction[1] = 'L';
			break;
		case KeyEvent.VK_RIGHT:
			if(direction[1] != 'L') direction[1] = 'R';
			break;
		case KeyEvent.VK_UP:
			if(direction[1] != 'D') direction[1] = 'U';
			break;
		case KeyEvent.VK_DOWN:
			if(direction[1] != 'U') direction[1] = 'D';
			break;
		case KeyEvent.VK_ESCAPE:
			timer.stop();
			frame.setSize(new Dimension(600, 600));
			frame.setLocationRelativeTo(null);
			startGame.showInGameMenu();
		}
	}
}
