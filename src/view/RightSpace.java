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
public class RightSpace extends JPanel {

    private static final long serialVersionUID = 1L;
    private boolean isOpenP;
    private boolean isCloseP;
    private RightSpace panel;
    private Point startPoint, endPoint;

    public RightSpace(int width, int height) {
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

    public void setPanel(RightSpace panel) {
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
            drawArrowLine(g, startPoint.x, startPoint.y, endPoint.x, endPoint.y,
                    5, 4);
        } else if (startPoint != null) {
            g.clearRect(startPoint.x, startPoint.y, 1, 1);
        }

        ConnectionGraph connectionGraph = ConnectionCollection.getInstance()
                .getGraph(this.panel);

        if (connectionGraph != null) {
            Map<SymbolIO, ArrayList<SymbolIO>> edges = connectionGraph.getEdges();
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

                    drawArrowLine(g, startX, startY, endX, endY, 5, 4);
                }
            }
        }
    }

    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }

    public void setStart(Point point) {
        startPoint = point;
    }

    public void setEnd(Point point) {
        endPoint = point;
    }
}
