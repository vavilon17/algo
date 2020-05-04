package com.prinston2.boggle;

import com.prinston2.Prinston2Test;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BoggleTest extends Prinston2Test {

    @Test
    public void testReadFile() {
        BoggleBoard board = new BoggleBoard(getFullPath("boggle/board-antidisestablishmentarianisms.txt"));
        StdOut.println(board.toString());
        assertEquals(1, board.rows());
        assertEquals(29, board.cols());
        assertEquals('D', board.getLetter(0, 4));

        board = new BoggleBoard(getFullPath("boggle/board-dichlorodiphenyltrichloroethanes.txt"));
        StdOut.println(board.toString());
        assertEquals(32, board.rows());
        assertEquals(1, board.cols());
        assertEquals('E', board.getLetter(6, 0));

        board = new BoggleBoard(getFullPath("boggle/board4x4.txt"));
        StdOut.println("\n" + board.toString());
        assertEquals(4, board.rows());
        assertEquals(4, board.cols());
        assertEquals('A', board.getLetter(0, 0));
        assertEquals('E', board.getLetter(3, 3));
        assertEquals('Y', board.getLetter(1, 2));
    }

    @Test
    public void testExplore() {
        BoggleBoard board = new BoggleBoard(getFullPath("boggle/board4x4.txt"));
        BoggleSolver solver = new BoggleSolver(getWords());
        Iterable<String> words = solver.getAllValidWords(board);
        assertEquals(33, totalScore(words, solver));

        board = new BoggleBoard(getFullPath("boggle/board-q.txt"));
        words = solver.getAllValidWords(board);
        assertEquals(84, totalScore(words, solver));

        board = new BoggleBoard(getFullPath("boggle/board-dichlorodiphenyltrichloroethanes.txt"));
        words = solver.getAllValidWords(board);
        System.out.println(words);
    }

    private String[] getWords() {
        In in = new In(getFullPath("boggle/dictionary-algs4.txt"));
        List<String> words = new ArrayList<>();
        while (in.hasNextLine()) {
            words.add(in.readLine());
        }
        String[] arr = new String[words.size()];
        words.toArray(arr);
        return arr;
    }

    private int totalScore(Iterable<String> validWords, BoggleSolver solver) {
        int score = 0;
        for (String word : validWords) {
            score += solver.scoreOf(word);
        }
        return score;
    }
}
