package imageCreator.window.pane;

import imageCreator.backend.Pixel;
import imageCreator.backend.SaveLoad;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ColorSelectPane extends JPanel {

	/*
	 * will be a pane with an array of old colors selected 
	 * allows for selecting new colors by right clicking cells
	 * also has a current color viewer
	 */
	private JFrame parentFrame;
	private Pixel[][] colors;
	private Rectangle[][] selector;
	
	public ColorSelectPane(JFrame parent) {
		super();
		this.setMinimumSize(new Dimension(45,200));
		parentFrame = parent;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Pixel[][] temp = SaveLoad.loadColorSelections();
		if(temp != null)
			colors = temp;
		else
			this.initializeColors();
		
		this.buildComponents();
		
		this.addMouseListener(mouseAdapter);
	}
	
	private void buildComponents() {
		
	}
	
	private void initializeColors() {
		colors = new Pixel[2][10];
		for(int i = 0; i < colors.length; i++){
			for(int o = 0; o < colors[i].length; o++){
				colors[i][o] = new Pixel();
			}
		}
	}
	private void initializeRect() {
		
	}
	
	private MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			for(int i = 0; i < selector.length; i++) {
				for(int o = 0; o < selector[i].length; o++) {
					if(selector[i][o].contains(e.getPoint()))
						
				}
			}
		}
	};
	
	@Override
	public void paintComponent(Graphics g){
		
	}
	
	
}
