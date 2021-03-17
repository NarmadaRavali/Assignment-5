package controller;


import model.SymbolIO;
import view.RightPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ravikanth
 * @since 03-14-2021
 * @Description:
 */
public class ConnectionCollection {

    private static ConnectionCollection dataObj;

    private String symbolSelected;

    private SymbolIO connectorSelected;
    private Map<RightPanel, SymbolIoGraph> graphMap;

    public static ConnectionCollection getInstance() {

        if (dataObj == null) {
            dataObj = new ConnectionCollection();
            dataObj.graphMap = new HashMap<>();
            dataObj.connectorSelected = null;
        }
        return dataObj;
    }


    public String getSymbolSelected() {
        return symbolSelected;
    }

    public void setSymbolSelected(String symbolSelected) {
        this.symbolSelected = symbolSelected;
    }

    public SymbolIoGraph getGraph(RightPanel w) {
        return graphMap.get(w);
    }

    public void addConnection(RightPanel w, SymbolIO c1, SymbolIO c2) {

        if(graphMap.containsKey(w)) {
           graphMap.get(w).addEdge(c1, c2);
        }
        else {
            SymbolIoGraph symbolIOGraph = new SymbolIoGraph();
            symbolIOGraph.addEdge(c1, c2);
            graphMap.put(w, symbolIOGraph);
        }
        w.repaint();
    }

    public SymbolIO getConnectorSelected() {
        return connectorSelected;
    }

    public void setConnectorSelected(SymbolIO connectorSelected) {
        if(this.connectorSelected != null) {
            this.connectorSelected.setBorder(UIManager.getBorder("Button.border"));
        }
        this.connectorSelected = connectorSelected;
        if(this.connectorSelected != null) {
            this.connectorSelected.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
    }

    public void removeConnection(SymbolIO c) {
        RightPanel workPanel = (RightPanel) c.getParent().getParent();
        if (graphMap.containsKey(workPanel))
            graphMap.get(workPanel).removeEdge(c);
    }
//
//    private void checkBarFalse(Connector c2) {
//        WorkPanel workPanel = (WorkPanel) c2.getParent().getParent();
//        Map<Connector, ArrayList<Connector>> map = this.graphs.get(workPanel);
//        if (map == null){
//            return;
//        }
//        Map<Connector, ArrayList<Connector>> Lines = ConnectionHolder.getInstance().getTabLines(workPanel);
//        int count = 0;
//        for(Connector c1 : Lines.keySet()) {
//            if(c1.equals(c2)) {
//                count += Lines.get(c1).size();
//            }else {
//                for(Connector c : Lines.get(c1)) {
//                    if(c.equals(c2)) {
//                        count ++;
//                    }
//                }
//            }
//
//        }
//
//        if (count <= 1) {
//            c2.setLine(false);
//        }else {
//            c2.setLine(true);
//        }
//
//    }

    public Map<RightPanel, SymbolIoGraph> getGraphMap() {
        return graphMap;
    }

    public void setGraphMap(Map<RightPanel, SymbolIoGraph> graphMap) {
        this.graphMap = graphMap;
    }

    public void initialize() {
        graphMap.clear();
    }
}
