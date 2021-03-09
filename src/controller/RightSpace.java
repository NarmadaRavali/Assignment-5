package controller;

import javax.swing.*;
import java.awt.*;


public class RightSpace implements java.io.Serializable {


    private static final long serialVersionUID = 1L;
    private static RightSpace rightSpaceObj;
    private JTabbedPane rightPanelTab;
    private int rightSpaceWidth, rightSpaceHeight;

    public static RightSpace getInstance() {
        if (rightSpaceObj == null) {
            rightSpaceObj = new RightSpace();
        }
        return rightSpaceObj;
    }


    public void createTabbedPane(JFrame jFrame) {
        rightPanelTab = new JTabbedPane();
        rightSpaceWidth = jFrame.getPreferredSize().width / 6 * 5;
        rightSpaceHeight = jFrame.getPreferredSize().height / 8 * 7;
        rightPanelTab.setName("Tabbed Panel");
        rightPanelTab.setPreferredSize(new Dimension(rightSpaceWidth, rightSpaceHeight));
        rightPanelTab.setBackground(new Color(235, 235, 235));
        addTab("Main");
        JScrollPane scrollPane = new JScrollPane(rightPanelTab);
        jFrame.add(scrollPane);
    }


    public void addTab(String name) {
        RightPanel panel = new RightPanel(rightSpaceWidth, rightSpaceHeight);
        rightPanelTab.addTab(name, panel);
    }
}
