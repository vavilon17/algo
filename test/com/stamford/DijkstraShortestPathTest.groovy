package com.stamford

import com.stamford.common.AlgoUtils
import com.stamford.common.datastruc.GraphPath
import com.stamford.graph.path.DijkstraShortest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created with IntelliJ IDEA.
 * User: vit
 */
@RunWith(JUnit4.class)
class DijkstraShortestPathTest {

    DijkstraShortest program = new DijkstraShortest()

    @Test
    public void dijkstraShortestTest() {
        String fileName = "resources/stamford/test/ass5/shortestpath/ex1"
        GraphPath graph = AlgoUtils.readGraphPath(fileName)
        def pathes = program.startDikkstra(graph, 1)
        assert pathes[2] == 1
        assert pathes[3] == 3
        assert pathes[4] == 6

        fileName = "resources/stamford/test/ass5/shortestpath/ex2"
        graph = AlgoUtils.readGraphPath(fileName)
        pathes = program.startDikkstra(graph, 1)
        assert pathes[2] == 3
        assert pathes[3] == 7
        assert pathes[4] == 5
        assert pathes[5] == 12
        assert pathes[6] == 44
        assert pathes[7] == 57
        assert pathes[8] == 79
    }
}
