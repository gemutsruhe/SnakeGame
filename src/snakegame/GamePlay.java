package snakegame;

import javax.swing.JPanel;

public class GamePlay {
	SnakeGameFrame frame;
	JPanel panel;
	GamePlay(SnakeGameFrame frame){
		this.frame = frame;
		this.panel = frame.getPanel();
	}
	
	public void paint() {
		
	}
}
