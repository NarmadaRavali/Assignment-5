package model;

import javax.swing.JComponent;


/**
*
* @author Narmada Ravali
* @since 03-06-2021
* @Description: Class for pipe symbol
*/

public class PipeSymbol extends Symbol {
    public PipeSymbol(JComponent panel, int x, int y) {
        super("||", panel, x, y);
        
        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
        
        new Pipe(10, 10, this, true);
        new Pipe(width - 25, 10, this, false);
    }
}
