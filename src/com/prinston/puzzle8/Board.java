package com.prinston.puzzle8;

import java.util.LinkedList;
import java.util.List;

public class Board {

    private final int N;
    private final int[][] tiles;

    private int manhattanCached = -1;
    private int hammingCached = -1;

    public Board(int[][] blocks) {
        if (blocks != null) {
            N = blocks[0].length;
            // is good in terms of immutability
            //tiles = blocks;
            tiles = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tiles[i][j] = blocks[i][j];
                }
            }
        } else {
            N = 0;
            tiles = null;
        }
    }

    // board dimension N
    public int dimension() {
        return N;
    }

    // number of blocks out of place
    public int hamming() {
        if (hammingCached == -1) {
            int res = 0;
            int goalI, goalJ;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (tiles[i][j] != 0) {
                        goalI = (int) Math.floor((tiles[i][j] - 1)/N);
                        goalJ = tiles[i][j] - goalI*N - 1;
                        if (goalI != i || goalJ != j) {
                            res++;
                        }
                    }
                }
            }
            hammingCached = res;
        }
        return hammingCached;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        if (manhattanCached == -1) {
            int res = 0;
            int val;
            int goalI, goalJ;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    val = tiles[i][j];
                    if (val != 0) {
                        goalI = (int) Math.floor((val - 1)/N);
                        goalJ = val - goalI*N - 1;
                        res += Math.abs(goalI - i) + Math.abs(goalJ - j);
                    }
                }
            }
            manhattanCached = res;
        }
        return manhattanCached;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // a board obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        int[][] twin = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                twin[i][j] = tiles[i][j];
            }
        }
        // just swap elements in the row when there is not zero element
        if (tiles[0][0] == 0 || tiles[0][1] == 0) {
            twin[1][0] = tiles[1][1];
            twin[1][1] = tiles[1][0];
        } else {
            twin[0][0] = tiles[0][1];
            twin[0][1] = tiles[0][0];
        }
        return new Board(twin);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (!(y instanceof Board)) {
            return false;
        }
        Board that = (Board) y;
        if (dimension() != that.dimension()) {
            return false;
        }
        return toString().equals(that.toString());
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new LinkedList<Board>();
        int[][] temp;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    //top
                    if (i > 0) {
                        temp = new int[N][N];
                        for (int k = 0; k < N; k++) {
                            for (int l = 0; l < N; l++) {
                                temp[k][l] = tiles[k][l];
                            }
                        }
                        temp[i-1][j] = 0;
                        temp[i][j] = tiles[i-1][j];
                        neighbors.add(new Board(temp));
                    }

                    //right
                    if (j < N-1) {
                        temp = new int[N][N];
                        for (int k = 0; k < N; k++) {
                            for (int l = 0; l < N; l++) {
                                temp[k][l] = tiles[k][l];
                            }
                        }
                        temp[i][j+1] = 0;
                        temp[i][j] = tiles[i][j+1];
                        neighbors.add(new Board(temp));
                    }

                    //bottom
                    if (i < N-1) {
                        temp = new int[N][N];
                        for (int k = 0; k < N; k++) {
                            for (int l = 0; l < N; l++) {
                                temp[k][l] = tiles[k][l];
                            }
                        }
                        temp[i+1][j] = 0;
                        temp[i][j] = tiles[i+1][j];
                        neighbors.add(new Board(temp));
                    }

                    //left
                    if (j > 0) {
                        temp = new int[N][N];
                        for (int k = 0; k < N; k++) {
                            for (int l = 0; l < N; l++) {
                                temp[k][l] = tiles[k][l];
                            }
                        }
                        temp[i][j-1] = 0;
                        temp[i][j] = tiles[i][j-1];
                        neighbors.add(new Board(temp));
                    }
                }
            }
        }
        return neighbors;
    }

    // string representation of the board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
