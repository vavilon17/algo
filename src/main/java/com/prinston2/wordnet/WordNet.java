package com.prinston2.wordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class WordNet {

    private final Map<Integer, String> verticesSynsetsMap = new HashMap<>();
    private final Map<String, List<Integer>> nounsVerticesMap = new HashMap<>();
    private Digraph digraph;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException();
        }
        readSynsets(synsets);
        readHypernyms(hypernyms);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounsVerticesMap.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        return nounsVerticesMap.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        validateNouns(nounA, nounB);
        List<Integer> synsetA = nounsVerticesMap.get(nounA);
        List<Integer> synsetB = nounsVerticesMap.get(nounB);

        SAP sap = new SAP(this.digraph);
        return sap.length(synsetA, synsetB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        validateNouns(nounA, nounB);
        List<Integer> synsetA = nounsVerticesMap.get(nounA);
        List<Integer> synsetB = nounsVerticesMap.get(nounB);

        SAP sap = new SAP(this.digraph);
        int synsetId = sap.ancestor(synsetA, synsetB);
        return this.verticesSynsetsMap.get(synsetId);
    }

    public static void main(String[] args) {
        String synsetFile = "/Users/vitaliiiaskal/projects/algo/src/main/resources/synsets.csv";
        String hypernymsFile = "/Users/vitaliiiaskal/projects/algo/src/main/resources/hypernyms.csv";
        WordNet net = new WordNet(synsetFile, hypernymsFile);

        assert net.digraph.V() == 82192;
        assert net.digraph.E() == 84505;

        assert ((Set<String>) net.nouns()).size() == 119188;

        assert net.isNoun("saddle_block_anaesthesia");
        assert !net.isNoun("fermentation_aa_bb");

        assert net.distance("worm", "bird") == 5;
        assert net.sap("worm", "bird").equals("animal animate_being beast brute creature fauna");

        assert net.sap("individual", "edible_fruit").equals("physical_entity");

        assert net.distance("white_marlin", "mileage") == 23;
        assert net.distance("Black_Plague", "black_marlin") == 33;
        assert net.distance("American_water_spaniel", "histology") == 27;
        assert net.distance("Brown_Swiss", "barrel_roll") == 29;
    }

    private void readHypernyms(String hypernyms) {
        In hyper = new In(hypernyms);
        while (!hyper.isEmpty()) {
            String line = hyper.readLine();
            String[] parts = line.split(",");
            for (int i = 1; i < parts.length; i++) {
                this.digraph.addEdge(Integer.parseInt(parts[0]), Integer.parseInt(parts[i]));
            }
        }
    }

    private void readSynsets(String synsets) {
        In syn = new In(synsets);
        while (!syn.isEmpty()) {
            String line = syn.readLine();
            String[] parts = line.split(",");
            int vertexIdx = Integer.parseInt(parts[0]);
            this.verticesSynsetsMap.put(vertexIdx, parts[1]);
            String[] nouns = parts[1].split(" ");
            for (String noun : nouns) {
                if (nounsVerticesMap.containsKey(noun)) {
                    nounsVerticesMap.get(noun).add(vertexIdx);
                } else {
                    nounsVerticesMap.put(noun, new ArrayList<>(Arrays.asList(vertexIdx)));
                }
            }
        }
        this.digraph = new Digraph(this.verticesSynsetsMap.size());
    }

    private void validateNouns(String nounA, String nounB) {
        if (nounA == null || nounB == null) {
            throw new IllegalArgumentException();
        }
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
    }

}
