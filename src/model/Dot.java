package model;

import javax.swing.*;
import java.awt.*;

/**
 * @author Narmada Ravali
 * @Description: Class for adding Dots on symbols
 * @since 03-04-2021
 */
public class Dot extends JButton {
    public Dot(int x, int y, JButton symbol) {
        super();
        this.setBounds(x, y, 15, 15);
        this.setBackground(new Color(147, 184, 189));
        symbol.add(this);
    }
}
