package snakegame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class GameMenu extends JPanel{
	StartGame startGame;
	JFrame frame;

	private MainMenu mainmenu;
	private Ranking rankmenu;
	
	GameMenu(StartGame startGame, JFrame frame){
		this.startGame = startGame;
		this.frame = frame;
		
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
	
	public Ranking getRankingMenu() {
		return rankmenu;
	}
}