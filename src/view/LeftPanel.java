package view;

import controller.CommonConstants;
import controller.SymbolFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Narmada Ravali
 * @since 03-07-2021
 */
public class LeftPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public LeftPanel(JFrame mainFrame) {
        super();
        this.setName(CommonConstants.LEFT_PANEL_NAME);
        this.setLayout(new FlowLayout());
        int leftPanelWidth = CommonConstants.FRAME_WIDTH / 5;
        int leftPanelHeight = CommonConstants.FRAME_HEIGHT;
        this.setPreferredSize(new Dimension(leftPanelWidth, leftPanelHeight));
        JScrollPane scrollPane = new JScrollPane(this);
        mainFrame.add(scrollPane, BorderLayout.LINE_START);
        ArrayList<Integer> inputs = CommonConstants.inputs;
        ArrayList<Integer> outputs = CommonConstants.outputs;
        int i=0;
        for (String symbolName : CommonConstants.symbolNames) {
            SymbolFactory.createSymbol(this, symbolName, 0, 0);
            i++;
        }
    }
}
