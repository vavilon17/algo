package com.prinston.collinear;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        Set<Point> pointSet = new TreeSet<>();
        // for our purpose temp array has fixed size
        Point[] pointsArr = new Point[4];
        List<LineSegment> segmentList = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            if (pointSet.contains(points[i])) {
                throw new IllegalArgumentException("Input array must contain unique elements");
            }
            pointSet.add(points[i]);
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if ((points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]))
                                && (points[i].slopeTo(points[j]) == points[i].slopeTo(points[l]))) {
                            // fill and sort temp array in order to obtain min and max elements
                            pointsArr[0] = points[i];
                            pointsArr[1] = points[j];
                            pointsArr[2] = points[k];
                            pointsArr[3] = points[l];
                            Arrays.sort(pointsArr);
                            segmentList.add(new LineSegment(pointsArr[0], pointsArr[3]));
                        }
                    }
                }
            }
        }
        // prepare segments array
        segments = new LineSegment[segmentList.size()];
        segmentList.toArray(segments);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }
}
