package com.prinston.percolation;

public class PercolationTest {

    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(2, 100000);
        stats.mean();


        /*Percolation p = new Percolation(3);
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
        assert p.percolates();*/
    }
}
