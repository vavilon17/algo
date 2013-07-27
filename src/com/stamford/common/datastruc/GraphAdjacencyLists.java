package com.stamford.common.datastruc;

import org.codehaus.groovy.runtime.dgmimpl.arrays.IntegerArrayGetAtMetaMethod;

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

    public GraphAdjacencyLists copyGraph() {
        GraphAdjacencyLists copyGraph = new GraphAdjacencyLists();
        copyGraph.setEdges(new HashMap<Integer, List<Integer>>());
        for (Integer origKey : edges.keySet()) {
            copyGraph.getEdges().put(new Integer(origKey), new ArrayList<Integer>(edges.get(origKey)));
        }
        return copyGraph;
    }

    public Map<Integer, List<Integer>> getEdges() {
        return edges;
    }

    public void setEdges(Map<Integer, List<Integer>> edges) {
        this.edges = edges;
    }

}
