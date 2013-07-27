package com.stamford.common

import com.stamford.common.datastruc.GraphAdjacencyLists

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

    /*static List<String> transformLineWithSpaces(String spacedLine) {
        return spacedLine.split("\\s+").toList();
    }*/

    static int[] transformLineWithSpaces(String spacedLine) {
        /*String[] strArr = spacedLine.split("\\s+").toList()
        int[] intArr = new int[strArr.length]
        strArr.each {
            intArr
        }*/
        spacedLine.split("\\s+").collect {
            it.toInteger()
        }
    }
}
