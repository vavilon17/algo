package com.prinston2.wordnet;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SAP {

    private static class ShortestAncPath {

        private int commonAnc;
        private int minDist;

        public ShortestAncPath(int commonAnc, int minDist) {
            this.commonAnc = commonAnc;
            this.minDist = minDist;
        }

        public int getCommonAnc() {
            return commonAnc;
        }

        public void setCommonAnc(int commonAnc) {
            this.commonAnc = commonAnc;
        }

        public int getMinDist() {
            return minDist;
        }

        public void setMinDist(int minDist) {
            this.minDist = minDist;
        }
    }

    private final Digraph digraph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) {
            throw new IllegalArgumentException();
        }
        this.digraph = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        validateVertices(Arrays.asList(v, w));
        ShortestAncPath res = calcMinPath(Collections.singletonList(v), Collections.singletonList(w));
        return res.getMinDist();
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        validateVertices(Arrays.asList(v, w));
        ShortestAncPath res = calcMinPath(Collections.singletonList(v), Collections.singletonList(w));
        return res.getCommonAnc();
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertices(v);
        validateVertices(w);
        ShortestAncPath res = calcMinPath(v, w);
        return res.getMinDist();
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertices(v);
        validateVertices(w);
        ShortestAncPath res = calcMinPath(v, w);
        return res.getCommonAnc();
    }

    // do unit testing of this class
    public static void main(String[] args) {
        String input = "/Users/vitaliiiaskal/projects/algo/resources/prinston2/wordnet/digraph25.txt";
        In in = new In(input);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        List<Integer> v = Arrays.asList(13, 23, 24);
        List<Integer> w = Arrays.asList(6, 16, 17);

        int length = sap.length(v, w);
        int ancestor = sap.ancestor(v, w);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }

    private ShortestAncPath calcMinPath(Iterable<Integer> setOne, Iterable<Integer> setTwo) {
        BreadthFirstDirectedPaths setOnePaths = new BreadthFirstDirectedPaths(new Digraph(digraph), setOne);
        BreadthFirstDirectedPaths setTwoPaths = new BreadthFirstDirectedPaths(new Digraph(digraph), setTwo);
        int commonAnc = -1;
        int minDist = Integer.MAX_VALUE;
        // check whether vertices have the same ancestor
        for (int i = 0; i < digraph.V(); i++) {
            if (setOnePaths.hasPathTo(i) && setTwoPaths.hasPathTo(i)) {
                int currDist = setOnePaths.distTo(i) + setTwoPaths.distTo(i);
                if (currDist < minDist) {
                    minDist = currDist;
                    commonAnc = i;
                }
            }
        }
        if (commonAnc == -1) {
            minDist = -1;
        }
        return new ShortestAncPath(commonAnc, minDist);
    }

    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException();
        }
        for (Integer v : vertices) {
            if (v == null || v < 0 || v >= digraph.V()) {
                throw new IllegalArgumentException();
            }
        }
    }
}
