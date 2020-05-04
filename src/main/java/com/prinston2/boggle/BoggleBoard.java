package com.prinston2.boggle;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.Random;

public class BoggleBoard {

    private char[][] board;

    // Initializes a random 4-by-4 Boggle board (by rolling the Hasbro dice)
    public BoggleBoard() {
        new BoggleBoard(4, 4);
    }

    // Initializes a random m-by-n Boggle board (using the frequency of letters in the English language)
    public BoggleBoard(int m, int n) {
        if (m < 1 || n < 1) {
            throw new IllegalArgumentException();
        }
        board = new char[m][n];
        Random random = new Random();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = (char) (65 + random.nextInt(26));
            }
        }
    }

    // Initializes a Boggle board from the specified filename.
    public BoggleBoard(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException();
        }
        In in = new In(filename);
        String[] sizes = in.readLine().trim().split(" ");
        int m = Integer.parseInt(sizes[0]);
        int n = Integer.parseInt(sizes[1]);
        board = new char[m][n];
        int currentRowIdx = 0;
        while (in.hasNextLine()) {
            String[] row = in.readLine().split("\\s+");
            if (!row[0].isEmpty()) {
                for (int i = 0; i < n; i++) {
                    board[currentRowIdx][i] = row[i].length() < 2 ? row[i].charAt(0) : 'Q';
                }
                currentRowIdx++;
            }
        }
    }

    // Initializes a Boggle board from the 2d char array (with 'Q' representing the two-letter sequence "Qu")
    public BoggleBoard(char[][] a) {
        if (a == null) {
            throw new IllegalArgumentException();
        }
        board = Arrays.stream(a).map(char[]::clone).toArray($ -> a.clone());
    }

    public int rows() {
        return board.length;
    }

    public int cols() {
        return board[0].length;
    }

    public char getLetter(int i, int j) {
        if (i < 0 || j < 0) {
            throw new IllegalArgumentException();
        }
        return board[i][j];
    }

    public String toString() {
        StringBuilder str = new StringBuilder("Size: ").append(rows()).append(":").append(cols()).append("\n");
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                str.append(board[i][j] != 'Q' ? board[i][j] : "Qu")
                        .append(j < cols() - 1 ? " " : "\n");
            }
        }
        return str.toString();
    }
}

