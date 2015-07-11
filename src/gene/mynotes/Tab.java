package gene.mynotes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gene.utils.Utils;

public class Tab extends JPanel {
	
	private static final long serialVersionUID = -6007703548120788452L;
		
	private int width = 100;
	private int height = 18;
	
	private int position;
	
	private JLabel title;
	private JLabel closeBtn; // Close button
	private ImageIcon closeIconOut;
	private ImageIcon closeIconIn;
	
	public Tab(String titleStr, int position) {
		this.position = position;
		title = new JLabel(titleStr);
		title.setBackground(Color.blue);
		add(title);
		
		BufferedImage img = Utils.getImage("/res/close_button1.png");
		closeIconOut = new ImageIcon(img);
		img = Utils.getImage("/res/close_button2.png");
		closeIconIn = new ImageIcon(img);
		
		closeBtn = new JLabel(closeIconOut);
		closeBtn.setSize(new Dimension(16, 16));
		add(closeBtn);
				
		FlowLayout layout = new FlowLayout();
		layout.setHgap(0);
		layout.setVgap(0);
		setLayout(layout);
		
		setPreferredSize(new Dimension(width, height));
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public JLabel getCloseButton() {
		return closeBtn;
	}
	
	public ImageIcon getCloseIconIn() {
		return closeIconIn;
	}
	
	public ImageIcon getCloseIconOut() {
		return closeIconOut;
	}
}
