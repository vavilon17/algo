package com.stamford.graph.mincuts;

import com.stamford.common.AlgoUtils;
import com.stamford.common.datastruc.GraphAdjacencyLists;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * User: YaskalVV
 * Date: 25.07.13
 */
public class MinCuts {

    public static final void main(String[] args) {

        GraphAdjacencyLists graph = AlgoUtils.readGraphAdjList("resources/stamford/ass3/kargerMinCut.txt");
        int result = new MinCuts().countMinCuts(graph);
        System.out.print(result);
    }

    public int countMinCuts(GraphAdjacencyLists graph) {
        int n = graph.getEdges().size();
        int iterationCount = n;
        int[] results = new int[iterationCount];
        GraphAdjacencyLists resultGraph;
        for (int i = 0; i < iterationCount; i++) {
            resultGraph = randomContraction(graph.copyGraph());
            results[i] = resultGraph.getEdges().get(resultGraph.getEdges().keySet().toArray()[0]).size();
            System.out.println((i + 1) + "th iteration completed");
        }
        return AlgoUtils.getIthElement(1, results);
    }

    public GraphAdjacencyLists randomContraction(GraphAdjacencyLists graph) {
        if (graph.getEdges().size() == 2) {
            return graph;
        } else {
            return randomContraction(contractEdgeUniformly(graph));
        }
    }

    public GraphAdjacencyLists contractEdgeUniformly(GraphAdjacencyLists graph) {
        int verticesCount = graph.getEdges().size();
        //get an uniform edge v-w
        Integer v = (Integer) graph.getEdges().keySet().toArray()[(int) Math.floor(Math.random()*(verticesCount))];
        List<Integer> vEdges = graph.getEdges().get(v);
        Integer w = vEdges.get((int) Math.floor(Math.random()*(vEdges.size())));
        return contractEdge(graph, v, w);
    }

    public GraphAdjacencyLists contractEdge(GraphAdjacencyLists graph, Integer v, Integer w) {
        List<Integer> vEdges = graph.getEdges().get(v);
        //contract v-w due to contraction algorithm
        List<Integer> edgeWithW;
        for (Integer wEdgeVertex : graph.getEdges().get(w)) {
            edgeWithW = graph.getEdges().get(wEdgeVertex);
            edgeWithW.set(edgeWithW.indexOf(w), v);
            vEdges.add(wEdgeVertex);
        }
        graph.getEdges().remove(w);
        Iterator<Integer> itr = vEdges.iterator();
        while (itr.hasNext()) {
            if (itr.next().equals(v)) {
                itr.remove();
            }
        }
        return graph;
    }
}
