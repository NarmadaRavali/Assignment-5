package model;

import controller.CommonConstants;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mariya Varghese
 * @Description: Super Class for all the symbol classes
 * @since 03-04-2021
 */
public class Symbol extends JButton {

    private static final long serialVersionUID = 1L;
    private int symbolWidth;
    private int symbolHeight;
    private final int x;
    private final int y;

    public Symbol(String text, int x, int y) {
        super(text);
        this.symbolWidth = CommonConstants.SYMBOL_WIDTH;
        this.symbolHeight = CommonConstants.SYMBOL_HEIGHT;
        this.x = x;
        this.y = y;
        setLayout(null);
        setPreferredSize(new Dimension(symbolWidth, symbolHeight));
        setMinimumSize(new Dimension(symbolWidth, symbolHeight));
        setBounds(x, y, symbolWidth, symbolHeight);
        setTransferHandler(new TransferHandler(text));

    }

}