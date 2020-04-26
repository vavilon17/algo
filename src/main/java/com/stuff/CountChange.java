package com.stuff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 28.09.13
 */
public class CountChange {

    private int count = 0;

    public int countChange(int money, List<Integer> coins) {
        count = 0;
        Collections.sort(coins);
        eagerCalc(0, money, coins);
        return (count + 1)/2;
    }

    public void eagerCalc(int sum, int money, List<Integer> coins) {
        if (sum == money) {
            count++;
        } else if (sum < money) {
            for (int i : coins) {
                eagerCalc(sum + i, money, coins);
            }
        } else {
            return;
        }
    }

    public static void main(String[] args) {
        CountChange program = new CountChange();
        List<Integer> coins = new ArrayList<Integer>();
        coins.add(1);
        coins.add(2);
        System.out.println(program.countChange(5, coins));
        coins.clear();
        coins.add(5);
        coins.add(10);
        coins.add(20);
        coins.add(50);
        coins.add(100);
        coins.add(200);
        coins.add(500);
        //System.out.println(program.countChange(300, coins));
    }
}
