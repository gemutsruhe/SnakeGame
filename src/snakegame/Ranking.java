package snakegame;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Ranking extends JPanel{
	private GameMenu gamemenu;
	
	private JLabel ranktitle;
	private JPanel rankPanel;
	private JButton back;
	
	private JLabel name;
	private JLabel score;
	private int maxScore;
	Ranking(GameMenu gamemenu){
		
		this.gamemenu=gamemenu;
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		ranktitle=new JLabel("Ranking");
		ranktitle.setFont(new Font("Serif",Font.PLAIN,40));
		ranktitle.setAlignmentX(CENTER_ALIGNMENT);
		add(ranktitle);
		
		rankPanel=new JPanel();
		rankPanel.setAlignmentX(CENTER_ALIGNMENT);
		rankPanel.setLayout(new GridLayout(2,3));
		Dimension d = gamemenu.getFrame().getBounds().getSize();
		d.width/=2;
		d.height/=4;
		rankPanel.setMaximumSize(d);
		
		name=new JLabel("player_name");
		score = new JLabel("0");
		
		rankPanel.add(new JLabel("Rank"));
		rankPanel.add(new JLabel("Name"));
		rankPanel.add(new JLabel("Score"));
		rankPanel.add(new JLabel("Top"));
		rankPanel.add(name);
		rankPanel.add(score);
		add(rankPanel);		
		
		back=new JButton("Back");
		back.setAlignmentX(CENTER_ALIGNMENT);
		
		
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout card = (CardLayout)gamemenu.getLayout();
				card.show(gamemenu,"main");
			}			
		});
		
		
		add(back);
	}
	public void setRank(String newName, int newScore) {
		int nowscore = Integer.valueOf(score.getText()).intValue();
		if(newScore>=nowscore) {
			
			name.setText(newName);
			score.setText(Integer.toString(newScore));
		}
	}
	
}