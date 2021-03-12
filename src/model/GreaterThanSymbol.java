package model;

/**
 * @author Mariya Varghese
 * @Description: Class representing '>' symbol
 * @since 03-07-2021
 */
public class GreaterThanSymbol extends Symbol {

    private static final long serialVersionUID = 1L;

    public GreaterThanSymbol(int x, int y) {
        super(">", x, y);
        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
        new Dot(10, 10, this);
        new Dot(10, height - 25, this);
        new Dot(width - 25, height / 2 - 7, this);
    }
}
