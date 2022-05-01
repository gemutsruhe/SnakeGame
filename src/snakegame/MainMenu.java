package snakegame;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainMenu extends JPanel{
	private GameMenu gamemenu;
	
	private JLabel title;
	private JButton start;
	private JButton load;
	private JButton rank;
	private JButton exit;
	private JTextField tf;
	
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
				StartGame sg = gamemenu.getStartGame();
				sg.newGame();
			}
			
		});
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File("saveGame.data");
				if(file.exists() == true) {
					StartGame sg = gamemenu.getStartGame();
					sg.loadGame();
				}
			}
			
		});
		rank.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					newRank();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	private	void newRank() throws IOException {
		FileReader fileReader = new FileReader("saveGame.data");
		BufferedReader reader=new BufferedReader(fileReader);
		String name = tf.getText();
		String read;
		int cnt=-3;
		while((read=reader.readLine())!=null) {
			cnt++;
		}
		Ranking rankmenu = gamemenu.getRankingMenu();
		rankmenu.setRank(name, cnt);
	}
}