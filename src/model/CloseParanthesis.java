package model;

import javax.swing.JComponent;

public class CloseParanthesis extends Symbol {
    public CloseParanthesis(JComponent panel, int x, int y) {
        super(")", panel, x, y);
        
        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
        
        // new Dot(10, height / 2 - 7, this, true);
    }
}
