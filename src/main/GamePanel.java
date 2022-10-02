package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	// screen settings
	final int originalTileSize = 16; // 16*16 title
	final int scale = 3;
	public final int tileSize = originalTileSize*scale; // 48*48 tile
	public final int MaxScreenCol = 16;
	public final int MaxScreenRow = 12;
	public final int screenWidth = tileSize* MaxScreenCol;//768
	public final int screenHeight = tileSize * MaxScreenRow ; //576
	
	// FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandlee keyH = new KeyHandlee();
	Thread gameThread;
	Player player = new Player(this, keyH);

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
//	public void run() {
//		// TODO Auto-generated method stub
//		
//		double drawInterval = 1000000000/FPS; // O.0166 second
//		double nextDrawTime =  System.nanoTime() + drawInterval;
//		while(gameThread!=null) {
//			
//			update();
//			
//			repaint();
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				
//				remainingTime = remainingTime/1000000;
//				
//				if (remainingTime < 0) {
//					remainingTime = 0;
//				}
//				Thread.sleep((long) remainingTime );
//				
//				nextDrawTime += drawInterval;
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//		}
//		
//	}
	
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while(gameThread != null) {
			currentTime =  System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	public void update() {
		
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
	}
	
}
