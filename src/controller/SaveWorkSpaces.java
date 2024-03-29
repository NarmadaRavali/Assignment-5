/**
 *
 */
package controller;

import model.Symbol;
import view.RightSpace;
import view.RightPanel;

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
                JTabbedPane rightPanel = RightPanel.getInstance()
                        .getRightPanel();
                spaceIndex = 0;

                // Save all the tabs
                while (spaceIndex < rightPanel.getComponents().length) {
                    String tabTitle = rightPanel.getTitleAt(spaceIndex);
                    fileWriter.write("Tab" + ";" + spaceIndex + ";" + tabTitle
                            + System.lineSeparator());
                    spaceIndex++;
                }
                spaceIndex = 0;

                // Save all the Symbols
                for (Component i : rightPanel.getComponents()) {
                    RightSpace tab = (RightSpace) i;

                    for (Component j : tab.getComponents()) {
                        Symbol symbol = (Symbol) j;
                        String symbolName = symbol.getText();
                        String x = Integer.toString(symbol.getX());
                        String y = Integer.toString(symbol.getY());
                        String symbolInput = symbol.getUserInput();

                        fileWriter.write("shape" + ";" + symbolName + ";" + x
                                + ";" + y + ";" + symbolInput + ";" + spaceIndex
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
     *
     * @return - String of text to be saved
     */
    private String getLines() {
        String lines = "";
        Map<RightSpace, ConnectionGraph> panelGraphMap = ConnectionCollection
                .getInstance().getGraphMap();
        for (RightSpace tab : panelGraphMap.keySet()) {
            ConnectionGraph connectionGraph = panelGraphMap.get(tab);

            Map<Symbol, ArrayList<Symbol>> edges = connectionGraph.getEdges();
            Set<Symbol> outputs = edges.keySet();

            for (Symbol output : outputs) {
                int panelIndex = RightPanel.getInstance().getRightPanel()
                        .indexOfComponent(tab);

                int symbol1Index = getSymbolIndex((Symbol) output, tab);

                for (Symbol input : edges.get(output)) {
                    lines += "line" + ";" + panelIndex + ";" + symbol1Index
                            + ";";

                    int symbol2Index = getSymbolIndex((Symbol) input, tab);

                    lines += symbol2Index;
                    lines += System.lineSeparator();
                }

            }

        }
        return lines;
    }

    private int getSymbolIndex(Symbol symbol, RightSpace rightSpace) {
        int i = 0;
        for (Component component : rightSpace.getComponents()) {
            if (symbol == (Symbol) component) {
                return i;
            }
            i++;
        }
        return -1;

    }
}