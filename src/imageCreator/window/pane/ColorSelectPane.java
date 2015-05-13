package imageCreator.window.pane;

import imageCreator.backend.ColorSelectionState;
import imageCreator.backend.ColorSelectionState.ColorSelectionListener;
import imageCreator.backend.Pixel;
import imageCreator.backend.SaveLoad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ColorSelectPane extends JPanel{

	/*
	 * will be a pane with an array of old colors selected 
	 * allows for selecting new colors by right clicking cells
	 * also has a current color viewer
	 */
	@SuppressWarnings("unused")
	private JFrame parentFrame;
	private JButton pickColor;
	private ColorSelector colorArray;
	
	
	public ColorSelectPane(JFrame parent) {
		super();
		this.setMinimumSize(new Dimension(45,200));
		parentFrame = parent;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.buildComponents();
		
		repaint();
	}
	
	private void buildComponents() {
		this.add(Box.createVerticalGlue());
		this.add(Box.createRigidArea(new Dimension(5,5)));
		colorArray = new ColorSelector();
		this.add(colorArray);
		this.add(Box.createRigidArea(new Dimension(5,5)));
		pickColor = new JButton("Add Colors");
		this.add(pickColor);
		this.add(Box.createRigidArea(new Dimension(5,5)));
		this.add(Box.createVerticalGlue());
		
	}

	
	private class ColorSelector extends JComponent implements ColorSelectionListener{
		private Pixel[][] colors;
		private Rectangle[][] selector;
		
		
		public ColorSelector() {
			super();
			//this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			setMinimumSize(new Dimension(25,110));
			
			Pixel[][] temp = SaveLoad.loadColorSelections();
			if(temp != null)
				colors = temp;
			else
				initializeColors();
			
			initializeRect();
			
			addMouseListener(mouseAdapter);
			repaint();
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
			selector = new Rectangle[2][10];
			int x = 0, y = 0;
			for(int i = 0; i < selector.length; i++){
				for(int o = 0; o < selector[i].length; o++){
					selector[i][o] = new Rectangle(x, y, 10, 10);
					y += 11;
				}
				x += 11;
				y = 0;
			}
		}
		
		private MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for(int i = 0; i < selector.length; i++) {
					for(int o = 0; o < selector[i].length; o++) {
						if(selector[i][o].contains(e.getPoint()))
							ColorSelectionState.setCurrentPixel(colors[i][o]);
					}
				}
				repaint();
			}
		};
		
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			for(int i = 0; i < selector.length; i++){
				for(int o = 0; o < selector[i].length; o++){
					System.out.println("yes");
					g.setColor(Color.BLACK);
					g.drawRect(selector[i][o].x, selector[i][o].y, selector[i][o].width, selector[i][o].height);
					g.setColor(colors[i][o].getColor());
					g.fillRect(selector[i][o].x, selector[i][o].y, selector[i][o].width, selector[i][o].height);
				}
			}
		}

		@Override
		public void colorChanged() {
			repaint();
		}
	}
	
}
