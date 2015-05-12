package imageCreator.backend;

import java.awt.Color;

public class Pixel {

	
	private int red;
	private int green;
	private int blue;
	
	public Pixel() {
		red = 255;
		green = 255;
		blue = 255;
	}
	
	public Pixel(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public Pixel(Pixel copy){
		this.red = copy.red;
		this.green = copy.green;
		this.blue = copy.blue;
	}
	
	public Color getColor() {
		return new Color(red, green, blue);
	}
	
	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		if(red < 255 && red > -1)
			this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		if(green < 255 && green > -1)
			this.green = green;	
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		if(blue < 255 && blue > -1)
			this.blue = blue;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(red);
		sb.append(" ");
		
		sb.append(green);
		sb.append(" ");
		
		sb.append(blue);
		
		return sb.toString();
	}
}
