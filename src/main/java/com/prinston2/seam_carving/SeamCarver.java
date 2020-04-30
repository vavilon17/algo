package com.prinston2.seam_carving;

import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {


    private static final int BORDER_ENERGY = 1000;

    private Picture picture;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }
        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return picture;
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x > width() - 1 || y < 0 || y > height() - 1) {
            throw new IllegalArgumentException();
        }
        // we set pretty high energy for the image borders
        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
            return BORDER_ENERGY;
        }
        double deltaXSquared = calcEnergyGradient(x - 1, y, x + 1, y);
        double deltaYSquared = calcEnergyGradient(x, y - 1, x, y + 1);

        return Math.sqrt(deltaXSquared + deltaYSquared);
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        Picture tempPict = new Picture(picture.height(), picture.width());
        for (int i = 0; i < tempPict.width(); i++) {
            for (int j = 0; j < tempPict.height(); j++) {
                tempPict.set(i, j, picture.get(j, i));
            }
        }
        return new SeamCarver(tempPict).findVerticalSeam();
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        // first, prepare the energy matrix
        double[][] energyMatrix = new double[width()][height()];
        for (int i = 0; i < width(); i ++) {
            for (int j = 0; j < height(); j++) {
                energyMatrix[i][j] = energy(i, j);
            }
        }

        double[][] distTo = new double[width()][height()];
        for (int i = 0; i < width(); i++) {
            distTo[i][0] = BORDER_ENERGY;
            for (int j = 1; j < height(); j++) {
                distTo[i][j] = Double.MAX_VALUE;
            }
        }

        int[][] edgeTo = new int[width()][height()];

        for (int j = 1; j < height(); j++) {
            for (int i = 0; i < width(); i++) {
                processPixel(i, j, distTo, edgeTo, energyMatrix);
            }
        }

        int minPathX = -1;
        double minPath = Double.MAX_VALUE;
        for (int i = 0; i < width(); i++) {
            if (distTo[i][height() - 1] < minPath) {
                minPathX = i;
                minPath = distTo[i][height() - 1];
            }
        }

        int[] resPath = new int[height()];
        resPath[height() - 1] = minPathX;
        for (int j = height() - 1; j > 0; j--) {
            int prevLevelX = edgeTo[resPath[j]][j];
            resPath[j - 1] = prevLevelX;
        }

        return resPath;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        validateSeam(seam, width(), height() - 1);

        Picture oldPicture = this.picture;
        int newHeight = height() - 1;
        Picture newPicture = new Picture(oldPicture.width(), newHeight);
        for (int i = 0; i < width(); i++) {
            int yToRemove = seam[i];
            for (int j = 0; j < newHeight; j++) {
                Color newColor = (j < yToRemove) ? oldPicture.get(i, j) : oldPicture.get(i, j + 1);
                newPicture.set(i, j, newColor);
            }
        }
        this.picture = newPicture;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        validateSeam(seam, height(), width() - 1);

        Picture oldPicture = this.picture;
        int newWidth = oldPicture.width() - 1;
        Picture newPicture = new Picture(newWidth, oldPicture.height());
        for (int j = 0; j < height(); j++) {
            int xToRemove = seam[j];
            for (int i = 0; i < newWidth; i++) {
                Color newColor = (i < xToRemove) ? oldPicture.get(i, j) : oldPicture.get(i + 1, j);
                newPicture.set(i, j, newColor);
            }
        }
        this.picture = newPicture;
    }

    private double calcEnergyGradient(int x1, int y1, int x2, int y2) {
        Color col1 = picture.get(x1, y1);
        Color col2 = picture.get(x2, y2);
        int deltaRed = col1.getRed() - col2.getRed();
        int deltaGreen = col1.getGreen() - col2.getGreen();
        int deltaBlue = col1.getBlue() - col2.getBlue();
        return deltaRed*deltaRed + deltaGreen*deltaGreen + deltaBlue*deltaBlue;
    }

    private void processPixel(int x, int y, double[][] distTo, int[][] edgeTo, double[][] energyMatrix) {
        double distUpLeft = (x == 0) ? Double.MAX_VALUE : distTo[x-1][y-1];
        double distUpUp = distTo[x][y-1];
        double distUpRight = (x == width() - 1) ? Double.MAX_VALUE : distTo[x+1][y-1];
        double smallest = Math.min(distUpLeft, Math.min(distUpUp, distUpRight));
        distTo[x][y] = smallest + energyMatrix[x][y];
        if (smallest == distUpLeft) {
             edgeTo[x][y] = x - 1;
        } else if (smallest == distUpUp) {
             edgeTo[x][y] = x;
        } else {
            edgeTo[x][y] = x + 1;
        }
    }

    private void validateSeam(int[] seam, int requiredLength, int maxIndex) {
        if (seam == null || seam.length != requiredLength) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] > maxIndex) {
                throw new IllegalArgumentException();
            }
            if (i > 0 && Math.abs(seam[i] - seam[i - 1]) > 1) {
                throw new IllegalArgumentException();
            }
        }
    }
}

