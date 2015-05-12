package imageCreator;

import imageCreator.window.DrawWindow;

import javax.swing.SwingUtilities;

public class ImageCreator {

	
	public ImageCreator() {
		new DrawWindow();
		System.out.println("all good");
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            new ImageCreator();
	            
	         }
	      });
	}
}
