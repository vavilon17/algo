package com.prinston2.wordnet;

import com.prinston2.Prinston2Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class WordNetTest extends Prinston2Test {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_fail_two_roots() {
        String synsetFile = getFullPath("wordnet/synsets3.txt");
        String hypernymsFile = getFullPath("wordnet/hypernyms3InvalidTwoRoots.txt");
        new WordNet(synsetFile, hypernymsFile);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_fail_cycle() {
        String synsetFile = getFullPath("wordnet/synsets3.txt");
        String hypernymsFile = getFullPath("wordnet/hypernyms3InvalidCycle.txt");
        new WordNet(synsetFile, hypernymsFile);
    }
}
