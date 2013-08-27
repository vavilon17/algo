package com.prinston.percolation;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 26.08.13
 */
public class Percolation {

    //private QuickUnionPathCompressionUF model;
    int N;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N) {
            throw new IndexOutOfBoundsException("Out of the ranges");
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N) {
            throw new IndexOutOfBoundsException("Out of the ranges");
        }
        return false;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N) {
            throw new IndexOutOfBoundsException("Out of the ranges");
        }
        return false;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }
}
