package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.MaxScreenCol][gp.MaxScreenRow];
		getTileImage();
		loadMap();
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResource("/tiles/grass01.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResource("/tiles/wall.png"));

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResource("/tiles/water00.png"));

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			while( col < gp.MaxScreenCol && row < gp.MaxScreenRow) {
				String line = br.readLine();
				while(col < gp.MaxScreenCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.MaxScreenCol) {
					col = 0;
					row ++;
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[1].image, 100, 0, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[2].image, 0, 100,   gp.tileSize, gp.tileSize, null);

		int col  = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		while(col < gp.MaxScreenCol&& row < gp.MaxScreenRow) {
			int titleNum  = mapTileNum[col][row];
			g2.drawImage(tile[titleNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			if(col == gp.MaxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
	}
}
