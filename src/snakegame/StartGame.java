package snakegame;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartGame {
	
	JFrame frame;
	JPanel panel;
	
	StartGame(){
		frame = new Frame();
		//showMenu();
		newGame();
		//showInGameMenu();
	}
	public void showMenu() {
		if(panel != null) frame.remove(panel);
		panel = new GameMenu(this, frame);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	public void newGame() {
		if(panel != null) frame.remove(panel);
		panel = new GamePlay(this, frame);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	public void loadGame() {
		frame.remove(panel);
		panel = new GamePlay(this, frame);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	public void showInGameMenu() {
		//frame.remove(panel);
		new InGameMenu(this, frame);
		//panel = new InGameMenu(this, frame);
		//frame.add(panel);
		//frame.setVisible(true);
	}
	
}