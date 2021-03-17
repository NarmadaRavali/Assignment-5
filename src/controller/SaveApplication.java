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
 * @Description: Stores all the data in the space tabs including symbol's
 *               properties in a .txt file.
 *
 */

public class SaveApplication {

	public SaveApplication(JFrame mainFrame) {
		super();
		JLabel fileName = new JLabel("no file selected");
		JFileChooser fileChooser = new JFileChooser();
		int choice = fileChooser.showSaveDialog(null);
		int tabIndex;

		if (choice == JFileChooser.APPROVE_OPTION) {
			try (FileWriter fw = new FileWriter(
					fileChooser.getSelectedFile() + ".txt")) {
				JTabbedPane rightPannelTab = RightSpace.getInstance()
						.getRightPanel();
				tabIndex = 0;
				
				
				// Save all the tabs
				for ( Component i : rightPannelTab.getComponents()) {
					String tabTitle = rightPannelTab.getTitleAt(tabIndex);
					fw.write("Tab" + ";" + tabIndex + ";" + tabTitle
							+ System.lineSeparator());
					tabIndex++;
				}
				tabIndex = 0;
				
				//Save all the Symbols
				for (Component i : rightPannelTab.getComponents()) {
					RightPanel tab = (RightPanel) i;

					for (Component j : tab.getComponents()) {
						Symbol symbol = (Symbol) j;

						String name = symbol.getText();
						String x = Integer.toString(symbol.getX());
						String y = Integer.toString(symbol.getY());
						String userInput = symbol.getUserInput();

						fw.write("shape" + ";" + name + ";" + x + ";" + y + ";"
								+ userInput + ";" + tabIndex
								+ System.lineSeparator());
					}
					tabIndex++;
				}
				
				
				//Save all the symbols
				String lines = getLines();
				fw.write(lines);

				fw.close();

			} catch (IOException e) {

				e.printStackTrace();
			}
		} else
			fileName.setText("the user cancelled the operation");
	}

	
	private String getLines() {
		String lines = "";
		Map<RightPanel, SymbolIoGraph> tabLines = ConnectionCollection.getInstance().getGraphMap();
		for (RightPanel tab : tabLines.keySet()) {
			SymbolIoGraph symbolIOGraph = tabLines.get(tab);
			Map<SymbolIO, ArrayList<SymbolIO>> edges = symbolIOGraph.getEdges();
			 Set<SymbolIO> outputs = edges.keySet();
			for (SymbolIO c1 : outputs) {
				int tabIndex = RightSpace.getInstance().getRightPanel()
						.indexOfComponent(tab);
				int pC1Index = getSymbolIndex((Symbol) c1.getParent(), tab);
				int c1Index = getConnectorIndex(c1, (Symbol) c1.getParent());

				lines += "line" + ";" + tabIndex + ";" + pC1Index + ";"
						+ c1Index + ";";

				for (SymbolIO c2 : edges.get(c1)) {
					int pC2Index = getSymbolIndex((Symbol) c2.getParent(), tab);
					int c2Index = getConnectorIndex(c2,
							(Symbol) c2.getParent());

					lines += pC2Index + "-" + c2Index + ":";
				}
				lines += System.lineSeparator();
			}
		}
		return lines;
	}
	
	private int getSymbolIndex(Symbol symbol, RightPanel tab) {
		int i = 0;
		for(Component s : tab.getComponents()) {
			if(symbol == (Symbol) s) {
				return i;
			}
			i++;
		}
		return -1;
		
	}
	
	private int getConnectorIndex(SymbolIO c1, Symbol symbol) {
		int i = 0;
		for(Component c2 : symbol.getComponents()) {
			if(c1== (SymbolIO) c2) {
				return i;
			}
			i++;
		}
		return -1;
		
	}
	
	

}