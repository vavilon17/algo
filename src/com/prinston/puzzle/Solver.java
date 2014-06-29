package com.prinston.puzzle;

import com.prinston.common.stdlib.In;
import com.prinston.common.stdlib.StdOut;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 21.09.13
 */
public class Solver {

    private Board gameBoard;
    private List<Board> solution = new ArrayList<Board>();

    public Solver(Board initial) {
        gameBoard = initial;
        Board prev = null, temp, curr = gameBoard;
        solution.add(curr);
        Iterator<Board> it;
        while (!curr.isGoal()) {
            it = curr.neighbors().iterator();
            while (it.hasNext()) {
                temp = it.next();
                if (prev == null || !prev.equals(temp)) {
                    prev = curr;
                    curr = temp;
                    break;
                }
            }
            solution.add(curr);
        }
    }

    public boolean isSolvable() {
        return true;
    }

    public int moves() {
        return Math.min(gameBoard.hamming(), gameBoard.manhattan());
    }

    public Iterable<Board> solution() {
        return solution;
    }

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
