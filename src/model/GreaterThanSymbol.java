package model;

/**
 * @author Mariya Varghese
 * @since 03-07-2021
 */
public class GreaterThanSymbol extends Symbol {

    private static final long serialVersionUID = 1L;

    public GreaterThanSymbol(int x, int y) {
        super(">", x, y, 2, 1);
    }
}
