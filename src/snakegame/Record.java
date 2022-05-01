package snakegame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Record{
	private Frame ranking;
	private JLabel messageField;
	private JTextField nameField;
	private GamePlay gamePlay;
	private String name;
	
	public Record(GamePlay gamePlay) {
		this.gamePlay = gamePlay;
		ranking = new Frame();
		ranking.setSize(200, 100);
		ranking.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

		messageField = new JLabel("Enter palyer's name");
		nameField = new JTextField();
		
		messageField.setFont(new Font("Serif",Font.PLAIN,20));
		nameField.addKeyListener(new MyKeyAdapter());
		
		messageField.setAlignmentX(panel.CENTER_ALIGNMENT);
		nameField.setAlignmentX(panel.CENTER_ALIGNMENT);
		
		panel.add(Box.createVerticalGlue());
		panel.add(messageField);
		panel.add(Box.createRigidArea(new Dimension(0,15)));
		panel.add(nameField);
		
		ranking.add(panel);
		ranking.setVisible(true);
		
	}
	
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				if(nameField.getText().compareTo("") != 0) {
					name = nameField.getText();
					gamePlay.setUserName(name);
					ranking.dispose();
				}
			}
		}
	}
}
