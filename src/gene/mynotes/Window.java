package gene.mynotes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

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
		JMenu menu;
		JMenuItem menuItem;
		
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("New File");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_N);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Actions.newFile();	
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Open File");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_O);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Actions.openFile(Window.this);	
			}
		});
		menu.add(menuItem);
		
		setJMenuBar(menuBar);
	}
	
	private void createContentPane() {
		JPanel contentPane = new JPanel(new BorderLayout());
		
		tabbedPane = new TabbedPane();
		contentPane.add(tabbedPane);
		
		tabbedPane.addTab("untitled", new JTextPane());
		
		setContentPane(contentPane);
	}
}
