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
		gamePlay = new AutoPlay(this, frame);
		inGameMenu = new InGameMenu(this, frame);
		frame.add(gameMenu);
		showMenu();
	}
	
	public void showMenu() {
		inGameMenu.setVisible(false);
		gamePlay.setVisible(false);
		gameMenu.setVisible(true);
		frame.setVisible(true);
	}
	
	public void newSinglePlay() {
		gameMenu.setVisible(false);
		inGameMenu.setVisible(false);
		gamePlay = new SoloPlay(this, frame, false);
		frame.add(gamePlay);
		frame.requestFocus();
		frame.setVisible(true);
	}
	
	public void newDualPlay() {
		gameMenu.setVisible(false);
		inGameMenu.setVisible(false);
		gamePlay = new DualPlay(this, frame);
		frame.add(gamePlay);
		frame.requestFocus();
		frame.setVisible(true);
	}
	
	public void newAutoPlay() {
		gameMenu.setVisible(false);
		inGameMenu.setVisible(false);
		gamePlay = new AutoPlay(this, frame);
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
		if(gamePlay.getClass().getSimpleName().equals("SoloPlay")) {
			gamePlay = new SoloPlay(this, frame, false);
		} else if (gamePlay.getClass().getSimpleName().equals("DualPlay")) {
			gamePlay = new DualPlay(this, frame);
		} else if (gamePlay.getClass().getSimpleName().equals("AutoPlay")) {
			gamePlay = new AutoPlay(this, frame);
		}
		frame.add(gamePlay);
		frame.requestFocus();
		frame.setVisible(true);
	}
	
	public void loadSinglePlay() {
		gameMenu.setVisible(false);
		inGameMenu.setVisible(false);
		gamePlay = new SoloPlay(this, frame, true);
		frame.add(gamePlay);
		frame.requestFocus();
		frame.setVisible(true);
	}
	
	public void showInGameMenu() {
		gameMenu.setVisible(false);
		gamePlay.setVisible(false);
		frame.add(inGameMenu);
		inGameMenu.setVisible(true);
		inGameMenu.setSaveVisible(gamePlay.getClass().getSimpleName());
		frame.setVisible(true);
	}
	
	
	public void saveGame() {
		try {
			((SoloPlay) gamePlay).saveGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exitInGameMenu() {
		inGameMenu.setVisible(false);
		gamePlay.setVisible(false);
		gameMenu.setVisible(true);
		frame.setVisible(true);
	}
	
	public GamePlay getGamePlay() {
		return gamePlay;
	}
}