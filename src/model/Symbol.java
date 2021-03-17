package model;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mariya Varghese
 * @since 03-04-2021
 */
public class Symbol extends JButton {

    private static final long serialVersionUID = 1L;
    protected String userInput;

    public Symbol(String text, int x, int y) {
        super(text);
        int symbolWidth = 180;
        int symbolHeight = 80;
        setLayout(null);
        setPreferredSize(new Dimension(symbolWidth, symbolHeight));
        setMinimumSize(new Dimension(symbolWidth, symbolHeight));
        setBounds(x, y, symbolWidth, symbolHeight);
        setTransferHandler(new TransferHandler(text));
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

}