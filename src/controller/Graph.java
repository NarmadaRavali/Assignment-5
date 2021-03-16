package controller;

import model.AtTheRateSymbol;
import model.Dot;
import model.OpenParanthesisSymbol;
import model.SymbolIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Ravikanth
 * @since 03-14-2021
 * @Description:
 */
public class Graph {
    private final Map<SymbolIO, ArrayList<SymbolIO>> edges;
    private int Vertices;

    public Graph() {
        edges = new HashMap<>();
        Vertices = edges.size();
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
        Iterator<SymbolIO> outputs = edges.keySet().iterator();
        while (outputs.hasNext()) {
            SymbolIO output = outputs.next();
            if (edges.get(output) != null) {
                Iterator<SymbolIO> inputs = edges.get(output).iterator();
                while (inputs.hasNext()) {
                    SymbolIO input = inputs.next();
                    if (node == input) {
                        inputs.remove();
                        input.setConnected(false);
                    }
                    if (edges.get(output).isEmpty()) {
                        outputs.remove();
                        output.setConnected(false);
                    }
                }
            }

        }
    }

    public Map<SymbolIO, ArrayList<SymbolIO>> getEdges(){
        return edges;
    }

    public String checkLoop() {
        boolean atLoop  = false, disconnected = false;
        Iterator<SymbolIO> outputs = edges.keySet().iterator();
        while (outputs.hasNext()) {
            SymbolIO output = outputs.next();
            
            
        Map<SymbolIO, Boolean> visited = new HashMap();
        LinkedList<SymbolIO> queue = new LinkedList<SymbolIO>();
        visited.put(output, true); 
        queue.add(output); 

        while (queue.size() != 0) {             
            SymbolIO t = queue.poll();           
            Iterator<SymbolIO> i = edges.get(t).iterator();
            while (i.hasNext()) 
            { 
                SymbolIO n = i.next(); 
                if (!visited.getOrDefault(n, false)) 
                { 
                    visited.put(n, true);  
                    queue.add(n); 
                } 
                if (n.getParent() instanceof AtTheRateSymbol) {
                          atLoop = true;
                }
            } 
        }       
        }
        while (outputs.hasNext()) {
            SymbolIO output = outputs.next();
            if (output.getParent() instanceof OpenParanthesisSymbol) {
                Map<SymbolIO, Boolean> visited = new HashMap();
                LinkedList<SymbolIO> queue = new LinkedList<SymbolIO>();
                visited.put(output, true); 
                queue.add(output); 

                while (queue.size() != 0) {             
                    SymbolIO t = queue.poll();           
                    Iterator<SymbolIO> i = edges.get(t).iterator();
                    while (i.hasNext()) 
                    { 
                        SymbolIO n = i.next(); 
                        if (!visited.get(n)) 
                        { 
                            visited.put(n, true);  
                            queue.add(n); 
                        } 
                       
                    } 
                }
                for(SymbolIO s: edges.keySet()) {
                    if (!visited.getOrDefault(s, false))
                        disconnected = true;
                }
                
            }
        }
        if (!atLoop)
            return "@";
        else if (!disconnected)
            return "D";
        
        return "OK";
    }
    
    public boolean checkConnection(int s){ 
        boolean visited[] = new boolean[Vertices];
        LinkedList<Integer> queue = new LinkedList<Integer>();       
        visited[s]=true; 
        queue.add(s); 

//        while (queue.size() != 0) {             
//            s = queue.poll();           
//            Iterator<Integer> i = adj.get(s).listIterator(); 
//            while (i.hasNext()) 
//            { 
//                int n = i.next(); 
//                if (!visited[n]) 
//                { 
//                    visited[n] = true; 
//                    queue.add(n); 
//                } 
//            } 
//        }       
        
        for(boolean flag: visited) {
            if (!flag) {
                return false;
            }
        }
        
        return true;
    } 
    

}
