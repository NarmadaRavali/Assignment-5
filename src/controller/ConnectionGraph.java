package controller;

import model.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Ravikanth
 * @since 03-14-2021
 */
public class ConnectionGraph {
    private final Map<Symbol, ArrayList<Symbol>> edges;

    public ConnectionGraph() {
        edges = new HashMap<>();
    }

    public void addEdge(Symbol node1, Symbol node2) {
        ArrayList<Symbol> a = new ArrayList<>();
        if (node1.getOutputs() > 0 && node2.getInputs() > 0) {
            if (edges.containsKey(node1) && !edges.get(node1).contains(node2)) {
                edges.get(node1).add(node2);
            } else if (!edges.containsKey(node1)) {
                a.add(node2);
                edges.put(node1, a);
            } else {
                return;
            }
            if (node1.getOutputs() != Integer.MAX_VALUE)
                node1.setOutputs(node1.getOutputs() - 1);
            if (node2.getInputs() != Integer.MAX_VALUE)
                node2.setInputs(node2.getInputs() - 1);
        }
    }

    public void removeEdge(Symbol node) {
        Iterator<Symbol> outputs = edges.keySet().iterator();
        while (outputs.hasNext()) {
            Symbol output = outputs.next();
            if (edges.get(output) != null) {
                Iterator<Symbol> inputs = edges.get(output).iterator();
                while (inputs.hasNext()) {
                    Symbol input = inputs.next();
                    if (node == input) {
                        inputs.remove();
                        if (input.getInputs() != Integer.MAX_VALUE)
                            input.setInputs(input.getInputs() + 1);

                        if (output.getOutputs() != Integer.MAX_VALUE)
                            output.setOutputs(output.getOutputs() + 1);
                    }
                    if (edges.get(output).isEmpty()) {
                        outputs.remove();
                    }
                }
            }
        }
    }

    public Map<Symbol, ArrayList<Symbol>> getEdges() {
        return edges;
    }

}
