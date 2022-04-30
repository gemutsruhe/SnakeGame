package snakegame;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel{
	private GameMenu gamemenu;
	
	private JLabel title;
	private JButton start;
	private JButton load;
	private JButton rank;
	private JButton exit;
	
	MainMenu(GameMenu gamemenu){
		this.gamemenu = gamemenu;
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
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
				JFrame frame = gamemenu.getFrame();
				StartGame sg = gamemenu.getStartGame();
				frame.remove(gamemenu);
				frame.add(new GamePlay(sg,frame));
				frame.setVisible(true);
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
				CardLayout card = (CardLayout)gamemenu.getLayout();
				card.show(gamemenu,"rank");
			}
			
		});
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = gamemenu.getFrame();
				frame.dispatchEvent(new WindowEvent(frame,WindowEvent.WINDOW_CLOSING));
			}
			
		});
	}
}
