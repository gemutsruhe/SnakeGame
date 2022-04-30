package snakegame;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartGame {
	
	JFrame frame;
	JPanel panel;
	InGameMenu inGameMenu;
	
	StartGame(){
		frame = new Frame();
		showMenu();
	}
	public void showMenu() {
		if(panel != null) frame.remove(panel);
		panel = new GameMenu(this, frame);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	public void newGame() {
		frame.remove(panel);
		inGameMenu = null;
		panel = new GamePlay(this, frame, false);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	public void loadGame() {
		frame.remove(panel);
		panel = new GamePlay(this, frame, true);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	public void showInGameMenu(char direction) {
		inGameMenu = new InGameMenu(this, frame, (GamePlay)panel, direction);
	}
	
}