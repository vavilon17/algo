package com.prinston.percolation;

import com.prinston.common.algs4.QuickFindUF;
import com.prinston.common.algs4.QuickUnionPathCompressionUF;
import com.prinston.common.algs4.WeightedQuickUnionUF;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 26.08.13
 */
public class Percolation {

    private WeightedQuickUnionUF model;
    private int N;
    private boolean[][] sites;

    public Percolation(int N) {
        model = new WeightedQuickUnionUF(N*N + 2);
        sites = new boolean[N][N];
        int lastGridIndex = N*N;
        for (int i = 1; i <= N; i++) {
            model.union(i, 0);
            model.union(lastGridIndex - i - 1, lastGridIndex + 1);
        }
        this.N = N;
    }

    public void open(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N) {
            throw new IndexOutOfBoundsException("Out of the ranges");
        }
        sites[i-1][j-1] = true;
        //check top element
        if (i > 1 && isOpen(i-1, j)) {
            model.union((i-1)*N + j, (i-2)*N + j);
        }
        //check right element
        if (j < N && isOpen(i, j+1)) {
            model.union((i-1)*N + j, (i-1)*N + j + 1);
        }
        //check bottom element
        if (i < N && isOpen(i+1, j)) {
            model.union((i-1)*N + j, i*N + j);
        }
        //check left element
        if (j > 1 && isOpen(i, j-1)) {
            model.union((i-1)*N + j, (i-1)*N + j - 1);
        }
    }

    public boolean isOpen(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N) {
            throw new IndexOutOfBoundsException("Out of the ranges");
        }
        return sites[i-1][j-1];
    }

    public boolean isFull(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N) {
            throw new IndexOutOfBoundsException("Out of the ranges");
        }
        return model.connected((i-1)*N + j, 0);
    }

    public boolean percolates() {
        return model.connected(0, N*N + 1);
    }
}
