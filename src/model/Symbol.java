package model;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComponent;

public class Symbol extends JButton{
	
	private int symbolWidth =  180;
	private int symbolHeight = 80;
	private int x;
	private int y;
	
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
	}

}