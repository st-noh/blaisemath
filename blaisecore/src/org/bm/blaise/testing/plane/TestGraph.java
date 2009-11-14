/*
 * TestPlaneVisometry.java
 *
 * Created on Jul 30, 2009, 3:15:03 PM
 */
package org.bm.blaise.testing.plane;

import data.propertysheet.PropertySheet;
import org.bm.blaise.specto.plane.*;
import org.bm.blaise.specto.plane.graph.PlaneGraph;
import org.bm.blaise.specto.visometry.Plottable;
import scio.graph.GraphCreation;

/**
 *
 * @author ae3263
 */
public class TestGraph extends javax.swing.JFrame {

    /** Creates new form TestPlaneVisometry */
    public TestGraph() {
        data.beans.EditorRegistration.registerEditors();
        initComponents();


        // BASIC ELEMENTS

        graphPlot.addPlottable(new PlaneGraph(new PlaneGraph.TestNSI()));
        graphPlot.addPlottable(new PlaneGraph(GraphCreation.getLoopGraph(15)));
        graphPlot.addPlottable(new PlaneGraph(GraphCreation.getCompleteGraph(5, true)));
        graphPlot.addPlottable(new PlaneGraph(GraphCreation.getRandomGraph(30, .1, true)));
        graphPlot.setDesiredRange(-5.0, -5.0, 5.0, 5.0);


        // PANELS

        rollupPanel1.add("Visometry", new PropertySheet(graphPlot.getVisometry()));
        for (Plottable p : graphPlot.getPlottables()) {
            rollupPanel1.add(p.toString(), new PropertySheet(p));
        }
//        rollupPanel1.add("Point Style", new PropertySheet(planePlotComponent1.getVisometryGraphics().getPointStyle()));
//        rollupPanel1.add("Stroke Style", new PropertySheet(planePlotComponent1.getVisometryGraphics().getPathStyle()));
//        rollupPanel1.add("Text Style", new PropertySheet(planePlotComponent1.getVisometryGraphics().getTextStyle()));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        rollupPanel1 = new gui.RollupPanel();
        tabPane = new javax.swing.JTabbedPane();
        graphPlot = new org.bm.blaise.specto.plane.PlanePlotComponent();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jToolBar1.setRollover(true);

        jButton1.setText("jButton1");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setViewportView(rollupPanel1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.EAST);

        tabPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabPaneStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout graphPlotLayout = new org.jdesktop.layout.GroupLayout(graphPlot);
        graphPlot.setLayout(graphPlotLayout);
        graphPlotLayout.setHorizontalGroup(
            graphPlotLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 567, Short.MAX_VALUE)
        );
        graphPlotLayout.setVerticalGroup(
            graphPlotLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 370, Short.MAX_VALUE)
        );

        tabPane.addTab("Graph", graphPlot);

        getContentPane().add(tabPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("Here");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabPaneStateChanged
        rollupPanel1.removeAll();
        PlanePlotComponent ppc = (PlanePlotComponent) tabPane.getSelectedComponent();
        rollupPanel1.add("Visometry", new PropertySheet(ppc.getVisometry()));
        for (Plottable p : ppc.getPlottables()) {
            rollupPanel1.add(p.toString(), new PropertySheet(p));
        }
}//GEN-LAST:event_tabPaneStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TestGraph().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.bm.blaise.specto.plane.PlanePlotComponent graphPlot;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private gui.RollupPanel rollupPanel1;
    private javax.swing.JTabbedPane tabPane;
    // End of variables declaration//GEN-END:variables
}