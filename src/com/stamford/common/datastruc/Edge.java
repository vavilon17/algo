package com.stamford.common.datastruc;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 11.08.13
 * Time: 18:23
 * To change this template use File | Settings | File Templates.
 */
public class Edge {
    private int tail;
    private int head;
    private int length;

    public Edge(int tail, int head, int length) {
        this.tail = tail;
        this.head = head;
        this.length = length;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
