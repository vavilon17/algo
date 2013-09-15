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
public class Brute {

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

        List<TreeSet<Point>> collinearPoints = new ArrayList<TreeSet<Point>>();
        if (points != null) {
            for (int i = 0; i < points.length; i++) {
                for (int j = i + 1; j < points.length; j++) {
                    for (int k = j + 1; k < points.length; k++) {
                        for (int l = k + 1; l < points.length; l++) {
                            if ((points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]))
                                    && (points[i].slopeTo(points[j]) == points[i].slopeTo(points[l]))) {
                                TreeSet tempSet = new TreeSet();
                                tempSet.add(points[i]);
                                tempSet.add(points[j]);
                                tempSet.add(points[k]);
                                tempSet.add(points[l]);
                                collinearPoints.add(tempSet);
                            }
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
