package com.prinston.queues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

    public static void main(String[] args) {
        try {
            String s;
            RandomizedQueue<String> rq = new RandomizedQueue<String>();
            while (!StdIn.isEmpty()) {
                s = StdIn.readString();
                rq.enqueue(s);
            }
            int k = Integer.parseInt(args[0]);
            if (k < 0 || k > rq.size()) {
                throw new IllegalArgumentException("out of ranges");
            }
            while (k > 0) {
                StdOut.println(rq.dequeue());
                k--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
