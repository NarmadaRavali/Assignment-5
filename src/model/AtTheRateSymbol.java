package model;

/**
 * @author Mariya Varghese
 * @since 03-05-2021
 */
public class AtTheRateSymbol extends Symbol {

    private static final long serialVersionUID = 1L;

    public AtTheRateSymbol(int x, int y) {
        super("@", x, y,2,2);
        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
    }
}
