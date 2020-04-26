package com.prinston.puzzle8;

public class PuzzleChecker {

    public static void main(String[] args) {

        // for each command-line argument
        for (String filename : args) {

            // read in the board specified in the filename
            /*In in = new In(filename);
            int N = in.readInt();
            int[][] tiles = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
            Board initial = new Board(tiles);
            long start = System.currentTimeMillis();
            Solver solver = new Solver(initial);
            long end = System.currentTimeMillis();
            System.out.println(filename + ": " + solver.moves() + " in " + (end - start) + " mills");
            System.out.println(solver.solution());*/
        }
    }
}