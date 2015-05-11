package imageCreator;

import javax.swing.SwingUtilities;

import monkMapCreator.Main;

public class ImageCreator {

	
	public ImageCreator(){
		
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
	         @Override
	         public void run() {
	            new Main();
	            
	         }
	      });
	}
}
