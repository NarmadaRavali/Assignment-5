package view;

import controller.CommonConstants;

import javax.swing.*;
import java.awt.*;

/**
 * @author Nikhil Hiremath
 * @Description: This class is for adding shapes and interacting with added
 *               shapes
 * @since 03-07-2021
 */
public class RightSpace implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private static RightSpace rightSpaceObj;
    private CommonConstants constants = new CommonConstants();
    private JTabbedPane rightPanelTab;
    private int rightSpaceWidth, rightSpaceHeight;

    public static RightSpace getInstance() {
        if (rightSpaceObj == null) {
            rightSpaceObj = new RightSpace();
        }
        return rightSpaceObj;
    }

    /**
     * Initializes the tabbed pane in the right panel
     * 
     * @param jFrame - jFrame to attach the created tab pane
     */
    public void createTabbedPane(JFrame jFrame) {
        rightPanelTab = new JTabbedPane();
        rightSpaceWidth = jFrame.getPreferredSize().width / 6 * 5;
        rightSpaceHeight = jFrame.getPreferredSize().height / 8 * 7;
        rightPanelTab.setName("Tabbed Panel");
        rightPanelTab.setPreferredSize(
                new Dimension(rightSpaceWidth, rightSpaceHeight));
        rightPanelTab.setBackground(constants.LIGHT_GREY);
        addTab("Main");
        JScrollPane scrollPane = new JScrollPane(rightPanelTab);
        jFrame.add(scrollPane);
    }

    /**
     * adds a new workspace tab to the right panel
     * 
     * @param name - Sets this value as name for the new workspace created
     */
    public void addTab(String name) {
        RightPanel panel = new RightPanel(rightSpaceWidth, rightSpaceHeight);
        rightPanelTab.addTab(name, panel);
    }
}
