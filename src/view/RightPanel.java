package view;
import javax.swing.*;

import controller.CommonConstants;
import controller.DropEventListener;

import java.awt.*;

public class RightPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private boolean isOpenP;
	private boolean isCloseP;
	private RightPanel panel;
	private int rightPanelWidth;
	private int rightPanelHeight;
	CommonConstants constants = new CommonConstants();
	
	public RightPanel(int width, int height) {
			this.setOpenP(false);
			this.setCloseP(false);
			this.rightPanelWidth = width;
			this.rightPanelHeight = height-100;
			this.setName(constants.RIGHT_PANEL_NAME);
			this.setLayout(null);
			this.setPreferredSize(new Dimension(rightPanelWidth,rightPanelHeight));
			this.setBackground(constants.LIGHT_GREY);
			this.setPanel(this);
			new DropEventListener(this);
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
	
