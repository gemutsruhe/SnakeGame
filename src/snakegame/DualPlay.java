package snakegame;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class DualPlay extends GamePlay{

	DualPlay(StartGame startGame, JFrame frame) {
		super(startGame, frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void repaintPanel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void drawSnake() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void drawApple() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isSnakeEatApple(int[] snakeHead, int[] apple) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void moveApple(int[] apple) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isCollision() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void keyReact(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
