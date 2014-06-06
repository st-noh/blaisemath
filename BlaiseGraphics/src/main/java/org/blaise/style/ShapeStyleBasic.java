/*
 * ShapeStyleBasic.java
 * Created Jan 9, 2011 (based on much earlier code)
 */

package org.blaise.style;

/*
 * #%L
 * BlaiseGraphics
 * --
 * Copyright (C) 2009 - 2014 Elisha Peterson
 * --
 * Licensed under the Apache License, Version 2.0.
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import javax.annotation.Nullable;

/**
 * Draws a shape using a stroke (with thickness) and a fill color.
 *
 * @author Elisha
 */
public class ShapeStyleBasic implements PathStyle, ShapeStyle {

    @Nullable 
    protected Color fill;
    @Nullable
    protected Color stroke;
    protected float strokeWidth = 1f;

    public ShapeStyleBasic() {
    }
    
    @Override
    public String toString() {
        return String.format("ShapeStyleBasic[fill=%s, stroke=%s, stroke_width=%.1f]", 
                fill, stroke, strokeWidth);
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="BUILDER PATTERNS">

    /** 
     * Sets fill color & returns pointer to object
     * @param c
     * @return 
     */
    public ShapeStyleBasic fill(@Nullable Color c) {
        fill = c;
        return this;
    }

    /** 
     * Sets stroke color & returns pointer to object
     * @param c
     * @return
     */
    public ShapeStyleBasic stroke(@Nullable Color c) {
        stroke = c;
        return this;
    }

    /** 
     * Sets strokeWidth & returns pointer to object
     * @param thick
     * @return 
     */
    public ShapeStyleBasic strokeWidth(float thick) {
        strokeWidth = thick;
        return this;
    }

    // </editor-fold>

    
    //<editor-fold defaultstate="collapsed" desc="PROPERTY PATTERNS">
    //
    // PROPERTY PATTERNS
    //
    
    @Nullable 
    public Color getFill() {
        return fill;
    }

    public void setFill(@Nullable Color fill) {
        this.fill = fill;
    }

    @Override
    @Nullable 
    public Color getStroke() {
        return stroke;
    }

    public void setStroke(@Nullable Color stroke) {
        this.stroke = stroke;
    }

    @Override
    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float thickness) {
        this.strokeWidth = thickness;
    }
    
    //</editor-fold>

    
    @Override
    public Shape shapeOfPath(Shape primitive) {
        return primitive;
    }

    @Override
    public void draw(Shape s, Graphics2D canvas, StyleHintSet visibility) {
        if (fill != null) {
            canvas.setColor(ColorUtils.applyHints(fill, visibility));
            canvas.fill(s);
        }
        if (stroke != null && strokeWidth >= 0) {
            canvas.setColor(ColorUtils.applyHints(stroke, visibility));
            canvas.setStroke(visibility != null
                    && (visibility.contains(StyleHints.HIGHLIGHT_HINT) || visibility.contains(StyleHints.SELECTED_HINT))
                    ? new BasicStroke(strokeWidth+1f) : new BasicStroke(strokeWidth));
            canvas.draw(s);
        }

        canvas.setStroke(Styles.DEFAULT_STROKE);
    }

}
