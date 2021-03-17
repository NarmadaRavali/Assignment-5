package controller;

import model.Pipe;
import model.SymbolIO;
import view.RightPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Ravikanth
 * @since 03-14-2021
 */
public class ConnectionListener extends MouseAdapter {
    private RightPanel panel;
    private static ConnectionListener listener;
    private Point startPoint;
    private SymbolIO selectedOut;

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
        if (!(((SymbolIO) e.getComponent())
                .getType() == CommonConstants.Type.OUTPUT)) {
            ConnectionCollection.getInstance()
                    .removeConnection((SymbolIO) e.getComponent());
        }
    }

    /**
     * Stores the start location of selected output.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        selectedOut = (SymbolIO) e.getComponent();
        this.panel = (RightPanel) selectedOut.getParent().getParent();
        startPoint = new Point(e.getComponent().getParent().getX(),
                e.getComponent().getParent().getY());
        if (selectedOut.getType() == CommonConstants.Type.OUTPUT
                && ((!selectedOut.getConnected())
                        || selectedOut instanceof Pipe)) {
            panel.setStart(new Point(
                    selectedOut.getX() + selectedOut.getParent().getX()
                            + selectedOut.getWidth() / 2,
                    selectedOut.getY() + selectedOut.getParent().getY()
                            + selectedOut.getHeight() / 2));
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
                        e.getX() + startPoint.x + e.getComponent().getX(),
                        e.getY() + startPoint.y + e.getComponent().getY())
                .getLocation();

        Component selectedIn = panel.getComponentAt(symbolLocation)
                .getComponentAt(
                        e.getX() + startPoint.x + e.getComponent().getX()
                                - symbolLocation.x,
                        e.getY() + startPoint.y + e.getComponent().getY()
                                - symbolLocation.y);

        if (selectedOut != null && selectedIn instanceof SymbolIO
                && !selectedIn.getParent().equals(selectedOut.getParent())) {
            if (((SymbolIO) selectedIn).getType()
                    .equals(CommonConstants.Type.INPUT)
                    && (!((SymbolIO) selectedIn).getConnected()
                            || selectedIn instanceof Pipe))
                ConnectionCollection.getInstance().addConnection(panel,
                        selectedOut, (SymbolIO) selectedIn);

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
        panel.setEnd(
                new Point(e.getX() + startPoint.x + e.getComponent().getX(),
                        e.getY() + startPoint.y + e.getComponent().getY()));
        panel.repaint();
    }

}
