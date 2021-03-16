package controller;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JTabbedPane;

import model.Symbol;
import model.SymbolIO;
import view.RightPanel;
import view.RightSpace;

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
//        Map<RightPanel, Graph> tabsGraph = ConnectionCollection.getInstance()
//                .getGraphMap();
        
        JTabbedPane rightPannelTab = RightSpace.getInstance()
                .getRightPanel();
        
        System.out.println("open parn ");
        for (Component i : rightPannelTab.getComponents()) {
            RightPanel tab = (RightPanel) i;
            //Graph graph = tabsGraph.get(tab);
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
    

    public String checkTab(RightPanel tab) {
        Component[] symbols = tab.getComponents();
        int noOfSymbols = tab.getComponents().length;   
        //Graph graph = new Graph(noOfSymbols);
        Graph graph = ConnectionCollection.getInstance().getGraphMap().get(tab);
        
        
        
        
        
//        for (Map.Entry line : tabLines.entrySet()) {
//            
//            int fromSymbolIndex = getSymbolId(symbols, ((SymbolIO) line.getKey()).getParent());
//            
//            ArrayList<SymbolIO> connectorList = (ArrayList<SymbolIO>) line.getValue(); 
//            int size = connectorList.size();
//            for(int index = 0; index < size ; index++) {
//                int toSymbolIndex = getSymbolId(symbols, connectorList.get(index).getParent());
//                graph.addEdge(fromSymbolIndex, toSymbolIndex);
//            }           
//        }
        
//        int openParaVertex = getOpenParaVertex(symbols);
//        if (openParaVertex == -1) {
//            return "Something Went Wrong....";
//        }
//        
        String res  =  graph.checkLoop();
            if(res.equals("@")) {
                return "Compile Failed : \n Loop not present at  @";
            } 
            else if (res.equals("D"))
                return "Compile Failed : \n Disconnected graph";
        
//        if (!graph.checkConnection(openParaVertex)) {
//            return "Compile Failed : \nDisconnected circuit present";
//        }   
        
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
