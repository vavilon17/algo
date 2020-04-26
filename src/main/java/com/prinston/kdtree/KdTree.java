package com.prinston.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.LinkedList;
import java.util.List;


public class KdTree {

    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;

    private static class Node implements Comparable<Point2D> {

        private Point2D p;
        private RectHV rect;
        private boolean orientation;
        private Node lb;
        private Node rt;

        Node(Point2D p, boolean orientation, Node parent) {
            this.p = p;
            this.orientation = orientation;
            if (parent == null) {
                rect = new RectHV(0, 0, 1, 1);
            } else {
                RectHV parentRect = parent.rect;
                // depending on the orientation we set node rectangle
                if (parent.orientation == VERTICAL) {
                    if (p.x() < parent.p.x()) {
                        rect = new RectHV(parentRect.xmin(), parentRect.ymin(), parent.p.x(), parentRect.ymax());
                    } else {
                        rect = new RectHV(parent.p.x(), parentRect.ymin(), parentRect.xmax(), parentRect.ymax());
                    }
                } else {
                    if (p.y() < parent.p.y()) {
                        rect = new RectHV(parentRect.xmin(), parentRect.ymin(), parentRect.xmax(), parent.p.y());
                    } else {
                        rect = new RectHV(parentRect.xmin(), parent.p.y(), parentRect.xmax(), parentRect.ymax());
                    }
                }
            }
        }

        @Override
        public int compareTo(Point2D point) {
            if (p.equals(point)) {
                return 0;
            } else {
                if (orientation == VERTICAL) {
                    if (point.x() < p.x()) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    if (point.y() < p.y()) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        }

        public void addPoint(Point2D point) {
            if (compareTo(point) < 0) {
                if (lb != null) {
                    lb.addPoint(point);
                } else {
                    lb = new Node(point, !this.orientation, this);
                }
            } else {
                if (rt != null) {
                    rt.addPoint(point);
                } else {
                    rt = new Node(point, !this.orientation, this);
                }
            }
        }

        public Node findPoint(Point2D point) {
            if (p.equals(point)) {
                return this;
            } else {
                if (compareTo(point) < 0) {
                    if (lb != null) {
                        return lb.findPoint(point);
                    } else {
                        return null;
                    }
                } else {
                    if (rt != null) {
                        return rt.findPoint(point);
                    } else {
                        return null;
                    }
                }
            }
        }

        public void pointsInRect(RectHV targetRect, List<Point2D> results) {
            if (targetRect.contains(p)) {
                results.add(p);
            }
            if (lb != null && lb.rect.intersects(targetRect)) {
                lb.pointsInRect(targetRect, results);
            }
            if (rt != null && rt.rect.intersects(targetRect)) {
                rt.pointsInRect(targetRect, results);
            }
        }

        public Point2D closest(Point2D currNearest, Point2D targetPoint, double currentBestDistance) {
            double distance = targetPoint.distanceSquaredTo(p);
            if (distance < currentBestDistance) {
                currNearest = p;
                currentBestDistance = distance;
            }
            boolean leftEligible = (lb != null && (lb.rect.distanceSquaredTo(targetPoint) < currentBestDistance));
            boolean rightEligible = (rt != null && (rt.rect.distanceSquaredTo(targetPoint) < currentBestDistance));
            if (leftEligible && rightEligible) {
                boolean firstRight = true;
                if ((orientation == VERTICAL && targetPoint.x() < p.x()) || (orientation == HORIZONTAL && targetPoint.y() < p.y())) {
                    firstRight = false;
                }
                if (firstRight) {
                    Point2D fromRight = rt.closest(currNearest, targetPoint, currentBestDistance);
                    if (lb.rect.distanceSquaredTo(targetPoint) < fromRight.distanceSquaredTo(targetPoint)) {
                        Point2D fromLeft = lb.closest(currNearest, targetPoint, currentBestDistance);
                        return nearestFromTwo(fromLeft, fromRight, targetPoint);
                    } else {
                        return fromRight;
                    }
                } else {
                    Point2D fromLeft = lb.closest(currNearest, targetPoint, currentBestDistance);
                    if (rt.rect.distanceSquaredTo(targetPoint) < fromLeft.distanceSquaredTo(targetPoint)) {
                        Point2D fromRight = rt.closest(currNearest, targetPoint, currentBestDistance);
                        return nearestFromTwo(fromLeft, fromRight, targetPoint);
                    } else {
                        return fromLeft;
                    }
                }
            } else if (leftEligible) {
                return lb.closest(currNearest, targetPoint, currentBestDistance);
            } else if (rightEligible) {
                return rt.closest(currNearest, targetPoint, currentBestDistance);
            }
            return currNearest;
        }


        private Point2D nearestFromTwo(Point2D p1, Point2D p2, Point2D target) {
            if (target.distanceSquaredTo(p1) < target.distanceSquaredTo(p2)) {
                return p1;
            } else {
                return p2;
            }
        }
    }

    private int size = 0;
    private Node root = null;

    public KdTree() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (root == null) {
            root = new Node(p, VERTICAL, null);
            size++;
        } else {
            if (!contains(p)) {
                root.addPoint(p);
                size++;
            }
        }
    }

    public boolean contains(Point2D p) {
        if (root == null) {
            return false;
        } else {
            return root.findPoint(p) != null;
        }
    }

    public void draw() {
    }

    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> result = new LinkedList<Point2D>();
        if (root != null && root.rect.intersects(rect)) {
            root.pointsInRect(rect, result);
        }
        return result;
    }

    public Point2D nearest(Point2D p) {
        if (root != null) {
            Point2D resPoint = root.p;
            return root.closest(resPoint, p, Double.MAX_VALUE);
        } else {
            return null;
        }
    }
}
