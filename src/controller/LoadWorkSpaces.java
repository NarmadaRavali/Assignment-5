
package controller;

import model.Symbol;
import view.RightSpace;
import view.MenuBar;
import view.RightPanel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author Mariya Varghese
 * @since 03-11-2021
 */
public class LoadWorkSpaces implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Opens the file chooser and loads the selected file
     */
    public LoadWorkSpaces() {
        super();

        JLabel fileName = new JLabel("no file selected");
        JFileChooser fileChooser = new JFileChooser();
        int choice = fileChooser.showOpenDialog(null);

        if (choice == JFileChooser.APPROVE_OPTION) {
            fileName.setText(fileChooser.getSelectedFile().getAbsolutePath());

            File fileContent = fileChooser.getSelectedFile();
            BufferedReader reader;

            JTabbedPane rightSpace = RightPanel.getInstance().getRightPanel();
            rightSpace.removeAll();

            ConnectionCollection.getInstance().initialize();

            try {
                reader = new BufferedReader(new FileReader(fileContent));
                String lines = reader.readLine();

                while (lines != null) {

                    String[] line = lines.split(";");

                    if (line[0].equals("Tab")) {

                        RightSpace.loadFlag = true;
                        RightSpace rightS = new RightSpace(
                                rightSpace.getWidth() - 100,
                                rightSpace.getHeight() - 100, false);
                        RightSpace.loadFlag = false;
                        rightSpace.addTab(line[2], rightS);
                        MenuBar.setCounter(Integer.parseInt(line[1]) + 1);
                    } else if (line[0].equals("shape")) {
                        String symbolName = line[1];

                        int x = Integer.parseInt(line[2]);
                        int y = Integer.parseInt(line[3]);
                        String symbolValue = line[4];
                        int symbolIndex = Integer.parseInt(line[5]);
                        createSymbol(symbolName, x, y, symbolValue,
                                symbolIndex);
                        rightSpace.repaint();

                    } else {
                        int spaceIndex = Integer.parseInt(line[1]);
                        int shapeStartIndex = Integer.parseInt(line[2]);
                        int shapeEndIndex = Integer.parseInt(line[3]);
                        createLine(spaceIndex, shapeStartIndex, shapeEndIndex);
                    }
                    lines = reader.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            fileName.setText("Cancelled!!");
    }

    /**
     * Creates a symbol and sets its value
     *
     * @param name      - type of the symbol
     * @param x         - x coordinate of symbol
     * @param y         - x coordinate of symbol
     * @param userInput - value of the symbol
     * @param tabIndex  - work space index
     */
    private void createSymbol(String name, int x, int y, String userInput,
            int tabIndex) {
        JTabbedPane rightSpace = RightPanel.getInstance().getRightPanel();
        RightSpace workSpace = (RightSpace) rightSpace.getComponent(tabIndex);
        Symbol symbol = SymbolFactory.createSymbol(workSpace, name, x, y);
        symbol.setUserInput(userInput);
    }

    /**
     * Creates a connection/edge between an output and an input.
     */
    private void createLine(int workSpaceIndex, int startSymbolIndex,
            int endSymbolIndex) {
        RightSpace panel = (RightSpace) RightPanel.getInstance().getRightPanel()
                .getComponent(workSpaceIndex);
        Symbol startSymbol = (Symbol) panel.getComponent(startSymbolIndex);
        Symbol endSymbol = (Symbol) panel.getComponent(endSymbolIndex);
        ConnectionCollection.getInstance().addConnection(panel, startSymbol,
                endSymbol);
    }
}
