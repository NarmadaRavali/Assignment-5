package view;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    
    public MainFrame(String title) {
        super(title);
        final int frameWidth = 1000;
        final int frameHeight = 800;
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(frameWidth/2, frameHeight/2));
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
        new MenuBar(this);
        new LeftPanel(this);
        this.setVisible(true);
    }
    
    public static void main(String args[]) {
        new MainFrame("Assignment 5");
    }
}
