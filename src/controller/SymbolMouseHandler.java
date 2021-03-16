package controller;

import model.Symbol;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Ravikanth
 * @Description:
 * @since 03-12-2021
 */
public class SymbolMouseHandler extends MouseAdapter{
    private int panelLeftBoundary, panelRightBoundary, panelBottomBoundary,
            panelTopBoundary;
    private int prevMouseXPos, prevMouseYPos;
    private int prevSymbolXPos, prevSymbolYPos;
    private final Symbol symbol;
    private final JPanel panel;

    public SymbolMouseHandler(JPanel panel, Symbol symbol) {
        this.symbol = symbol;
        this.panel = panel;
        symbol.addMouseListener(this);
        symbol.addMouseMotionListener(this);
    }

    /**
     * Changes the symbol position whenever it is dragged
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        int newXPosition = e.getXOnScreen() + prevSymbolXPos - prevMouseXPos;
        int newYPosition =
                e.getYOnScreen() + prevSymbolYPos - prevMouseYPos;
        newXPosition = Math.min(Math.max(newXPosition, panelLeftBoundary),
                panelRightBoundary);
        newYPosition = Math.min(Math.max(newYPosition, panelTopBoundary),
                panelBottomBoundary);
        symbol.setLocation(newXPosition, newYPosition);
        panel.setComponentZOrder(symbol, 0);
        panel.repaint();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        prevSymbolXPos = symbol.getX();
        prevSymbolYPos = symbol.getY();
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
        if (e.getClickCount() == 2) 
            setInputForSymbol(symbol);    
    }
    
    
    /**
     * This method sets input given by user to the symbol
     * @param symbol   symbol to which user gives input
     */
    private void setInputForSymbol(Symbol symbol) {
        String prevInput = symbol.getUserInput();
        String input = (String) JOptionPane.showInputDialog(null,
                 "Data:",
                 "Enter User Input",
                 JOptionPane.QUESTION_MESSAGE, null,null, prevInput);
        
        if (input == null)
            input = prevInput;
        
        symbol.setUserInput(input);
    }
}
