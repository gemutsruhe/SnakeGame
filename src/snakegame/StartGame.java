package snakegame;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartGame {
	
	JFrame frame;
	JPanel panel;
	
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
		frame.remove(panel);
		panel = new InGameMenu(this, frame);
		frame.add(panel);
		frame.setVisible(true);
	}
	
}