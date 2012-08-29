/**
 * DelegatingShapeGraphic.java
 * Created Aug 28, 2012
 */

package org.blaise.graphics;

import java.awt.Shape;
import org.blaise.style.ObjectStyler;
import org.blaise.style.ShapeStyle;

/**
 * <p>
 *  Delegates style and some other properties of a {@link Graphic} to an {@link ObjectStyler}.
 * </p>
 * @param <Src> type of source object
 * @author elisha
 */
public class DelegatingShapeGraphic<Src> extends AbstractShapeGraphic {
    
    /** Source object */
    protected Src source;
    /** Styler managing delgators */
    protected ObjectStyler<Src,? extends ShapeStyle> styler = new ObjectStyler<Src,ShapeStyle>();

    /** Initialize without arguments */
    public DelegatingShapeGraphic() {
        this(null, null, false);
    }

    /** Initialize with source (source object) and graphic */
    public DelegatingShapeGraphic(Src obj, Shape shape, boolean strokeOnly) {
        super(shape, strokeOnly);
        setSourceObject(obj);
    }

    public Src getSourceObject() {
        return source;
    }

    public void setSourceObject(Src edge) {
        this.source = edge;
        setDefaultTooltip(styler.getTipDelegate().of(edge));
        fireGraphicChanged();
    }

    public ObjectStyler<Src,? extends ShapeStyle> getStyler() {
        return styler;
    }

    public void setStyler(ObjectStyler<Src,? extends ShapeStyle> styler) {
        this.styler = styler;
        setDefaultTooltip(styler.getTipDelegate().of(source));
        fireGraphicChanged();
    }

    @Override
    public ShapeStyle drawStyle() {
        ShapeStyle style = styler.getStyleDelegate().of(source);
        if (style != null) {
            return style;
        }
        return paintingAsStroke() ? parent.getStyleProvider().getPathStyle(this)
                : parent.getStyleProvider().getShapeStyle(this);
    }

}