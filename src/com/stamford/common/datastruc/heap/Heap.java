package com.stamford.common.datastruc.heap;

import com.stamford.common.datastruc.heap.exception.HeapIsEmptyException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 17.08.13
 */
public abstract class Heap {

    List<Integer> keys;

    public abstract void heapify(int[] unsortedArray);

    public abstract void insert(int elem);

    public abstract Integer extractHead() throws HeapIsEmptyException;

    public Integer lookupHead() throws HeapIsEmptyException{
        if (keys != null && keys.size() > 0) {
            return keys.get(0);
        } else {
            throw new HeapIsEmptyException();
        }
    }

    public int heapSize() {
        return keys.size();
    }
}

