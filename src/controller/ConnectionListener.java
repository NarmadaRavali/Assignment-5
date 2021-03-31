package controller;

import model.Symbol;
import view.RightSpace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Ravikanth
 * @since 03-14-2021
 */
public class ConnectionListener extends MouseAdapter {
    private RightSpace panel;
    private static ConnectionListener listener;
    private Symbol selectedOut;
    private int panelLeftBoundary, panelRightBoundary, panelBottomBoundary,
            panelTopBoundary;
    private int prevMouseXPos, prevMouseYPos;
    private int prevSymbolXPos, prevSymbolYPos;

    public static ConnectionListener getInstance() {

        if (listener == null) {
            listener = new ConnectionListener();
        }
        return listener;
    }

    /**
     * Removes the connection when right mouse button is clicked
     * Adds a new connection when left mouse button is clicked
     * Opens input dialog when double clicked
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        panel = (RightSpace) e.getComponent().getParent();

        if (e.getClickCount() == 2) {
            e.getComponent().setForeground(Color.BLACK);
            selectedOut = null;
            String prevInput = ((Symbol) e.getComponent()).getUserInput();
            String input = (String) JOptionPane.showInputDialog(null, "Data:",
                    "Enter User Input", JOptionPane.QUESTION_MESSAGE, null, null,
                    prevInput);

            if (input == null)
                input = prevInput;

            ((Symbol) e.getComponent()).setUserInput(input);
        }

        else  if (SwingUtilities.isRightMouseButton(e))
                ConnectionCollection.getInstance()
                        .removeConnection((Symbol) e.getComponent());

        else {
            if (selectedOut == null) {
                selectedOut = (Symbol) e.getComponent();
                selectedOut.setForeground(Color.GREEN);
            } else {
                Point symbolLocation = panel
                        .getComponentAt(
                                e.getX() + e.getComponent().getX(),
                                e.getY() + e.getComponent().getY())
                        .getLocation();

                Component selectedIn = panel.getComponentAt(symbolLocation);
                if (selectedIn instanceof Symbol
                        && !selectedIn.equals(selectedOut)) {

                    ConnectionCollection.getInstance().addConnection(panel,
                            selectedOut, (Symbol) selectedIn);
                }
                selectedOut.setForeground(Color.BLACK);

                selectedOut = null;
            }
        }
        panel.repaint();


    }

    /**
     * Changes the symbol position whenever it is dragged
     */
    @Override
    public void mousePressed(MouseEvent e) {
        panel = (RightSpace) e.getComponent().getParent();

        super.mousePressed(e);
        prevSymbolXPos = e.getComponent().getX();
        prevSymbolYPos = e.getComponent().getY();
        prevMouseXPos = e.getXOnScreen();
        prevMouseYPos = e.getYOnScreen();
        panelLeftBoundary = panel.getX() + panel.getParent().getX();
        panelRightBoundary = panel.getX() + panel.getBounds().width - 190;
        panelBottomBoundary = panel.getY() + panel.getBounds().height - 110;
        panelTopBoundary = panel.getY() + panel.getParent().getY();

    }


    /**
     * This method sets input given by user to the symbol
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        int newXPosition = e.getXOnScreen() + prevSymbolXPos - prevMouseXPos;
        int newYPosition = e.getYOnScreen() + prevSymbolYPos - prevMouseYPos;
        newXPosition = Math.min(Math.max(newXPosition, panelLeftBoundary),
                panelRightBoundary);
        newYPosition = Math.min(Math.max(newYPosition, panelTopBoundary),
                panelBottomBoundary);
        e.getComponent().setLocation(newXPosition, newYPosition);
        panel.setComponentZOrder(e.getComponent(), 0);
        panel.repaint();
    }

}
