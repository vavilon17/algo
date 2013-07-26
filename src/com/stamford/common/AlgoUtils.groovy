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
        new File(fileName).eachLine { line ->
            List<String> lineNumbers = AlgoUtils.transformLineWithSpaces(line)
            Integer baseVertex = lineNumbers.get(0)

            lineNumbers.eachWithIndex { lnItem, index ->
                if (index > 0) {
                    graph.getEdges().put(baseVertex)
                } else {

                }
            }

        }
        /*new File(fileName).eachLine { line ->

        }*/
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
