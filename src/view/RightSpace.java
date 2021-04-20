package view;


import controller.*;
import model.Symbol;

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



    public RightSpace(int width, int height, boolean textPanel) {
        this.setName(CommonConstants.RIGHT_PANEL_NAME);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width, height - 100));
        this.setBackground(CommonConstants.LIGHT_GREY);
        if (!textPanel) {
            new DropEventListener(this);
            this.add(SymbolFactory.createSymbol(this, "(", 20, 20));
            this.add(SymbolFactory.createSymbol(this, ")", width - 100, height - 100));
        }
        else{
            setLayout(new FlowLayout());
        }
    }



    /**
     * Draws lines for all the existing connections
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        ConnectionGraph connectionGraph = ConnectionCollection.getInstance()
                .getGraph(this);

        if (connectionGraph != null) {
            Map<Symbol, ArrayList<Symbol>> edges = connectionGraph.getEdges();
            for (Symbol output : edges.keySet()) {
                for (Symbol input : edges.get(output)) {

                    int startX = output.getX()
                            + output.getWidth();
                    int endX = input.getX()
                            ;

                    int startY = output.getY()
                            + output.getHeight() / 2 ;
                    int endY = input.getY() + input.getHeight() /2 ;

                    g.setColor(Color.BLACK);
                    drawArrowLine(g, startX, startY, endX, endY, 10, 5);
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

}
