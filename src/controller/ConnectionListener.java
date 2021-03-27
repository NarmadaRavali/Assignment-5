package controller;

import model.Symbol;
import view.RightSpace;

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

    public static ConnectionListener getInstance() {

        if (listener == null) {
            listener = new ConnectionListener();
        }
        return listener;
    }

    /**
     * Removes the connection when input is clicked
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
            ConnectionCollection.getInstance()
                    .removeConnection((Symbol) e.getComponent());

    }

    /**
     * Stores the start location of selected output.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        selectedOut = (Symbol) e.getComponent();
        this.panel = (RightSpace) selectedOut.getParent();
        Point startPoint = new Point(e.getX() + e.getComponent().getX(),
                e.getY() + e.getComponent().getY());
        if (selectedOut.getOutputs() > 0) {
        panel.setStart(startPoint);
        } else {
            selectedOut = null;
        }

    }

    /**
     * Draws a permanent line between an input and an output when mouse is
     * released on a SymbolIO object of input type.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        Point symbolLocation = panel
                .getComponentAt(
                        e.getX() + e.getComponent().getX(),
                        e.getY() + e.getComponent().getY())
                .getLocation();

        Component selectedIn = panel.getComponentAt(symbolLocation);
        if (selectedOut != null && selectedIn instanceof Symbol
                && !selectedIn.equals(selectedOut)) {

            ConnectionCollection.getInstance().addConnection(panel,
                        selectedOut, (Symbol) selectedIn);
        }
        panel.setEnd(null);
        panel.setStart(null);
        panel.repaint();

    }

    /**
     * Draws the line when mouse is being dragged from output sumbolIO
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        panel.setEnd(new Point(e.getX() +e.getComponent().getX(),
                e.getY() + e.getComponent().getY()));
        panel.repaint();
    }

}
