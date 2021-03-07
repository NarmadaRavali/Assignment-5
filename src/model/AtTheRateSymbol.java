package model;

import javax.swing.JComponent;

public class AtTheRateSymbol extends Symbol {
	
	private static final long serialVersionUID = 1L;

	public AtTheRateSymbol(JComponent panel, int x, int y) {
		super("@", panel, x, y);
		
		//int width = this.getPreferredSize().width;
		//int height = this.getPreferredSize().height;
	}
}
