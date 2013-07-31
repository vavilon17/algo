package com.stamford

import com.stamford.common.AlgoUtils
import com.stamford.common.datastruc.GraphAdjacencyLists
import com.stamford.common.datastruc.GraphSCC
import com.stamford.graph.scc.CalcSCC
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 30.07.13
 * Time: 23:52
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4)
class CountSCCTest {

    private CalcSCC calcSCC = new CalcSCC()

    @Test
    public void dfsLoopTest() {
        String fileName = "resources/stamford/test/ass4/dfs/ex1"
        GraphAdjacencyLists graph = AlgoUtils.readGraphLineByEdge(fileName)
        assert graph.edges.size() == 7
        boolean[] exploration = new boolean[7]
        calcSCC.dfsLoop(graph, 1, exploration)
        for (boolean exp : exploration) {
            assert exp == true
        }
    }

    /*@Test
    public void calcFinishingTimes() {
        String fileName = "resources/stamford/test/ass4/dfs/ex2"
        GraphAdjacencyLists graph = AlgoUtils.readGraphLineByEdge(fileName)
        Set<CalcSCC.FinishingTime> finTimes = calcSCC.calcSCC(graph, null)
        assert finTimes.size() == graph.edges.size()
    }*/

    @Test
    public void calcSCC() {
        String fileName = "resources/stamford/test/ass4/dfs/ex2"
        GraphSCC graph = AlgoUtils.readGraphForSCC(fileName)
        Set<Integer> res = calcSCC.calcSCC(graph, null)
        assert res.toList() == [3, 3, 3]

        fileName = "resources/stamford/test/ass4/dfs/ex1"
        graph = AlgoUtils.readGraphForSCC(fileName)
        res = calcSCC.calcSCC(graph, null)
        assert res.toList() == [3, 1, 1, 1, 1]

        fileName = "resources/stamford/test/ass4/dfs/ex3"
        graph = AlgoUtils.readGraphForSCC(fileName)
        res = calcSCC.calcSCC(graph, null)
        assert res.toList() == [7]

        fileName = "resources/stamford/test/ass4/dfs/ex4"
        graph = AlgoUtils.readGraphForSCC(fileName)
        res = calcSCC.calcSCC(graph, null)
        assert res.toList() == [8, 7, 2, 2, 1, 1, 1, 1]
    }
}
