package com.stamford.common

import com.stamford.common.datastruc.GraphAdjacencyLists
import com.stamford.common.datastruc.GraphPath
import com.stamford.common.datastruc.GraphSCC

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

    static listToIntArray(def inputList) {
        int[] intArr = new int[inputList.size]
        for (int i=0; i<inputList.size; i++) {
            intArr[i] = inputList.get(i)
        }
        intArr
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
}
