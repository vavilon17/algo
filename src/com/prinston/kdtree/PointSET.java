package com.prinston.kdtree;

import com.prinston.common.algs4.Point2D;
import com.prinston.common.algs4.RectHV;

import java.util.*;

/**
 * Created by vit on 16.08.2014.
 */
public class PointSET {

    private TreeSet<Point2D> rbTree = null;

    public PointSET() {
        rbTree = new TreeSet<Point2D>();
    }

    public boolean isEmpty() {
        return rbTree.isEmpty();
    }

    public int size() {
        return rbTree.size();
    }

    public void insert(Point2D p) {
        if (!rbTree.contains(p)) {
            rbTree.add(p);
        }
    }

    public boolean contains(Point2D p) {
        return rbTree.contains(p);
    }

    public void draw() {
        for (Point2D point : rbTree) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        Set<Point2D> res = new HashSet<Point2D>();
        for (Point2D point : rbTree) {
            if (rect.contains(point)) {
                res.add(point);
            }
        }
        return res;
    }

    public Point2D nearest(Point2D p) {
        double minDistance = Double.MAX_VALUE;
        Point2D resPoint = null;
        for (Point2D point : rbTree) {
            if (point.distanceTo(p) < minDistance) {
                minDistance = point.distanceTo(p);
                resPoint = point;
//                System.out.println("BRUTE current nearest = " + resPoint);
            }
        }
        return resPoint;
    }
}
