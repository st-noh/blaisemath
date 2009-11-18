/**
 * PlaneVectorField.java
 * Created on Sep 3, 2009
 */

package org.bm.blaise.specto.plane.diffeq;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.MultivariateVectorialFunction;
import org.bm.blaise.specto.plane.PlaneVisometry;
import org.bm.blaise.specto.plottable.VPrimitiveMappingPlottable;
import org.bm.blaise.specto.primitive.GraphicArrow;
import org.bm.blaise.specto.primitive.ArrowStyle;
import org.bm.blaise.specto.primitive.BlaisePalette;
import org.bm.blaise.specto.visometry.Visometry;
import org.bm.blaise.specto.visometry.VisometryGraphics;
import scio.coordinate.sample.SampleCoordinateSetGenerator;

/**
 * <p>
 *   <code>PlaneVectorField</code> displays a vector field, specified by an underlying
 *   function with 2 inputs and 2 outputs (a <code>MultivariateVectorialFunction</code>).
 * </p>
 *
 * @author Elisha Peterson
 */
public class PlaneVectorField extends VPrimitiveMappingPlottable<Point2D.Double, GraphicArrow> {

    /** Style for drawing the vectors. */
    private static ArrowStyle DEFAULT_STYLE = new ArrowStyle(BlaisePalette.STANDARD.vector(), ArrowStyle.ArrowShape.REGULAR, 5);

    /** Underlying function */
    MultivariateVectorialFunction func;

    /** Whether arrows are centered at sample points. */
    boolean centered;
    /** Multiplier for vec field length. */
    double lengthMultiplier = 1;

    /**
     * Construct the vector field.
     * @param func underlying function that determines the vectors
     */
    public PlaneVectorField(MultivariateVectorialFunction func, SampleCoordinateSetGenerator<Point2D.Double> ssg) {
        super((ArrowStyle) DEFAULT_STYLE.clone(), ssg);
        setFunc(func);
    }


    //
    //
    // BEAN PATTERNS
    //
    //

    /** @return function describing the field */
    public MultivariateVectorialFunction getFunc() {
        return func;
    }

    /**
     * Sets the function for the field.
     * @param func the function
     */
    public void setFunc(MultivariateVectorialFunction func) {
        this.func = func;
    }

    public ArrowStyle getStyle() {
        return (ArrowStyle) style;
    }

    public void setStyle(ArrowStyle arrowStyle) {
        this.style = arrowStyle;
    }

    public boolean isCentered() {
        return centered;
    }

    public void setCentered(boolean centered) {
        this.centered = centered;
    }

    public double getLengthMultiplier() {
        return lengthMultiplier;
    }

    public void setLengthMultiplier(double lengthMultiplier) {
        this.lengthMultiplier = lengthMultiplier;
    }

    
    //
    //
    // DRAW METHODS
    //
    //

    @Override
    public void scalePrimitives(Visometry vis) {
        PlaneVisometry pv = (PlaneVisometry) vis;
        double maxRad = Math.min(Math.abs(ssg.getSampleDiff().x * pv.getScaleX()), Math.abs(ssg.getSampleDiff().y * pv.getScaleY()));
        GraphicArrow.scaleVectors(primitives, lengthMultiplier * maxRad, 0.9, centered);
    }

    public GraphicArrow primitiveAt(Point2D.Double coord, Visometry<Point2D.Double> vis, VisometryGraphics<Point2D.Double> vg) {
        double[] value = new double[]{0, 0};
        try {
            value = func.value(new double[]{coord.x, coord.y});
        } catch (FunctionEvaluationException ex) {
            Logger.getLogger(PlaneVectorField.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PlaneVectorField.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (centered) {
            return new GraphicArrow(
                    vis.getWindowPointOf(new Point2D.Double(coord.x - value[0]/2.0, coord.y - value[1]/2.0)),
                    vis.getWindowPointOf(new Point2D.Double(coord.x + value[0]/2.0, coord.y + value[1]/2.0)));
        } else {
            return new GraphicArrow(
                    vis.getWindowPointOf(coord),
                    vis.getWindowPointOf(new Point2D.Double(coord.x + value[0], coord.y + value[1])));
        }
    }

    public GraphicArrow[] primitivesAt(List<Point2D.Double> coords, Visometry<Point2D.Double> vis, VisometryGraphics<Point2D.Double> vg) {
        GraphicArrow[] result = new GraphicArrow[coords.size()];
        int i = 0;
        for (Point2D.Double c : coords) {
            result[i] = primitiveAt(c, vis, vg);
            i++;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Vector Field [" + ssg + "]";
    }
}
