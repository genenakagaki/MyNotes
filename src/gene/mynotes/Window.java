package gene.mynotes;

import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import gene.mynotes.menu.EditMenu;
import gene.mynotes.menu.FileMenu;

public class Window extends JFrame {
	
	private static final long serialVersionUID = -4450301599286362681L;
	
	private TabbedPane tabbedPane;

	public Window(int width, int height, int xPos, int yPos) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createMenuBar();
		createContentPane();
		
		setTitle("Untitled");
		setPreferredSize(new Dimension(width, height));
		setBounds(xPos, yPos, 0, 0);
		pack();
		setVisible(true);
	}
	
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		menuBar.add(new FileMenu(this));
		menuBar.add(new EditMenu());
		
		
		setJMenuBar(menuBar);
	}
	
	private void createContentPane() {
		tabbedPane = new TabbedPane(this);
		
		tabbedPane.setLayout(new BoxLayout(tabbedPane, BoxLayout.Y_AXIS));
		
		tabbedPane.addTab();
		
		setContentPane(tabbedPane);
	}
	
	public void close() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	public TabbedPane getTabbedPane() {
		return tabbedPane;
	}
}
