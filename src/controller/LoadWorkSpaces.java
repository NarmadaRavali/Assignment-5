package controller;

import model.Symbol;
import model.SymbolIO;
import view.RightPanel;
import view.RightSpace;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author Mariya Varghese
 * @Description: Used to load the saved work including all tabs, symbols and connections. 
 * @since 03-11-2021
 */
public class LoadWorkSpaces implements java.io.Serializable{
		
	private static final long serialVersionUID = 1L;

	public LoadWorkSpaces(JFrame frame) {
		super();
		
		JLabel fileName = new JLabel("no file selected");
		JFileChooser fileChooser = new JFileChooser();  
		int choice = fileChooser.showOpenDialog(null); 
		
		if (choice == JFileChooser.APPROVE_OPTION) {  
            fileName.setText(fileChooser.getSelectedFile().getAbsolutePath()); 
            
            File fileContent = fileChooser.getSelectedFile();
    		BufferedReader reader;	
    		
    		JTabbedPane rightSpace = RightSpace.getInstance().getRightPanel();
    		rightSpace.removeAll();
    		
    			ConnectionCollection.getInstance().initialize();
    		
    		try {
    			reader = new BufferedReader(new FileReader(fileContent));
                String lines = reader.readLine();
                
                while(lines != null) {
                	
                	String[] line = lines.split(";");
                	
                	if(line[0].equals("Tab")) {
                		
                		RightPanel rightPanel = new RightPanel(rightSpace.getWidth(), rightSpace.getHeight()); 
                		rightSpace.addTab(line[2], rightPanel);
                	}
                	else if(line[0].equals("shape")){
                    	
                    	String symbolName = line[1];
                    	int x = Integer.parseInt(line[2]);
                    	int y = Integer.parseInt(line[3]);
                    	String symbolValue = line[4];
                    	int symbolIndex = Integer.parseInt(line[5]);    
                    	createSymbol(symbolName,x, y, symbolValue, symbolIndex);
                    	rightSpace.repaint();
                	}
                	else {
                		
                		int spaceIndex = Integer.parseInt(line[1]);
                		int shapeStartIndex = Integer.parseInt(line[2]);
                		int lineStartFrom = Integer.parseInt(line[3]);
                		
                		for(String shape2 : line[4].split(":")) {
                			String[] shape2Split = shape2.split("-");
                			int shapeEndIndex = Integer.parseInt(shape2Split[0]);
                    		int lineEnd = Integer.parseInt(shape2Split[1]);
                    		createLine(spaceIndex, shapeStartIndex, lineStartFrom, shapeEndIndex, lineEnd);
                		}
                	}
                	lines = reader.readLine();
                }
    		}
    		catch (Exception e) {
    			e.printStackTrace();
    		}
		}
		else
			fileName.setText("Cancelled!!"); 
	}
	
	private void createSymbol(String name,int x, int y, String userInput, int tabIndex) {
		JTabbedPane rightSpace = RightSpace.getInstance().getRightPanel();
		RightPanel workSpace = (RightPanel) rightSpace.getComponent(tabIndex);
		SymbolFactory.createSymbol(workSpace, name, x, y);
	}
	
	private void createLine(int workSpaceIndex, int startSymbolIndex, int connectFrom, int endSymbolIndex, int connectTo) {
		
		RightPanel panel = (RightPanel) RightSpace.getInstance().getRightPanel().getComponent(workSpaceIndex);
		
		Symbol startSymbol = (Symbol) panel.getComponent(startSymbolIndex);
		SymbolIO connection1 = (SymbolIO) startSymbol.getComponent(connectFrom);
		
		Symbol endSymbol = (Symbol) panel.getComponent(endSymbolIndex);
		SymbolIO connection2 = (SymbolIO) endSymbol.getComponent(connectTo);
		connection1.setConnected(true);
		connection2.setConnected(true);

		ConnectionCollection.getInstance().addConnection(panel, connection1, connection2);
		
	}
}