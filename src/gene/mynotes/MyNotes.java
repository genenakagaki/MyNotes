package gene.mynotes;

import javax.swing.AbstractAction;
import javax.swing.Action;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.sql.Savepoint;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;

public class MyNotes extends JFrame {
	
	private JTextArea textArea = new JTextArea(1000, 1000);
	private JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
	private String currentFile = "Untitled";
	private boolean changed = false;
	private boolean newFile = true;
	
	private Font font = new Font("Arial", Font.PLAIN, 11);
	
	private Action action_new;
	private Action action_open;
	private Action action_save;
	private Action action_saveAs;
	private Action action_quit;
	
	private Action action_cut;
	private Action action_copy;
	private Action action_paste;
	
	public MyNotes() {
		textArea.setFont(font);
		add(textArea);
		
		createActions();
		
		createMenuBar();

		JScrollPane scrollPane = new JScrollPane();
		
		add(scrollPane);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(currentFile);
		setSize(600, 400);
		setVisible(true);
	}
	
	private void createActions() {
		action_open = new AbstractAction("Open") {
			
			public void actionPerformed(ActionEvent arg0) {
				if (dialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					readInFile(dialog.getSelectedFile().getAbsolutePath());
				}
			}
		};
		
		action_save = new AbstractAction("Save") {
			
			public void actionPerformed(ActionEvent e) {
				if (newFile) {
					saveFileAs();
				}
				else {
					saveFile(currentFile);
				}
			}
		};
		
		action_saveAs = new AbstractAction("Save As") {

			public void actionPerformed(ActionEvent e) {
				saveFileAs();
			}
		};
		
		action_quit = new AbstractAction("Quit") {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		
		ActionMap m = textArea.getActionMap();
		action_cut   = m.get(DefaultEditorKit.cutAction);
		action_copy  = m.get(DefaultEditorKit.copyAction);
		action_paste = m.get(DefaultEditorKit.pasteAction);
	}
	
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		file.add(action_new);
		file.add(action_open);
		file.add(action_save);
		file.add(action_saveAs);
		file.addSeparator();
		file.add(action_quit);
		
//		for(int i=0; i<4; i++)
//			file.getItem(i).setIcon(null);
		
		JMenu edit = new JMenu("Edit");
		edit.add(action_cut);
		edit.add(action_copy);
		edit.add(action_paste);
		
		menuBar.add(file);
		menuBar.add(edit);
	}
	
	private void readInFile(String fileName) {
		try {
			FileReader r = new FileReader(fileName);
			textArea.read(r, null);
			r.close();
			currentFile = fileName;
			setTitle(currentFile);
			changed = false;
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Can't find the file called :"+ fileName);
		}
	}
	
	private void saveFileAs() {
		if(dialog.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			saveFile(dialog.getSelectedFile().getAbsolutePath());
	}

	private void saveFile(String fileName) {
		try {
			FileWriter w = new FileWriter(fileName);
			textArea.write(w);
			w.close();
			currentFile = fileName;
			setTitle(currentFile);
			changed = false;
			action_save.setEnabled(false);
		}
		catch(IOException e) {
		}
	}
	
	public static void main(String[] args) {
		new MyNotes();
	}
}
