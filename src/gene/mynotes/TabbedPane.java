package gene.mynotes;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class TabbedPane extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = -6946514662424253722L;
	
	private JPanel tabContainer;
	
	private Tab[] tabs;
	private JTextPane[] textPanes;
	private JPanel cardPane;
	
	private int tabCount;
	private int selectedTab;
	
	public TabbedPane() {
		super();
		
		tabs = new Tab[2];
		textPanes = new JTextPane[2];
		
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
		addTab("Untitled", new JTextPane());
	}
	
	public void addTab(String title, JTextPane textPane) {
		Tab tab = new Tab(title, tabCount);
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
		textPanes[tabCount] = textPane;
		
		tabContainer.add(tabs[tabCount]);
		cardPane.add(textPanes[tabCount], Integer.toString(tabCount));
		
		setSelectedTab(tabCount);
		
		tabCount++;
		if (tabCount >= tabs.length) {
			increaseTabCapacity();
		}
		
		revalidate();
	}
	
	public void closeTab(int index) {
//		System.out.println("closeTab called: "+ index);
		tabContainer.remove(tabs[index]);
		cardPane.remove(textPanes[index]);
		
		tabCount --;
		for (int i = index; i < tabCount; i++) {
			tabs[i] = tabs[i+1];
			tabs[i].setPosition(i);
			textPanes[i] = textPanes[i+1];
		}
		
		tabContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, MyNotes.getMainColor()));
		if (index != 0 && index == selectedTab) {
			System.out.println(index + " "+ tabCount);
			if (index == tabCount) {
				setSelectedTab(index-1);
			}
			else {
				setSelectedTab(index);
			}
		}			
		
		revalidate();
	}
	
	public void setSelectedTab(int index) {
		tabs[selectedTab].setBackground(MyNotes.getSubColor());
		tabs[index].setBackground(MyNotes.getMainColor());
		
		CardLayout cardLayout = (CardLayout)(cardPane.getLayout());
		cardLayout.show(cardPane, Integer.toString(index));
		selectedTab = index;
	}
	// --------------------------------------------------------------------------------
	//   Private methods
	// -------------------------------------------------------------------------------- 
	private void increaseTabCapacity() {
		Tab[] newTabs = new Tab[tabs.length * 2];
		JTextPane[] newContents = new JTextPane[tabs.length * 2];
		for (int i = 0; i < tabs.length; i++) {
			newTabs[i] = tabs[i];
			newContents[i] = textPanes[i];
		}
		
		tabs = newTabs;
		textPanes = newContents;
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
