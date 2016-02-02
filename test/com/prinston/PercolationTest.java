package com.prinston;

import com.prinston.percolation.Percolation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PercolationTest {

    @Test
    public void dataStrucTest() {
        Percolation p = new Percolation(3);
        assert !p.isFull(1,1);
        p.open(1,1);
        p.open(1,2);
        p.open(2,3);
        assert !p.isFull(2,3);
        assert !p.isFull(2,2);
        assert p.isFull(1,2);
        assert !p.percolates();
        p.open(2,2);
        assert p.isFull(2,2);
        assert !p.percolates();
        p.open(3,3);
        assert p.isFull(3,3);
        assert !p.isFull(3,2);
        assert !p.isFull(3,1);
        assert p.percolates();
    }

    @Test
    public void percEdgeTest() {
        Percolation p = new Percolation(1);
        assert !p.percolates();
        p.open(1,1);
        assert p.percolates();

        p = new Percolation(2);
        assert !p.percolates();
        p.open(1,1);
        p.open(2,2);
        assert p.isFull(1,1);
        assert !p.isFull(2,2);
        assert !p.isFull(2,1);
        assert !p.percolates();
        p.open(1,2);
        assert p.isFull(2,2);
        assert p.percolates();
    }

    @Test
    public void percolationMemoryTests() {

    }

    @Test
    public void percolationStatsTimingTests() {

    }

    @Test
    public void percolationStatsMemoryTests() {
        /*def t = [10, 100, 1000]
        PercolationStats ps
        t.each {
            ps = new PercolationStats(100, it)
        }*/
    }
}
