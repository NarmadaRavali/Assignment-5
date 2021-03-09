package model;

import controller.CommonConstants;
import controller.DragEventListener;

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

    public Symbol(String text, JComponent panel, int x, int y) {
        super(text);
        CommonConstants constants = new CommonConstants();
        this.symbolWidth = constants.SYMBOL_WIDTH;
        this.symbolHeight = constants.SYMBOL_HEIGHT;
        this.x = x;
        this.y = y;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(symbolWidth, symbolHeight));
        this.setMinimumSize(new Dimension(symbolWidth, symbolHeight));
        this.setBounds(x, y, symbolWidth, symbolHeight);
        panel.add(this);
        this.setTransferHandler(new TransferHandler(text));

        if (panel.getName().equals("Left Panel")) {
            new DragEventListener(this);
        }

        panel.add(this);
    }

}