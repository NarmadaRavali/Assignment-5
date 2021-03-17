package controller;

import model.Symbol;
import model.SymbolIO;
import view.RightPanel;
import view.RightSpace;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Narmada Ravali
 * @since 03-14-2021
 * @Description:
 */
public class Compile {
    private int V;
    private ArrayList<LinkedList<Integer>> adj;
    
    public String compileWorkSpace() {
        
        JTabbedPane rightPannelTab = RightSpace.getInstance()
                .getRightPanel();
        
        System.out.println("open parn ");
        for (Component i : rightPannelTab.getComponents()) {
            RightPanel tab = (RightPanel) i;
            String tabName = tab.getName();
            
            System.out.println("open parn "+tab.isOpenParen());
            System.out.println("open parn "+tab.isCloseParen());
            
            if (!tab.isOpenParen()) {
                return "Open Paranthesis missing on "+tabName;
            }
            if (!tab.isCloseParen()) {
                return "Close Paranthesis missing on  "+tabName;
            }
            
            String msg = checkAllConnections(tab);
            if (!msg.equals("Compiled Successfully")) {
                return msg+ " on "+tabName;
            }
            
            
            String msgFromTab = checkTab(tab);
            if (!msgFromTab.equals("Compiled Successfully")) {
                return msgFromTab+ " on "+ tabName;
            }

        }
        return "Compiled Successfully";
    }
    
    public String checkAllConnections(RightPanel tab) {
        int noOfSymbols = tab.getComponents().length;    

        for(int symbolIndex = 0; symbolIndex < noOfSymbols; symbolIndex++) {
            Symbol symbol = (Symbol) tab.getComponent(symbolIndex);
            
            int noOfConnectors = symbol.getComponents().length;
            int connectorIndex;
            for(connectorIndex = 0; connectorIndex < noOfConnectors; connectorIndex++) {
                SymbolIO connector = (SymbolIO) symbol.getComponent(connectorIndex);
                if (!connector.getConnected()) {
                    return "Connector # " + connectorIndex +" of symbol # "+symbolIndex+" Not connected";
                }
            }
        }
        return "Compiled Successfully";
    }
    

    public String checkTab(RightPanel tab) {
        Component[] symbols = tab.getComponents();
        int noOfSymbols = tab.getComponents().length;
        SymbolGraph graph = new SymbolGraph(noOfSymbols);
        Map<SymbolIO, ArrayList<SymbolIO>> edges =
                ConnectionCollection.getInstance().getGraph(tab).getEdges();

        edges.forEach((key, value) -> {
            int fromSymbolIndex = getSymbolId(symbols,
                    key.getParent());
            int size = value.size();
            for (SymbolIO symbolIO : value) {
                int toSymbolIndex = getSymbolId(symbols,
                        symbolIO.getParent());
                graph.addEdge(fromSymbolIndex, toSymbolIndex);
            }
        });

        ArrayList<Integer> AtTheRateVertices = getNamedSymbolVertices(symbols,
                "@");
        for(Integer v : AtTheRateVertices) {
            if (!graph.checkForLoops(v)) {
                return "Compilation Failed: \n Loop not present at '@' symbol";
            }
        }

        if (!graph.checkForIslands(getNamedSymbolVertices(symbols, "(").get(0))) {
            return "Compilation Failed: \nDisconnected symbols present in " +
                    "the " +
                    "program";
        }

        return "Compiled Successfully";

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
    
    private ArrayList<Integer> getNamedSymbolVertices(Component[] symbols,
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
