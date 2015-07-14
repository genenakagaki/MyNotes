package gene.mynotes;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

import gene.mynotes.menu.EditMenu;

public class TabbedPane extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = -6946514662424253722L;

	private Window window;
	
	private JPanel tabContainer;
	
	private Tab[] tabs;
	private JTextPane[] contents;
	private JPanel cardPane;
	
	private int tabCount;
	private int selectedTab;
	
	private long lastEditedTime = System.currentTimeMillis();
	
	public TabbedPane(Window window) {
		super();
		
		this.window = window;
		
		tabs = new Tab[2];
		contents = new JTextPane[2];
		
		tabCount = 0;

		FlowLayout tabLayout = new FlowLayout(FlowLayout.LEFT);
		tabLayout.setHgap(0);
		tabLayout.setVgap(0);
		tabContainer = new JPanel();
		tabContainer.setLayout(tabLayout);
		tabContainer.setPreferredSize(new Dimension(600, 18));	
		tabContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, MyNotes.getMainColor()));
		
		add(tabContainer);
		addMouseListener(this);
		
		cardPane = new JPanel(new CardLayout());
		cardPane.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, MyNotes.getMainColor()));
		
		add(cardPane);	
	}
	// --------------------------------------------------------------------------------
	//   Tab actions
	// --------------------------------------------------------------------------------
	public void addTab() {
		addTab(null, new JTextPane());
	}
	
	public void addTab(File file, JTextPane textPane) {
		Tab tab = new Tab(file, tabCount);
		tab.getCloseButton().addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent e) {
				tab.getCloseButton().setIcon(tab.getCloseIconIn());
			}
			public void mouseExited(MouseEvent e) {
				tab.getCloseButton().setIcon(tab.getCloseIconOut());
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {
				closeTab(tab.getPosition());
			}
		});
		
		tabs[tabCount] = tab;
		tabContainer.add(tabs[tabCount]);

		Document textPaneDoc = textPane.getDocument();
		textPaneDoc.addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {	
				EditMenu.getUndoManager().addEdit(e.getEdit());
			}
		});
		contents[tabCount] = textPane;
		cardPane.add(contents[tabCount], Integer.toString(tabCount));
		
		setSelectedTab(tabCount);
		
		tabCount++;
		if (tabCount >= tabs.length) {
			increaseTabCapacity();
		}
		
		contents[selectedTab].requestFocus();
		
		revalidate();
	}
	
	public void closeTab(int index) {
//		System.out.println("closeTab called: "+ index);
		
		if (index >= 0) {
			tabContainer.remove(tabs[index]);
			cardPane.remove(contents[index]);
			
			tabCount --;
			for (int i = index; i < tabCount; i++) {
				tabs[i] = tabs[i+1];
				tabs[i].setPosition(i);
				contents[i] = contents[i+1];
			}
			
			tabContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, MyNotes.getMainColor()));
			if (index == selectedTab) {
				if (index == tabCount) {
					setSelectedTab(index-1);
				}
				else {
					setSelectedTab(index);
				}
			}
			
			revalidate();
		}
		else {
			window.close();
		}
	}
	
	public void setSelectedTab(int index) {
		if (index != -1) {
			if (selectedTab != -1) {
				tabs[selectedTab].setBackground(MyNotes.getSubColor());				
			}
			tabs[index].setBackground(MyNotes.getMainColor());
			
			CardLayout cardLayout = (CardLayout)(cardPane.getLayout());
			cardLayout.show(cardPane, Integer.toString(index));
			selectedTab = index;			
		}
		else {
			selectedTab = -1;
		}
	}
	
	public Tab getSelectedTab() {
		return tabs[selectedTab];
	}
	
	public JTextPane getSelectedTabContent() {
		return contents[selectedTab];
	}
	// -------
	// Private
	// -------
	private void increaseTabCapacity() {
		Tab[] newTabs = new Tab[tabs.length * 2];
		JTextPane[] newContents = new JTextPane[tabs.length * 2];
		for (int i = 0; i < tabs.length; i++) {
			newTabs[i] = tabs[i];
			newContents[i] = contents[i];
		}
		
		tabs = newTabs;
		contents = newContents;
	}
	// --------------------------------------------------------------------------------
	//   Get and set methods
	// --------------------------------------------------------------------------------
	public int getSelectedTabIndex() {
		return selectedTab;
	}
	// --------------------------------------------------------------------------------
	//   Mouse listener
	// --------------------------------------------------------------------------------
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
		if (tabContainer.getMousePosition() != null) {
			for (int i = 0; i < tabCount; i++) {
				if (tabs[i].getMousePosition() != null) {
					JLabel closeBtn = tabs[i].getCloseButton();
					if (closeBtn.getMousePosition() != null) {
						System.out.println("unko");
					}
				}
			}
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent arg0) {
		if (tabContainer.getMousePosition() != null) {
			for (int i = 0; i < tabCount; i++) {
				if (tabs[i].getMousePosition() != null) {
					JLabel closeBtn = tabs[i].getCloseButton();
					if (closeBtn.getMousePosition() != null) {
						closeTab(i);
					}
					else if (i != selectedTab) {
						setSelectedTab(i);
					}
				}
			}
		}
	}
}
