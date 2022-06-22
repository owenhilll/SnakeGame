package SnakeGame;

import javax.swing.JFrame;

public class interFace extends JFrame {
	interFace(){
		
		panel panel = new panel();
		
		this.add(panel);
		
		this.setTitle("Game");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setResizable(false);
		
		this.pack();
		
		this.setVisible(true);
		
		this.setLocationRelativeTo(null);
		
		
	}
}
