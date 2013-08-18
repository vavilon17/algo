package com.stamford

import com.stamford.common.datastruc.heap.Heap
import com.stamford.common.datastruc.heap.MaxHeap
import com.stamford.common.datastruc.heap.MinHeap
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 18.08.13
 */
@RunWith(JUnit4.class)
class HeapTest {

    @Test
    public void minHeapTest() {
        Heap heap = new MinHeap()
        heap.insert(5)
        heap.insert(6)
        heap.insert(3)
        heap.insert(2)
        heap.insert(9)
        heap.insert(8)
        heap.insert(4)
        assert heap.lookupHead() == 2
        assert heap.getHeapArray() == [2, 3, 4, 6, 9, 8, 5]
        assert heap.extractHead() == 2
        assert heap.getHeapArray() == [3, 5, 4, 6, 9, 8]
        assert heap.extractHead() == 3
        assert heap.getHeapArray() == [4, 5, 8, 6, 9]
        heap.insert(11)
        heap.insert(7)
        heap.insert(1)
        assert heap.getHeapArray() == [1, 4, 7, 5, 9, 11, 8, 6]
    }

    @Test
    public void maxHeapTest() {
        Heap heap = new MaxHeap()
        heap.insert(5)
        heap.insert(6)
        heap.insert(3)
        heap.insert(2)
        heap.insert(9)
        heap.insert(8)
        heap.insert(4)
        assert heap.lookupHead() == 9
        assert heap.getHeapArray() == [9, 6, 8, 2, 5, 3, 4]
        assert heap.extractHead() == 9
        assert heap.getHeapArray() == [8, 6, 4, 2, 5, 3]
        assert heap.extractHead() == 8
        assert heap.getHeapArray() == [6, 5, 4, 2, 3]
        heap.insert(11)
        heap.insert(7)
        heap.insert(1)
        assert heap.getHeapArray() == [11, 5, 7, 2, 3, 4, 6, 1]
    }
}
