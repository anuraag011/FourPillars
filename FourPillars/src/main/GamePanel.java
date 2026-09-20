package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	final int originalTileSize = 16; //16x16
	final int scale = 3; // scaling the character
	final int tileSize = originalTileSize * scale; // basically 48x48
	final int maxScreenColumn = 16;   //---|4x3
	final int maxScreenRow = 12;      //---|ratio
	final int screenWidth = tileSize * maxScreenColumn;
	final int screenHeight = tileSize * maxScreenRow;
	
	int FPS = 60;
	
	KeyHandler KeyH = new KeyHandler();
	Thread gameThread; //notion of time in the game
	
	// set player default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // better rendering/smoother
		this.addKeyListener(KeyH);
		this.setFocusable(true);  // focused to recieve key input(?)
	}
	
	public void startGameThread() {
		gameThread = new Thread(this); // instantiate a thread
		gameThread.start();
	}
	
	@Override
	public void run() {

	    double drawInterval = 1000000000 / FPS;
	    double delta = 0;
	    long lastTime = System.nanoTime();
	    long currentTime;
	    long timer = 0;
	    int drawCount = 0;

	    while(gameThread != null) {

	        currentTime = System.nanoTime();

	        delta += (currentTime - lastTime) / drawInterval;
	        timer += (currentTime - lastTime);
	        lastTime = currentTime;

	        if(delta >= 1) {
	            update();
	            repaint();
	            delta--;
	            drawCount++;
	        }
	        if (timer>= 1000000000) {
	        	System.out.println("FPS" + drawCount);
	        	drawCount = 0;
	        	timer = 0;
	        }
	    }
	}
	public void update() {

	    if(KeyH.upPressed) {
	        playerY -= playerSpeed;
	    }
	    else if(KeyH.downPressed) {
	        playerY += playerSpeed;
	    }
	    else if(KeyH.leftPressed) {
	        playerX -= playerSpeed;
	    }
	    else if(KeyH.rightPressed) {
	        playerX += playerSpeed;
	    }
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		g2.dispose();
		
	}
}

