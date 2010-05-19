/**
 * PlotComponent.java
 * Created on Jul 30, 2009
 */

package visometry;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Collection;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import visometry.plottable.Plottable;
import visometry.plottable.PlottableGroup;

/**
 * <p>
 *   <code>PlotComponent</code> is an automatic implementation of a plot window with an underlying visometry.
 *   Plottable elements are stored within a single <code>PlottableGroup</code>, and this class passes on mouse
 *   events to that subgroup, or to the visometry if no plottable is able to handle it.
 *   We use generic typing to enforce the type of plottable that is added to this panel.
 *   Event handling is set up for a timer that is owned by the panel (<code>ActionEvent</code>s), as well as for any changes
 *   (<code>ChangeEvent</code>s) that occur on the plottables contained herein.
 * </p>
 * <p>
 *   Note that the plottables themselves are responsible for handling any computations
 *   required for displaying or painting. These computations may happen only once, or
 *   they may depend on the painted window, or they may depend on a timer. This class
 *   owns a <code>ClockTimer</code> that may be used to repaint <code>AnimatingPlottable</code>s
 *   as that timer fires <code>ActionEvent</code>s. If a <code>Plottable</code> changes,
 *   and this class is notified, it is assumed that any plottables that must be recomputed
 *   have already achieved this, so no effort is placed into recomputation. But if the
 *   underlying <code>Visometry</code> changes in some way, a global <code>recompute</code>
 *   command is passed along to the <code>Plottable</code>s.
 * </p>
 * <p>
 *   This significant revision of the original <code>PlotComponent</code> class is intended to be very lightweight,
 *   without many extra features that might get in the way of the basic functionality.
 * </p>
 *
 * @param <C> the coordinate type of the plottable
 *
 * @see ClockTimer
 * @see Plottable
 * @see PlottableGroup
 * @see Visometry
 * @see VisometryGraphics
 *
 * @author Elisha Peterson
 */
