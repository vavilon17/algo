package com.stamford.mst;

import com.stamford.common.datastruc.Edge;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 08.09.13
 */
public class EdgeMST extends Edge implements Comparable<Edge>{

    public EdgeMST(int tail, int head, int length) {
        super(tail, head, length);
    }

    @Override
    public int compareTo(Edge o) {
        int res = Integer.valueOf(this.getLength()).compareTo(Integer.valueOf(o.getLength()));
        //FOR PRIMS
        //return res != 0 ? res : -1;
        //FOR KRUSKALS
        return (res != 0) ? res : 1;
    }

    @Override
    public String toString() {
        return "[" + this.getHead() + "-" + this.getTail() + ": " + this.getLength() + "]";
    }
}
