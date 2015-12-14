package monkMapCreator;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MapItem implements java.io.Serializable{

	
	private static final long serialVersionUID = 1L;
	
	int arraySize = 1200;
	public Tile[] mapArray = new Tile[1200];
	public Tile[] mapTreeArray = new Tile[1200];
	public Rectangle[] blocks = new Rectangle[1200];
	
	
	
	public MapItem(Tile tile){
		int x = 0, y = 0;
		for(int i = 0; i < arraySize; i++){
			mapArray[i] = tile;
			mapTreeArray[i] = tile;
			if(x>=800){
				x=0;
				y += 20;
			}
			blocks[i] = new Rectangle(x,y,20,20);
			x+=20;
		}
	}
	
	public void setTile(int loc, Tile tile){
		if(tile.getId() > 58 && tile.getId() < 68){
			mapTreeArray[loc] = tile;
		}else if(tile.getId() == 58){ //&& mapTreeArray[loc].getId() != 58
			mapTreeArray[loc] = tile;
		}else{
			mapArray[loc] = tile;
		}
	}
	
	public Tile getTile(int loc, Tile blank){
		if(mapTreeArray[loc].getId() == blank.getId()){
			return mapArray[loc];
		}else{
			return mapTreeArray[loc];
		}
		
	}
	
	public BufferedImage getImage(int x, int y,SheetGrabber sg1){
		return sg1.grabSprite(x, y, 20, 20);
	}
	
	public void drawTiles(Graphics g,SheetGrabber sg){
		for(int i = 0; i < 1200; i++){
			if(i < 1200){
				g.drawImage(getImage(mapArray[i].x,mapArray[i].y, sg), blocks[i].x, blocks[i].y, null);
			}
		}
	}
	
	public void drawTrees(Graphics g,SheetGrabber sg){
		for(int i = 0; i < 1200; i++){
			if(i < 1200){
				g.drawImage(getImage(mapTreeArray[i].x,mapTreeArray[i].y, sg), blocks[i].x, blocks[i].y, null);
			}
		}
	}
	
	
}
