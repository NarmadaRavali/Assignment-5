package model;
import javax.swing.JComponent;

public class GreaterthanSymbol extends Symbol {
	public GreaterthanSymbol(JComponent panel, int x, int y) {
		super(">", panel, x, y);
		
		int width = this.getPreferredSize().width;
		int height = this.getPreferredSize().height;
		
	}

}
