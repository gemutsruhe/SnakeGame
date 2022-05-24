package snakegame;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public class AutoPlay extends GamePlay{
	
	private ArrayList<int[]> snake;
	int[] snakeHead = {300, 300};
	char direction;
	int[] apple = {0,0};
	
	AutoPlay(StartGame startGame, JFrame frame) {
		super(startGame, frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void repaintPanel() {
		// TODO Auto-generated method stub
		if(direction != '0') { // not in InGameMenu
			if(isSnakeEatApple()) {
				growSnake(snake);
				moveApple();
			}
			
			AutoPlayAlgorithm();
			
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
			
			if(isCollision()) { // die snake
				
				frame.removeKeyListener(new MyKeyAdapter());
				startGame.showMenu(); // back to menu
				timer.stop(); // stop game
				
			} else {
				repaint();
			}
		}
	}
	
	private void AutoPlayAlgorithm() {
		
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
	protected void moveApple(int[] apple) {
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
	public void init() {
		// TODO Auto-generated method stub
		snakeHead[0] = 300;
		snakeHead[1] = 300;
		snake = new ArrayList<int[]>();
		snake.add(snakeHead);
		direction = 'U';
		moveApple();
	}

	@Override
	protected void keyReact(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			timer.stop();
			startGame.showInGameMenu();
		}
	}
	
	

}
