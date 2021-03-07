package view;

import javax.swing.*;
import java.awt.*;


public class LeftPanel extends JPanel {
    public LeftPanel(JFrame mainFrame) {
        super();
        this.setName("Left Panel");
        this.setLayout(new FlowLayout());
        int leftPanelWidth = mainFrame.getPreferredSize().width / 5;
        int leftPanelHeight = mainFrame.getPreferredSize().height;
        this.setPreferredSize(new Dimension(leftPanelWidth, leftPanelHeight));
        JScrollPane scrollPane = new JScrollPane(this);
        mainFrame.add(scrollPane, BorderLayout.LINE_START);
    }
}
