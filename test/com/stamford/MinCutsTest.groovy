package com.stamford

import com.stamford.common.AlgoUtils
import com.stamford.common.datastruc.GraphAdjacencyLists
import com.stamford.graph.mincuts.MinCuts
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * User: YaskalVV
 * Date: 25.07.13
 */
@RunWith(JUnit4.class)
class MinCutsTest {

    private MinCuts minCuts = new MinCuts()

    @Test
    public void contractEdgeTest() {
        String fileName = "resources/stamford/test/ass3/contract/g1.txt"
        GraphAdjacencyLists graph = AlgoUtils.readGraphAdjList(fileName)
        minCuts.contractEdge(graph, 1, 3)
        assert graph.edges.size() == 4
        assert graph.edges[1] == [2, 4, 5, 2, 4]
        assert graph.edges[2] == [1, 1, 5]
        assert graph.edges[4] == [1, 1, 5]
        assert graph.edges[5] == [1, 2, 4]

        minCuts.contractEdge(graph, 4, 1)
        assert graph.edges.size() == 3
        assert graph.edges[2] == [4, 4, 5]
        assert graph.edges[4] == [5, 2, 5, 2]
        assert graph.edges[5] == [4, 2, 4]

        minCuts.contractEdge(graph, 5, 2)
        assert graph.edges.size() == 2
        assert graph.edges[4] == [5, 5, 5, 5]
        assert graph.edges[5] == [4, 4, 4, 4]
    }

    @Test
    public void countMinCutsTest() {
        String fileName = "resources/stamford/test/ass3/contract/g1.txt"
        GraphAdjacencyLists graph = AlgoUtils.readGraphAdjList(fileName)
        assert minCuts.countMinCuts(graph) == 3

        fileName = "resources/stamford/test/ass3/contract/g2.txt"
        graph = AlgoUtils.readGraphAdjList(fileName)
        assert minCuts.countMinCuts(graph) == 1

        fileName = "resources/stamford/test/ass3/contract/g3.txt"
        graph = AlgoUtils.readGraphAdjList(fileName)
        assert minCuts.countMinCuts(graph) == 2
    }

}
