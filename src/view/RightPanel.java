package view;

import controller.*;
import model.SymbolIO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Nikhil Hiremath
 * @Description: Panel class which contains work spaces in it.
 * @since 03-07-2021
 */
public class RightPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private boolean isOpenP;
    private boolean isCloseP;
    private RightPanel panel;
    private int rightPanelWidth;
    private int rightPanelHeight;
    private Point startPoint, endPoint;

    public RightPanel(int width, int height) {
        this.setOpenParen(false);
        this.setCloseParen(false);
        this.rightPanelWidth = width;
        this.rightPanelHeight = height - 100;
        this.setName(CommonConstants.RIGHT_PANEL_NAME);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(rightPanelWidth, rightPanelHeight));
        this.setBackground(CommonConstants.LIGHT_GREY);
        this.setPanel(this);
        new DropEventListener(this);
    }

    public void setConnectionHandler(){
        ConnectionListener.getInstance().setPanel(this);
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

    public RightPanel getPanel() {
        return panel;
    }

    public void setPanel(RightPanel panel) {
        this.panel = panel;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (startPoint!= null && endPoint != null) {
            g.setColor(Color.GREEN);
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
        else if (startPoint != null){
            g.clearRect(startPoint.x, startPoint.y, 1, 1);
        }

       SymbolIoGraph symbolIOGraph =
                ConnectionCollection.getInstance().getGraph(this.panel);

        if(symbolIOGraph != null) {
            Map<SymbolIO, ArrayList<SymbolIO>> edges = symbolIOGraph.getEdges();
            for(SymbolIO c1 : edges.keySet()) {
                for(SymbolIO c2 : edges.get(c1)) {

                    int x1 = c1.getX() + c1.getParent().getX() + c1.getWidth()/2;
                    int x2 = c2.getX() + c2.getParent().getX() + c2.getWidth()/2;

                    int y1 = c1.getY() + c1.getParent().getY() + c1.getHeight()/2;
                    int y2 = c2.getY() + c2.getParent().getY() + c2.getHeight()/2;
                    g.setColor(Color.BLACK);

                    g.drawLine(x1, y1, x2, y2);
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
