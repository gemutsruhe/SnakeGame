package snakegame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InGameMenu extends JPanel{
	private JLabel title;
	private JButton resume;
	private JButton restart;
	private JButton save;
	private JButton exit;
	
	InGameMenu(StartGame startGame, JFrame frame, GamePlay gamePlayPanel){

		this.setSize(300, 300);
		this.setBounds(150, 150, 450, 450);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		title = new JLabel("In Game Menu");
		resume = new JButton("Resume Game");
		restart = new JButton("Restart Game");
		save = new JButton("Save Game");
		exit = new JButton("Exit");
		
		title.setFont(new Font("Serif",Font.PLAIN,40));
		
		title.setAlignmentX(this.CENTER_ALIGNMENT);
		resume.setAlignmentX(this.CENTER_ALIGNMENT);
		restart.setAlignmentX(this.CENTER_ALIGNMENT);
		save.setAlignmentX(this.CENTER_ALIGNMENT);
		exit.setAlignmentX(this.CENTER_ALIGNMENT);
		
		this.add(Box.createVerticalGlue());
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.add(resume);
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.add(restart);
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.add(save);
		this.add(Box.createRigidArea(new Dimension(0,20)));
		this.add(exit);
		this.add(Box.createVerticalGlue());
		
		resume.addActionListener(new ActionListener() { // resume game

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startGame.resumeGame();
			}
		});
		restart.addActionListener(new ActionListener() { // restart game

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startGame.restartGame();
				return ;
			}
		});
		save.addActionListener(new ActionListener() { // save game and back to main menu

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startGame.saveGame(); // save game and back to main menu
				startGame.showMenu();
			}
		});
		exit.addActionListener(new ActionListener() { // back to main menu

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startGame.exitInGameMenu();
				return ;
			}
		});
	}
	
}