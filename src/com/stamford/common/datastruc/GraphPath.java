package com.stamford.common.datastruc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 11.08.13
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
public class GraphPath {

    List<Edge> edges = new ArrayList<Edge>();
    int verticesCount = 0;

    public int getEdgesCount() {
        return edges.size();
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getVerticesCount() {
        return verticesCount;
    }

    public void setVerticesCount(int verticesCount) {
        this.verticesCount = verticesCount;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(int tail, int head, int length) {
        this.edges.add(new Edge(tail, head, length));
    }

    public List<Edge> getOutcoming(int tail) {
        List<Edge> res = new ArrayList<Edge>();
        for (Edge edge : edges) {
            if (tail == edge.getTail()) {
                res.add(edge);
            }
        }
        return res;
    }

    public List<Edge> getIncoming(int head) {
        List<Edge> res = new ArrayList<Edge>();
        for (Edge edge : edges) {
            if (head == edge.getHead()) {
                res.add(edge);
            }
        }
        return res;
    }

}

