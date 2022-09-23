package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandlee;

public class Player extends Entity{

	GamePanel gp;
	KeyHandlee keyH;
	
	public Player(GamePanel gp, KeyHandlee keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		x = 100;
		y =100;
		speed =4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		try {
			pic1 = ImageIO.read(getClass().getResource("/player/anh1.png"));
			pic2 = ImageIO.read(getClass().getResource("/player/anh2.png"));
			pic3 = ImageIO.read(getClass().getResource("/player/anh3.png"));
			pic4 = ImageIO.read(getClass().getResource("/player/anh4.png"));
			pic5 = ImageIO.read(getClass().getResource("/player/anh5.png"));
			pic6 = ImageIO.read(getClass().getResource("/player/anh6.png"));
			pic7 = ImageIO.read(getClass().getResource("/player/anh7.png"));

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true||keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
				y -= speed;
			}
			else if(keyH.downPressed == true) {
				direction = "down";
				y += speed;
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
				x -= speed;
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
				x += speed;
			}
			
			spriteCounter ++;
			if(spriteCounter>12) {
				if(spriteNum == 1 ) {
					spriteNum = 2;
				} else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter  = 0;
			}	
		}

	}
	
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x	, y,gp.titleSize, gp.titleSize);
		BufferedImage image = null;
		switch (direction) {
		case "up": {
			if (spriteNum == 1) {
				image = pic1;
			}
			if(spriteNum == 2) {
				image = pic2;
			}
			break;
		}
		case "down": {
			image = pic7;
			break;
		}
		case "left": {
			if (spriteNum == 1) {
				image = pic3;
			}
			if(spriteNum == 2) {
				image = pic4;
			}
			break;
		}
		case "right": {
			if (spriteNum == 1) {
				image = pic5;
			}
			if(spriteNum == 2) {
				image = pic6;
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
	
}
