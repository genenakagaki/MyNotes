package gene.mynotes.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultEditorKit;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class EditMenu extends JMenu {

	private static final long serialVersionUID = -2139954716931532637L;
	
	private static UndoManager undoMgr = new UndoManager();
	
	public EditMenu() {
		super("Edit");
		
		setMnemonic(KeyEvent.VK_E);
		JMenuItem menuItem;
		
		JMenuItem undoItem = new JMenuItem("Undo");
		undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		undoItem.setMnemonic(KeyEvent.VK_U);
		undoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					undoMgr.undo();
				}
				catch (CannotUndoException cre) {
					cre.printStackTrace();
				}
				undoItem.setEnabled(undoMgr.canUndo());
			}
		});
		add(undoItem);
		
		addSeparator();
		
		menuItem = new JMenuItem(new DefaultEditorKit.CutAction());
		menuItem.setText("Cut");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_T);
		add(menuItem);

        menuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
        menuItem.setText("Copy");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        menuItem.setMnemonic(KeyEvent.VK_C);
        add(menuItem);

        menuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
        menuItem.setText("Paste");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        menuItem.setMnemonic(KeyEvent.VK_P);
        add(menuItem);
	}
	
	public static UndoManager getUndoManager() {
		return undoMgr;
	}
}
