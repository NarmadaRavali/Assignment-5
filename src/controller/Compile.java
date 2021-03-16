package controller;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import model.Symbol;
import model.SymbolIO;
import view.RightPanel;

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
        Map<RightPanel, Graph> tabsGraph = ConnectionCollection.getInstance()
                .getGraphMap();
        for (RightPanel tab : tabsGraph.keySet()) {
            Graph graph = tabsGraph.get(tab);
            String tabName = tab.getName();
            
            if (!tab.isOpenP()) {
                return "Open Paranthesis missing on "+tabName;
            }
            if (!tab.isCloseP()) {
                return "Close Paranthesis missing on  "+tabName;
            }
            
            String msg = checkAllConnections(tab, graph);
            if (!msg.equals("Compiled Successfully")) {
                return msg+ " on "+tabName;
            }
            
            
            String msgFromTab = checkTab(tab, graph);
            if (!msgFromTab.equals("Compiled Successfully")) {
                return msgFromTab+ " on "+ tabName;
            }

        }
        return "Compiled Successfully";
    }
    
    public String checkAllConnections(RightPanel tab, Graph graph) {
        int noOfSymbols = tab.getComponents().length;    
        
       
        int symbolIndex = 0;
        int lessSymbolCount = 0;
        int greatSymbolCount = 0;
        for(symbolIndex = 0; symbolIndex < noOfSymbols; symbolIndex++) {
            Symbol symbol = (Symbol) tab.getComponent(symbolIndex);
            
            if (symbol.getText().equals("<")) {
                lessSymbolCount++;
            }
            if (symbol.getText().equals(">")) {
                greatSymbolCount++;
            }
            
            int noOfConnectors = symbol.getComponents().length;
            int connectorIndex;
            for(connectorIndex = 0; connectorIndex < noOfConnectors; connectorIndex++) {
                SymbolIO connector = (SymbolIO) symbol.getComponent(connectorIndex);
                if (!connector.getConnected()) {
                    return "Connector # " + connectorIndex +" of symbol # "+symbolIndex+" Not connected";
                }
            }
        }
        if (lessSymbolCount != greatSymbolCount) {
            return "# of open and close not equal";
        }
        return "Compiled Successfully";
    }
    

    public String checkTab(RightPanel tab, Graph graph) {
        Component[] symbols = tab.getComponents();
        int noOfSymbols = tab.getComponents().length;
        V = noOfSymbols;
        Map<SymbolIO, ArrayList<SymbolIO>> tabLines = graph.getEdges();
        Graph tempGraph = new Graph();
        

        for (Map.Entry line : tabLines.entrySet()) {

            int fromSymbolIndex = getSymbolId(symbols,
                    ((SymbolIO) line.getKey()).getParent());

            ArrayList<SymbolIO> connectorList = (ArrayList<SymbolIO>) line
                    .getValue();
            int size = connectorList.size();
            for (int index = 0; index < size; index++) {
                int toSymbolIndex = getSymbolId(symbols,
                        connectorList.get(index).getParent());
                tempGraph.addEdge((SymbolIO) line.getKey(), connectorList.get(index));
            }
        }

        ArrayList<Integer> atSymbolVertex = getAtSymbolVertex(symbols);
        for (Integer at : atSymbolVertex) {
            if (!tempGraph.checkLoop(at)) {
                return "Compile Failed : \n Loop not present at  @";
            }
        }
        
        int openParaVertex = getOpenParaVertex(symbols);
        if (openParaVertex == -1) {
            return "Something Went Wrong....";
        }
        
        if (!tempGraph.checkConnection(openParaVertex)) {
            return "Compile Failed : \nDisconnected circuit present";
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
    
    private int getOpenParaVertex(Component[] symbols) {
        int size = symbols.length;
        for(int index = 0; index < size ; index++) {
            if (((Symbol) (symbols[index])).getText().equals("(")) {
                return index;
            }
        }
        return -1;
    }
    
    private ArrayList<Integer> getAtSymbolVertex(Component[] symbols) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int size = symbols.length;
        for(int index = 0; index < size ; index++) {
            if (((Symbol) (symbols[index])).getText().equals("@")) {
                list.add(index);
            }
        }
        return list;
    }
    
}
