package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.CommonConstants;
import model.AtTheRateSymbol;
import model.CloseParanthesis;
import model.GreaterthanSymbol;
import model.LessthanSymbol;
import model.MinusSymbol;
import model.OpenParanthesis;
import model.PipeSymbol;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;



public class LeftPanel extends JPanel{
	

	private static final long serialVersionUID = 1L;
	CommonConstants constants = new CommonConstants();
    int leftPanelWidth,leftPanelHeight;
	
	public LeftPanel(JFrame mainFrame) {
        super();
        this.setName(constants.LEFT_PANEL_NAME);
        this.setLayout(new FlowLayout());
        int leftPanelWidth = mainFrame.getPreferredSize().width / 5;
        int leftPanelHeight = mainFrame.getPreferredSize().height;
        this.setPreferredSize(new Dimension(leftPanelWidth, leftPanelHeight));
        JScrollPane scrollPane = new JScrollPane(this);
        mainFrame.add(scrollPane, BorderLayout.LINE_START);
		new LessthanSymbol(this, 0, 0);
		new GreaterthanSymbol(this, 0, 0);
		new AtTheRateSymbol(this,0,0);
		new CloseParanthesis(this,0,0);
		new OpenParanthesis(this,0,0);
		new PipeSymbol(this,0,0);
		new MinusSymbol(this,0,0);
    }
}
