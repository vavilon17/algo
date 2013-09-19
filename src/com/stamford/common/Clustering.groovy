package com.stamford.common

import com.prinston.common.algs4.WeightedQuickUnionUF
import com.stamford.mst.EdgeMST
import com.stamford.mst.GraphMST

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 19.09.13
 */
class Clustering {

    WeightedQuickUnionUF union;
    GraphMST graph

    Clustering(GraphMST graph) {
        this.graph = graph
        union = new WeightedQuickUnionUF(graph.verticesCount)
    }

    public int startClustering(){
        List<EdgeMST> queue = new LinkedList<EdgeMST>();
        for (EdgeMST edge : graph.edgesOrdered) {
            queue.add(edge);
        }
        EdgeMST currentEdge;
        while (true) {
            currentEdge = queue.pollFirst()
            union.union(currentEdge.head - 1, currentEdge.tail - 1)
            if (union.count() == 4) break;
        }
        while (!queue.isEmpty()) {
            currentEdge = queue.pollFirst()
            if (!union.connected(currentEdge.head - 1, currentEdge.tail - 1)) {
                return currentEdge.length
            }
        }
    }

    public static GraphMST readGraph(String fileName) {
        GraphMST graph = new GraphMST()
        boolean first = true
        String[] temp
        EdgeMST edgeTemp
        new File(fileName).eachLine { line ->
            if (!first) {
                temp = line.split(" ")
                edgeTemp = new EdgeMST(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]))
                graph.edgesOrdered.add(edgeTemp)
            } else {
                graph.setVerticesCount(Integer.parseInt(line.trim()))
                first = false
            }
        }
        graph
    }

    public static void main(String[] args) {
        String path = "resources\\stamford\\ass22\\clustering1.txt"
        //String path = "resources\\stamford\\test\\ass22\\ex3"
        GraphMST g = readGraph(path)
        println "Result = " + new Clustering(g).startClustering()
    }
}
