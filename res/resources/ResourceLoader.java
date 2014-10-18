package resources;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

import javax.imageio.ImageIO;

import monkMapCreator.Tile;

public class ResourceLoader implements Serializable{


	private static final long serialVersionUID = 1L;

	
	public Image getIcon(String fileName){
		return Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/images/icons/" + fileName));
	}
	
	public Image getTitle(String fileName){
		return Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/images/title/" + fileName));
	}
	
	public BufferedImage getSprite(String filename) throws IOException{
		URL url = this.getClass().getResource("/resources/images/sprites/" + filename);
		BufferedImage img = ImageIO.read(url);
		return img;
	}
	
	public BufferedImage getSquares(String filename) throws IOException{
		URL url = this.getClass().getResource("/resources/images/squares/" + filename);
		BufferedImage img = ImageIO.read(url);
		return img;
	}
	public static BufferedImage getSheet(String filename) throws IOException{
		URL url = ResourceLoader.class.getResource("/resources/images/squares/" + filename);
		BufferedImage img = ImageIO.read(url);
		return img;
	}
	public InputStream getSaveFile() throws IOException{
		
		InputStream in = this.getClass().getResourceAsStream("/resources/files/save.txt");
		return in;
	}
	public Tile[][] getMap(){
		
		return new Tile[9][1200];
	}
	/*public OutputStream getSaveFileOut() throws IOException{
		
		OutputStream in = this.getClass().getResource("/resources/files/save.txt");
		return in;
	}*/
}
