package view;

import model.AtTheRateSymbol;
import model.GreaterthanSymbol;
import model.LessthanSymbol;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author
 * @since 03-07-2021
 * @Description:
 */
public class LeftPanel extends JPanel{
	

	private static final long serialVersionUID = 1L;

	
	public LeftPanel(JFrame mainFrame) {
        super();
        this.setName("Left Panel");
        this.setLayout(new FlowLayout());
        int leftPanelWidth = mainFrame.getPreferredSize().width / 7;
        int leftPanelHeight = mainFrame.getPreferredSize().height;
        this.setPreferredSize(new Dimension(leftPanelWidth, leftPanelHeight));
        JScrollPane scrollPane = new JScrollPane(this);
        mainFrame.add(scrollPane, BorderLayout.LINE_START);
   
		new LessthanSymbol(this, 0, 0);
		new GreaterthanSymbol(this, 0, 0);
		new AtTheRateSymbol(this,0,0);
    }
}
