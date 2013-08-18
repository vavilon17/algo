package com.stamford

import com.stamford.common.datastruc.HashTable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 17.08.13
 * Time: 14:02
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
class HashTableTest {

    @Test
    public void mainTest() {
        HashTable ht = new HashTable(5)
        ht.insert(3)
        assert ht.lookup(3)
        assert !ht.lookup(6)
        assert ht.bucketCount() == 5
        ht.insert(6)
        assert ht.lookup(6)
        ht.insert(6)
        assert ht.bucketCount() == 10
        ht.insert(9)
        ht.insert(7)
        ht.insert(67)
        assert ht.bucketCount() == 70
        ht.insert(73)
        assert ht.bucketCount() == 140
        assert ht.lookup(9)
        assert ht.lookup(7)
        assert ht.lookup(67)
        assert ht.lookup(73)
        assert !ht.lookup(17)
        assert !ht.lookup(27)
        assert !ht.lookup(37)
        assert !ht.lookup(40)
    }
}
