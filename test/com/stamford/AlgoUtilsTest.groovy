package com.stamford

import com.stamford.common.AlgoUtils
import com.stamford.common.datastruc.GraphAdjacencyLists
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * User: YaskalVV
 * Date: 25.07.13
 */
@RunWith(JUnit4.class)
class AlgoUtilsTest {

    @Test
    public void transformSpacedLineTest() {
        String spacedLine = "1   3  4       5 6 7    1   44  234  0 -1   -16"
        assert AlgoUtils.transformLineWithSpaces(spacedLine).toList() == [1, 3, 4, 5, 6, 7, 1, 44, 234, 0, -1, -16]

        spacedLine = "3"
        assert AlgoUtils.transformLineWithSpaces(spacedLine).toList() == [3]

        spacedLine = "3   4         1234 -800"
        assert AlgoUtils.transformLineWithSpaces(spacedLine).toList() == [3, 4, 1234, -800]
    }

    @Test
    public void readGraphTest() {
        String fileName = "resources/stamford/test/ass3/readgraph/ex1.txt"
        GraphAdjacencyLists graph = AlgoUtils.readGraphAdjList(fileName)
        assert graph.edges.size() == 4
        assert graph.edges.get(1) == [2, 3]
        assert graph.edges.get(2) == [1, 3, 4]
        assert graph.edges.get(3) == [1, 2, 4]
        assert graph.edges.get(4) == [3, 2]

        fileName = "resources/stamford/test/ass3/readgraph/ex2.txt"
        graph = AlgoUtils.readGraphAdjList(fileName)
        assert graph.edges.size() == 10
        assert graph.edges.get(2) == [1, 3, 7, 8]
        assert graph.edges.get(5) == [10, 4, 6, 9]
        assert graph.edges.get(7) == [3, 2, 8]
        assert graph.edges.get(9) == [8, 5]
    }

    @Test
    public void readGraphLineByEdgeTest() {
        String fileName = "resources/stamford/test/ass4/readgraph/ex1"
        GraphAdjacencyLists graph = AlgoUtils.readGraphLineByEdge(fileName)
        assert graph.edges.size() == 9
        assert graph.edges.get(1) == [9]
        assert graph.edges.get(2) == [7, 6, 5]
        assert graph.edges.get(3) == [4, 1, 9]
        assert graph.edges.get(4) == [2, 5]
        assert graph.edges.get(5) == [6, 7, 1, 9]
        assert graph.edges.get(6) == [7]
        assert graph.edges.get(7) == [2, 3]
        assert graph.edges.get(8) == [4, 1]
        assert graph.edges.get(9) == [7]

        fileName = "resources/stamford/test/ass4/readgraph/ex2"
        graph = AlgoUtils.readGraphLineByEdge(fileName)
        assert graph.edges.size() == 19
        assert graph.edges.get(1) == [234, 33456]
        assert graph.edges.get(2) == [56]
        assert graph.edges.get(4) == [1278]
        assert graph.edges.get(5) == [6]
        assert graph.edges.get(6) == []
        assert graph.edges.get(7) == [18990, 12, 666]
        assert graph.edges.get(8) == [13, 69, 16]
        assert graph.edges.get(9) == [2, 6107543]
        assert graph.edges.get(12) == []
        assert graph.edges.get(13) == []
        assert graph.edges.get(16) == []
        assert graph.edges.get(56) == []
        assert graph.edges.get(69) == []
        assert graph.edges.get(234) == []
        assert graph.edges.get(666) == []
        assert graph.edges.get(1278) == []
        assert graph.edges.get(18990) == []
        assert graph.edges.get(33456) == []
        assert graph.edges.get(6107543) == []
    }
}
