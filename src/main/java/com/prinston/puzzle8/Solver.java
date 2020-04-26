package com.prinston.puzzle8;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {

    private Deque<Board> solution = null;
    private int moves;

    /**
     * Helper class to contain board and number of moves made so far
     */
    private class BoardContainer {

        private final int moves;
        private final Board board;
        private final BoardContainer prev;

        private BoardContainer(int moves, Board board, BoardContainer prev) {
            this.moves = moves;
            this.board = board;
            this.prev = prev;
        }

        public int getMoves() {
            return moves;
        }

        public Board getBoard() {
            return board;
        }

        public BoardContainer previous() {
            return prev;
        }

        public int priority() {
            return moves + board.manhattan();
        }
    }

    private final Comparator<BoardContainer> comparator = new Comparator<BoardContainer>() {
        @Override
        public int compare(BoardContainer o1, BoardContainer o2) {
            int result = Integer.compare(o1.priority(), o2.priority());
            if (result == 0) {
                result = Integer.compare(o1.getMoves() + o1.getBoard().hamming(), o2.getMoves() + o2.getBoard().hamming());
            }
            return result;
        }
    };

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        BoardContainer prev = null, prevTwin = null;
        solution = new LinkedList<Board>();
        Set<String> uniqueItems = new HashSet<String>();
        Set<String> uniqueTwinItems = new HashSet<String>();
        BoardContainer current = new BoardContainer(0, initial, prev);
        BoardContainer currentTwin = new BoardContainer(0, initial.twin(), prevTwin);
        MinPQ<BoardContainer> gameTree = new MinPQ<BoardContainer>(comparator);
        MinPQ<BoardContainer> gameTwinTree = new MinPQ<BoardContainer>(comparator);
        while (!current.getBoard().isGoal() && !currentTwin.getBoard().isGoal()) {
            // essential subroutine
            for (Board neighbor : current.getBoard().neighbors()) {
                if (!uniqueItems.contains(neighbor.toString())) {
                    gameTree.insert(new BoardContainer(current.getMoves() + 1, neighbor, current));
                }
            }
            prev = current;
            uniqueItems.add(prev.getBoard().toString());
            current = gameTree.delMin();

            // subroutine for twins
            for (Board neighbor : currentTwin.getBoard().neighbors()) {
                if (!uniqueTwinItems.contains(neighbor.toString())) {
                    gameTwinTree.insert(new BoardContainer(currentTwin.getMoves() + 1, neighbor, currentTwin));
                }
            }
            prevTwin = currentTwin;
            uniqueTwinItems.add(prevTwin.getBoard().toString());
            currentTwin = gameTwinTree.delMin();
        }
        if (current.getBoard().isGoal()) {
            moves = current.getMoves();
            while (current != null) {
                solution.addFirst(current.getBoard());
                current = current.previous();
            }
        } else {
            moves = -1;
            solution = null;
        }
    }

    public boolean isSolvable() {
        return moves != -1;
    }

    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution() {
        return solution;
    }
}
