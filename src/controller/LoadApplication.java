package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import model.Symbol;
import model.SymbolIO;
import view.RightPanel;
import view.RightSpace;

/**
 * @author Mariya Varghese
 * @Description: Used to load the saved work including all tabs, symbols and connections. 
 * @since 03-11-2021
 */
public class LoadApplication implements java.io.Serializable{
		
	private static final long serialVersionUID = 1L;

	public LoadApplication(JFrame frame) {
		super();
		
		JLabel fileName = new JLabel("no file selected");
		JFileChooser fileChooser = new JFileChooser();  
		int r = fileChooser.showOpenDialog(null); 
		
		if (r == JFileChooser.APPROVE_OPTION) {  
            fileName.setText(fileChooser.getSelectedFile().getAbsolutePath()); 
            
            File fileContent = fileChooser.getSelectedFile();
    		BufferedReader reader;	
    		
    		JTabbedPane rp = RightSpace.getInstance().getRightPanel();
    		rp.removeAll();
    		
    			ConnectionCollection.getInstance().initialize();
    		
    		try {
    			reader = new BufferedReader(new FileReader(fileContent));
                String edges = reader.readLine();
                
                while(edges != null) {
                	
                	String[] line = edges.split(";");
                	
                	if(line[0].equals("Tab")) {
                		
                		RightPanel rightPanel = new RightPanel(rp.getWidth(), rp.getHeight()); 
                		rp.addTab(line[2], rightPanel);
                	}
                	else if(line[0].equals("shape")){
                    	
                    	String name = line[1];
                    	int x = Integer.parseInt(line[2]);
                    	int y = Integer.parseInt(line[3]);
                    	String userInput = line[4];
                    	int spaceIndex = Integer.parseInt(line[5]);                    	
                    	createSymbol(name,x, y, userInput, spaceIndex);
                    	rp.repaint();
                	}
                	else {
                		
                		int spaceIndex = Integer.parseInt(line[1]);
                		int component1Index = Integer.parseInt(line[2]);
                		int connectFrom = Integer.parseInt(line[3]);
                		
                		for(String c2 : line[4].split(":")) {
                			String[] c2L = c2.split("-");
                			int component2Index = Integer.parseInt(c2L[0]);
                    		int connectTo = Integer.parseInt(c2L[1]);
                    		addLine(spaceIndex, component1Index, connectFrom, component2Index, connectTo);
                		}
                	}
                	edges = reader.readLine();
                }
    		}
    		catch (Exception e) {
    			e.printStackTrace();
    		}
		}
		else
			fileName.setText("Operation cancelled"); 
	}
	
	private void createSymbol(String name,int x, int y, String userInput, int tabIndex) {
		
		Symbol symbol = null;
		JTabbedPane rp = RightSpace.getInstance().getRightPanel();
		RightPanel wp = (RightPanel) rp.getComponent(tabIndex);
		switch(name){
			case "(": symbol = SymbolFactory.createSymbol(wp, "(", x, y);
				wp.setOpenP(true);
				break;
			case ")": symbol =  SymbolFactory.createSymbol(wp, ")", x, y);
				wp.setCloseP(true);
				break;
			case "<": symbol = SymbolFactory.createSymbol(wp, "<", x, y);
				break;
			case ">": symbol = SymbolFactory.createSymbol(wp, ">", x, y);
				break;
			case "@": symbol = SymbolFactory.createSymbol(wp, "@", x, y);
				break;
			case "||": symbol = SymbolFactory.createSymbol(wp, "||", x, y);
				break;
			case "-": symbol = SymbolFactory.createSymbol(wp, "-", x, y);
				break;
			default:
				System.out.println("Invalid case");

		}
		symbol.setUserInput(userInput);
	}
	
	private void addLine(int tabIndex, int component1Index, int connectFrom, int component2Index, int connectTo) {
		
		RightPanel panel = (RightPanel) RightSpace.getInstance().getRightPanel().getComponent(tabIndex);
		
		Symbol symbol1 = (Symbol) panel.getComponent(component1Index);
		SymbolIO connection1 = (SymbolIO) symbol1.getComponent(connectFrom);
		
		Symbol symbol2 = (Symbol) panel.getComponent(component2Index);
		SymbolIO connection2 = (SymbolIO) symbol2.getComponent(connectTo);
		connection1.setConnected(true);
		connection2.setConnected(true);
		
		ConnectionCollection.getInstance().addConnection(panel, connection1, connection2);
		
	}
}