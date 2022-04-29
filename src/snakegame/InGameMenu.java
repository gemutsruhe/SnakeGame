package snakegame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InGameMenu {
	private StartGame startGame;
	private JFrame frame;
	
	private JLabel title;
	private JButton resume;
	private JButton restart;
	private JButton save;
	private JButton exit;
	
	InGameMenu(StartGame startGame, JFrame frame){
		this.startGame = startGame;
		this.frame = frame;
		
		Frame inGameMenu = new Frame();
		inGameMenu.setSize(400,400);
		inGameMenu.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

		title = new JLabel("In Game Menu");
		resume = new JButton("Resume Game");
		restart = new JButton("Restart Game");
		save = new JButton("Save Game");
		exit = new JButton("Exit");
		
		title.setFont(new Font("Serif",Font.PLAIN,40));
		
		title.setAlignmentX(panel.CENTER_ALIGNMENT);
		resume.setAlignmentX(panel.CENTER_ALIGNMENT);
		restart.setAlignmentX(panel.CENTER_ALIGNMENT);
		save.setAlignmentX(panel.CENTER_ALIGNMENT);
		exit.setAlignmentX(panel.CENTER_ALIGNMENT);
		
		panel.add(Box.createVerticalGlue());
		panel.add(title);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(resume);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(restart);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(save);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(exit);
		panel.add(Box.createVerticalGlue());
		
		inGameMenu.setAlwaysOnTop(true);
		inGameMenu.add(panel);
		inGameMenu.setVisible(true);
		
		resume.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		frame.addKeyListener(new MyKeyAdapter());
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
	}
}