package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	// screen settings
	final int originalTileSize = 16; // 16*16 title
	final int scale = 3;
	final int titleSize = originalTileSize*scale; // 48*48 tile
	final int MaxScreenCol = 16;
	final int MaxScreenRow = 12;
	final int screenWidth = titleSize* MaxScreenCol;//768
	final int screenHeight = titleSize * MaxScreenRow ; //576
	
	// FPS
	int FPS = 60;
	
	KeyHandlee keyH = new KeyHandlee();
	Thread gameThread;
	
	// set player's default position
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	public GamePanel() {
	
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		double drawInterval = 1000000000/FPS; // O.0166 second
		double nextDrawTime =  System.nanoTime() + drawInterval;
		while(gameThread!=null) {
			
			update();
			
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				
				remainingTime = remainingTime/1000000;
				
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime );
				
				nextDrawTime += drawInterval;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
	
	public void update() {
		
		if(keyH.upPressed == true) {
			playerY -= playerSpeed;
		}
		else if(keyH.downPressed == true) {
			playerY += playerSpeed;
		}
		else if(keyH.leftPressed == true) {
			playerX -= playerSpeed;
		}
		else if(keyH.rightPressed == true) {
			playerX += playerSpeed;
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.white);
		
		g2.fillRect(playerX	, playerY,titleSize, titleSize);
		
		g2.dispose();
	}
	
}
