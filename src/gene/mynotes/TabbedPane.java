package gene.mynotes;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class TabbedPane extends JPanel {
	
	private Tab[] tabs;
	private JTextPane[] contents;
	
	private int tabCount;
	
	public TabbedPane() {
		super(new BorderLayout());
		
		tabs = new Tab[2];
		contents = new JTextPane[2];
		
		tabCount = 0;
	}
	
	public void addTab(String title, JTextPane content) {
		tabs[tabCount] = new Tab(title);
		contents[tabCount] = content;
		
		add(tabs[tabCount]);
		add(contents[tabCount]);
		
		tabCount++;
	}
}
