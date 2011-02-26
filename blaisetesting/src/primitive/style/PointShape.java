/*
 * PointShape.java
 * Created Jan 8, 2011
 */

package primitive.style;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

/**
 * Provides methods for drawing various custom shapes.
 * @author elisha
 */
public enum PointShape {
    CIRCLE(true, 0, 0, 0){ public Shape getShape(double x, double y, double radius) {
        return new Ellipse2D.Double(x - radius, y - radius, 2*radius, 2*radius);
    }},
    SQUARE(true, 90, 0, 0){ public Shape getShape(double x, double y, double radius) {
        return new Rectangle2D.Double(x - radius/Math.sqrt(2), y - radius/Math.sqrt(2), 2*radius/Math.sqrt(2), 2*radius/Math.sqrt(2));
    }},
    DIAMOND(true, 90, 0, 0){ public Shape getShape(double x, double y, double radius) {
        GeneralPath gp = new GeneralPath();
        gp.moveTo((float)x, (float)(y-radius));
        gp.lineTo((float)(x-radius), (float)y);
        gp.lineTo((float)x, (float)(y+radius));
        gp.lineTo((float)(x+radius), (float)y);
        gp.closePath();
        return gp;
    }},
    TRIANGLE(true, 120, 90, 90){ public Shape getShape(double x, double y, double radius) {
        GeneralPath gp2 = new GeneralPath();
        gp2.moveTo((float)x, (float) (y-radius));
        gp2.lineTo((float)(x+radius*Math.cos(Math.PI*1.16667)), (float)(y-radius*Math.sin(Math.PI*1.16667)));
        gp2.lineTo((float)(x+radius*Math.cos(Math.PI*1.83333)), (float)(y-radius*Math.sin(Math.PI*1.83333)));
        gp2.closePath();
        return gp2;
    }},
    PLUS(false, 90, 0, 0){ public Shape getShape(double x, double y, double radius) {
        GeneralPath gp3 = new GeneralPath();
        gp3.moveTo((float) x, (float) (y-radius));
        gp3.lineTo((float) x, (float) (y+radius));
        gp3.moveTo((float) (x-radius), (float) y);
        gp3.lineTo((float) (x+radius), (float) y);
        return gp3;
    }},
    CROSS(false, 90, 0, 0){ public Shape getShape(double x, double y, double radius) {
        GeneralPath gp4 = new GeneralPath();
        double r2 = 0.7 * radius;
        gp4.moveTo((float) (x-r2), (float) (y-r2));
        gp4.lineTo((float) (x+r2), (float) (y+r2));
        gp4.moveTo((float) (x-r2), (float) (y+r2));
        gp4.lineTo((float) (x+r2), (float) (y-r2));
        return gp4;
    }},
    STAR(true, 72, 90, 90){ public Shape getShape(double x, double y, double radius) {
        GeneralPath gp5 = new GeneralPath();
        gp5.moveTo((float)x, (float) (y-radius));
        for (int i = 0; i < 5; i++) {
            double angle = Math.PI/2 + 2*Math.PI*i/5;
            gp5.lineTo((float)(x+radius*Math.cos(angle)), (float)(y-radius*Math.sin(angle)));
            angle += Math.PI/5;
            gp5.lineTo((float)(x+radius/Math.sqrt(8)*Math.cos(angle)), (float)(y-radius/Math.sqrt(8)*Math.sin(angle)));
        }
        gp5.closePath();
        return gp5;
    }},
    STAR_7(true, 51, 90, 90){ public Shape getShape(double x, double y, double radius) {
        GeneralPath gp6 = new GeneralPath();
        gp6.moveTo((float)x, (float) (y-radius));
        for (int i = 0; i < 7; i++) {
            double angle = Math.PI/2 + 2*Math.PI*i/7;
            gp6.lineTo((float)(x+radius*Math.cos(angle)), (float)(y-radius*Math.sin(angle)));
            angle += Math.PI/7;
            gp6.lineTo((float)(x+radius/2*Math.cos(angle)), (float)(y-radius/2*Math.sin(angle)));
        }
        gp6.closePath();
        return gp6;
    }},
    STAR_11(true, 33, 90, 90){ public Shape getShape(double x, double y, double radius) {
        GeneralPath gp7 = new GeneralPath();
        gp7.moveTo((float)x, (float) (y-radius));
        for (int i = 0; i < 11; i++) {
            double angle = Math.PI/2 + 2*Math.PI*i/11;
            gp7.lineTo((float)(x+radius*Math.cos(angle)), (float)(y-radius*Math.sin(angle)));
            angle += Math.PI/11;
            gp7.lineTo((float)(x+radius/1.5*Math.cos(angle)), (float)(y-radius/1.5*Math.sin(angle)));
        }
        gp7.closePath();
        return gp7;
    }},
    HAPPY_FACE(true, 360, 90, 0){ public Shape getShape(double x, double y, double radius) {
        GeneralPath gp8 = new GeneralPath();
        gp8.append(new Ellipse2D.Double(x - radius, y - radius, 2*radius, 2*radius), false);
        gp8.append(new Ellipse2D.Double(x - radius/3 - radius/12, y - radius/3, radius/6, radius/6), false);
        gp8.append(new Ellipse2D.Double(x + radius/3 - radius/12, y - radius/3, radius/6, radius/6), false);
        gp8.append(new Arc2D.Double(x - radius/2, y - radius/2, radius, radius, 200, 140, Arc2D.CHORD), false);
        return gp8;
    }},
    HOUSE(true, 360, -1, 0){ public Shape getShape(double x, double y, double radius) {
        GeneralPath gp13 = new GeneralPath();
        gp13.moveTo(-.9f, -.9f);
        gp13.lineTo(.9f, -.9f); gp13.lineTo(.9f, .5f); gp13.lineTo(1f, .5f);
        gp13.lineTo(.75f, .625f); gp13.lineTo(.75f, 1f); gp13.lineTo(.5f, 1f); gp13.lineTo(.5f,.75f);
        gp13.lineTo(0f, 1f); gp13.lineTo(-1f, .5f); gp13.lineTo(-.9f, .5f); gp13.lineTo(-.9f, -.9f);
        gp13.closePath();
        gp13.transform(new AffineTransform(radius, 0, 0, -radius, x, y));
        return gp13;
    }},
    TRIANGLE_SIDE(true, 360, 0, 0){ public Shape getShape(double x, double y, double radius) {
        GeneralPath gp9 = new GeneralPath();
        gp9.moveTo((float)(x+radius), (float) y);
        gp9.lineTo((float)(x+radius*Math.cos(Math.PI*0.6667)), (float)(y-radius*Math.sin(Math.PI*0.6667)));
        gp9.lineTo((float)(x+radius*Math.cos(Math.PI*1.3333)), (float)(y-radius*Math.sin(Math.PI*1.3333)));
        gp9.closePath();
        return gp9;
    }},
    ARROWHEAD(true, 360, 0, 0){ public Shape getShape(double x, double y, double radius) {
        GeneralPath gp10 = new GeneralPath();
        gp10.moveTo((float)(x+radius), (float) y);
        gp10.lineTo((float)(x-radius), (float)(y+radius));
        gp10.lineTo((float)(x-.5*radius), (float) y);
        gp10.lineTo((float)(x-radius), (float)(y-radius));
        gp10.closePath();
        return gp10;
    }},
    TEARDROP(true, 360, 0, 0){ public Shape getShape(double x, double y, double radius) {
        GeneralPath gp11 = new GeneralPath();
        gp11.moveTo(-.25f, -.5f);
        gp11.curveTo(-1f, -.5f, -1f, .5f, -.25f, .5f);
        gp11.curveTo(.5f, .5f, .5f, 0, 1f, 0);
        gp11.curveTo(.5f, 0, .5f, -.5f, -.2f, -.5f);
        gp11.closePath();
        gp11.transform(new AffineTransform(radius, 0, 0, radius, x, y));
        return gp11;
    }},
    CAR(true, 360, -1, 0){ public Shape getShape(double x, double y, double radius) {
        GeneralPath gp12 = new GeneralPath();
        gp12.moveTo(1f, 0);
        gp12.lineTo(.67f, 0);
        gp12.append(new Arc2D.Double(-.33f, -.5f,   1f,   1f, 0, 180, Arc2D.OPEN), true); // top
        gp12.append(new Arc2D.Double(-.83f,   0f,   1f, .67f, 90, 90, Arc2D.OPEN), true); // hood
        gp12.append(new Arc2D.Double(-1f,   .33f, .33f, .33f, 90, 90, Arc2D.OPEN), true); // bumper
        gp12.lineTo(-.7f, .5f);
        gp12.append(new Arc2D.Double(-.7f, .3f, .4f, .4f, 180, -180, Arc2D.OPEN), true); // wheel well
        gp12.lineTo(.3f, .5f);
        gp12.append(new Arc2D.Double(.3f, .3f, .4f, .4f, 180, -180, Arc2D.OPEN), true); // wheel well
        gp12.lineTo(1f, .5f);
        gp12.closePath();

        gp12.append(new Arc2D.Double(-.2f,  -.4f, .7f, .6f, 90, 90, Arc2D.PIE), false); // windows
        gp12.append(new Arc2D.Double(-.05f, -.4f, .6f, .6f, 0, 90, Arc2D.PIE), false); // windows

        gp12.append(new Ellipse2D.Double(-.67f, .33f, .33f, .33f), false); // wheels
        gp12.append(new Ellipse2D.Double( .33f, .33f, .33f, .33f), false);
        gp12.transform(new AffineTransform(-radius, 0, 0, radius, x, y));
        return gp12;
    }};

    /** Whether shape is fillable */
    boolean fillable;
    /** Rotational symmetry in degrees (0 for all, 360 for none) */
    int rSymmetry;
    /** Degree of axis of mirror symmetry (-1 for none) */
    int mSymmetry;
    /** Primary direction of point (0 for none/default) */
    int dir;

    /** 
     * Construct with symmetry notes
     * @param fillable whether shape encloses an area
     * @param rSymmetry degrees of rotational symmetry (360 for none, 0 for all)
     * @param mSymmetry degree of main axis of mirror symmetry
     * @parm dir degrees of primary direction
     */
    PointShape(boolean fillable, int rSymmetry, int mSymmetry, int dir) {
        this.fillable = fillable;
        this.rSymmetry = rSymmetry;
        this.mSymmetry = mSymmetry;
        this.dir = dir;
    }

    /**
     * @param shape enum describing type of shape
     * @param x x coordinate of center of drawn shape
     * @param y y coordinate of center of drawn shape
     * @param radius radius of drawn shape
     * @return shape of given parameters for given shape type
     */
    abstract public Shape getShape(double x, double y, double radius);
}