package snakegame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Ranking extends JPanel{
	private GameMenu gamemenu;
	
	private JLabel ranktitle;
	private JPanel rankPanel;
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
		
		rankPanel.add(new JLabel("Rank"));
		rankPanel.add(new JLabel("Name"));
		rankPanel.add(new JLabel("Score"));
		rankPanel.add(new JLabel("1"));
		rankPanel.add(new JLabel("player_name"));
		rankPanel.add(new JLabel("player_score"));
		
		add(rankPanel);		
	}
}
