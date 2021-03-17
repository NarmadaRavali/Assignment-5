package controller;

import model.Symbol;
import model.SymbolIO;
import view.RightPanel;
import view.RightSpace;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Narmada Ravali
 * @since 03-14-2021
 * @Description:
 */
public class Compile {
    
    public String compileWorkSpace() {
        
        JTabbedPane rightPanelTab = RightSpace.getInstance()
                .getRightPanel();

        for (Component component : rightPanelTab.getComponents()) {
            RightPanel tab = (RightPanel) component;
            String panelName = tab.getName();

            if (!tab.isOpenParen()) {
                return "Open Parenthesis symbol missing on " + panelName;
            }
            if (!tab.isCloseParen()) {
                return "Close Parenthesis symbol missing on "+panelName;
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
    
    public String checkIfDisconnected(RightPanel panel) {
        Component[] components = panel.getComponents();

        for(Component component:
        components) {
            Symbol symbol = (Symbol) component;
            for(Component component1:
            symbol.getComponents()) {
                SymbolIO symbolIO = (SymbolIO) component1;
                if (!symbolIO.getConnected()) {
                    return panel.getName()+":\nOne or more Input/Output of" +
                            " a '" + symbol.getText() +
                            "' is " +
                            "not connected.";
                }
            }
        }
        return "Compiled Successfully!";
    }
    

    public String checkPanel(RightPanel panel) {
        Component[] symbols = panel.getComponents();
        SymbolGraph graph = new SymbolGraph(symbols.length);
        Map<SymbolIO, ArrayList<SymbolIO>> edges =
                ConnectionCollection.getInstance().getGraph(panel).getEdges();

        edges.forEach((key, value) -> {
            for (SymbolIO symbolIO : value) {
                graph.addEdge(getSymbolId(symbols,
                        key.getParent()), getSymbolId(symbols,
                        symbolIO.getParent()));
            }
        });

        ArrayList<Integer> AtTheRateVertices = getSymbolVertices(symbols,
                "@");
        for(Integer v : AtTheRateVertices) {
            if (graph.isInValid(v, 0)) {
                return panel.getName()+":\nCompilation Failed: Loop not present for '@' symbol";
            }
        }

        if (!graph.isInValid(getSymbolVertices(symbols, "(").get(0)
        , 1)) {
            return panel.getName()+":\nCompilation Failed: Disconnected symbols present in " +
                    "the " +
                    "program";
        }

        return "Compiled Successfully!";

    }
    
    
    private int getSymbolId(Component[] symbols, Component key) {
        int size = symbols.length;
        for(int index = 0; index < size ; index++) {
            if (key.equals(symbols[index])) {
                return index;
            }
        }
        return -1;
    }
    
    private ArrayList<Integer> getSymbolVertices(Component[] symbols,
                                                 String name) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int size = symbols.length;
        for(int index = 0; index < size ; index++) {
            if (((Symbol) (symbols[index])).getText().equals(name)) {
                list.add(index);
            }
        }
        return list;
    }
    
}
