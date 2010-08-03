/*
 * BetweenCentrality.java
 * Created Jul 3, 2010
 */

package org.bm.blaise.scio.graph.metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import org.bm.blaise.scio.graph.Graph;
import util.Matrices;

/**
 * <p>
 * Provides a metric describing the betweenness centrality of a vertex in a
 * CONNECTED graph. Returns infinity if the graph is not connected. May take a
 * long time for large graphs.
 * </p>
 * <p>
 * Computationally, the centrality measures the probability that a given node
 * lies on a randomly chosen geodesic.
 * </p>
 *
 * @author Elisha Peterson
 */
public class BetweenCentrality implements NodeMetric<Double> {

    private BetweenCentrality() {}
    private static final BetweenCentrality INSTANCE = new BetweenCentrality();

    /** Factory method to return instance of between centrality */
    public static BetweenCentrality getInstance() { return INSTANCE; }

    public boolean supportsGraph(boolean directed) { return true; }
    public <V> double nodeMax(boolean directed, int order) { return (order-1)*(order-2)*(directed ? 1.0 : 0.5); }
    public <V> double centralMax(boolean directed, int order) { throw new UnsupportedOperationException("Not supported yet."); }

    public <V> Double value(Graph<V> graph, V node) {
        System.out.println("BC-one");
        List<V> nodes = graph.nodes();
        int n = nodes.size();
        HashMap<V, Double> between = new HashMap<V, Double>();
        for (V v : nodes)
            between.put(v, 0.0);
        for (V start : nodes)
            brandes(graph, start, between);

        return between.get(node) * (graph.isDirected() ? 1.0 : 0.5);
    }

    public <V> List<Double> allValues(Graph<V> graph) {
        System.out.print("BC-all (" + graph.order() + " nodes, " + graph.edgeNumber() + " edges): ");
        long l0 = System.currentTimeMillis();
        List<V> nodes = graph.nodes();
        int n = nodes.size();

        HashMap<V, Double> between = new HashMap<V, Double>();
        for (V v : nodes)
            between.put(v, 0.0);
        for (V start : nodes)
            brandes(graph, start, between);

        double multiplier = graph.isDirected() ? 1.0 : 0.5;
        
        ArrayList<Double> result = new ArrayList<Double>(n);
        for (V v : nodes) result.add(multiplier * between.get(v));
        System.out.println((System.currentTimeMillis()-l0)+"ms");
        return result;
    }

    /**
     * Breadth-first search algorithm for an unweighted graph to generate betweenness
     * scores, with specified starting vertex.
     * From <i>Brandes</i>, "A Faster Algorithm for Betweenness Centrality"
     * @param graph the graph
     * @param start the start vertex
     * @param between data structure storing existing betweenness centrality values
     * @return data structure encoding the result
     */
    static <V> HashMap<V, Double> brandes(Graph<V> graph, V start, HashMap<V, Double> between) {
//        System.out.println("brandes: " + start + "   ... initial between values: " + between);
        List<V> nodes = graph.nodes();
        if (!nodes.contains(start))
            return new HashMap<V, Double>();

        HashMap<V, Integer> numShortest = new HashMap<V, Integer>(); // number of shortest paths to each vertex
          for (V v : nodes) numShortest.put(v, 0);
          numShortest.put(start, 1);
        HashMap<V, Integer> lengths = new HashMap<V, Integer>(); // length of shortest paths to each vertex
          for (V v : nodes) lengths.put(v, -1);
          lengths.put(start, 0);

        // breadth-first search algorithm
        LinkedList<V> queue = new LinkedList<V>(); // tracks elements for search algorithm
        Stack<V> stack = new Stack<V>(); // tracks elements in non-increasing order for later use
        HashMap<V,Set<V>> pred = new HashMap<V,Set<V>>(); // tracks vertex predecessors in resulting tree
          for (V v : nodes) pred.put(v, new HashSet<V>());

        queue.add(start);
        while (!queue.isEmpty()) {
            V v = queue.remove();
            stack.addElement(v);
            for (V w : graph.neighbors(v)) {
                // if w is found for the first time in the tree, add it to the queue, and adjust the length
                if (lengths.get(w) == -1) {
                    queue.add(w);
                    lengths.put(w, lengths.get(v) + 1);
                }
                // adjust the number of shortest paths to w if shortest path goes through v
                if (lengths.get(w) == lengths.get(v) + 1) {
                    numShortest.put(w, numShortest.get(w) + numShortest.get(v));
                    pred.get(w).add(v);
                }
            }
        }
//        System.out.println("      lengths: " + lengths);
//        System.out.println("      #paths:  " + numShortest);
//        System.out.println("  stack: " + stack);
//        System.out.println("  preds: " + pred);

        HashMap<V, Double> dependencies = new HashMap<V, Double>();
          for (V v : nodes) dependencies.put(v, 0.0);

        // compute betweenness
        while (!stack.isEmpty()) {
            V w = stack.pop();
            for (V v : pred.get(w))
                dependencies.put(v, dependencies.get(v)
                    + (double)numShortest.get(v)/numShortest.get(w) * (1 + dependencies.get(w)));
            if (w != start)
                between.put(w, between.get(w) + dependencies.get(w));
        }
//          System.out.println("  dependencies: " + dependencies);

        return between;
    } // brandes
    
    /**
     * Computes matrix of distances and #s of shortest paths between any 2 vertices
     * @param n size of matrix/graph
     * @param adj adjacency matrix of graph
     * @param dists n x n matrix to set up with distances (return value)
     * @param nPaths n x n matrix to set up with shortest paths (return value)
     */
    private static void computeShortestPaths(int n, int[][] adj, int[][] dists, int[][] nPaths) {
        int[][] curAdj = new int[n][n];
        int power = 1;
        for (int i = 0; i < n; i++) {
            dists[i][i] = 0;
            nPaths[i][i] = 1;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                curAdj[i][j] = adj[i][j];
                if (adj[i][j] == 0) {
                    dists[i][j] = nPaths[i][j] = -1;
                } else {
                    dists[i][j] = nPaths[i][j] = 1;
                }
            }
        }
        int nFound = -1;
        while (nFound != 0) {
            nFound = 0;
            curAdj = Matrices.matrixProduct(curAdj, adj);
            power++;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    if (dists[i][j] == -1 && curAdj[i][j] != 0) {
                        dists[i][j] = power;
                        nPaths[i][j] = curAdj[i][j];
                        nFound++;
                    }
                }
        }
    } // computeShortestPaths

    /**
     * Computes the betweenness of a specified vertex
     * @param n size of matrix/graph
     * @param dists the matrix of distances between vertices
     * @param nPaths the matrix of # of shortest paths between any two vertices
     * @param v0 the index of the node whose betweenness is to be computed
     * @param directed whether the graph for the computation is directed or not
     * @return the betweenness of the vertex
     */
    private static double computeBetweenness(int n, int[][] dists, int[][] nPaths, int v0) {
        double result = 0.0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                else if ((i == v0 || j == v0) && dists[i][j] != -1)
                    result++;
                else if (dists[i][v0] != -1 && dists[v0][j] != -1 && dists[i][j] != -1 && dists[i][v0] + dists[v0][j] == dists[i][j])
                    result += nPaths[i][v0] * nPaths[v0][j] / (double) nPaths[i][j];
            }
        }
        return result;
    }
}