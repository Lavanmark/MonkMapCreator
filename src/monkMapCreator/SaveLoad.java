package monkMapCreator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoad {

	public void save(String fname, MapItem mi){
		try
	      {
			
			File o = new File(System.getProperty("user.home")+"/maps/"+fname+".tdmc");
			o.createNewFile();
	         FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.home") + "/maps/"  + fname + ".tdmc");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(mi);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public MapItem load(String fname){
		MapItem m = null;
	      try
	      {
	         FileInputStream fileIn = new FileInputStream(System.getProperty("user.home") + "/maps/"  + fname + ".tdmc");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         m = (MapItem) in.readObject();
	         in.close();
	         fileIn.close();
	         System.out.println("Loaded");
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return null;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return null;
	      }
	      return m;
	}
	
	
	public void exportPT(String fname, MapItem mi, boolean outside){
		
		
		if(outside){
			int[] ao = new int[1200];
			int[] tao = new int[1200];
			for(int i = 0; i < 1200; i++){
				ao[i] = mi.mapArray[i].getId();
				tao[i] = mi.mapTreeArray[i].getId();
			}
			try{
		         FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.home") + "/maps/" + fname + ".txt");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(ao);
		         out.close();
		         fileOut.close();
		         fileOut = new FileOutputStream(System.getProperty("user.home") + "/maps/" + fname + "tree.txt");
		         out = new ObjectOutputStream(fileOut);
		         out.writeObject(tao);
		         out.close();
		         fileOut.close();
		      }catch(IOException e){
		          e.printStackTrace();
		      }
		}else{
			int[] ao = new int[1200];
			for(int i = 0; i < 1200; i++){
				ao[i] = mi.mapArray[i].getId();
			}
			try{
		         FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.home") + "/maps/" + fname + ".txt");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(ao);
		         out.close();
		         fileOut.close();
		      }catch(IOException e){
		          e.printStackTrace();
		      }
		}
	}
	
}
