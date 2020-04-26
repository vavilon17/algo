package com.stamford.common.datastruc.heap;

import com.stamford.common.datastruc.heap.exception.HeapIsEmptyException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 17.08.13
 */
public class MinHeap extends Heap{

    public MinHeap() {
        this.keys = new ArrayList<Integer>();
    }

    @Override
    public void heapify(int[] unsortedArray) {
        throw new NoSuchMethodError("This method is not implemented");
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
            if (parent > keys.get(elemIndex)) {
                keys.set(parentIndex, keys.get(elemIndex));
                keys.set(elemIndex, parent);
                restoreHeapPropertyUp(parentIndex);
            }
        }
    }

    private void restoreHeapPropertyDown(int elementIndex) {
        int leftChildIndex = elementIndex*2 + 1;
        int rightChildIndex = elementIndex*2 + 2;
        int lValue = leftChildIndex <= tailIndex() ? keys.get(leftChildIndex) : Integer.MAX_VALUE;
        int rValue = rightChildIndex <= tailIndex() ? keys.get(rightChildIndex) : Integer.MAX_VALUE;
        int min = Math.min(lValue, rValue);
        if (min < keys.get(elementIndex)) {
            int indexToSwap = (min == lValue) ? leftChildIndex : rightChildIndex;
            int temp = keys.get(elementIndex);
            keys.set(elementIndex, min);
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
