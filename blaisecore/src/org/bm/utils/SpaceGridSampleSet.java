/*
 * PlaneSampleSet.java
 * Created on Nov 4, 2009
 */

package org.bm.utils;

import java.awt.geom.Rectangle2D;
import java.util.AbstractList;
import java.util.List;
import scio.coordinate.P3D;
import scio.coordinate.sample.SampleCoordinateSetGenerator;

/**
 * <p>
 *     This class generates a grid of evenly-spaced sample points in space.
 * </p>
 * @author Elisha Peterson
 */
public class SpaceGridSampleSet implements SampleCoordinateSetGenerator<P3D> {

    /** Boundary of sampled region. */
    P3D min;
    P3D max;
    /** Border around the sampling region. */
    double sampleBorder = 0;
    /**
     * Determines whether the grid is sampled at "nice" points, i.e. corresponding
     * to integers, half-integers, etc.
     */
    boolean sampleNice = false;
    /** Sample numbers. */
    int nx = 5;
    int ny = 5;
    int nz = 5;

    public SpaceGridSampleSet(P3D min, P3D max) {
        this.min = min;
        this.max = max;
    }

    //
    //
    // BEANS
    //
    //

    public P3D getMax() {
        return max;
    }

    public void setMax(P3D max) {
        this.max = max;
    }

    public P3D getMin() {
        return min;
    }

    public void setMin(P3D min) {
        this.min = min;
    }

    public int getNX() {
        return nx;
    }

    public void setNX(int nx) {
        this.nx = nx;
    }

    public int getNY() {
        return ny;
    }

    public void setNY(int ny) {
        this.ny = ny;
    }

    public int getNZ() {
        return nz;
    }

    public void setNZ(int nz) {
        this.nz = nz;
    }

    public boolean isSampleNice() {
        return sampleNice;
    }

    public void setSampleNice(boolean sampleNice) {
        this.sampleNice = sampleNice;
    }
    

    //
    //
    // SAMPLE ALGORITHMS
    //
    //

    /** Sample step. */
    transient P3D diff = new P3D();

    /** 
     * Computes samples using the current boundaries.
     *
     * @param nXSamples number of samples in the x direction
     * @param nYSamples number of samples in the y direction
     * @param nZSamples number of samples in the z direction
     * @return an <code>AbstractList</code> containing the grid of sampled points
     */
    List<P3D> computeSamples(int nXSamples, int nYSamples, int nZSamples) {
        nx = nXSamples;
        ny = nYSamples;
        nz = nZSamples;
        diff.x = (max.x - min.x) / (nx - 1);
        diff.y = (max.y - min.y) / (ny - 1);
        diff.z = (max.z - min.z) / (nz - 1);
        return new AbstractList<P3D>() {
            int n = nx * ny * nz;
            @Override
            public P3D get(int index) {
                return new P3D(
                        min.x + diff.x * (index % nx),
                        min.y + diff.y * ( ((int) (index / nx)) % ny),
                        min.z + diff.z * ( ((int) (index / (nx * ny))) ) );
            }
            @Override
            public int size() {
                return n;
            }
        };
    }

    /**
     * Computes samples using the current boundaries; and puts the sample points
     * at "nice" coordinates. Tries to ensure the spacing is about what is suggested
     * by the parameters to the command, although this is not guaranteed, as placement
     * of the poitns takes priority.
     *
     * @param approxDX approximate spacing in the x direction
     * @param approxDY approximate spacing in the y direction
     * @param approxDZ approximate spacing in the z direction
     * @return an <code>AbstractList</code> containing the grid of sampled points
     */
    public List<P3D> computeNiceSamples(double approxDX, double approxDY, double approxDZ) {
        final double[] xRange = NiceRangeGenerator.STANDARD.niceRange(min.x, max.x, approxDX);
        final double[] yRange = NiceRangeGenerator.STANDARD.niceRange(min.y, max.y, approxDY);
        final double[] zRange = NiceRangeGenerator.STANDARD.niceRange(min.z, max.z, approxDZ);
        nx = xRange.length;
        ny = yRange.length;
        nz = zRange.length;
        diff.x = xRange[1] - xRange[0];
        diff.y = yRange[1] - yRange[0];
        diff.z = zRange[1] - zRange[0];
        return new AbstractList<P3D>() {
            int n = nx * ny * nz;
            @Override
            public P3D get(int index) {
                return new P3D(xRange[index % nx], yRange[((int) (index / nx)) % ny], zRange[index / (nx * ny)]);
            }
            @Override
            public int size() {
                return n;
            }
        };
    }

    public List<P3D> getSamples() {
        if (sampleNice) {
            return computeNiceSamples((max.x - min.x) / nx, (max.y - min.y) / ny, (max.z - min.z) / nz);
        } else {
            return computeSamples(nx, ny, nz);
        }
    }

    public P3D getSampleDiff() {
        return diff;
    }
}
