package controller;

import model.SymbolIO;
import view.RightPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ConnectionHandler extends MouseAdapter {
    private RightPanel panel;
    private static ConnectionHandler handler;
    private Point startPoint;
    private SymbolIO selectedOut;
    public static ConnectionHandler getInstance() {

        if (handler == null) {
            handler = new ConnectionHandler();
        }
        return handler;
    }

    public void setPanel(RightPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        selectedOut = (SymbolIO) e.getComponent();
        setPanel((RightPanel) selectedOut.getParent().getParent());
        startPoint = new Point(e.getComponent().getParent().getX()
                        , e.getComponent().getParent().getY());
        if (selectedOut.getType().equals(CommonConstants.Type.OUTPUT)){
            panel.setStart(new Point(selectedOut.getX() + selectedOut.getParent().getX() + selectedOut.getWidth()/2,
                    selectedOut.getY() + selectedOut.getParent().getY() + selectedOut.getHeight()/2));
        }
        else{
           ConnectionCollection.getInstance().removeConnection(selectedOut);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        Point button =
                panel.getComponentAt(e.getX() + startPoint.x + e.getComponent().getX(),
                e.getY() + startPoint.y + e.getComponent().getY()).getLocation();
        Component selectedIn =  panel.getComponentAt(button)
                .getComponentAt(e.getX() + startPoint.x + e.getComponent().getX()- button.x
                , e.getY() + startPoint.y +  e.getComponent().getY() - button.y);

        if (selectedIn instanceof SymbolIO && selectedIn.getParent() != selectedOut.getParent()){
           if (!((SymbolIO) selectedIn).getType().equals(CommonConstants.Type.OUTPUT))
            ConnectionCollection.getInstance().addConnection(panel,
                    selectedOut,
                    (SymbolIO) selectedIn);

//            panel.setEnd(new Point(selectedOutput.getX() + selectedOutput.getParent().getX() + selectedOutput.getWidth() / 2,
//                    selectedOutput.getY() + selectedOutput.getParent().getY() + selectedOutput.getHeight() / 2));
        }
        panel.setEnd(null);
        panel.setStart(null);
        panel.repaint();

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        panel.setEnd(new Point(e.getX() + startPoint.x + e.getComponent().getX()
                , e.getY() + startPoint.y +  e.getComponent().getY()));
        panel.repaint();
    }

}
