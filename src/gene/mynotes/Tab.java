package gene.mynotes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tab extends JPanel {
	
	private static final long serialVersionUID = -6007703548120788452L;
	
	private int width = 100;
	private int height = 18;
	
	private JLabel title;
	
	public Tab(String titleStr) {
		title = new JLabel(titleStr);
		title.setBackground(Color.blue);
		add(title);
		
		FlowLayout layout = new FlowLayout();
		layout.setHgap(0);
		layout.setVgap(0);
		setLayout(layout);
		
		setPreferredSize(new Dimension(width, height));
	}
}
