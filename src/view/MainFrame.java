package view;

import controller.CommonConstants;

import javax.swing.*;
import java.awt.*;

/**
 * @author Narmada Ravali
 * @Description: Class for window of the application
 * @since 03-07-2021
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    CommonConstants constants = new CommonConstants();

    public MainFrame(String title) {
        super(title);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(constants.FRAME_WIDTH / 2,
                constants.FRAME_HEIGHT / 2));
        this.setPreferredSize(
                new Dimension(constants.FRAME_WIDTH, constants.FRAME_HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new MenuBar(this);
        new LeftPanel(this);
        this.setVisible(true);
        RightSpace.getInstance().createTabbedPane(this);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        String frameName = "Assignment 5";
        new MainFrame(frameName);
    }
}
