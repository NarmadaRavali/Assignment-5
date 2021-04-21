package model;

/**
 * @author Mariya Varghese
 * @since 03-07-2021
 */
public class LessThanSymbol extends Symbol {

    private static final long serialVersionUID = 1L;

    public LessThanSymbol(int x, int y) {
        super("<", x, y, 1, 2);
    }

}