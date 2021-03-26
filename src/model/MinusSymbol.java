package model;



/**
 * @author Narmada Ravali
 * @since 03-06-2021
 */
public class MinusSymbol extends Symbol {
    public MinusSymbol(int x, int y) {
        super("-", x, y, 1, 1);
        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
    }
}
