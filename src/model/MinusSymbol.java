package model;

import controller.CommonConstants;

/**
 * @author Narmada Ravali
 * @since 03-06-2021
 */
public class MinusSymbol extends Symbol {
    public MinusSymbol(int x, int y) {
        super("-", x, y);
        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
        new Dot(10, height / 2 - 7, this, CommonConstants.Type.INPUT);
        new Dot(width - 25, height / 2 - 7, this, CommonConstants.Type.OUTPUT);
    }
}
