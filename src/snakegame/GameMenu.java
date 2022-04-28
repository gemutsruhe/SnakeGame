package snakegame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameMenu extends JPanel{
	StartGame startGame;
	JFrame frame;
	
	private JLabel title;
	private JButton start;
	private JButton load;
	private JButton rank;
	private JButton exit;
	private JButton[] buttonList;
	GameMenu(StartGame startGame, JFrame frame){
		this.startGame = startGame;
		this.frame = frame;
		frame.addKeyListener(new MyKeyAdapter());
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		title= new JLabel("Snake Game");
		start = new JButton("Start");
		load = new JButton("Load");
		rank = new JButton("Rank");
		exit = new JButton("Exit");
		
		title.setFont(new Font("Serif",Font.PLAIN,40));
		
		title.setAlignmentX(CENTER_ALIGNMENT);
		start.setAlignmentX(CENTER_ALIGNMENT);
		load.setAlignmentX(CENTER_ALIGNMENT);
		rank.setAlignmentX(CENTER_ALIGNMENT);
		exit.setAlignmentX(CENTER_ALIGNMENT);
		
		add(Box.createVerticalGlue());
		add(title);
		add(Box.createRigidArea(new Dimension(0,20)));
		add(start);
		add(Box.createRigidArea(new Dimension(0,20)));
		add(load);
		add(Box.createRigidArea(new Dimension(0,20)));
		add(rank);
		add(Box.createRigidArea(new Dimension(0,20)));
		add(exit);
		add(Box.createVerticalGlue());
		
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		rank.addActionListener(new ActionListener() {
			
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
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
	}
}