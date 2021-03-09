package model;

import javax.swing.*;
import java.awt.*;

/**
 * @author Narmada Ravali
 * @Description: Class for bar on pipe symbol
 * @since 03-06-2021
 */
public class Pipe extends JButton {
    public Pipe(int x, int y, JButton symbol) {
        super();
        this.setBounds(x, y, 15, 60);
        this.setBackground(new Color(147, 184, 189));
        symbol.add(this);
    }
}
