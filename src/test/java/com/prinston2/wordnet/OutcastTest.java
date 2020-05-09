package com.prinston2.wordnet;

import com.prinston2.Prinston2Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class OutcastTest extends Prinston2Test {

    @Test
    public void testOutcast() {
        String synsetFile = getFullPath("wordnet/synsets.txt");
        String hypernymsFile = getFullPath("wordnet/hypernyms.txt");
        WordNet net = new WordNet(synsetFile, hypernymsFile);

        Outcast outcast = new Outcast(net);
        String[] input = new String[] {"probability", "statistics", "mathematics", "physics"};
        System.out.println(outcast.outcast(input));

//
//        Outcast o = new Outcast(net);
//        String[] input = new String[] {"horse", "zebra", "cat", "bear", "table"};
//        System.out.println(outcast.outcast(input));
//
//        input = new String[] {"water", "soda", "bed", "orange_juice", "milk", "apple_juice", "tea", "coffee"};
//        assert o.outcast(input).equals("bed");
//
//        input = new String[] {"apple", "pear", "peach", "banana", "lime", "lemon", "blueberry", "strawberry",
//                "mango", "watermelon", "potato"};
//        assert o.outcast(input).equals("potato");
    }

}
