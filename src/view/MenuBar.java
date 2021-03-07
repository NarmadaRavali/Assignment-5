package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuBar {
    public MenuBar(JFrame mainFrame) {
        JMenuBar menu = new JMenuBar(); 
        JMenu save = new JMenu("Save");
        JMenu load = new JMenu("Load");
        JMenu newSpace = new JMenu("New Space");
        
        menu.add(save);
        menu.add(load);
        menu.add(newSpace);
        
        save.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    
            }
        });
        
        load.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
        newSpace.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
        mainFrame.setJMenuBar(menu);
    }
}
