package controller;

import model.Symbol;
import model.SymbolIO;
import view.RightSpace;
import view.RightPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Narmada Ravali
 * @since 03-15-2021
 */
public class Compile {
    /**
     * This method compiles all the workspaces in the application
     * 
     * @return - message to be shown in dialog
     */
    public String compileWorkSpace() {

        JTabbedPane rightPanelTab = RightPanel.getInstance().getRightPanel();

        for (Component component : rightPanelTab.getComponents()) {
            RightSpace tab = (RightSpace) component;
            String panelName = tab.getName();

            if (!tab.isOpenParen()) {
                return "Open Parenthesis symbol missing on " + panelName;
            }
            if (!tab.isCloseParen()) {
                return "Close Parenthesis symbol missing on " + panelName;
            }

            String msg1 = checkIfDisconnected(tab);
            String msg2 = checkPanel(tab);

            if (!msg1.equals("Compiled Successfully!")) {
                return msg1;
            }
            if (!msg2.equals("Compiled Successfully!")) {
                return msg2;
            }
        }
        return "Compiled Successfully!";
    }

    /**
     * This method checks for inputs/outputs which are not connected in a work
     * space.
     * 
     * @return - message to be shown in dialog
     */
    public String checkIfDisconnected(RightSpace panel) {
        Component[] components = panel.getComponents();

        for (Component component : components) {
            Symbol symbol = (Symbol) component;
            for (Component component1 : symbol.getComponents()) {
                SymbolIO symbolIO = (SymbolIO) component1;
                if (!symbolIO.getConnected()) {
                    return panel.getName() + ":\nOne or more Input/Output of"
                            + " a '" + symbol.getText() + "' is "
                            + "not connected.";
                }
            }
        }
        return "Compiled Successfully!";
    }

    /**
     * This method checks for valid '@' loops and for Islands/Disconnected
     * graphs in a work space
     * 
     * @return - message to be shown in the dialog
     */
    public String checkPanel(RightSpace panel) {
        Component[] symbols = panel.getComponents();
        SymbolGraph graph = new SymbolGraph(symbols.length);
        Map<Symbol, ArrayList<Symbol>> edges = ConnectionCollection
                .getInstance().getGraph(panel).getEdges();

        edges.forEach((key, value) -> {
            for (Symbol symbol : value) {
                graph.addEdge(getSymbolId(symbols, key.getParent()),
                        getSymbolId(symbols, symbol));
            }
        });

        ArrayList<Integer> AtTheRateVertices = getSymbolVertices(symbols, "@");
        for (Integer v : AtTheRateVertices) {
            if (graph.isInValid(v, 0)) {
                return panel.getName()
                        + ":\nCompilation Failed: Loop not present for '@' symbol";
            }
        }

        if (!graph.isInValid(getSymbolVertices(symbols, "(").get(0), 1)) {
            return panel.getName()
                    + ":\nCompilation Failed: Disconnected symbols present in "
                    + "the " + "program";
        }

        return "Compiled Successfully!";

    }

    /**
     * Returns the index of Symbol object in the work space
     * 
     * @param symbols - symbols present in work space
     * @param key     - required symbol
     * @return - index of the key
     */
    private int getSymbolId(Component[] symbols, Component key) {
        int size = symbols.length;
        for (int index = 0; index < size; index++) {
            if (key.equals(symbols[index])) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Returns the list of indices of a particular symbol in work space
     * 
     * @param name - name of the symbol
     * @return - list of indices
     */
    private ArrayList<Integer> getSymbolVertices(Component[] symbols,
            String name) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int size = symbols.length;
        for (int index = 0; index < size; index++) {
            if (((Symbol) (symbols[index])).getText().equals(name)) {
                list.add(index);
            }
        }
        return list;
    }

}
