package com.prinston.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 26.08.13
 */
public class Percolation {

    private WeightedQuickUnionUF model;
    private WeightedQuickUnionUF forIsFullModel;
    private int N;
    private boolean[][] sites;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }
        model = new WeightedQuickUnionUF(N*N + 2);
        forIsFullModel = new WeightedQuickUnionUF(N*N + 1);
        sites = new boolean[N][N];
        int lastGridIndex = N*N;
        for (int i = 1; i <= N; i++) {
            model.union(0, i);
            forIsFullModel.union(0, i);
            model.union(lastGridIndex +1, lastGridIndex - i + 1);
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
            forIsFullModel.union((i-1)*N + j, (i-2)*N + j);
        }
        //check right element
        if (j < N && isOpen(i, j+1)) {
            model.union((i-1)*N + j, (i-1)*N + j + 1);
            forIsFullModel.union((i-1)*N + j, (i-1)*N + j + 1);
        }
        //check bottom element
        if (i < N && isOpen(i+1, j)) {
            model.union((i-1)*N + j, i*N + j);
            forIsFullModel.union((i-1)*N + j, i*N + j);
        }
        //check left element
        if (j > 1 && isOpen(i, j-1)) {
            model.union((i-1)*N + j, (i-1)*N + j - 1);
            forIsFullModel.union((i-1)*N + j, (i-1)*N + j - 1);
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
        return isOpen(i, j) && forIsFullModel.connected((i-1)*N + j, 0);
    }

    public boolean percolates() {
        if (N == 1) {
            return isOpen(1, 1);
        } else {
            return model.connected(0, N*N + 1);
        }
    }
}
