package imageCreator.window;

import imageCreator.window.pane.ColorSelectPane;
import imageCreator.window.pane.DrawPane;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawWindow extends JFrame {
	private JMenuBar menuBar;
	private JPanel mainPanel;
	private DrawPane drawPane;
	private ColorSelectPane colorSelectPane;
	
	
	public DrawWindow() {
		super("Draw");
		
		this.buildComponents();
		this.buildMenuBar();
		
		this.add(menuBar);
		this.add(mainPanel);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private void buildMenuBar(){
		menuBar = new JMenuBar();
	}
	
	private void buildComponents() {
		mainPanel = new JPanel(new BorderLayout());
		
		colorSelectPane = new ColorSelectPane(this);
		
		
		drawPane = new DrawPane();
		
		mainPanel.add(colorSelectPane, BorderLayout.WEST);
		mainPanel.add(drawPane, BorderLayout.CENTER);
		
	}
}
