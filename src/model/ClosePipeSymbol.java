package model;



/**
 * @author Narmada Ravali
 * @since 03-24-2021
 */
public class ClosePipeSymbol extends Symbol {
    public ClosePipeSymbol(int x, int y) {
        super("|-", x, y,Integer.MAX_VALUE, 1);
        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
    }
}
