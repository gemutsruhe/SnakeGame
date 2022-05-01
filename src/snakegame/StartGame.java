package snakegame;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartGame {
	
	JFrame frame;
	JPanel panel;
	GameMenu gameMenu;
	GamePlay gamePlay;
	InGameMenu inGameMenu;
	
	StartGame(){
		frame = new Frame();
		gameMenu = new GameMenu(this, frame);
		inGameMenu = new InGameMenu(this, frame, gamePlay);
		frame.add(gameMenu);
		showMenu();
	}
	
	public void showMenu() {
		inGameMenu.setVisible(false);
		if(gamePlay != null) gamePlay.setVisible(false);
		gameMenu.setVisible(true);
		frame.setVisible(true);
	}
	
	public void newGame() {
		gameMenu.setVisible(false);
		inGameMenu.setVisible(false);
		gamePlay = new GamePlay(this, frame, false);
		frame.add(gamePlay);
		frame.requestFocus();
		frame.setVisible(true);
	}
	
	public void resumeGame() {
		gameMenu.setVisible(false);
		inGameMenu.setVisible(false);
		gamePlay.setVisible(true);
		frame.addKeyListener(gamePlay.getKeyListener());
		frame.requestFocus();
		gamePlay.getTimer().restart();
		frame.setVisible(true);
	}
	
	public void restartGame() {
		gameMenu.setVisible(false);
		inGameMenu.setVisible(false);
		gamePlay = new GamePlay(this, frame, false);
		frame.add(gamePlay);
		frame.requestFocus();
		frame.setVisible(true);
	}
	
	public void loadGame() {
		gameMenu.setVisible(false);
		inGameMenu.setVisible(false);
		gamePlay = null;
		gamePlay = new GamePlay(this, frame, true);
		frame.add(gamePlay);
		frame.requestFocus();
		frame.setVisible(true);
	}
	
	public void showInGameMenu() {
		gameMenu.setVisible(false);
		gamePlay.setVisible(false);
		frame.add(inGameMenu);
		inGameMenu.setVisible(true);
		frame.setVisible(true);
	}
	
	
	public void saveGame() {
		try {
			gamePlay.saveGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exitInGameMenu() {
		inGameMenu.setVisible(false);
		gameMenu.setVisible(true);
	}
}