public class PlotComponent<C> extends javax.swing.JComponent
        implements ChangeListener, ComponentListener, 
            MouseListener, MouseMotionListener, MouseWheelListener {

    /** Stores the visual components. */
    protected PlottableGroup<C> pGroup;
    /** Used for the translation process. */
    protected Visometry<C> visometry;
    /** Responsible for the conversion of local primitives into screen primitives. */
    protected PlotProcessor<C> processor;
    /** Responsible for the drawing process. */
    protected SimplePlotRenderer renderer = new SimplePlotRenderer();

    /** Default handler for mouse events. */
    protected MouseListener defaultMouseListener;
    /** Default handler for mouse wheel events. */
    protected MouseWheelListener defaultMouseWheelListener;

    /** Construction of a generic plot component requires a visometry and a processor. */
    public PlotComponent(Visometry<C> vis, PlotProcessor<C> proc) {
        visometry = vis;
        processor = proc;        
        pGroup = new PlottableGroup<C>();

        addComponentListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        visometry.addChangeListener(this);
        pGroup.addChangeListener(this); // receive notification of recompute and redraw requests

        setBackground(Color.WHITE);
        setOpaque(true);
        setPreferredSize(new Dimension(300, 200));
    }

    //
    // BEAN PROPERTY PATTERNS
    //

    public Visometry<C> getVisometry() {
        return visometry;
    }

    public void setVisometry(Visometry<C> visometry) {
        this.visometry = visometry;
    }

    public SimplePlotRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(SimplePlotRenderer renderer) {
        this.renderer = renderer;
    }


    //
    // COMPOSITE METHODS
    //

    public void add(Plottable<C> plottable) {
        pGroup.add(plottable);
    }

    public void addAll(Collection<? extends Plottable<C>> pp) {
        pGroup.addAll(pp);
    }

    public boolean remove(Plottable<C> plottable) {
        return pGroup.remove(plottable);
    }

    public void clear() {
        pGroup.clear();
    }

    public Plottable[] getPlottableArray() {
        return pGroup.getPlottableArray();
    }

    

    //
    // PAINT METHODS
    //

    Graphics2D canvas;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        pGroup.recompute();                         // this will recompute objects, but only if necessary
        canvas = (Graphics2D) g;
        if (processor != null)
            processor.prepare(pGroup, visometry);   // this ensures all primitives have been converted to local coordinates
                                                    // and are positioned properly for the renderer
        renderer.draw(canvas, processor.primitives);    // this draws everything using the active renderer

        if (defaultMouseListener != null && defaultMouseListener instanceof PaintsCanvas)
            ((PaintsCanvas)defaultMouseListener).paint(canvas);
    }

    //
    // CHANGE EVENTS
    //

    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == pGroup)
            repaint();
        else if (e.getSource() == visometry) {
            for (VPrimitiveEntry en : pGroup)
                en.needsConversion = true;
            repaint();
        }
    }

    //
    // WINDOW SIZING EVENTS: PASS ON TO VISOMETRY
    //

    public void componentResized(ComponentEvent e) { visometry.setWindowBounds(getVisibleRect()); repaint(); }
    public void componentShown(ComponentEvent e) { visometry.setWindowBounds(getVisibleRect()); repaint(); }
    public void componentHidden(ComponentEvent e) {}
    public void componentMoved(ComponentEvent e) {}

    //
    // MOUSE EVENT HANDLING
    //

    /** The object which is handling mouse events. */
    VDraggablePrimitiveEntry handler;
    /** Starting point of drag. */
    C dragStart, dragCurrent;

    /** Finds an appropriate handler for the event, using the last drawn canvas to supply font metric info, etc. */
    private VDraggablePrimitiveEntry primitiveForEvent(MouseEvent e) {
        for (VPrimitiveEntry en : pGroup)
            if (en instanceof VDraggablePrimitiveEntry && en.handles(canvas, e))
                return (VDraggablePrimitiveEntry) en;
        return null;
    }

    public void mousePressed(MouseEvent e) { 
        handler = primitiveForEvent(e);
        if (handler != null)
            handler.fireDragInitiated( dragStart = visometry.getCoordinateOf(e.getPoint()) );
        else if (defaultMouseListener != null)
            defaultMouseListener.mousePressed(e);
    }

    public void mouseDragged(MouseEvent e) {
        if (handler != null)
            handler.fireDragged( dragCurrent = visometry.getCoordinateOf(e.getPoint()) );
        else if (defaultMouseListener != null && defaultMouseListener instanceof MouseMotionListener)
            ((MouseMotionListener) defaultMouseListener).mouseDragged(e);    }

    public void mouseReleased(MouseEvent e) {
        if (handler != null)
            handler.fireDragCompleted( dragCurrent = visometry.getCoordinateOf(e.getPoint()) );
        else if (defaultMouseListener != null)
            defaultMouseListener.mouseReleased(e);
        handler = null;
        dragStart = null;
    }

    public void mouseClicked(MouseEvent e) {
        // TODO - handle code, possibly for all entries...
        if (defaultMouseListener != null)
            defaultMouseListener.mouseClicked(e);
    }

    public void mouseEntered(MouseEvent e) {
        if (defaultMouseListener != null)
            defaultMouseListener.mouseEntered(e);
    }

    public void mouseExited(MouseEvent e) {
        if (defaultMouseListener != null)
            defaultMouseListener.mouseExited(e);
    }
    
    public void mouseMoved(MouseEvent e) {
        Object coord = visometry.getCoordinateOf(e.getPoint());
        VDraggablePrimitiveEntry newHandler = primitiveForEvent(e);
        if (newHandler != handler) {
            if (handler != null)
                handler.fireExited(coord);
            if (newHandler != null)
                newHandler.fireEntered(coord);
            handler = newHandler;
        }
        if (handler != null) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            handler.fireMoved(coord);
        } else if (defaultMouseListener != null && defaultMouseListener instanceof MouseMotionListener)
            ((MouseMotionListener)defaultMouseListener).mouseMoved(e);
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        defaultMouseWheelListener.mouseWheelMoved(e);
    }
}