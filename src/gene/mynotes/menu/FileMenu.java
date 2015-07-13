package gene.mynotes.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import gene.mynotes.Window;

public class FileMenu extends JMenu {
	
	private static final long serialVersionUID = 4774848703846896316L;

	private final static JFileChooser fileChooser = new JFileChooser();
	
	private Window window;

	public FileMenu(Window window) {
		super("File");
		
		this.window = window;
		setMnemonic(KeyEvent.VK_F);
		JMenuItem menuItem;

		menuItem = new JMenuItem("New File");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_N);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newFile();
			}
		});
		add(menuItem);

		menuItem = new JMenuItem("Open File");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_O);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();	
			}
		});
		add(menuItem);
		
		menuItem = new JMenuItem("Save File");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_S);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file = window.getTabbedPane().getSelectedTab().getFile();
				if (file != null) {
					saveFile(file, window.getTabbedPane().getSelectedTabContent());
				}
				else {
					File newFile = saveFileAs(window.getTabbedPane().getSelectedTabContent());
					window.getTabbedPane().getSelectedTab().setFile(newFile);			
				}
			}
		});
		add(menuItem);
		
		menuItem = new JMenuItem("Save File As");
//		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_A);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file = saveFileAs(window.getTabbedPane().getSelectedTabContent());
				window.getTabbedPane().getSelectedTab().setFile(file);
			}
		});
		add(menuItem);
		
		addSeparator();
		
		menuItem = new JMenuItem("Close File");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_C);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close(window.getTabbedPane().getSelectedTabIndex());
			}
		});
		add(menuItem);
	}
	
	// --------------------------------------------------------------------------------
	//   File Actions
	// --------------------------------------------------------------------------------
	private void newFile() {
		window.getTabbedPane().addTab();
	}

	private void openFile() {
		int returnVal = fileChooser.showOpenDialog(window);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			FileReader reader;
			
			JTextPane textPane = new JTextPane();			
			
			try {
				reader = new FileReader(file);
				
				try {
					textPane.read(reader, file);				
				}
				finally {
					reader.close();
				}
			}
			catch (IOException e) {
				System.err.println("Failed to read file: "+ file.getName());
				e.printStackTrace();
			}
			
			window.getTabbedPane().addTab(file, textPane);
		}
	}
	
	private File saveFileAs(JTextPane textPane) {
		int returnVal = fileChooser.showSaveDialog(window);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            saveFile(file, textPane);
            
            return file;
        }
        return null;
	}
	
	private void saveFile(File file, JTextPane textPane) {
		String content = textPane.getText();
		
		FileWriter writer;
		try {
			writer = new FileWriter(file, false);
			try {
				writer.write(content);
			}
			finally {
				writer.close();
			}
		}
		catch (IOException e) {
			System.err.println("Failed to write file: "+ file.getName());
        	e.printStackTrace();
		}
	}
	
	private void close(int index) {
		window.getTabbedPane().closeTab(index);
	}
}
