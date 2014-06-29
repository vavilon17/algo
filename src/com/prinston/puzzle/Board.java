package com.prinston.puzzle;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 21.09.13
 */
public class Board {

    private int N;
    private int[][] board;

    public Board(int[][] blocks) {
        board = blocks;
        N = (blocks != null && blocks.length > 0) ? blocks[0].length : 0;
    }

    public int dimension() {
        return N;
    }

    public int hamming() {
        int res = 0;
        int goalI, goalJ;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    goalI = (int) Math.floor((board[i][j] - 1)/N);
                    goalJ = board[i][j] - goalI*N - 1;
                    if (goalI != i || goalJ != j) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    public int manhattan() {
        int res = 0;
        int goalI, goalJ;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    goalI = (int) Math.floor((board[i][j] - 1)/N);
                    goalJ = board[i][j] - goalI*N - 1;
                    res += Math.abs(goalI - i) + Math.abs(goalJ - j);
                }
            }
        }
        return res;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        int[][] twinBoard;
        int temp;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0 && (j < N-1 && board[i][j+1] != 0)) {
                    twinBoard = new int[N][N];
                    for (int k = 0; k < N; k++) {
                        for (int l = 0; l < N; l++) {
                            twinBoard[k][l] = board[k][l];
                        }
                    }
                    temp = twinBoard[i][j];
                    twinBoard[i][j] = twinBoard[i][j+1];
                    twinBoard[i][j+1] = temp;
                    return new Board(twinBoard);
                }
            }
        }
        return null;
    }

    public boolean equals(Object y) {
        if (y == null || !(y instanceof Board)) {
            return false;
        }
        Board other = (Board) y;
        if (other.dimension() != N) {
            return false;
        }
        return this.toString().equals(other.toString());
    }

    public Iterable<Board> neighbors() {
        Set<Board> neighbors = new TreeSet<Board>(new Comparator<Board>() {
            @Override
            public int compare(Board o1, Board o2) {
                int res = Integer.valueOf(o1.manhattan()).compareTo(Integer.valueOf(o2.manhattan()));
                return res != 0 ? res : 1;
            }
        });
        int[][] temp;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    //top
                    if (i > 0) {
                        temp = new int[N][N];
                        for (int k = 0; k < N; k++) {
                            for (int l = 0; l < N; l++) {
                                temp[k][l] = board[k][l];
                            }
                        }
                        temp[i-1][j] = 0;
                        temp[i][j] = board[i-1][j];
                        neighbors.add(new Board(temp));
                    }

                    //right
                    if (j < N-1) {
                        temp = new int[N][N];
                        for (int k = 0; k < N; k++) {
                            for (int l = 0; l < N; l++) {
                                temp[k][l] = board[k][l];
                            }
                        }
                        temp[i][j+1] = 0;
                        temp[i][j] = board[i][j+1];
                        neighbors.add(new Board(temp));
                    }

                    //bottom
                    if (i < N-1) {
                        temp = new int[N][N];
                        for (int k = 0; k < N; k++) {
                            for (int l = 0; l < N; l++) {
                                temp[k][l] = board[k][l];
                            }
                        }
                        temp[i+1][j] = 0;
                        temp[i][j] = board[i+1][j];
                        neighbors.add(new Board(temp));
                    }

                    //left
                    if (j > 0) {
                        temp = new int[N][N];
                        for (int k = 0; k < N; k++) {
                            for (int l = 0; l < N; l++) {
                                temp[k][l] = board[k][l];
                            }
                        }
                        temp[i][j-1] = 0;
                        temp[i][j] = board[i][j-1];
                        neighbors.add(new Board(temp));
                    }
                    return neighbors;
                }
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
