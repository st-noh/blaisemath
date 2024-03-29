/**
 * IconRenderer.java
 * Created on Mar 5, 2015
 */
package com.googlecode.blaisemath.graphics.swing;

/*
 * #%L
 * BlaiseGraphics
 * --
 * Copyright (C) 2009 - 2015 Elisha Peterson
 * --
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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

import com.googlecode.blaisemath.style.AttributeSet;
import com.googlecode.blaisemath.style.Renderer;
import com.googlecode.blaisemath.util.AnchoredIcon;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Renderer for drawing an icon on a canvas.
 * 
 * @author petereb1
 */
public class IconRenderer implements Renderer<AnchoredIcon, Graphics2D> {

    private static final IconRenderer INST = new IconRenderer();
    
    public static Renderer<AnchoredIcon, Graphics2D> getInstance() {
        return INST;
    }

    @Override
    public void render(AnchoredIcon primitive, AttributeSet style, Graphics2D canvas) {
        primitive.getIcon().paintIcon(null, canvas, (int) primitive.getX(), (int) primitive.getY());
    }

    @Override
    public Rectangle2D boundingBox(AnchoredIcon primitive, AttributeSet style) {
        return new Rectangle2D.Double(primitive.getX(), primitive.getY(), 
                primitive.getIconWidth(), primitive.getIconHeight());
    }

    @Override
    public boolean contains(AnchoredIcon primitive, AttributeSet style, Point2D point) {
        return boundingBox(primitive, style).contains(point);
    }

    @Override
    public boolean intersects(AnchoredIcon primitive, AttributeSet style, Rectangle2D rect) {
        return boundingBox(primitive, style).intersects(rect);
    }

}
