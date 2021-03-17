package controller;


import model.SymbolIO;
import view.RightPanel;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ravikanth
 * @since 03-14-2021
 */
public class ConnectionCollection {

    private static ConnectionCollection dataObj;
    private Map<RightPanel, SymbolIoGraph> graphMap;

    public static ConnectionCollection getInstance() {

        if (dataObj == null) {
            dataObj = new ConnectionCollection();
            dataObj.graphMap = new HashMap<>();
        }
        return dataObj;
    }

    /**
     * When a saved file is loaded, this method is called to clear the
     * existing graph map
     */
    public void initialize() {
        graphMap.clear();
    }

    /**
     * Adds a new edge between two SymbolIos in a graph associated with the
     * working
     * space.
     */
    public void addConnection(RightPanel w, SymbolIO output, SymbolIO input) {

        if(graphMap.containsKey(w)) {
           graphMap.get(w).addEdge(output, input);
        }
        else {
            SymbolIoGraph symbolIOGraph = new SymbolIoGraph();
            symbolIOGraph.addEdge(output, input);
            graphMap.put(w, symbolIOGraph);
        }
        w.repaint();
    }
    /**
     * Removes an existing edge between two SymbolIos in a graph associated
     * with the working space.
     */
    public void removeConnection(SymbolIO c) {
        RightPanel workPanel = (RightPanel) c.getParent().getParent();
        if (graphMap.containsKey(workPanel))
            graphMap.get(workPanel).removeEdge(c);
    }

    public SymbolIoGraph getGraph(RightPanel w) {
        return graphMap.get(w);
    }

    public Map<RightPanel, SymbolIoGraph> getGraphMap() {
        return graphMap;
    }

    public void setGraphMap(Map<RightPanel, SymbolIoGraph> graphMap) {
        this.graphMap = graphMap;
    }


}
