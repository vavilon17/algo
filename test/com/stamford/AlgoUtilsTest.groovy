package com.stamford

import com.stamford.common.AlgoUtils
import com.stamford.common.datastruc.GraphAdjacencyLists
import com.stamford.common.datastruc.GraphPath
import com.stamford.common.datastruc.GraphSCC
import com.stamford.mst.GraphMST
import com.stamford.mst.PrimsMST
import com.stamford.scheduling.Job
import com.stamford.scheduling.JobSubstract
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

    @Test
    public void readSCCGraph() {
        String fileName = "resources/stamford/test/ass4/dfs/ex2"
        GraphSCC graph = AlgoUtils.readGraphForSCC(fileName)
        //check direct graph
        assert graph.countVertices == 9
        assert graph.edges.get(1) == [7]
        assert graph.edges.get(2) == [5]
        assert graph.edges.get(3) == [9]
        assert graph.edges.get(4) == [1]
        assert graph.edges.get(5) == [8]
        assert graph.edges.get(6) == [3, 8]
        assert graph.edges.get(7) == [4, 9]
        assert graph.edges.get(8) == [2]
        assert graph.edges.get(9) == [6]

        //check reversed graph
        GraphAdjacencyLists reversedGraph = graph.reversedGraph
        assert reversedGraph.countVertices == 9
        assert reversedGraph.edges.get(1) == [4]
        assert reversedGraph.edges.get(2) == [8]
        assert reversedGraph.edges.get(3) == [6]
        assert reversedGraph.edges.get(4) == [7]
        assert reversedGraph.edges.get(5) == [2]
        assert reversedGraph.edges.get(6) == [9]
        assert reversedGraph.edges.get(7) == [1]
        assert reversedGraph.edges.get(8) == [5, 6]
        assert reversedGraph.edges.get(9) == [3, 7]
    }

    @Test
    public void readGraphPath() {
        String fileName = "resources/stamford/test/ass5/readgraph/ex1"
        GraphPath graph = AlgoUtils.readGraphPath(fileName)
        assert graph.edgesCount == 5
        assert graph.verticesCount == 5
        assert graph.getIncoming(4).get(0).length == 5
        assert graph.getIncoming(5).get(0).length == 13
        assert graph.getIncoming(2).size() == 2

        fileName = "resources/stamford/test/ass5/readgraph/ex2"
        graph = AlgoUtils.readGraphPath(fileName)
        assert graph.edgesCount == 11
        assert graph.verticesCount == 7
        graph.getOutcoming(1).size() == 1
        graph.getOutcoming(1).get(0).length == 1
        graph.getOutcoming(6).size() == 2
        graph.getOutcoming(6).get(0).length == 8
        graph.getOutcoming(6).get(1).length == 9
        graph.getIncoming(3).size() == 2
        graph.getIncoming(3).get(0).length == 2
        graph.getIncoming(3).get(1).length == 5
    }

    @Test
    public void readLongArrayTest() {
        /*String fileName = "resources/stamford/test/ass6/readarray/ex1"
        long[] arr = AlgoUtils.readLongArray(fileName)
        assert arr.length == 7
        assert arr[5] == 23555555234L*/
    }

    @Test
    public void readJobsTest() {
        String fileName = "resources/stamford/test/ass21/jobsTest"
        List<Job> jobs = AlgoUtils.readSubstractionJobs(fileName)
        assert jobs.size() == 6
        assert jobs.get(3).getWeight() == 234
        assert jobs.get(3).getLength() == 123
        assert jobs.get(0).id == 1
        assert jobs.get(jobs.size() - 1).id == jobs.size()
    }

    @Test
    public void readJobsWithPriorityTest() {
        String fileName = "resources/stamford/test/ass21/jobsTest2"
        List<Job> jobs = AlgoUtils.readSubstractionJobs(fileName)
        Job[] array = (Job[]) jobs.toArray();
        Arrays.sort(array);
        assert array.length == 8
        assert array[0].id == 4
        assert array[1].id == 7
        assert array[2].id == 3
        assert array[3].id == 8
        assert array[4].id == 5
        assert array[5].id == 1
        assert array[6].id == 6
        assert array[7].id == 2
    }

    @Test
    public void prepareMSTGraph() {
        String fileName = "resources/stamford/test/ass21/g1"
        PrimsMST program = new PrimsMST();
        assert program.run(fileName) ==  7

        fileName = "resources/stamford/test/ass21/g2"
        assert program.run(fileName) ==  37

        fileName = "resources/stamford/test/ass21/g3"
        assert program.run(fileName) ==  -27534

        fileName = "resources/stamford/test/ass21/primcase.txt"
        assert program.run(fileName) == 120971
    }
}
