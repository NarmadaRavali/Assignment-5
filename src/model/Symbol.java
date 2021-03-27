package model;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mariya Varghese
 * @since 03-04-2021
 */
public class Symbol extends JButton {

    private static final long serialVersionUID = 1L;
    protected String userInput;
    private int inputs;
    private int outputs;

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
        setTransferHandler(new TransferHandler(text));
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
    

    public int getOutputs() {
        return outputs;
    }
    
    public int getInputs() {
        return inputs;
    }

    public void setInputs(int inputs) {
        this.inputs = inputs;
    }

    public void setOutputs(int outputs) {
        this.outputs = outputs;
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.drawOval(0, 0, getSize().width-1, getSize().height-1);
      }


}