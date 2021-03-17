package view;

import controller.CommonConstants;

import javax.swing.*;
import java.awt.*;

/**
 * @author Narmada Ravali
 * @since 03-07-2021
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public MainFrame(String title) {
        super(title);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(CommonConstants.FRAME_WIDTH / 2,
                CommonConstants.FRAME_HEIGHT / 2));
        this.setPreferredSize(new Dimension(CommonConstants.FRAME_WIDTH,
                CommonConstants.FRAME_HEIGHT));
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
