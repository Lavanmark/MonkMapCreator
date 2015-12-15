package monkMapCreator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoad {
	
	private Main main;
	
	public SaveLoad(Main m){
		main = m;
	}

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
	
	public MapItem load(File f){
		MapItem m = null;
		try{
			if(f == null)
				return null;
			if(!f.getName().contains(".tdmc"))
				return null;
	    	FileInputStream fileIn = new FileInputStream(f);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			m = (MapItem) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Loaded");
		}catch(IOException i){
	        i.printStackTrace();
	        return null;
	    }catch(ClassNotFoundException c){
	        System.out.println("MapItem class not found");
	        c.printStackTrace();
	        return null;
	    }
		return m;
	}
	
	public MapItem importMap(File f){
		int[] mint = new int[1200];
		int[] tree = new int[1200];
		FileInputStream fileIn = null;
		ObjectInputStream finstrm = null;
		boolean doTrees = false;
		try {
			if(!f.getName().contains(".txt"))
				return null;
			fileIn = new FileInputStream(f);
			
			finstrm = new ObjectInputStream(fileIn);
			mint = (int[]) finstrm.readObject();
			f = new File(f.getCanonicalPath().replace(".txt", "tree.txt"));
			System.out.println(f.getCanonicalPath());
			if(f.exists()){
				fileIn = new FileInputStream(f);
				finstrm = new ObjectInputStream(fileIn);
				tree = (int[]) finstrm.readObject();
				doTrees = true;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		MapItem mi = new MapItem(main.TILE_BLANK);
		for(int i = 0; i < mint.length; i++){
			mi.setTile(i, main.tileArray[mint[i]]);
			if(doTrees)
				mi.setTile(i, main.tileArray[tree[i]]);
		}
		return mi;
	}
	
	public void exportPT(String fname, MapItem mi, boolean outside){
		
		
		if(outside){
			TileXY[] ao = new TileXY[1200];
			TileXY[] tao = new TileXY[1200];
			for(int i = 0; i < 1200; i++){
				ao[i] = new TileXY(mi.mapArray[i].x,mi.mapArray[i].y);
				tao[i] = new TileXY(mi.mapTreeArray[i].x,mi.mapTreeArray[i].y);
			}
			try{
		         FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.home") + "/maps/" + fname + ".mmc");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(ao);
		         out.close();
		         fileOut.close();
		         fileOut = new FileOutputStream(System.getProperty("user.home") + "/maps/" + fname + "tree.mmc");
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
		         FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.home") + "/maps/" + fname + ".mmc");
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
