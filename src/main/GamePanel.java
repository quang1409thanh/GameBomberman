package main;

import java.awt.Color;
import java.awt.Dimension;

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
	
	Thread gameThread;
	public GamePanel() {
	
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(gameThread!=null) {
			System.out.println("The game loop is running");
			
		}
		
	}
	
}
