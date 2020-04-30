package com.prinston2.seam_carving;

import com.prinston2.Prinston2Test;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SeamCarverTest extends Prinston2Test {

    @Test
    public void energyTest() {
        Picture pic = new Picture(getPrinston2File("seam_carving/test3_4.png"));
        SeamCarver seamCarver = new SeamCarver(pic);
        double energy11 = seamCarver.energy(1, 1);
        double energy12 = seamCarver.energy(1, 2);
        double energy01 = seamCarver.energy(0, 1);
        double energy20 = seamCarver.energy(2, 0);

        assert energy01 == 1000;
        assert energy20 == 1000;
        assert Math.round(energy11*energy11) == 52225;
        assert Math.round(energy12*energy12) == 52024;
    }

    @Test
    public void seamTest() {
        Picture pic = new Picture(getPrinston2File("seam_carving/6x5.png"));
        SeamCarver seamCarver = new SeamCarver(pic);
        printEnergies(seamCarver.picture());

        int[] seam = seamCarver.findHorizontalSeam();
        System.out.println("\nSeam: ");
        for (int i : seam) {
            System.out.print(i + ", ");
        }

        seamCarver.removeHorizontalSeam(seam);
        printEnergies(seamCarver.picture());

        seamCarver.picture().show();
        while (true) {
            int a = 1;
        }
    }

    private void printEnergies(Picture picture) {
        StdOut.printf("image is %d pixels wide by %d pixels high.\n", picture.width(), picture.height());
        SeamCarver sc = new SeamCarver(picture);
        StdOut.printf("Printing energy calculated for each pixel.\n");
        for (int row = 0; row < sc.height(); row++) {
            for (int col = 0; col < sc.width(); col++)
                StdOut.printf("%9.0f ", sc.energy(col, row));
            StdOut.println();
        }
    }


}
