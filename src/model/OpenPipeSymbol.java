package model;


/**
 * @author Narmada Ravali
 * @since 03-06-2021
 */
public class OpenPipeSymbol extends Symbol {
    public OpenPipeSymbol(int x, int y) {
        super("|-", x, y, Integer.MAX_VALUE,1);
        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
    }
}
