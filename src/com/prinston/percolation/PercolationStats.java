package com.prinston.percolation;

import com.prinston.common.stdlib.StdOut;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 27.08.13
 */
public class PercolationStats {

    private Percolation percolation;
    private double[] means;

    public PercolationStats(int N, int T) {
        int i,j, countOpened;
        means = new double[T];
        for (int k=1; k <= T; k++) {
            StdOut.println("iteration " + k);
            percolation = new Percolation(N);
            countOpened = 0;
            while (!percolation.percolates()) {
                i = 1 + (int) (Math.random()*N);
                j = 1 + (int) (Math.random()*N);
                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    countOpened++;
                }
            }
            means[k-1] = (double) countOpened/(N*N);
        }
        StdOut.println("Percolation Stats with N=" + N + " and T=" + T);
        StdOut.println("mean = " + mean());
    }

    public double mean() {
        double res = 0;
        for (double d : means) {
            res += d;
        }
        return res/means.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0;
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return 0;
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return 0;
    }

    public static void main(String[] args) {
        new PercolationStats(200, 100);
    }
}
