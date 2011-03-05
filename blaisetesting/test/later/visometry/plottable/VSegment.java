/**
 * VSegment.java
 * Created on Apr 8, 2010
 */

package later.visometry.plottable;

import java.awt.Color;
import primitive.style.temp.PathStylePoints;
import primitive.style.temp.PointLabeledStyle;
import deprecated.visometry.VMouseDragListener;

/**
 * <p>
 *   <code>VSegment</code> displays a segment between two points. It is dynamic, allowing
 *   the points to be moved around.
 * </p>
 *
 * @author Elisha Peterson
 */
public class VSegment<C> extends VAbstractTwoPoint<C>
        implements VMouseDragListener<C> {
    
    /** Style for points. */
    PointLabeledStyle pointStyle;

    /** Construct to specified coordinates */
    public VSegment(C... values) {
        super(values, new PointLabeledStyle(), new PathStylePoints(new Color(64, 0, 0)));
    }

    /** @return current style of stroke for this plottable */
    public PathStylePoints getStrokeStyle() { return (PathStylePoints) entrySeg.renderer; }
    /** Set current style of stroke for this plottable */
    public void setStrokeStyle(PathStylePoints newValue) { if (entrySeg.renderer != newValue) { entrySeg.renderer = newValue; firePlottableStyleChanged(); } }

    /** @return true if points at vertices of polygon are visible */
    public boolean isPointsVisible() { return entryP1.visible; }
    /** Sets visiblity of the vertices. */
    public void setPointsVisible(boolean value) { 
        if (entryP1.visible != value) { entryP1.visible = entryP2.visible = value; firePlottableStyleChanged();
        }
    }

    /** @return current style of stroke for this plottable */
    public PointLabeledStyle getPointStyle() { return (PointLabeledStyle) entryP1.renderer; }
    /** Set current style of stroke for this plottable */
    public void setPointStyle(PointLabeledStyle newValue) { if (entryP1.renderer != newValue) { entryP1.renderer = newValue; entryP2.renderer = newValue; firePlottableStyleChanged(); } }

}
