package snakegame;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakeGameFrame extends JFrame {
	private JPanel panel;
	
	GameMenu menu;
	GamePlay gamePlay;
	InGameMenu inGameMenu;
	
	SnakeGameFrame() {
		this.setTitle("SnakeGame");
		this.setResizable(false);
		this.setSize(new Dimension(600, 600));
		this.setLocationRelativeTo(null);
		panel = new JPanel();
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		showMenu();
	}
	
	public void showMenu() {
		panel.removeAll();
		menu = new GameMenu(this);
		menu.paint();
	}
	
	public void newGame() {
		panel.removeAll();
		gamePlay = new GamePlay(this);
		gamePlay.paint();
	}
	
	public void loadGame() {
		panel.removeAll();
		gamePlay = new GamePlay(this);
		gamePlay.paint();
	}
	
	public void showInGameMenu() {
		panel.removeAll();
		inGameMenu = new InGameMenu(this);
		inGameMenu.paint();
	}
	
	public JPanel getPanel() {
		return panel;
	}
}
