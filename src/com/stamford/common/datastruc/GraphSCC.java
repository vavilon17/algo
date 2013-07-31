package com.stamford.common.datastruc;

/**
 * User: YaskalVV
 * Date: 31.07.13
 */
public class GraphSCC extends GraphAdjacencyLists {

    private GraphAdjacencyLists reversedGraph;

    {
        reversedGraph = new GraphAdjacencyLists();
    }

    public GraphAdjacencyLists getReversedGraph() {
        return reversedGraph;
    }

    public void setReversedGraph(GraphAdjacencyLists reversedGraph) {
        this.reversedGraph = reversedGraph;
    }
}
