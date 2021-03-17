package view;

import controller.*;
import model.SymbolIO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Nikhil Hiremath
 * @since 03-07-2021
 */
public class RightPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private boolean isOpenP;
    private boolean isCloseP;
    private RightPanel panel;
    private Point startPoint, endPoint;

    public RightPanel(int width, int height) {
        this.setOpenParen(false);
        this.setCloseParen(false);
        this.setName(CommonConstants.RIGHT_PANEL_NAME);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width, height - 100));
        this.setBackground(CommonConstants.LIGHT_GREY);
        this.setPanel(this);
        new DropEventListener(this);
    }

    public boolean isOpenParen() {
        return isOpenP;
    }

    public void setOpenParen(boolean isOpenP) {
        this.isOpenP = isOpenP;
    }

    public boolean isCloseParen() {
        return isCloseP;
    }

    public void setCloseParen(boolean isCloseP) {
        this.isCloseP = isCloseP;
    }

    public void setPanel(RightPanel panel) {
        this.panel = panel;
    }

    /**
     * Draws lines for all the existing connections and also for a new
     * connection which is being created
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (startPoint != null && endPoint != null) {
            g.setColor(Color.GREEN);
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        } else if (startPoint != null) {
            g.clearRect(startPoint.x, startPoint.y, 1, 1);
        }

        SymbolIoGraph symbolIOGraph = ConnectionCollection.getInstance()
                .getGraph(this.panel);

        if (symbolIOGraph != null) {
            Map<SymbolIO, ArrayList<SymbolIO>> edges = symbolIOGraph.getEdges();
            for (SymbolIO output : edges.keySet()) {
                for (SymbolIO input : edges.get(output)) {

                    int startX = output.getX() + output.getParent().getX()
                            + output.getWidth() / 2;
                    int endX = input.getX() + input.getParent().getX()
                            + input.getWidth() / 2;

                    int startY = output.getY() + output.getParent().getY()
                            + output.getHeight() / 2;
                    int endY = input.getY() + input.getParent().getY()
                            + input.getHeight() / 2;

                    g.setColor(Color.BLACK);
                    g.drawLine(startX, startY, endX, endY);
                }
            }
        }
    }

    public void setStart(Point point) {
        startPoint = point;
    }

    public void setEnd(Point point) {
        endPoint = point;
    }
}
