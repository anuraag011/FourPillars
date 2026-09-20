package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	final int originalTileSize = 16; //16x16
	final int scale = 3; // scaling the character
	final int tileSize = originalTileSize * scale; // basically 48x48
	final int maxScreenColumn = 16;   //---|4x3
	final int maxScreenRow = 12;      //---|ratio
	final int screenWidth = tileSize * maxScreenColumn;
	final int screenHeight = tileSize * maxScreenRow;
	
	Thread gameThread; //notion of time in the game
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // better rendering/smoother
	}
	
	public void startGameThread() {
		gameThread = new Thread(this); // instantiate a thread
		gameThread.start();
	}
	
	@Override
	public void run() {
		
	}
}
