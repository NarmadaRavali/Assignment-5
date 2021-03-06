package model;

import javax.swing.JComponent;


public class LessthanSymbol extends Symbol{

	public LessthanSymbol(JComponent panel, int x, int y) {
		super("<", panel, x, y);
		
		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height;
		
	}

}