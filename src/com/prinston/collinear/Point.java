package com.prinston.collinear;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public double slopeTo(Point that) {
        if (that.x == x && that.y == y) {
            return Double.NEGATIVE_INFINITY;
        } else if (that.x == x) {
            return Double.POSITIVE_INFINITY;
        } else if (that.y == y) {
            return +0.0;
        } else {
            return (double) (that.y - y)/(that.x - x);
        }
    }

    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                Double slope1 = slopeTo(o1);
                Double slope2 = slopeTo(o2);
                return slope1.compareTo(slope2);
            }
        };
    }

    @Override
    public int compareTo(Point o) {
        int comp = Integer.compare(y, o.y);
        if (comp == 0) {
            comp = Integer.compare(x, o.x);
        }
        return comp;
    }
}
