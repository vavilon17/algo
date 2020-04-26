package com.stamford.mst;

import com.stamford.common.AlgoUtils;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 08.09.13
 */
public class PrimsMST {

    public long run(String fileName) {
        GraphMST graph = AlgoUtils.fillGraphForMST(fileName);
        return calcMSTCost(graph);
    }

    private long calcMSTCost(GraphMST graph) {
        long totalCost = 0L;
        Set<Integer> proceeded = new HashSet<Integer>();
        List<EdgeMST> MST = new ArrayList<EdgeMST>();
        EdgeMST nextEdge;
        int currentVertex = 1;
        proceeded.add(currentVertex);
        while (proceeded.size() < graph.getVerticesCount()) {
            nextEdge = getNextEdge(graph, proceeded);
            MST.add(nextEdge);
            if (!proceeded.contains(nextEdge.getTail())) {
                proceeded.add(nextEdge.getTail());
            } else if (!proceeded.contains(nextEdge.getHead())) {
                proceeded.add(nextEdge.getHead());
            } else {
                throw new UnknownError("WTF???????");
            }
            totalCost += (long) nextEdge.getLength();
        }
        System.out.println("edges in MST: " + MST);
        return totalCost;
    }

    private EdgeMST getNextEdge(GraphMST graph, Set<Integer> proceeded) {
        EdgeMST temp;
        Iterator<EdgeMST> it = graph.getEdgesOrdered().iterator();
        while (it.hasNext()) {
            temp = it.next();
            if (proceeded.contains(temp.getHead()) && !proceeded.contains(temp.getTail()) ||
                    proceeded.contains(temp.getTail()) && !proceeded.contains(temp.getHead())) {
                it.remove();
                return temp;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        PrimsMST program = new PrimsMST();
        System.out.println("total cost = " + program.run(args[0]));
    }
}
