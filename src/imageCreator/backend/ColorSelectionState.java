package imageCreator.backend;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class ColorSelectionState {

	
	public interface ColorSelectionListener{
		public void colorChanged();
	}
	
	private static Pixel currentPixel;
	private static Set<ColorSelectionListener> listeners = new HashSet<ColorSelectionListener>();
	
	public static Color getCurrentColor(){
		return new Color(currentPixel.getRed(), currentPixel.getGreen(), currentPixel.getBlue());
	}
	
	public static Pixel getCurrentPixel() {
		return currentPixel;
	}
	
	public static void setCurrentPixel(Pixel newPixel){
		currentPixel = newPixel;
		for(ColorSelectionListener l : listeners)
			l.colorChanged();
	}
	
	public static void addListener(ColorSelectionListener l){
		listeners.add(l);
	}
}
