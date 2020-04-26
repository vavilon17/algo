package com.stamford.common.datastruc.heap;

import com.stamford.common.datastruc.heap.exception.HeapIsEmptyException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 18.08.13
 */
public class MaxHeap extends Heap {

    public MaxHeap() {
        this.keys = new ArrayList<Integer>();
    }

    @Override
    public void heapify(int[] unsortedArray) {
        throw new NoSuchMethodError("This method is not implemented yet");
    }

    @Override
    public void insert(int elem) {
        keys.add(elem);
        restoreHeapPropertyUp(tailIndex());
    }

    @Override
    public Integer extractHead() throws HeapIsEmptyException {
        Integer head = lookupHead();
        keys.set(0, keys.get(tailIndex()));
        keys.remove(tailIndex());
        restoreHeapPropertyDown(0);
        return head;
    }

    private void restoreHeapPropertyUp(int elemIndex)  {
        if (elemIndex > 0) {
            int parentIndex = getParentIndex(elemIndex);
            int parent = keys.get(parentIndex);
            if (parent < keys.get(elemIndex)) {
                keys.set(parentIndex, keys.get(elemIndex));
                keys.set(elemIndex, parent);
                restoreHeapPropertyUp(parentIndex);
            }
        }
    }

    private void restoreHeapPropertyDown(int elementIndex) {
        int leftChildIndex = elementIndex*2 + 1;
        int rightChildIndex = elementIndex*2 + 2;
        int lValue = leftChildIndex <= tailIndex() ? keys.get(leftChildIndex) : Integer.MIN_VALUE;
        int rValue = rightChildIndex <= tailIndex() ? keys.get(rightChildIndex) : Integer.MIN_VALUE;
        int max = Math.max(lValue, rValue);
        if (max > keys.get(elementIndex)) {
            int indexToSwap = (max == lValue) ? leftChildIndex : rightChildIndex;
            int temp = keys.get(elementIndex);
            keys.set(elementIndex, max);
            keys.set(indexToSwap, temp);
            restoreHeapPropertyDown(indexToSwap);
        }
    }

    private int tailIndex() {
        return keys != null ? keys.size() - 1 : -1;
    }

    private int getParentIndex(int childIndex) {
        return childIndex%2 == 1 ? childIndex/2 : childIndex/2 - 1;
    }

    //JUSt FOR TEST
    public List<Integer> getHeapArray() {
        return keys;
    }
}
