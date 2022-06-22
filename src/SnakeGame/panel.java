package SnakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;

public class panel extends JPanel implements ActionListener{
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	int score;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_WIDTH)/UNIT_SIZE;
	static final int DELAY = 75;
	boolean moving = false;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int size = 6;
	
	int appleX;
	int appleY;
	//begins by going right
	char direction = 'R';
	javax.swing.Timer t;
	Random rand;
	
	
	panel(){
		rand = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new kA());
		startG();
		
		
	}
	public void startG() {
		spawnApple();
		moving = true;
		t = new javax.swing.Timer(DELAY, this);
		t.start();
		
	}
	public void spawnApple() {
		appleX = rand.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = rand.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	public void paint(Graphics graphic) {
		super.paintComponent(graphic);
		draw(graphic);
	}
	
	public void draw(Graphics graphic) {
		if(moving) {
			for(int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE;i++) {
				graphic.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				graphic.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}
			graphic.setColor(Color.green);
			graphic.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
			for(int i = 0; i < size;i++) {
				if(i == 0) {
					graphic.setColor(Color.red);
					graphic.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					
				}
				else {
					graphic.setColor(Color.green);
					graphic.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
		} else {
			gameOver(graphic);
		}
	}
	
	public void move() {
		for(int i = size; i > 0;i--) {
			//shift all coordinates over one
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
			//get direction of user from switch statement
			switch(direction) {
			case 'U':
				y[0] = y[0] - UNIT_SIZE;
				break;
			case 'L':
				x[0] = x[0] - UNIT_SIZE;
				break;
			case 'R':
				x[0] = x[0] + UNIT_SIZE;
				break;
			case 'D':
				y[0] = y[0] + UNIT_SIZE;
				break;
			}
			
		}
	
	public void check() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			score++;
			size++;
			spawnApple();
		}
	}
	
	public void checkColl() {
		for(int i = size; i > 0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])) {
				moving = false;
			}
		}
		if(y[0] > SCREEN_HEIGHT) { moving = false; }
		if(y[0] < 0) { moving = false; }
		if(x[0] < 0) { moving = false; }
		if(x[0] > SCREEN_WIDTH) { moving = false; }
		
		if(!moving) {
			t.stop();
		}

	}
	
	public void gameOver(Graphics graphic) {
		                                    
}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(moving) {
			move();
			check();
			checkColl();
		}
		repaint();
	}
	
	public class kA extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if(direction != 'R') {
						direction = 'L';
						break;
					}
				case KeyEvent.VK_RIGHT:
					if(direction != 'L') {
						direction = 'R';
						break;
					}
				case KeyEvent.VK_DOWN:
					if(direction != 'U') {
						direction = 'D';
						break;
					}
				case KeyEvent.VK_UP:
					if(direction != 'D') {
						direction = 'U';
						break;
					}
			}
		}
	}
	
}
