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
	
	String[] names;
	int[] scores;
	
	Ranking(GameMenu gamemenu){
		
		this.gamemenu=gamemenu;
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		ranktitle=new JLabel("Ranking");
		ranktitle.setFont(new Font("Serif",Font.PLAIN,40));
		ranktitle.setAlignmentX(CENTER_ALIGNMENT);
		add(ranktitle);
		
		rankPanel=new JPanel();
		rankPanel.setAlignmentX(CENTER_ALIGNMENT);
		rankPanel.setLayout(new GridLayout(16,3));
		Dimension d = gamemenu.getFrame().getBounds().getSize();
		d.width/=2;
		d.height = d.height / 6 * 5;
		rankPanel.setMaximumSize(d);
		
		
		rankPanel.add(new JLabel("Rank"));
		rankPanel.add(new JLabel("Name"));
		rankPanel.add(new JLabel("Score"));
		
		for(int i = 0; i < 15; i++) {
			rankPanel.add(new JLabel(Integer.toString(i+1)));
			rankPanel.add(new JLabel(""));
			rankPanel.add(new JLabel(""));
		}
		
		add(rankPanel);
		
		names = new String[15];
		scores=new int[15];
		
		try {
			getRanking();
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
	public void getRanking() throws NumberFormatException, IOException {
		FileReader fileReader = new FileReader("Ranking.data");
		BufferedReader reader = new BufferedReader(fileReader);
		String read;
		int i=0;
		while((read=reader.readLine())!=null) {
			String[] content = read.split(" ");
			i++;
			((JLabel)rankPanel.getComponent(1 + 3 * i)).setText(content[0]);
			((JLabel)rankPanel.getComponent(2 + 3 * i)).setText(content[1]);
		}
	}
	
	
}