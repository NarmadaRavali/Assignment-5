/**
 * 
 */
package controller;

import model.Symbol;
import model.SymbolIO;
import view.RightPanel;
import view.RightSpace;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * @author Nikhil Hiremath
 * @since 03-12-2021
 */

public class SaveWorkSpaces {

	/**
	 * Opens the file chooser to save all workspaces into a file
	 */
	public SaveWorkSpaces() {
		super();
		JLabel fileName = new JLabel("No File Selected");
		JFileChooser fileChooser = new JFileChooser();
		int choice = fileChooser.showSaveDialog(null);
		int spaceIndex;

		if (choice == JFileChooser.APPROVE_OPTION) {
			try (FileWriter fileWriter = new FileWriter(
					fileChooser.getSelectedFile() + ".txt")) {
				JTabbedPane rightPanel = RightSpace.getInstance()
						.getRightPanel();
				spaceIndex = 0;
				
				
				// Save all the tabs
				while ( spaceIndex < rightPanel.getComponents().length) {
					String tabTitle = rightPanel.getTitleAt(spaceIndex);
					fileWriter.write("Tab" + ";" + spaceIndex + ";" + tabTitle
							+ System.lineSeparator());
					spaceIndex++;
				}
				spaceIndex = 0;

				//Save all the Symbols
				for (Component i : rightPanel.getComponents()) {
					RightPanel tab = (RightPanel) i;

					for (Component j : tab.getComponents()) {
						Symbol symbol = (Symbol) j;
						String symbolName = symbol.getText();
						String x = Integer.toString(symbol.getX());
						String y = Integer.toString(symbol.getY());
						String symbolInput = symbol.getUserInput();

						fileWriter.write("shape" + ";" + symbolName + ";" + x + ";" + y + ";"
								+ symbolInput + ";" + spaceIndex
								+ System.lineSeparator());
					}
					spaceIndex++;
				}

				String lines = getLines();
				fileWriter.write(lines);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			fileName.setText("Cancelled");
	}

	/**
	 * Creates the formatted text data to be saved from the workspaces.
	 * @return - String of text to be saved
	 */
	private String getLines() {
		String lines = "";
		Map<RightPanel, SymbolIoGraph> panelGraphMap = ConnectionCollection.getInstance().getGraphMap();
		for (RightPanel tab : panelGraphMap.keySet()) {
			SymbolIoGraph symbolIOGraph = panelGraphMap.get(tab);
			Map<SymbolIO, ArrayList<SymbolIO>> edges = symbolIOGraph.getEdges();
			 Set<SymbolIO> outputs = edges.keySet();
			for (SymbolIO output : outputs) {
				int panelIndex = RightSpace.getInstance().getRightPanel()
						.indexOfComponent(tab);
				int symbol1Index = getSymbolIndex((Symbol) output.getParent(), tab);
				int outputIndex = getSymbolIOIndex(output, (Symbol) output.getParent());

				lines += "line" + ";" + panelIndex + ";" + symbol1Index + ";"
						+ outputIndex + ";";

				for (SymbolIO input : edges.get(output)) {
					int symbol2Index = getSymbolIndex((Symbol) input.getParent(), tab);
					int inputIndex = getSymbolIOIndex(input,
							(Symbol) input.getParent());

					lines += symbol2Index + "-" + inputIndex + ":";
				}
				lines += System.lineSeparator();
			}
		}
		return lines;
	}


	private int getSymbolIndex(Symbol symbol, RightPanel rightPanel) {
		int i = 0;
		for(Component component : rightPanel.getComponents()) {
			if(symbol == (Symbol) component) {
				return i;
			}
			i++;
		}
		return -1;
		
	}
	
	private int getSymbolIOIndex(SymbolIO symbolIO, Symbol symbol) {
		int i = 0;
		for(Component component : symbol.getComponents()) {
			if(symbolIO ==  component) {
				return i;
			}
			i++;
		}
		return -1;
		
	}
	
	

}