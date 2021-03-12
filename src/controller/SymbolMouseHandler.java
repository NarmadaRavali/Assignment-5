package controller;

import model.Symbol;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Ravikanth
 * @Description: This class handles mouse drop listeners and adds a new symbol
 *               to the right panel.
 * @since 03-12-2021
 */
public class SymbolMouseHandler extends MouseAdapter{
    private int panelLeftBoundary;
    private int panelRightBoundary;
    private int panelBottomBoundary;
    private int panelTopBoundary;
    private int prevMouseXPos;
    private int prevMouseYPos;
    private int prevSymbolXPosition;
    private int prevSymbolYPosition;
    private final Symbol symbol;
    private final JPanel panel;

    public SymbolMouseHandler(JPanel panel, Symbol symbol) {
        this.symbol = symbol;
        this.panel = panel;
        symbol.addMouseListener(this);
        symbol.addMouseMotionListener(this);

    }

    /**
     * Changes the symbol position as whenever it is dragged
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        int newXPosition = e.getXOnScreen() + prevSymbolXPosition - prevMouseXPos;
        int newYPosition =
                e.getYOnScreen() + prevSymbolYPosition - prevMouseYPos;
        newXPosition = Math.min(Math.max(newXPosition, panelLeftBoundary),
                panelRightBoundary);
        newYPosition = Math.min(Math.max(newYPosition, panelTopBoundary),
                panelBottomBoundary);
        symbol.setLocation(newXPosition, newYPosition);
        panel.setComponentZOrder(symbol, 0);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        prevSymbolXPosition = symbol.getX();
        prevSymbolYPosition = symbol.getY();
        prevMouseXPos = e.getXOnScreen();
        prevMouseYPos = e.getYOnScreen();
        panelLeftBoundary = panel.getX() + panel.getParent().getX();
        panelRightBoundary = panel.getX() + panel.getBounds().width - 190;
        panelBottomBoundary =
                panel.getY() + panel.getBounds().height - 110;
        panelTopBoundary = panel.getY() + panel.getParent().getY();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

    }
}
