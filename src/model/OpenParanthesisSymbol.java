package model;

import javax.swing.*;

/**
 * @author Narmada Ravali
 * @Description: Class representing '(' symbol
 * @since 03-06-2021
 */
public class OpenParanthesisSymbol extends Symbol {
    public OpenParanthesisSymbol(JComponent panel, int x, int y) {
        super("(", panel, x, y);
        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
        new Dot(width - 25, height / 2 - 7, this);
    }
}
