package com.prinston.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        public Item item;
        public Node next;
        public Node prev;
    }

    private Node first = null;
    private Node last = null;
    private int N;

    public Deque() {
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("null cannot be added to the queue");
        }
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        N++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("null cannot be added to the queue");
        }
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
        } else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            oldLast.next = last;
            last.prev = oldLast;
        }
        N++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("the queue is empty!");
        }
        Item result = first.item;
        if (N == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        N--;
        return result;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("the queue is empty!");
        }
        Item result = last.item;
        if (N == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        N--;
        return result;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
}
