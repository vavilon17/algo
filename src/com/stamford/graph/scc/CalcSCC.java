package com.stamford.graph.scc;

import com.stamford.common.AlgoUtils;
import com.stamford.common.datastruc.GraphAdjacencyLists;
import com.stamford.common.datastruc.GraphSCC;

import java.util.*;

/**
 * User: vit
 * Date: 30.07.13
 */
public class CalcSCC {

    class FinishingTime {

        FinishingTime(Integer originalVertex, int finishingTime) {
            this.originalVertex = originalVertex;
            this.finishingTime = finishingTime;
        }

        Integer originalVertex;
        int finishingTime;
    }

    Integer t = 0;
    Integer s = null;

    public static final void main(String[] args) {
        String fileName = "resources/stamford/ass4/SCC.txt";
        GraphSCC graph = AlgoUtils.readGraphForSCC(fileName);
        CalcSCC calcSCCProgram = new CalcSCC();
        Set<Integer> res = calcSCCProgram.calcSCC(graph, null);
        for (int i : res) {
            System.out.print(i + ", ");
        }
    }

    public Set calcSCC(GraphSCC graph, Set<FinishingTime> finTimes) {
        t = 0;
        s = null;
        // first DFS Loop - compute finishing times
        if (finTimes == null) {
            finTimes = new TreeSet<FinishingTime>(new Comparator<FinishingTime>() {
                @Override
                public int compare(FinishingTime o1, FinishingTime o2) {
                    return Integer.valueOf(o2.finishingTime).compareTo(o1.finishingTime);
                }
            });
        }
        boolean[] exploration = new boolean[graph.getEdges().size()];
        for (int i = exploration.length; i >=1; i--) {
            if (!exploration[i-1]) {
                s = i;
                dfsLoopForSCC(graph, i, exploration, finTimes);
            }
        }

        //second DFS loop
        exploration = new boolean[graph.getEdges().size()];
        GraphAdjacencyLists reversedGraph = graph.getReversedGraph();
        Set<Integer> sccSizes = new TreeSet<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 <= o1 ? -1 : 1;
            }
        });
        Iterator<FinishingTime> it = finTimes.iterator();
        while(it.hasNext()) {
            t = 0;
            FinishingTime nextVertex = it.next();
            if (!exploration[nextVertex.originalVertex -1]) {
                dfsLoop(reversedGraph, nextVertex.originalVertex, exploration);
                sccSizes.add(t);
            }
        }
        return sccSizes;
    }

    public void dfsLoopForSCC(GraphAdjacencyLists graph, Integer vertex, boolean[] exploration, Set<FinishingTime> finTimes) {
        exploration[vertex - 1] = true;
        for (Integer edgeVertex : graph.getEdges().get(vertex)) {
            if (!exploration[edgeVertex - 1]) {
                dfsLoopForSCC(graph, edgeVertex, exploration, finTimes);
            }
        }
        t++;
        finTimes.add(new FinishingTime(vertex, t));
    }

    public void dfsLoop(GraphAdjacencyLists graph, Integer vertex, boolean[] exploredFlags) {
        exploredFlags[vertex - 1] = true;
        for (Integer edgeVertex : graph.getEdges().get(vertex)) {
            if (exploredFlags[edgeVertex - 1] == false) {
                dfsLoop(graph, edgeVertex, exploredFlags);
            }
        }
        t++;
    }
}
