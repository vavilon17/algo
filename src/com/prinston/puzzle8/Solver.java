package com.prinston.puzzle8;

import com.prinston.common.algs4.MinPQ;
import com.prinston.common.stdlib.In;
import com.prinston.common.stdlib.StdOut;

import java.util.*;

/**
 * Created by vit on 29.07.2014.
 */
public class Solver {

    private List<Board> solution = null;
    private int moves;
    private boolean solvable;

    private class BoardContainer {

        private final int moves;
        private final Board board;

        private BoardContainer(int moves, Board board) {
            this.moves = moves;
            this.board = board;
        }

        public int getMoves() {
            return moves;
        }

        public Board getBoard() {
            return board;
        }
    }

    private final Comparator<BoardContainer> comparator = new Comparator<BoardContainer>() {
        @Override
        public int compare(BoardContainer o1, BoardContainer o2) {
            int result = Integer.compare(o1.getMoves() + o1.getBoard().manhattan(),
                    o2.getMoves() + o2.getBoard().manhattan());
            if (result == 0) {
                result = Integer.compare(o1.getMoves() + o1.getBoard().hamming(),
                        o2.getMoves() + o2.getBoard().hamming());
            }
            return result;
        }
    };

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        BoardContainer prev = null, prevTwin = null;
        solution = new LinkedList<Board>();
        Set<String> unique = new HashSet<String>();
        Set<String> uniqueTwin = new HashSet<String>();
        BoardContainer current = new BoardContainer(0, initial);
        BoardContainer currentTwin = new BoardContainer(0, initial.twin());
        MinPQ<BoardContainer> gameTree = new MinPQ<BoardContainer>(comparator);
        MinPQ<BoardContainer> gameTwinTree = new MinPQ<BoardContainer>(comparator);
        while (!current.getBoard().isGoal() && !currentTwin.getBoard().isGoal()) {
            // essential subroutine
            moves++;
            for (Board neighbor : current.getBoard().neighbors()) {
                if (!neighbor.equals(prev) && !unique.contains(neighbor.toString())) {
                    gameTree.insert(new BoardContainer(moves, neighbor));
                }
            }
            prev = current;
            unique.add(prev.getBoard().toString());
            solution.add(prev.getBoard());
            current = gameTree.delMin();
//            moves++;

            // subroutine for twins
            for (Board neighbor : currentTwin.getBoard().neighbors()) {
                if (!neighbor.equals(prevTwin) && !uniqueTwin.contains(neighbor.toString())) {
                    gameTwinTree.insert(new BoardContainer(moves, neighbor));
                }
            }
            prevTwin = currentTwin;
            uniqueTwin.add(prevTwin.getBoard().toString());
            currentTwin = gameTwinTree.delMin();
        }
        if (current.getBoard().isGoal()) {
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
