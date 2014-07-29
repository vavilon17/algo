package com.prinston.puzzle8;

import com.prinston.common.algs4.MinPQ;
import com.prinston.common.stdlib.In;
import com.prinston.common.stdlib.StdOut;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vit on 29.07.2014.
 */
public class Solver {

    private List<Board> solution = null;
    private int moves;
    private boolean solvable;

    private final Comparator<Board> comparator = new Comparator<Board>() {
        @Override
        public int compare(Board o1, Board o2) {
            return Integer.compare(o1.manhattan(), o2.manhattan());
        }
    };

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        Board prev = null, prevTwin = null;
        solution = new LinkedList<Board>();
        Board current = initial;
        Board currentTwin = initial.twin();
        MinPQ<Board> gameTree = new MinPQ<Board>(comparator);
        MinPQ<Board> gameTwinTree = new MinPQ<Board>(comparator);
        while (!current.isGoal() && !currentTwin.isGoal()) {
            // essential subroutine
            for (Board neighbor : current.neighbors()) {
                if (!neighbor.equals(prev)) {
                    gameTree.insert(neighbor);
                }
            }
            prev = current;
            solution.add(prev);
            current = gameTree.delMin();
            moves++;

            // subroutine for twins
            for (Board neighbor : currentTwin.neighbors()) {
                if (!neighbor.equals(prevTwin)) {
                    gameTwinTree.insert(neighbor);
                }
            }
            prevTwin = currentTwin;
            currentTwin = gameTwinTree.delMin();
        }
        if (current.isGoal()) {
            solvable = true;
        } else {
            moves = -1;
            solution = null;
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if no solution
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution() {
        return solution;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
