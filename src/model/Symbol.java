package model;


import controller.DragEventListener;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;


public class Symbol extends JButton{
	
	
	@Serial
	private static final long serialVersionUID = 1L;
	private int symbolWidth =  180;
	private int symbolHeight = 80;
	private int x, y;	
	protected String userInput;
	
	public Symbol(String text, JComponent panel, int x, int y) {
		super(text);
		
		this.x = x;
		this.y = y;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(symbolWidth,symbolHeight));
		this.setMinimumSize(new Dimension(symbolWidth,symbolHeight));
		this.setBounds(x, y, symbolWidth, symbolHeight);
		panel.add(this);
		this.setTransferHandler(new TransferHandler(text));
		if (panel.getName().equals("Left Panel")) {
			new DragEventListener(this);
		}

		panel.add(this);
	}

}