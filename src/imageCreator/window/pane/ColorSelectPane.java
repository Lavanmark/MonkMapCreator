package imageCreator.window.pane;

import imageCreator.backend.ColorSelectionState;
import imageCreator.backend.ColorSelectionState.ColorSelectionListener;
import imageCreator.backend.Pixel;
import imageCreator.backend.SaveLoad;

import java.awt.BorderLayout;
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
import javax.swing.JLabel;
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
		
		this.setLayout(new BorderLayout());
		
		this.buildComponents();
		
		repaint();
	}
	
	private void buildComponents() {
		//this.add(Box.createVerticalGlue());
		//this.add(Box.createRigidArea(new Dimension(5,5)));
		colorArray = new ColorSelector();
		this.add(colorArray, BorderLayout.CENTER);
		//this.add(Box.createRigidArea(new Dimension(5,5)));
		pickColor = new JButton("Add Colors");
		this.add(pickColor, BorderLayout.SOUTH);
		//this.add(Box.createRigidArea(new Dimension(5,5)));
		//this.add(Box.createVerticalGlue());
		
	}

	
	private class ColorSelector extends JPanel implements ColorSelectionListener{
		private Pixel[][] colors;
		private Rectangle[][] selector;
		
		
		public ColorSelector() {
			super();
			setMinimumSize(new Dimension(700,700));
			this.set
			
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
			int x = 1, y = 1;
			for(int i = 0; i < selector.length; i++){
				for(int o = 0; o < selector[i].length; o++){
					selector[i][o] = new Rectangle(x, y, 30, 30);
					y += 30;
				}
				x += 30;
				y = 1;
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
					g.setColor(Color.BLACK);
					g.drawRect(selector[i][o].x-1, selector[i][o].y-1, selector[i][o].width+1, selector[i][o].height+1);
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
