package com.prinston.queues;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 07.09.13
 */
@RunWith(JUnit4.class)
public class RandomQueueTests {

    @Test
    public void randQueueTest() {
        long start = Runtime.getRuntime().freeMemory();
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        rq.enqueue(1);
        rq.dequeue();
        rq.enqueue(2);
        rq.enqueue(5);
        rq.enqueue(6);
        rq.dequeue();
        rq.dequeue();
        rq.enqueue(7);
        rq.dequeue();
        rq.dequeue();
        long end = Runtime.getRuntime().freeMemory();
        System.out.println("Memory usage = " + (start - end));
    }
}
