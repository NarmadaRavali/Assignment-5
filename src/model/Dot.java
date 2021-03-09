package model;

import java.awt.Color;

import javax.swing.JButton;

/**
*
* @author Narmada Ravali
* @since 03-06-2021
* @Description: Class for Dots on symbols
*/

public class Dot extends JButton {
    public Dot(int x, int y, JButton symbol, boolean type) {
        super();
        this.setBounds(x, y, 15, 15);
        this.setBackground(new Color(147, 184, 189));
        symbol.add(this);
    }
}
