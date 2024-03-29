/**
 * BoundingBoxGesture.java
 * Created Dec 13, 2014
 */
package com.googlecode.blaisemath.gesture.swing;

/*
 * #%L
 * BlaiseSketch
 * --
 * Copyright (C) 2015 Elisha Peterson
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


import com.googlecode.blaisemath.gesture.GestureOrchestrator;
import static com.google.common.base.Preconditions.checkArgument;
import com.googlecode.blaisemath.gesture.MouseGestureSupport;
import com.googlecode.blaisemath.graphics.core.PrimitiveGraphicSupport;
import com.googlecode.blaisemath.graphics.swing.JGraphicComponent;
import com.googlecode.blaisemath.graphics.swing.MarkerRenderer;
import com.googlecode.blaisemath.style.AttributeSet;
import com.googlecode.blaisemath.style.Markers;
import com.googlecode.blaisemath.style.Styles;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Displays control points on a line, allowing it to be changed.
 * 
 * @author elisha
 */
public class ControlLineGesture extends MouseGestureSupport {
    
    private static final int CAPTURE_RAD = 5;
    
    private final JGraphicComponent view;
    
    private final PrimitiveGraphicSupport graphic;
    private final AttributeSet controlStyle = Styles.fillStroke(new Color(0,0,255,64), null)
            .and(Styles.MARKER, Markers.CIRCLE)
            .and(Styles.MARKER_RADIUS, CAPTURE_RAD);
    private final AttributeSet selectedControlStyle = Styles.fillStroke(new Color(0,0,255,128), null)
            .and(Styles.MARKER, Markers.CIRCLE)
            .and(Styles.MARKER_RADIUS, CAPTURE_RAD);
    
    /** Current active control */
    private ControlPoint controlPoint;
    /** Line at start of drag */
    private Line2D startShape;
    
    public ControlLineGesture(GestureOrchestrator orchestrator, PrimitiveGraphicSupport graphic) {
        super(orchestrator, "Line editor", "Edit line control points");
        
        checkArgument(orchestrator.getComponent() instanceof JGraphicComponent, 
                "Orchestrator must use a JGraphicComponent");
        checkArgument(graphic != null && graphic.getPrimitive() instanceof Line2D);
        
        view = (JGraphicComponent) orchestrator.getComponent();
        this.graphic = graphic;
    }
    
    private Rectangle2D box() {
        return graphic.boundingBox();
    }

    @Override
    public void paint(Graphics2D g) {
        AffineTransform at = view.getTransform() == null ? new AffineTransform() : view.getTransform();
        Line2D line = (Line2D) graphic.getPrimitive();
        Point2D trStart = at.transform(line.getP1(), null);
        Point2D trEnd = at.transform(line.getP2(), null);
        Line2D trLine = new Line2D.Double(trStart, trEnd);
        for (ControlPoint cp : ControlPoint.values()) {
            AttributeSet sty = cp == controlPoint ? selectedControlStyle : controlStyle;
            Point2D pt = cp.location(trLine);
            MarkerRenderer.getInstance().render(pt, sty, g);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        startShape = (Line2D) graphic.getPrimitive();
        controlPoint = capture(startShape, pressPoint);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if (controlPoint != null) {
            double dx = locPoint.getX() - pressPoint.getX();
            double dy = locPoint.getY() - pressPoint.getY();
            Line2D transf = controlPoint.resize(startShape, dx, dy);
            graphic.setPrimitive(transf);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        controlPoint = null;
        startShape = null;
    }

    @Override
    public void finish() {
        controlPoint = null;
        startShape = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (graphic != null) {
            Point2D clickPt = transformedPoint(e);
            if (!graphic.contains(clickPt) && capture((Line2D) graphic.getPrimitive(), clickPt) == null) {
                orchestrator.finishGesture(this);
                view.getSelectionModel().deselect(graphic);
            }
        }
    }
    
    //
    // ControlPoint handling
    //

    // get the control point for the given press point
    private static ControlPoint capture(Line2D line, Point2D pressPoint) {
        for (ControlPoint cp : ControlPoint.values()) {
            if (cp.captures(line, pressPoint)) {
                return cp;
            }
        }
        return null;
    }
    
    private static enum ControlPoint {
        START {
            @Override
            Point2D location(Line2D line) {
                return line.getP1();
            }
            @Override
            Line2D resize(Line2D initial, double dx, double dy) {
                return new Line2D.Double(initial.getX1()+dx, initial.getY1()+dy,
                        initial.getX2(), initial.getY2());
            }
        },
        END {
            @Override
            Point2D location(Line2D line) {
                return line.getP2();
            }
            @Override
            Line2D resize(Line2D initial, double dx, double dy) {
                return new Line2D.Double(initial.getX1(), initial.getY1(),
                        initial.getX2()+dx, initial.getY2()+dy);
            }
        };

        /** Get location of control point, for the given bounds. */
        abstract Point2D location(Line2D line);

        /**
         * Edit line endpoints based on drag.
         * @param initial initial line
         * @param dx cumulative delta x during drag
         * @param dy cumulative delta y during drag
         * @return transformed line
         */
        abstract Line2D resize(Line2D initial, double dx, double dy);
        
        /** Test whether control point can use the given press point */
        private boolean captures(Line2D line, Point2D pressPoint) {
            double dist = location(line).distance(pressPoint);
            return dist < CAPTURE_RAD;
        }
    }
    
}
