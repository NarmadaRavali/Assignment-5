package view;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import controller.RightSpace;



public class MenuBar implements MenuListener {
	
	int counter = 1;
	String menuSelected;
	
	public MenuBar(JFrame mainFrame) {
        JMenuBar menu = new JMenuBar(); 
        JMenu save = new JMenu("Save");
        JMenu load = new JMenu("Load");
        JMenu newSpace = new JMenu("New Space");
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
	     System.out.println("Menu Selected: "+ menuSelected);
	     if(menuSelected.equals("New Space")) {
	    	 RightSpace a = RightSpace.getInstance();
	    	 //System.out.println("Space "+String.valueOf(counter));
	    	 a.addTab("Space "+String.valueOf(counter));
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
