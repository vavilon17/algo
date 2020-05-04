package com.prinston2.boggle;

import edu.princeton.cs.algs4.TST;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class BoggleSolver {

    private final Map<Integer, Integer> scoresMap = new HashMap<>(5);

    private final TST<Integer> dictTrie = new TST<>();

    private BoggleBoard currentBoard;
    private int rows;
    private int cols;
    private final Map<Integer, List<Integer>> edges = new HashMap<>();

    private int[][] boardIndices;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        if (dictionary == null) {
            throw new IllegalArgumentException();
        }
        for (int idx = 0; idx < dictionary.length; idx++) {
            dictTrie.put(dictionary[idx], idx);
        }
        initScores();
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        if (board == null) {
            throw new IllegalArgumentException();
        }
        this.currentBoard = board;
        this.rows = board.rows();
        this.cols = board.cols();
        setupDataStructs();
        return solveBoard();
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        if (word.length() < 3 || !dictTrie.contains(word)) {
            return 0;
        }
        if (word.length() >= 8) {
            return 11;
        }
        return scoresMap.get(word.length());
    }

    private void initScores() {
        scoresMap.put(3, 1);
        scoresMap.put(4, 1);
        scoresMap.put(5, 2);
        scoresMap.put(6, 3);
        scoresMap.put(7, 5);
    }

    private void setupDataStructs() {
        boardIndices = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boardIndices[i][j] = i*cols + j;
                addEdge(boardIndices[i][j], i, j);
            }
        }
    }

    private void addEdge(int boardIndex, int i, int j) {
        edges.put(boardIndex, adjBoardIndices(i, j));
    }

    private Set<String> solveBoard() {
        Set<String> allValidWords = new HashSet<>();
        for (int idx = 0; idx < rows * cols; idx++) {
            boolean[] visited = new boolean[rows*cols];
            dfs(idx, allValidWords, visited, new StringBuilder());
        }
        return allValidWords;
    }

    private void dfs(int boardIdx, Set<String> validWords, boolean[] visited, StringBuilder currentPrefix) {
        update(currentPrefix, boardIdx);
        if (visited[boardIdx]) {
            return;
        }
        if (!existWordsWithPrefix(currentPrefix)) {
            return;
        }
        if (currentPrefix.length() > 2 && dictTrie.contains(currentPrefix.toString())) {
            validWords.add(currentPrefix.toString());
        }
        visited[boardIdx] = true;
        for (int adjIndex : adjIndicesNotVisited(boardIdx, visited)) {
            dfs(adjIndex, validWords, visited.clone(), new StringBuilder(currentPrefix));
        }
    }

    private Iterable<Integer> adjIndicesNotVisited(int boardIdx, boolean[] visited) {
        List<Integer> res = new ArrayList<>();
        for (int candidate : edges.get(boardIdx)) {
            if (!visited[candidate]) {
                res.add(candidate);
            }
        }
        return res;
//        int j = boardIdx % cols;
//        int i = (boardIdx - j) / cols;
//        List<Integer> adjBoardIndices = new ArrayList<>();
//        // up left
//        if (i > 0 && j > 0 && !visited[boardIndices[i-1][j-1]]) {
//            adjBoardIndices.add(boardIndexByCoord(i-1, j-1));
//        }
//        // up
//        if (i > 0 && !visited[boardIndices[i-1][j]]) {
//            adjBoardIndices.add(boardIndexByCoord(i-1, j));
//        }
//        // up right
//        if (i > 0 && j < cols-1 && !visited[boardIndices[i-1][j+1]]) {
//            adjBoardIndices.add(boardIndexByCoord(i-1, j+1));
//        }
//        // right
//        if (j < cols-1 && !visited[boardIndices[i][j+1]]) {
//            adjBoardIndices.add(boardIndexByCoord(i, j+1));
//        }
//        // donw right
//        if (i < rows-1 && j < cols-1 && !visited[boardIndices[i+1][j+1]]) {
//            adjBoardIndices.add(boardIndexByCoord(i+1, j+1));
//        }
//        // down
//        if (i < rows - 1 && !visited[boardIndices[i+1][j]]) {
//            adjBoardIndices.add(boardIndexByCoord(i+1, j));
//        }
//        // down left
//        if (i < rows-1 && j > 0 && !visited[boardIndices[i+1][j-1]]) {
//            adjBoardIndices.add(boardIndexByCoord(i+1, j-1));
//        }
//        // left
//        if (j > 0 && !visited[boardIndices[i][j-1]]) {
//            adjBoardIndices.add(boardIndexByCoord(i, j-1));
//        }
//        return adjBoardIndices;
    }

    private List<Integer> adjBoardIndices(int i, int j) {
        List<Integer> adjBoardIndices = new ArrayList<>();
        // up left
        if (i > 0 && j > 0) {
            adjBoardIndices.add(boardIndexByCoord(i-1, j-1));
        }
        // up
        if (i > 0) {
            adjBoardIndices.add(boardIndexByCoord(i-1, j));
        }
        // up right
        if (i > 0 && j < cols-1) {
            adjBoardIndices.add(boardIndexByCoord(i-1, j+1));
        }
        // right
        if (j < cols-1) {
            adjBoardIndices.add(boardIndexByCoord(i, j+1));
        }
        // donw right
        if (i < rows-1 && j < cols-1) {
            adjBoardIndices.add(boardIndexByCoord(i+1, j+1));
        }
        // down
        if (i < rows - 1) {
            adjBoardIndices.add(boardIndexByCoord(i+1, j));
        }
        // down left
        if (i < rows-1 && j > 0) {
            adjBoardIndices.add(boardIndexByCoord(i+1, j-1));
        }
        // left
        if (j > 0) {
            adjBoardIndices.add(boardIndexByCoord(i, j-1));
        }
        return adjBoardIndices;
    }

    private void update(StringBuilder prefix, int boardIndex) {
        char c = getLetterByBoardIdx(boardIndex);
        prefix.append(c == 'Q' ? "QU" : c);
    }

    private int boardIndexByCoord(int i, int j) {
        return i*cols + j;
    }

    private char getLetterByBoardIdx(int boardIdx) {
        int j = boardIdx % cols;
        int i = (boardIdx - j) / cols;
        return currentBoard.getLetter(i, j);
    }

    private boolean existWordsWithPrefix(StringBuilder prefix) {
        return dictTrie.keysWithPrefix(prefix.toString()).iterator().hasNext();
    }
}
