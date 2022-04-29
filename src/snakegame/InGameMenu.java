package snakegame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class InGameMenu extends JPanel {
	StartGame startGame;
	JFrame frame;
	InGameMenu(StartGame startGame, JFrame frame){
		this.startGame = startGame;
		this.frame = frame;
		frame.addKeyListener(new MyKeyAdapter());
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
	}
}