package com.stamford.common.datastruc;

import java.util.List;
import java.util.Map;

/**
 * User: YaskalVV
 * Date: 25.07.13
 */
public class GraphAdjacencyLists {

    private List<Integer> vertices;
    private Map<Integer, List<Integer>> edges;

    public int[] verticesArr;
    public int[][] edgesArr;

    public List<Integer> getVertices() {
        return vertices;
    }

    public void setVertices(List<Integer> vertices) {
        this.vertices = vertices;
    }

    public Map<Integer, List<Integer>> getEdges() {
        return edges;
    }

    public void setEdges(Map<Integer, List<Integer>> edges) {
        this.edges = edges;
    }

    public int[] getVerticesArr() {
        return verticesArr;
    }

    public void setVerticesArr(int[] verticesArr) {
        this.verticesArr = verticesArr;
    }

    public int[][] getEdgesArr() {
        return edgesArr;
    }

    public void setEdgesArr(int[][] edgesArr) {
        this.edgesArr = edgesArr;
    }
}
