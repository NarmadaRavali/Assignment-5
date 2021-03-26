package controller;

import model.Symbol;
import view.RightSpace;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ravikanth
 * @since 03-14-2021
 */
public class ConnectionCollection {

    private static ConnectionCollection dataObj;
    private Map<RightSpace, ConnectionGraph> graphMap;

    public static ConnectionCollection getInstance() {

        if (dataObj == null) {
            dataObj = new ConnectionCollection();
            dataObj.graphMap = new HashMap<>();
        }
        return dataObj;
    }

    /**
     * When a saved file is loaded, this method is called to clear the existing
     * graph map
     */
    public void initialize() {
        graphMap.clear();
    }

    /**
     * Adds a new edge between two SymbolIos in a graph associated with the
     * working space.
     */
    public void addConnection(RightSpace w, Symbol output, Symbol input) {

        if (graphMap.containsKey(w)) {
            graphMap.get(w).addEdge(output, input);
        } else {
            ConnectionGraph connectionGraph = new ConnectionGraph();
            connectionGraph.addEdge(output, input);
            graphMap.put(w, connectionGraph);
        }
        w.repaint();
    }

    /**
     * Removes an existing edge between two Symbols in a graph associated with
     * the working space.
     */
    public void removeConnection(Symbol c) {
        RightSpace workPanel = (RightSpace) c.getParent();
        if (graphMap.containsKey(workPanel))
            graphMap.get(workPanel).removeEdge(c);
    }

    public ConnectionGraph getGraph(RightSpace w) {
        return graphMap.get(w);
    }

    public Map<RightSpace, ConnectionGraph> getGraphMap() {
        return graphMap;
    }

    public void setGraphMap(Map<RightSpace, ConnectionGraph> graphMap) {
        this.graphMap = graphMap;
    }

}
