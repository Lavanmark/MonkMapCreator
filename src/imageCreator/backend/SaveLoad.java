package imageCreator.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SaveLoad {

	
	public static Pixel[][] loadColorSelections() {
		Pixel[][] loadedPix = new Pixel[2][10];
		File f = new File(System.getProperty("user.home")+"/maps/.info/colorSelections.mmc");
		
		Scanner scan = null;
		boolean deleteFile = false;
		try {
			scan = new Scanner(f);
			
			for(int i = 0; i < loadedPix.length; i++){
				for(int o = 0; o < loadedPix[i].length; o++){
					int r = scan.nextInt(), g = scan.nextInt(), b = scan.nextInt();
					loadedPix[i][o] = new Pixel(r, g, b);
				}
			}
			return loadedPix;
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: Could not find Color Selection files!");
		} catch (NoSuchElementException e){
			System.err.println("ERROR: File was not formatted properly! Deleting file!");
			deleteFile = true;
		} finally {
			scan.close();
			if(deleteFile)
				f.delete();
		}
		
		return null;
	}
	
	public static void saveColorSelections(Pixel[][] selections){
		File f = new File(System.getProperty("user.home")+"/maps/.info/colorSelections.mmc");
		
		if(!f.exists()) {
			if(!f.getParentFile().getParentFile().exists())
				f.getParentFile().getParentFile().mkdir();
			if(!f.getParentFile().exists())
				f.getParentFile().mkdir();
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
			
			for(int i = 0; i < selections.length; i++){
				for(int o = 0; o < selections[i].length; o++){
					pw.print(selections[i][o].toString() + " ");
				}
				pw.print('\n');
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
}
