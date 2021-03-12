package model;

/**
 * @author Narmada Ravali
 * @Description: Class representing ')' symbol
 * @since 03-05-2021
 */

public class CloseParenthesisSymbol extends Symbol {

    public CloseParenthesisSymbol( int x, int y) {
        super(")", x, y);
        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
        new Dot(10, height / 2 - 7, this);
    }
}
