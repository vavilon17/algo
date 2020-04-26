package com.prinston.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 27.08.13
 */
public class PercolationStats {

    //private Percolation percolation;
    private double[] means;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("wrong arguments");
        }
        int i, j, countOpened;
        Percolation percolation;
        means = new double[T];
        for (int k = 1; k <= T; k++) {
            percolation = new Percolation(N);
            countOpened = 0;
            while (!percolation.percolates()) {
                i = StdRandom.uniform(1, N + 1);
                j = StdRandom.uniform(1, N + 1);
                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    countOpened++;
                }
            }
            means[k-1] = (double) countOpened/(N*N);
        }
    }

    public double mean() {
        return StdStats.mean(means);
    }

    public double stddev() {
        return StdStats.stddev(means);
    }

    public double confidenceLo() {
        return (mean() - (1.96*stddev())/Math.sqrt(means.length));
    }

    public double confidenceHi() {
        return (mean() + (1.96*stddev())/Math.sqrt(means.length));
    }

    public static void main(String[] args) {
        try {
            int size = Integer.parseInt(args[0]);
            int count = Integer.parseInt(args[1]);
            PercolationStats stats = new PercolationStats(size, count);
            StdOut.println("mean = " + stats.mean());
            StdOut.println("stddev = " + stats.stddev());
            StdOut.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
        } catch (NumberFormatException e) {
            StdOut.println(e.getMessage());
        }
    }
}
