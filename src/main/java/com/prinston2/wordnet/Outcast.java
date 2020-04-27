package com.prinston2.wordnet;

public class Outcast {

    private final WordNet wordNet;

    public Outcast(WordNet wordnet) {
        if (wordnet == null) {
            throw new IllegalArgumentException();
        }
        this.wordNet = wordnet;
    }

    public String outcast(String[] nouns) {
        if (nouns == null || nouns.length < 2) {
            throw new IllegalArgumentException();
        }
        int maxDist = Integer.MIN_VALUE;
        String resNoun = nouns[0];
        for (String noun : nouns) {
            int currDist = sumDistances(noun, nouns);
            if (currDist > maxDist) {
                resNoun = noun;
            }
        }
        return resNoun;
    }

    private int sumDistances(String fromNoun, String[] toNouns) {
        int dist = 0;
        for (String n : toNouns) {
            if (!n.equals(fromNoun)) {
                dist += this.wordNet.distance(fromNoun, n);
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        String synsetFile = "/Users/vitaliiiaskal/projects/algo/src/main/resources/synsets.csv";
        String hypernymsFile = "/Users/vitaliiiaskal/projects/algo/src/main/resources/hypernyms.csv";
        WordNet net = new WordNet(synsetFile, hypernymsFile);

        Outcast o = new Outcast(net);
        String[] input = new String[] {"horse", "zebra", "cat", "bear", "table"};
        assert o.outcast(input).equals("table");

        input = new String[] {"water", "soda", "bed", "orange_juice", "milk", "apple_juice", "tea", "coffee"};
        assert o.outcast(input).equals("bed");

        input = new String[] {"apple", "pear", "peach", "banana", "lime", "lemon", "blueberry", "strawberry",
                "mango", "watermelon", "potato"};
        assert o.outcast(input).equals("potato");

    }

}
