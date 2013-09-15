package com.prinston.collinear;

import com.prinston.common.stdlib.In;
import com.prinston.common.stdlib.StdDraw;
import com.prinston.common.stdlib.StdOut;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 15.09.13
 */
public class Fast {

    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
            p.draw();
        }

        boolean first;
        TreeMap<Double, Point> slopes = new TreeMap<Double, Point>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return (o1.compareTo(o2) == 0) ? -1 : o1.compareTo(o2);
            }
        });
        Set<TreeSet<Point>> collinearPoints = new HashSet<TreeSet<Point>>();
        TreeSet<Point> tempSet = new TreeSet<Point>();

        Point pivotPoint;
        for (int i = 0; i < points.length; i++) {
            pivotPoint = points[i];
            slopes.clear();
            for (int j = 0; j < points.length; j++) {
                if (j != i) {
                    slopes.put(pivotPoint.slopeTo(points[j]), points[j]);
                }
            }
            if (slopes.size() >= 3) {
                double slope = 0d;
                first = true;
                tempSet.clear();
                for (Map.Entry<Double, Point> entry : slopes.entrySet()) {
                    if (first) {
                        slope = entry.getKey();
                        tempSet.add(entry.getValue());
                        first = false;
                    } else {
                        if (slope == entry.getKey()) {
                            tempSet.add(entry.getValue());
                        } else {
                            if (tempSet.size() >= 3) {
                                TreeSet<Point> t = new TreeSet<Point>();
                                t.add(pivotPoint);
                                for (Point p2 : tempSet) {
                                    t.add(p2);
                                }
                                collinearPoints.add(t);
                            }
                            tempSet.clear();
                            tempSet.add(entry.getValue());
                            slope = entry.getKey();
                        }
                    }
                }
            }
        }
        Iterator<Point> itp;
        int i;
        for (TreeSet s : collinearPoints) {
            itp = s.iterator();
            i = 1;
            Point prev = null, current;
            while (itp.hasNext()) {
                current = itp.next();
                StdOut.print(current.toString());
                if (i != s.size()) {
                    StdOut.print(" -> ");
                }
                if (prev != null) {
                    prev.drawTo(current);
                }
                prev = current;
                i++;
            }
            StdOut.println();
        }
    }
}
