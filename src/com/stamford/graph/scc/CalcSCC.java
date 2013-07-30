package com.stamford.graph.scc;

import com.stamford.common.datastruc.GraphAdjacencyLists;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

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

    }

    public Set<FinishingTime> calcSCC(GraphAdjacencyLists graph, Set<FinishingTime> finTimes) {
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
        /*Iterator<FinishingTime> it = finTimes.iterator();
        int count = 0;
        while(it.hasNext()) {
            //dfsLoop();
        }               */
        return finTimes;
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
    }
}
