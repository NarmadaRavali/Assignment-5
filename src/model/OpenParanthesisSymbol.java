package model;

import controller.CommonConstants;

/**
 * @author Narmada Ravali
 * @since 03-06-2021
 */
public class OpenParanthesisSymbol extends Symbol {
    public OpenParanthesisSymbol(int x, int y) {
        super("(", x, y, 0, 1);
    }
}
