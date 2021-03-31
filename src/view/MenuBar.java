package view;

import controller.Compile;
import controller.LoadWorkSpaces;
import controller.SaveWorkSpaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * @author Nikhil Hiremath
 * @since 03-07-2021
 */
public class MenuBar implements ActionListener {

    public static int counter = 1;
  
	private String spaceLabel;
    JFrame mainFrame1;
    JMenuItem save, load, newSpace, compile;
    Compile compileWorkSpace;

    public MenuBar(JFrame mainFrame) {
        JMenuBar menu = new JMenuBar();
        save = new JMenuItem("save");
        load = new JMenuItem("load");
        newSpace = new JMenuItem("new space");
        compile = new JMenuItem("compile");
        compileWorkSpace = new Compile();
        newSpace.addActionListener(this);
        load.addActionListener(this);
        save.addActionListener(this);
        compile.addActionListener(this);
        menu.add(save);
        menu.add(load);
        menu.add(newSpace);
        menu.add(compile);
        mainFrame.setJMenuBar(menu);
        mainFrame1 = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(newSpace)) {
            RightPanel a = RightPanel.getInstance();
            spaceLabel = "space " + counter;
            a.addTab(spaceLabel);
            counter += 1;
        } else if (e.getSource().equals(save)) {
            new SaveWorkSpaces();
        } else if (e.getSource().equals(load)) {
            new LoadWorkSpaces();
        } else if (e.getSource().equals(compile)) {
            String msg = compileWorkSpace.compileWorkSpace();
            showMessageDialog(null, msg);
        }
    }
    
    public static int getCounter() {
  		return counter;
  	}

  	public static void setCounter(int counter) {
  		MenuBar.counter = counter;
  	}

}
