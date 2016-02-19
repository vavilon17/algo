package com.prinston.collinear;

import java.util.*;

public class FastCollinearPoints {

    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        Map<Double, List<Point>> slopes = new HashMap<>();
        Set<Point> pointSet = new TreeSet<>();
        Point pivotPoint;
        Point[] pointsArr;
        List<LineSegment> segmentList = new ArrayList<>();
        Set<String> segmentSet = new HashSet<>();
        int tempSize;
        for (int i = 0; i < points.length; i++) {
            pivotPoint = points[i];
            if (pointSet.contains(pivotPoint)) {
                throw new IllegalArgumentException("elements must be unique");
            }
            pointSet.add(pivotPoint);
            slopes.clear();
            double tempSlope;
            for (int j = 0; j < points.length; j++) {
                if (j != i) {
                    tempSlope = pivotPoint.slopeTo(points[j]);
                    if (!slopes.containsKey(tempSlope)) {
                        slopes.put(tempSlope, new ArrayList<Point>());
                        slopes.get(tempSlope).add(pivotPoint);
                    }
                    slopes.get(tempSlope).add(points[j]);
                }
            }
            for (Double slope : slopes.keySet()) {
                tempSize = slopes.get(slope).size();
                if (tempSize > 3) {
                    pointsArr = new Point[tempSize];
                    slopes.get(slope).toArray(pointsArr);
                    Arrays.sort(pointsArr);
                    String segmentSetItem = pointsArr[0].toString() + "->" + pointsArr[pointsArr.length - 1].toString();
                    if (!segmentSet.contains(segmentSetItem)) {
                        segmentList.add(new LineSegment(pointsArr[0], pointsArr[pointsArr.length - 1]));
                        segmentSet.add(segmentSetItem);
                    }
                }
            }
        }
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
