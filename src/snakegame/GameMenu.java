package snakegame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameMenu extends JPanel{
	StartGame startGame;
	JFrame frame;
	
	private MainMenu mainmenu;
	private Ranking rankmenu;
	
	GameMenu(StartGame startGame, JFrame frame){
		this.startGame = startGame;
		this.frame = frame;
		frame.addKeyListener(new MyKeyAdapter());
		setLayout(new CardLayout());
		mainmenu = new MainMenu(this);
		add(mainmenu,"main");
		
		rankmenu=new Ranking(this);
		add(rankmenu,"rank");
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public StartGame getStartGame() {
		return startGame;
	}
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
	}
}