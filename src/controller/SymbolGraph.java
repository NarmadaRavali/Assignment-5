package controller;

import java.util.ArrayList;
import java.util.LinkedList;

public class SymbolGraph {
    private final int size;
    private final ArrayList<LinkedList<Integer>> adjacentList;

    public SymbolGraph(Integer size){
        this.size = size;
        adjacentList = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            adjacentList.add(new LinkedList<>());
        }
    }

    public void addEdge(int vertex1,int vertex2){
        adjacentList.get(vertex1).add(vertex2);
    }

    public boolean isInValid(int startVertex, int mode){
        boolean[] visited = new boolean[size];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[startVertex]=true;
        queue.add(startVertex);

        while (queue.size() != 0) {
            int vertex = queue.poll();
            for (int neighbour : adjacentList.get(vertex)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
                if (mode == 0 && neighbour == startVertex) {
                    return false;
                }
            }
        }
        if (mode == 1) {
            for (boolean flag : visited) {
                if (!flag) {
                    return false;
                }
            }
        }

        return true;
    }


}
