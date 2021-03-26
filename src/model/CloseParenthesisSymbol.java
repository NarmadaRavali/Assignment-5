package model;

import controller.CommonConstants;

/**
 * @author Narmada Ravali
 * @since 03-05-2021
 */

public class CloseParenthesisSymbol extends Symbol {

    public CloseParenthesisSymbol(int x, int y) {
        super(")", x, y,1,0);
        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
    }
}
