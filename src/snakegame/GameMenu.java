package snakegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameMenu {
	SnakeGameFrame frame;
	JPanel panel;
	GameMenu(SnakeGameFrame frame){
		this.frame = frame;
		this.panel = frame.getPanel();
		JButton play = new JButton("PLAY");
		JButton load = new JButton("LOAD");
		JButton rank = new JButton("RANK");
		JButton exit = new JButton("EXIT");
		
		panel.add(play);
		panel.add(load);
		panel.add(rank);
		panel.add(exit);
		frame.add(panel);
		
		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startGame();
			}
			
		});
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loadGame();
			}
			
		});
		rank.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showRank();
			}
			
		});
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				exitGame();
			}
			
		});
	}
	
	public void paint() {
		
	}
	
	
	private void startGame(){
		
	}
	
	private void loadGame() {
		
	}
	
	private void showRank() {
		
	}
	
	private void exitGame() {
		frame.dispose();
	}
	
	
	
	
}
