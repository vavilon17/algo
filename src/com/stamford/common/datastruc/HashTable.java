package com.stamford.common.datastruc;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 17.08.13
 */
public class HashTable<T> {

    private LinkedList<T>[] buckets;
    private int elementsCount = 0;

    HashTable(int size) {
        buckets = new LinkedList[size];
    }

    HashTable(long[] inputArray) {

    }

    public void insert(T elem) throws Exception{
        if (elem instanceof Object) {
            int hash = elem.hashCode();
            if (hash > buckets.length) {
                increaseSize((int) Math.ceil(hash / buckets.length) + 1);
            }
            if (buckets[hash] == null) {
                buckets[hash] = new LinkedList<T>();
            }
            if (!buckets[hash].contains(elem)) {
                buckets[hash].add(elem);
                elementsCount++;
            }
        } else {
            throw new Exception("element cannot be inserted!");
        }
    }

    public boolean lookup(T elem) {
        boolean res = false;
        if (elem instanceof Object) {
            int hash = elem.hashCode();
            if (hash < buckets.length && buckets[hash] != null) {
                res = buckets[hash].contains(elem);
            }
        }
        return res;
    }

    //JUST FOR TEST
    public int bucketCount() {
        return buckets.length;
    }

    private void increaseSize(int increaseFactor) {
        buckets = Arrays.copyOf(buckets, buckets.length * increaseFactor);
    }

    private int hashFunction(T elem) {
        return 0;
    }
}
