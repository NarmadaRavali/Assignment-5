package view;

import controller.CommonConstants;
import controller.DropEventListener;

import javax.swing.*;
import java.awt.*;

/**
 * @author Nikhil Hiremath
 * @Description: Panel class which contains work spaces in it.
 * @since 03-07-2021
 */
public class RightPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private CommonConstants constants;
    private boolean isOpenP;
    private boolean isCloseP;
    private RightPanel panel;
    private int rightPanelWidth;
    private int rightPanelHeight;

    public RightPanel(int width, int height) {
        constants = new CommonConstants();
        this.setOpenP(false);
        this.setCloseP(false);
        this.rightPanelWidth = width;
        this.rightPanelHeight = height - 100;
        this.setName(constants.RIGHT_PANEL_NAME);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(rightPanelWidth, rightPanelHeight));
        this.setBackground(constants.LIGHT_GREY);
        this.setPanel(this);
        new DropEventListener(this);
    }

    public boolean isOpenP() {
        return isOpenP;
    }

    public void setOpenP(boolean isOpenP) {
        this.isOpenP = isOpenP;
    }

    public boolean isCloseP() {
        return isCloseP;
    }

    public void setCloseP(boolean isCloseP) {
        this.isCloseP = isCloseP;
    }

    public RightPanel getPanel() {
        return panel;
    }

    public void setPanel(RightPanel panel) {
        this.panel = panel;
    }

}
