package imageCreator.window;

import imageCreator.window.pane.ColorSelectPane;
import imageCreator.window.pane.DrawPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class DrawWindow extends JFrame {
	private JMenuBar menuBar;
	private JSplitPane mainPanel;
	private DrawPane drawPane;
	private ColorSelectPane colorSelectPane;
	
	
	public DrawWindow() {
		super("Draw");
		
		this.buildMenuBar();
		this.buildComponents();
		
		this.add(menuBar);
		this.add(mainPanel);
		
		this.setSize(500,500);
		//this.setMinimumSize(new Dimension(500,500));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
//		this.pack();
		this.setVisible(true);
	}
	
	private void buildMenuBar(){
		menuBar = new JMenuBar();
	}
	
	private void buildComponents() {
		
		
		colorSelectPane = new ColorSelectPane(this);
		
		
		drawPane = new DrawPane();
		
		mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, colorSelectPane, drawPane);
	}
}
