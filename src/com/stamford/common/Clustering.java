package com.stamford.common;

import com.prinston.common.algs4.WeightedQuickUnionUF;
import com.stamford.mst.EdgeMST;
import com.stamford.mst.GraphMST;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 19.09.13
 */
public class Clustering {
    public Clustering(GraphMST graph) {
        this.graph = graph;
        union = new WeightedQuickUnionUF(graph.getVerticesCount());
    }

    public int startClustering() {
        List<EdgeMST> queue = new LinkedList<EdgeMST>();
        for (EdgeMST edge : graph.getEdgesOrdered()) {
            ((LinkedList<EdgeMST>) queue).add(edge);
        }

        EdgeMST currentEdge;
        while (true) {
            currentEdge = ((LinkedList<EdgeMST>) queue).pollFirst();
            union.union(currentEdge.getHead() - 1, currentEdge.getTail() - 1);
            if (union.count() == 4) break;
        }
/*
        while (!((LinkedList<EdgeMST>) queue).isEmpty()) {
            currentEdge = ((LinkedList<EdgeMST>) queue).pollFirst();
            if (!union.connected(currentEdge.getHead() - 1, currentEdge.getTail() - 1).asBoolean()) {
                return currentEdge.getLength();
            }

        }*/
        return -1;
    }

    public static GraphMST readGraph(String fileName) {
        final GraphMST graph = new GraphMST();
        /*final Reference<Boolean> first = new groovy.lang.Reference<boolean>(true);
        final Reference<String[]> temp;
        final Reference<EdgeMST> edgeTemp;
        new File(fileName).invokeMethod("eachLine", new Object[]{new Closure(this, this) {
            public Object doCall(Object line) {
                if (!first.get().asBoolean()) {
                    temp.set(line.invokeMethod("split", new Object[]{" "}));
                    edgeTemp.set(new EdgeMST(Integer.parseInt(temp.get()[0]), Integer.parseInt(temp.get()[1]), Integer.parseInt(temp.get()[2])));
                    return graph.getEdgesOrdered().add(edgeTemp.get());
                } else {
                    graph.setVerticesCount(Integer.parseInt(line.invokeMethod("trim", new Object[0])));
                    return setGroovyRef(first, false);
                }

            }

        }});*/
        return graph;
    }

    public static void main(String[] args) {
        String path = "resources\\stamford\\ass22\\clustering1.txt";
        //String path = "resources\\stamford\\test\\ass22\\ex3"
        GraphMST g = readGraph(path);
//        invokeMethod("println", new Object[]{"Result = " + new Clustering(g).startClustering()});
    }

    public WeightedQuickUnionUF getUnion() {
        return union;
    }

    public void setUnion(WeightedQuickUnionUF union) {
        this.union = union;
    }

    public GraphMST getGraph() {
        return graph;
    }

    public void setGraph(GraphMST graph) {
        this.graph = graph;
    }

    private WeightedQuickUnionUF union;
    private GraphMST graph;
}
