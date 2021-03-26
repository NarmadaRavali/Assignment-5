package model;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * @author Mariya Varghese
 * @since 03-04-2021
 */
public class Symbol extends JButton {

    private static final long serialVersionUID = 1L;
    protected String userInput;
    private final int inputs;
    private final int outputs;

    public Symbol(String text, int x, int y,int inputs, int outputs) {
        super(text);
        this.inputs = inputs;
        this.outputs = outputs;
        int symbolWidth = 180;
        int symbolHeight = 80;
        setLayout(null);
        setPreferredSize(new Dimension(symbolWidth, symbolHeight));
        setMinimumSize(new Dimension(symbolWidth, symbolHeight));
        setBounds(x, y, symbolWidth, symbolHeight);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setTransferHandler(new TransferHandler(text));
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
    

    public int getNumberOfOutputs() {
        return outputs;
    }
    
    public int getNumberOfInputs() {
        return inputs;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
          g.setColor(Color.lightGray);
        } else {
          g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width-1,getSize().height-1);
        super.paintComponent(g);
      }
    
    
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width-1, getSize().height-1);
      }


}