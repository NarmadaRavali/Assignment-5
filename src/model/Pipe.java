package model;

import java.awt.Color;

import javax.swing.JButton;

/**
*
* @author Narmada Ravali
* @since 03-06-2021
* @Description: Class for bar on pipe symbol
*/

public class Pipe extends JButton {
    public Pipe(int x, int y, JButton symbol, boolean type) {
        super();
        
        this.setBounds(x, y, 15, 60);
        this.setBackground(new Color(147, 184, 189));
        symbol.add(this);
    }
}
