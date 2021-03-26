package model;

import controller.CommonConstants;

/**
 * @author Mariya Varghese
 * @since 03-07-2021
 */
public class GreaterThanSymbol extends Symbol {

    private static final long serialVersionUID = 1L;

    public GreaterThanSymbol(int x, int y) {
        super(">", x, y,2,1);
        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
    }
}
