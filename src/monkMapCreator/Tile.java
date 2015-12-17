package monkMapCreator;

import monkMapCreator.SheetGrabber;

public class Tile implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//TODO convert all chairs to items
	
	public int  x , y;
	private boolean solid;
	private boolean door;
	private boolean sign;
	private String signMsg;
	private int id;
	private static int nextId;
	
	public Tile(boolean solid, boolean door, int x, int y, SheetGrabber sg){
		this.solid = solid;
		this.door = door;
		this.sign = false;
		this.signMsg = "";
		this.x=x;
		this.y=y;
		this.setImage(x, y,sg);
		id = nextId++;
	}
	public Tile(boolean solid, boolean door, boolean sign, int x, int y, SheetGrabber sg){
		this.solid = solid;
		this.door = door;
		this.sign = sign;
		this.signMsg = "";
		this.x=x;
		this.y=y;
		this.setImage(x, y,sg);
		id = nextId++;
	}
	public Tile(boolean solid, boolean door, int x, int y, SheetGrabber sg, int id){
		this.solid = solid;
		this.door = door;
		this.sign = false;
		this.signMsg = "";
		this.x=x;
		this.y=y;
		this.setImage(x, y,sg);
		this.id = id;
	}
	
	
	/*public BufferedImage getImage(){
		return tileImage;
	}*/
	public boolean isSolid(){
		return solid;
	}
	public boolean isDoor(){
		return door;
	}
	public boolean isSign(){
		return sign;
	}
	public String getSignMsg(){
		return signMsg;
	}
	public void setSignMsg(String msg){
		sign = true;
		signMsg = msg;
	}
	public void setImage(int x, int y, SheetGrabber sg1){
		//int width = 20, height = 20;
		/*tileImage = sg1.grabSprite(x, y, width, height);*/
	}
	public int getId(){
		return id;
	}
	
}
