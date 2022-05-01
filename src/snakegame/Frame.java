package snakegame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame extends JFrame{
	Frame() {
		this.setResizable(false);
		this.setSize(new Dimension(600, 600));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
	}
}