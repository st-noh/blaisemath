/*
 * GraphComponent.java
 * Created Jan 31, 2011
 */

package org.blaise.graph.view;

import org.blaise.graph.Graph;
import org.blaise.graphics.Edge;
import org.blaise.style.ObjectStyler;
import org.blaise.style.PathStyle;
import org.blaise.style.PointStyle;
import org.blaise.visometry.plane.PlanePlotComponent;


/**
 * Provides a view of a graph, using a {@link GraphManager} for positions/layout
 * and a {@link PlaneGraphAdapter} for appearance.
 *
 * @author elisha
 */
public class GraphComponent extends PlanePlotComponent {

    /** Manages the visual elements of the underlying graph */
    protected final PlaneGraphAdapter adapter;


    //<editor-fold defaultstate="collapsed" desc="INITIALIZATION">
    //
    // INITIALIZATION
    //

    /**
     * Construct without a graph
     */
    public GraphComponent() {
        this(new GraphManager());
    }

    /**
     * Construct with specified graph
     * @param graph the graph to initialize with
     */
    public GraphComponent(Graph graph) {
        this(new GraphManager(graph));
    }

    /**
     * Construct with specified graph manager (contains graph and positions)
     * @param gm graph manager to initialize with
     */
    public GraphComponent(GraphManager gm) {
        adapter = new PlaneGraphAdapter(gm);
        getVisometryGraphicRoot().addGraphic(adapter.getViewGraph());
        setDesiredRange(-5.0, -5.0, 5.0, 5.0);
    }

    //</editor-fold>


    //<editor-fold defaultstate="collapsed" desc="DELEGATING PROPERTIES">
    //
    // DELEGATING PROPERTIES
    //

    /**
     * Return the adapter that contains the graph manager and the graph, responsible for handling the visual appearance.
     * @return the adapter
     */
    public PlaneGraphAdapter getAdapter() {
        return adapter;
    }

    public ObjectStyler<Edge<Object>, PathStyle> getEdgeStyler() {
        return adapter.getEdgeStyler();
    }

    public void setEdgeStyler(ObjectStyler<Edge<Object>, PathStyle> edgeStyler) {
        adapter.setEdgeStyler(edgeStyler);
    }

    public ObjectStyler<Object, PointStyle> getNodeStyler() {
        return adapter.getNodeStyler();
    }

    public void setNodeStyler(ObjectStyler<Object, PointStyle> nodeStyler) {
        adapter.setNodeStyler(nodeStyler);
    }

    /**
     * Return the graph manager underlying the component, responsible for handling the graph and node locations.
     * @return the manager
     */
    public GraphManager getGraphManager() {
        return adapter.getGraphManager();
    }

    public void setGraphManager(GraphManager gm) {
        adapter.setGraphManager(gm);
    }

    public synchronized Graph getGraph() {
        return adapter.getGraphManager().getGraph();
    }

    public synchronized void setGraph(Graph graph) {
        adapter.getGraphManager().setGraph(graph);
    }

    public boolean isLayoutAnimating() {
        return getGraphManager().isLayoutAnimating();
    }

    public void setLayoutAnimating(boolean val) {
        getGraphManager().setLayoutAnimating(val);
    }

    //</editor-fold>




}