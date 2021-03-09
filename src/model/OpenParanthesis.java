package model;

import javax.swing.JComponent;

/**
*
* @author Narmada Ravali
* @since 03-06-2021
* @Description: Class for open paranthesis symbol
*/
public class OpenParanthesis extends Symbol {
    public OpenParanthesis(JComponent panel, int x, int y) {
        super("(", panel, x, y);
        
        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
        
        new Dot(width - 25, height / 2 - 7, this, false);
    }
}
