package controller;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class RightPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private boolean isOpenP;
	private boolean isCloseP;
	private RightPanel panel;
	private int rightPanelWidth;
	private int rightPanelHeight;
	
	
	public RightPanel(int width, int height) {
			this.setOpenP(false);
			this.setCloseP(false);
			this.rightPanelWidth = width;
			this.rightPanelHeight = height-100;
			this.setName("Right Panel");
			this.setLayout(null);
			this.setPreferredSize(new Dimension(rightPanelWidth,rightPanelHeight));
			this.setBackground(new Color(235, 235, 235));
			this.setPanel(this);
	}


	public boolean isOpenP() {
		return isOpenP;
	}

	
	public void setOpenP(boolean isOpenP) {
		this.isOpenP = isOpenP;
	}


	public boolean isCloseP() {
		return isCloseP;
	}


	public void setCloseP(boolean isCloseP) {
		this.isCloseP = isCloseP;
	}


	public RightPanel getPanel() {
		return panel;
	}


	public void setPanel(RightPanel panel) {
		this.panel = panel;
	}
	
}	
	
