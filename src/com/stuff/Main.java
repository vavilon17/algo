package com.stuff;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 22.09.13
 */
public class Main {

    private static final double PRECISION = 0.001;
    private static int TEST_COUNT = 20;

    public static void main(String[] args) {
        //System.out.println(newtonRoot(1, 0.000001));
        long start, duration = 0;
        for (int i = 0; i < TEST_COUNT; i++) {
            start = System.nanoTime();
            for (long j = 0; j < 1000000l; j++) {
                Math.sqrt(j);
            }
            duration += (System.nanoTime() - start);
        }
        System.out.println("average system = " + duration/TEST_COUNT);
        for (int i = 0; i < TEST_COUNT; i++) {
            start = System.nanoTime();
            for (long j = 0; j < 1000000l; j++) {
                newtonRoot(1, j);
            }
            duration += (System.nanoTime() - start);
        }
        System.out.println("average custom = " + duration/TEST_COUNT);
    }

    private static double newtonRoot(double arg, double orig) {
        if (isGoodEnough(arg, orig)) {
            return arg;
        } else {
            return newtonRoot(nextValue(arg,orig), orig);
        }
    }

    private static boolean isGoodEnough(double arg, double orig) {
        return Math.abs(arg*arg - orig)/orig <= PRECISION;
    }

    private static double nextValue(double arg, double orig) {
        return 0.5*(arg + (double) orig/arg);
    }
}
