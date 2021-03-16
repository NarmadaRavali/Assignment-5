package view;

import controller.CommonConstants;
import controller.LoadApplication;
import controller.SaveApplication;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * @author Nikhil Hiremath
 * @Description: Class for menu bar with save, load and new space commands.
 * @since 03-07-2021
 */
public class MenuBar implements MenuListener {

    private int counter = 1;
    private String spaceLabel;
    JFrame mainFrame1;

    public MenuBar(JFrame mainFrame) {
        JMenuBar menu = new JMenuBar();
        JMenu save = new JMenu(CommonConstants.SAVE);
        JMenu load = new JMenu(CommonConstants.LOAD);
        JMenu newSpace = new JMenu(CommonConstants.SPACE);
        newSpace.addMenuListener(this);
        load.addMenuListener(this);
        save.addMenuListener(this);
        menu.add(save);
        menu.add(load);
        menu.add(newSpace);
        mainFrame.setJMenuBar(menu);
        mainFrame1 = mainFrame;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        JMenu myMenu = (JMenu) e.getSource();
        String menuSelected = myMenu.getText();
        if (menuSelected.equals(CommonConstants.SPACE)) {
            RightSpace a = RightSpace.getInstance();
            spaceLabel = CommonConstants.SPACE + " " + String.valueOf(counter);
            a.addTab(spaceLabel);
            counter += 1;
            myMenu.setSelected(false);
        }
        if (menuSelected.equals(CommonConstants.SAVE)) {
        	
			new SaveApplication(mainFrame1);
            myMenu.setSelected(false);
        }
        if (menuSelected.equals(CommonConstants.LOAD)) {
        	
			new LoadApplication(mainFrame1);
            myMenu.setSelected(false);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        JMenu myMenu = (JMenu) e.getSource();
        myMenu.setSelected(false);

    }

    @Override
    public void menuCanceled(MenuEvent e) {
        JMenu myMenu = (JMenu) e.getSource();
        myMenu.setSelected(false);
    }
}
