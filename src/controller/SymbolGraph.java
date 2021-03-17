package controller;

import java.util.ArrayList;
import java.util.LinkedList;

public class SymbolGraph {
    private int size;
    private ArrayList<LinkedList<Integer>> adjacentList;

    public SymbolGraph(Integer size){
        this.size = size;
        adjacentList = new ArrayList<>();
        for (int i = 0; i< size; ++i) {
            adjacentList.add(new LinkedList<>());
        }
    }

    public void addEdge(int v,int w){
        adjacentList.get(v).add(w);
    }

    public boolean checkForIslands(int s){
        boolean[] visited = new boolean[size];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            for (int n : adjacentList.get(s)) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }

        for(boolean flag: visited) {
            if (!flag) {
                return false;
            }
        }

        return true;
    }

    public boolean checkForLoops(Integer s) {
        boolean[] visited = new boolean[size];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0) {
            Integer t = queue.poll();
            for (int n : adjacentList.get(t)) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
                if (n == s) {
                    return true;
                }
            }
        }

        return false;
    }
}
