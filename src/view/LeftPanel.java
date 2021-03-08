package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.AtTheRateSymbol;
import model.GreaterthanSymbol;
import model.LessthanSymbol;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;



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
