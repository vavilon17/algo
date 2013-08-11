package com.stamford.graph.path;

import com.stamford.common.AlgoUtils;
import com.stamford.common.datastruc.GraphPath;
import com.stamford.common.datastruc.Edge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 */
public class DijkstraShortest {

    public static void main(String[] args) {
        GraphPath graph = AlgoUtils.readGraphPath("resources/stamford/ass5/dijkstraData.txt");
        Map<Integer, Integer> res = new DijkstraShortest().startDikkstra(graph, 1);
        System.out.print(res);
    }

    public Map<Integer, Integer> startDikkstra(GraphPath graph, int sourceVertex) {
        Set<Integer> processedVertices = new HashSet<Integer>();
        Map<Integer, Integer> shortests = new HashMap<Integer, Integer>();
        processedVertices.add(sourceVertex);
        while (processedVertices.size() != graph.getVerticesCount()) {
            Set<Edge> crossingEdges = getCrossingEdges(processedVertices, graph);
            if (crossingEdges.size() == 0) {
                break;
            } else {
                Edge edge = fingEdgeWithMinCriteria(crossingEdges, shortests);
                processedVertices.add(edge.getHead());
            }
        }
        return shortests;
    }

    private Set<Edge> getCrossingEdges(Set<Integer> processed, GraphPath graph) {
        Set<Edge> res = new HashSet<Edge>();
        for (int proc : processed) {
            for (Edge edge : graph.getOutcoming(proc)) {
                if (!processed.contains(edge.getHead())) {
                    res.add(edge);
                }
            }
        }
        return res;
    }

    private Edge fingEdgeWithMinCriteria(Set<Edge> edges, Map<Integer, Integer> shortests) {
        int min = Integer.MAX_VALUE;
        int dijkstraCriterion = 0;
        Edge resEdge = null;
        for (Edge edge : edges) {
            dijkstraCriterion = shortests.containsKey(edge.getTail()) ? shortests.get(edge.getTail()) + edge.getLength()  : edge.getLength();
            if (dijkstraCriterion < min) {
                resEdge = edge;
                min = dijkstraCriterion;
            }
        }
        shortests.put(resEdge.getHead(), min);
        return resEdge;
    }
}
