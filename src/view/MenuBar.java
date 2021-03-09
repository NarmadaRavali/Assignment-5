package view;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import controller.CommonConstants;



public class MenuBar implements MenuListener {
	
	int counter = 1;
	String menuSelected;
	String spaceLabel;
	CommonConstants constants = new CommonConstants();
	
	public MenuBar(JFrame mainFrame) {
        JMenuBar menu = new JMenuBar(); 
        JMenu save = new JMenu(constants.SAVE);
        JMenu load = new JMenu(constants.LOAD);
        JMenu newSpace = new JMenu(constants.SPACE);
        newSpace.addMenuListener(this);
        load.addMenuListener(this);
        save.addMenuListener(this);
        menu.add(save);
        menu.add(load);
        menu.add(newSpace);
        mainFrame.setJMenuBar(menu);
    }
	
   
	@Override
	public void menuSelected(MenuEvent e) {
		 JMenu myMenu = (JMenu) e.getSource();
		 String menuSelected = myMenu.getText();
	     if(menuSelected.equals(constants.SPACE)) {
	    	 RightSpace a = RightSpace.getInstance();
	    	 spaceLabel = "Space "+String.valueOf(counter);
	    	 a.addTab(spaceLabel);
	    	 counter+=1;
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
		// TODO Auto-generated method stub
		JMenu myMenu = (JMenu) e.getSource();
		myMenu.setSelected(false);	
	}
}
