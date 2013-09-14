package com.stamford.common

import com.stamford.common.datastruc.GraphAdjacencyLists
import com.stamford.common.datastruc.GraphPath
import com.stamford.common.datastruc.GraphSCC
import com.stamford.mst.EdgeMST
import com.stamford.mst.GraphMST
import com.stamford.mst.PrimsMST
import com.stamford.scheduling.Job
import com.stamford.scheduling.JobMultiplication
import com.stamford.scheduling.JobSubstract

/**
 * Created by YaskalVV on 11.07.13.
 */
class AlgoUtils {

    static int[] readIntArray(String fileName) {
        def arr = []
        new File(fileName).eachLine {
            arr << Integer.parseInt(it)
        }
        listToIntArray(arr)
    }

    static long[] readLongArray(String fileName) {
        def arr = []
        new File(fileName).eachLine {
            arr << Long.parseLong(it)
        }
        listToLongArray(arr)
    }

    static long[] readUniqueLongArray(String fileName) {
        def arr = []
        long val
        HashSet<Long> used = new HashSet<Long>()
        new File(fileName).eachLine {
            val = Long.parseLong(it)
            if (!used.contains(val)) {
                arr << Long.parseLong(it)
                used.add(val)
            }
        }
        listToLongArray(arr)
    }

    static listToIntArray(def inputList) {
        int[] intArr = new int[inputList.size]
        for (int i=0; i<inputList.size; i++) {
            intArr[i] = inputList.get(i)
        }
        intArr
    }

    static listToLongArray(def inputList) {
        long[] longArr = new long[inputList.size]
        for (int i=0; i<inputList.size; i++) {
            longArr[i] = inputList.get(i)
        }
        longArr
    }

    static int getIthElement(int i, int... values) {
        Arrays.sort(values);
        return values[i-1];
    }

    static void printArray(int[] array) {
        array.each {print it + ", "}
    }

    static GraphAdjacencyLists readGraphAdjList(String fileName) {
        GraphAdjacencyLists graph = new GraphAdjacencyLists()
        graph.edges = new HashMap<Integer, List<Integer>>();
        List<Integer> edges = null;
        new File(fileName).eachLine { line ->
            int[] lineNumbers = AlgoUtils.transformLineWithSpaces(line)
            Integer baseVertex = lineNumbers[0]
            edges = new ArrayList<Integer>(lineNumbers.length - 1)
            graph.getEdges().put(baseVertex, edges)
            lineNumbers.eachWithIndex { lnItem, index ->
                if (index > 0) {
                    edges.add(lnItem)
                }
            }

        }
        graph
    }

    static GraphAdjacencyLists readGraphLineByEdge(String fileName) {
        GraphAdjacencyLists graph = new GraphAdjacencyLists()
        graph.edges = new HashMap<Integer, List<Integer>>();
        new File(fileName).eachLine { line ->
            int[] lineNumbers = AlgoUtils.transformLineWithSpaces(line)
            Integer baseVertex = lineNumbers[0]
            if (!graph.edges.containsKey(baseVertex)) {
                graph.edges.put(baseVertex, new ArrayList<Integer>())
            }
            graph.edges.get(baseVertex).add(lineNumbers[1])
            if (!graph.edges.containsKey(lineNumbers[1])) {
                graph.edges.put(lineNumbers[1], new ArrayList<Integer>())
            }
        }
        graph
    }

    static GraphSCC readGraphForSCC(String fileName) {
        GraphSCC graph = new GraphSCC()
        graph.edges = new HashMap<Integer, List<Integer>>()
        graph.reversedGraph.edges = new HashMap<Integer, List<Integer>>()
        Integer baseVertex, edgeVertex
        new File(fileName).eachLine { line ->
            int[] lineNumbers = AlgoUtils.transformLineWithSpaces(line)
            baseVertex = lineNumbers[0]
            edgeVertex = lineNumbers[1]
            //file base graph
            if (!graph.edges.containsKey(baseVertex)) {
                graph.edges.put(baseVertex, new ArrayList<Integer>())
            }
            graph.edges.get(baseVertex).add(lineNumbers[1])
            if (!graph.edges.containsKey(lineNumbers[1])) {
                graph.edges.put(lineNumbers[1], new ArrayList<Integer>())
            }

            //fill reversed graph
            if (!graph.reversedGraph.edges.containsKey(edgeVertex)) {
                graph.reversedGraph.edges.put(edgeVertex, new ArrayList<Integer>())
            }
            graph.reversedGraph.edges.get(edgeVertex).add(baseVertex)
            if (!graph.reversedGraph.edges.containsKey(baseVertex)) {
                graph.reversedGraph.edges.put(baseVertex, new ArrayList<Integer>())
            }
        }
        graph.setCountVertices(graph.edges.size())
        graph.reversedGraph.setCountVertices(graph.reversedGraph.edges.size())
        graph
    }

    static int[] transformLineWithSpaces(String spacedLine) {
        spacedLine.split("\\s+").collect {
            it.toInteger()
        }
    }

    static GraphPath readGraphPath(String fileName) {
        GraphPath graph = new GraphPath();
        Set<Integer> vertices = new HashSet<>();
        String[] items, heads
        int currentTail
        new File(fileName).eachLine { line ->
            items = line.split("\\s+")
            currentTail = Integer.parseInt(items[0])
            vertices.add(currentTail)
            items.eachWithIndex { edgeWithLength, index ->
                if (index != 0) {
                    heads = edgeWithLength.split(",")
                    graph.addEdge(currentTail, Integer.parseInt(heads[0]), Integer.parseInt(heads[1]))
                    vertices.add(Integer.parseInt(heads[0]))
                }
            }
        }
        graph.setVerticesCount(vertices.size())
        graph
    }

    static int sumListElements(List<Integer> list) {
        int res = 0
        for (int item : list) {
            res += item;
        }
        return res
    }

   static List<JobSubstract> readSubstractionJobs(String fileName) {
       List<JobSubstract> jobs = new ArrayList<JobSubstract>();
       boolean first = true;
       String[] temp;
       int count = 1;
       new File(fileName).eachLine { line ->
           if (!first) {
               temp = line.split(" ");
               jobs.add(new JobSubstract(count++, Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
           } else {
               first = false;
           }
       }
       jobs
   }

    static List<JobMultiplication> readMultiplicationJobs(String fileName) {
        List<JobMultiplication> jobs = new ArrayList<JobMultiplication>();
        boolean first = true;
        String[] temp;
        int count = 1;
        new File(fileName).eachLine { line ->
            if (!first) {
                temp = line.split(" ");
                jobs.add(new JobMultiplication(count++, Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
            } else {
                first = false;
            }
        }
        jobs
    }

    static GraphMST fillGraphForMST(String fileName) {
        GraphMST graph = new GraphMST();
        boolean first = true;
        String[] temp;
        EdgeMST edgeTemp;
        new File(fileName).eachLine { line ->
            if (!first) {
                temp = line.split(" ");
                edgeTemp = new EdgeMST(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]))
                graph.edgesOrdered.add(edgeTemp)
            } else {
                temp = line.split(" ");
                graph.setVerticesCount(Integer.parseInt(temp[0]))
                first = false;
            }
        }
        graph
    }

}
