package com.prinston.collinear;

import com.prinston.common.stdlib.StdDraw;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 14.09.13
 */
public class Point implements Comparable<Point> {

    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            return Double.valueOf(slopeTo(o1)).compareTo(Double.valueOf(slopeTo(o2)));
        }
    };;

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

    @Override
    public int compareTo(Point o) {
        int comp = Integer.valueOf(y).compareTo(o.y);
        if (comp == 0) {
            comp = Integer.valueOf(x).compareTo(o.x);
        }
        return comp;
    }
}
