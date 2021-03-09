package model;

import javax.swing.JComponent;

/**
 *
 * @author
 * @since 03-07-2021
 * @Description:
 */
public class AtTheRateSymbol extends Symbol {
	
	private static final long serialVersionUID = 1L;

	public AtTheRateSymbol(JComponent panel, int x, int y) {
		super("@", panel, x, y);
		
		//int width = this.getPreferredSize().width;
		//int height = this.getPreferredSize().height;
	}
}
