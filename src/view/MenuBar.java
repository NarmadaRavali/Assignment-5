package view;

import static javax.swing.JOptionPane.showMessageDialog;

import controller.CommonConstants;
import controller.Compile;
import controller.LoadApplication;
import controller.SaveApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * @author Nikhil Hiremath
 * @Description: Class for menu bar with save, load and new space commands
 * @since 03-07-2021
 */
public class MenuBar implements ActionListener {

    private int counter = 1;
    private String spaceLabel;
    JFrame mainFrame1;
    JMenuItem save, load, newSpace, compile;

    public MenuBar(JFrame mainFrame) {
        JMenuBar menu = new JMenuBar();
        save = new JMenuItem(CommonConstants.SAVE);
        load = new JMenuItem(CommonConstants.LOAD);
        newSpace = new JMenuItem(CommonConstants.SPACE);
        compile = new JMenuItem(CommonConstants.COMPILE);
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
            RightSpace a = RightSpace.getInstance();
            spaceLabel = CommonConstants.SPACE + " " + String.valueOf(counter);
            a.addTab(spaceLabel);
            counter += 1;
        } else if (e.getSource().equals(save)) {
            new SaveApplication(mainFrame1);
        } else if (e.getSource().equals(load)) {
            new LoadApplication(mainFrame1);
        }else if (e.getSource().equals(compile)) {
            String msg = new Compile().compileWorkSpace();
            showMessageDialog(null, msg);
        }
    }
}
