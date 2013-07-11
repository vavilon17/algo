package com.stamford.common

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

    static void printArray(int[] array) {
        array.each {print it + ", "}
    }
}
