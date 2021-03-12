package controller;

import model.Symbol;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Ravikanth
 * @Description: This class handles mouse drop listeners and adds a new symbol
 *               to the right panel.
 * @since 03-07-2021
 */
public class SymbolEventListener extends MouseAdapter{

    public SymbolEventListener(Symbol symbol) {
        symbol.addMouseListener(this);
        symbol.addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }
}
