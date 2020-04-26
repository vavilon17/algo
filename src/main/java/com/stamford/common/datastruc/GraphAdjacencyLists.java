package com.stamford.common.datastruc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: YaskalVV
 * Date: 25.07.13
 */
public class GraphAdjacencyLists {

    private Map<Integer, List<Integer>> edges;
    private int countVertices = 0;

    public GraphAdjacencyLists copyGraph() {
        GraphAdjacencyLists copyGraph = new GraphAdjacencyLists();
        copyGraph.setEdges(new HashMap<Integer, List<Integer>>());
        for (Integer origKey : edges.keySet()) {
            copyGraph.getEdges().put(Integer.valueOf(origKey), new ArrayList<Integer>(edges.get(origKey)));
        }
        return copyGraph;
    }

    public Map<Integer, List<Integer>> getEdges() {
        return edges;
    }

    public void setEdges(Map<Integer, List<Integer>> edges) {
        this.edges = edges;
    }

    public int getCountVertices() {
        return countVertices;
    }

    public void setCountVertices(int countVertices) {
        this.countVertices = countVertices;
    }
}
