package snakegame;

import javax.swing.JPanel;

public class GameMenu {
	SnakeGameFrame frame;
	JPanel panel;
	GameMenu(SnakeGameFrame frame){
		this.frame = frame;
		this.panel = frame.getPanel();
	}
	
	public void paint() {
		
	}
}
