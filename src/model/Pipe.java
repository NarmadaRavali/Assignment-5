package model;

import controller.CommonConstants;

import java.awt.*;

/**
 * @author Narmada Ravali
 * @since 03-06-2021
 */
public class Pipe extends SymbolIO {
    public Pipe(int x, int y, Symbol symbol, CommonConstants.Type type) {
        super(type);
        this.setBounds(x, y, 15, 60);
        this.setBackground(new Color(147, 184, 189));
        symbol.add(this);
    }
}
