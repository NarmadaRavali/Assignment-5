package controller;

import model.Dot;
import model.SymbolIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Ravikanth
 * @since 03-14-2021
 * @Description:
 */
public class Graph {
    private final Map<SymbolIO, ArrayList<SymbolIO>> edges;

    public Graph() {
        edges = new HashMap<>();
    }

    public void addEdge(SymbolIO node1, SymbolIO node2) {
        ArrayList<SymbolIO> a = new ArrayList<>();

        if (node1 instanceof Dot) {
            a.add(node2);
            edges.put(node1, a);
        } else {
            if (edges.containsKey(node1)) {
                edges.get(node1).add(node2);
            } else {
                a.add(node2);
                edges.put(node1, a);
            }
        }
        node1.setConnected(true);
        node2.setConnected(true);

    }

    public void removeEdge(SymbolIO node) {
        if(edges.containsKey(node)) {
            for(SymbolIO c2 : edges.get(node)) {
                if(c2 instanceof Dot) {
                    c2.setConnected(false);
                }
                else {
//                    checkBarFalse(c2);
                }
            }
            edges.remove(node);
            node.setConnected(false);
        }
        Iterator<SymbolIO> iterator = edges.keySet().iterator();
        while (iterator.hasNext()) {
            SymbolIO output = iterator.next();
            if (edges.get(output) != null) {
                Iterator<SymbolIO> iterator1 = edges.get(output).iterator();
                while (iterator1.hasNext()) {
                    SymbolIO input = iterator1.next();
                    if (node == input) {
                        iterator1.remove();
                    }
                    if (edges.get(output).isEmpty()) {
                        iterator.remove();
                        output.setConnected(false);
                    }
                }
            }

        }
    }

    public Map<SymbolIO, ArrayList<SymbolIO>> getEdges(){
        return edges;
    }


}
