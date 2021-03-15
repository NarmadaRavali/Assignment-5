package model;

import controller.CommonConstants;

import javax.swing.*;
import java.awt.*;

/**
 * @author Narmada Ravali
 * @Description: Class for adding Dots on symbols
 * @since 03-04-2021
 */
public class Dot extends SymbolIO {
    public Dot(int x, int y, Symbol symbol, CommonConstants.Type type) {
        super(type);

        setBounds(x, y, 15, 15);
        setBackground(new Color(147, 184, 189));
        symbol.add(this);
    }
}
