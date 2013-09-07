package com.prinston.queues;

import com.prinston.common.stdlib.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class RandQueueIterator implements Iterator<Item> {

        private int currentIndex = 0;
        private Item[] shuffled;

        private RandQueueIterator(Item[] shuffled) {
            this.shuffled = shuffled;
        }

        @Override
        public boolean hasNext() {
            return (currentIndex < shuffled.length && shuffled[currentIndex] != null);
        }

        @Override
        public Item next() {
            if (currentIndex > shuffled.length - 1 || shuffled[currentIndex] == null) {
                throw new NoSuchElementException();
            }
            return shuffled[currentIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Item[] data;
    private int N;

    public RandomizedQueue() {
        data = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }


    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == data.length) {
            int newLength = data.length * 2;
            Item[] newArray = (Item[]) new Object[newLength];
            for (int i = 0; i < data.length; i++) {
                newArray[i] = data[i];
            }
            data = newArray;
        }
        data[N] = item;
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(N);
        Item result = data[index];
        data[index] = data[N - 1];
        data[N - 1] = null;
        N--;
        if (N == data.length/4 && data.length != 1) {
            int newLength = data.length/2;
            Item[] newArray = (Item[]) new Object[newLength];
            for (int i = 0; i < N; i++) {
                newArray[i] = data[i];
            }
            data = newArray;
        }
        return result;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(N);
        return data[index];
    }

    @Override
    public Iterator<Item> iterator() {
        Item[] copy = (Item[]) new Object[N];
        for (int i = 0; i < N; i++) {
            copy[i] = data[i];
        }
        StdRandom.shuffle(copy);
        return new RandQueueIterator(copy);
    }
}
