package com.stamford.other;

import com.stamford.common.AlgoUtils;
import com.stamford.common.datastruc.heap.Heap;
import com.stamford.common.datastruc.heap.MaxHeap;
import com.stamford.common.datastruc.heap.MinHeap;
import com.stamford.common.datastruc.heap.exception.HeapIsEmptyException;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 18.08.13
 */
public class MedianMaintanence {

    public static final void main(String[] args) {
        String fileName = "resources/stamford/ass6/Median.txt";
        int[] arr = AlgoUtils.readIntArray(fileName);
        try {
            System.out.print(new MedianMaintanence().calcMedians(arr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int calcMedians(int[] inputStream) throws HeapIsEmptyException{
        Heap lowHeap = new MaxHeap();
        Heap highHeap = new MinHeap();
        List<Integer> medians = new ArrayList<Integer>();
        if (inputStream[0] < inputStream[1]) {
            lowHeap.insert(inputStream[0]);
            highHeap.insert(inputStream[1]);
        } else {
            lowHeap.insert(inputStream[1]);
            highHeap.insert(inputStream[0]);
        }
        medians.add(inputStream[0]);
        medians.add(lowHeap.lookupHead());
        for (int i = 2; i < inputStream.length; i++) {
            if (inputStream[i] > lowHeap.lookupHead()) {
                highHeap.insert(inputStream[i]);
                if (highHeap.heapSize() - lowHeap.heapSize() > 1) {
                    lowHeap.insert(highHeap.extractHead());
                }
            } else {
                lowHeap.insert(inputStream[i]);
                if (lowHeap.heapSize() - highHeap.heapSize() > 1) {
                    highHeap.insert(lowHeap.extractHead());
                }
            }
            int medianIndex = (i + 1)%2 == 1 ? (i + 1)/2 + 1 : (i + 1)/2;
            medians.add(medianIndex == lowHeap.heapSize() ? lowHeap.lookupHead() : highHeap.lookupHead());
        }
        return AlgoUtils.sumListElements(medians);
    }
}
