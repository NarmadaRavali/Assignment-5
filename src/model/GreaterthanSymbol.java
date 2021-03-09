package model;
import javax.swing.JComponent;

public class GreaterthanSymbol extends Symbol {

	private static final long serialVersionUID = 1L;

	public GreaterthanSymbol(JComponent panel, int x, int y) {
		super(">", panel, x, y);		
		int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
        new Dot(10, 10, this, true);
        new Dot(10, height - 25, this, true);
        new Dot(width - 25, height / 2 - 7, this, false);
	}
}
