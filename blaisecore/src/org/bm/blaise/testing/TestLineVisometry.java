/*
 * TestPlaneVisometry.java
 *
 * Created on Jul 30, 2009, 3:15:03 PM
 */
package org.bm.blaise.testing;

import org.bm.blaise.specto.plottable.VLine;
import org.bm.blaise.specto.plottable.VPoint;
import org.bm.blaise.specto.plottable.VPointSet;
import org.bm.blaise.specto.plottable.VPath;
import org.bm.blaise.specto.plottable.VRectangle;
import data.propertysheet.PropertySheet;
import java.awt.BasicStroke;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.bm.blaise.specto.line.LineAxis;
import org.bm.blaise.specto.line.LineFunction;
import org.bm.blaise.specto.primitive.ArrowStyle;
import org.bm.blaise.specto.primitive.TwoPointStyle;
import org.bm.blaise.specto.visometry.Plottable;

/**
 *
 * @author ae3263
 */
public class TestLineVisometry extends javax.swing.JFrame {

    /** Creates new form TestPlaneVisometry */
    public TestLineVisometry() {
        data.beans.EditorRegistration.registerEditors();
        initComponents();


        // BASIC ELEMENTS

        linePlot.addPlottable(new LineAxis());
        linePlot.addPlottable(new LineFunction(new UnivariateRealFunction() {

            public double value(double x) throws FunctionEvaluationException {
                return Math.cos(x);
            }
        }));

        linePlot2.addPlottable(new LineAxis());
        linePlot2.addPlottable(new VPoint<Double>(-1.0));
        VPointSet<Double> vch = new VPointSet<Double>(new Double[]{-3.0, -2.8, -2.6, -2.4});
        linePlot2.addPlottable(vch);
        linePlot2.setDefaultCoordinateHandler(vch);

        linePlot3.addPlottable(new LineAxis());
        linePlot3.addPlottable(new VRectangle<Double>(-0.7, -0.5));
        linePlot3.addPlottable(new VPath<Double>(new Double[]{3.0, 3.2, 3.4}));
        VLine<Double> vld = new VLine<Double>(1.1, 1.3);
        vld.getStyle().setStroke(new BasicStroke(2.0f));
        vld.getStyle().setEndStyle(TwoPointStyle.EndStyle.SEGMENT);
        vld.getStyle().setHeadShape(ArrowStyle.ArrowShape.REGULAR);
        linePlot3.addPlottable(vld);

        // PANELS

        rollupPanel1.add("Visometry", new PropertySheet(linePlot.getVisometry()));
        for (Plottable p : linePlot.getPlottables()) {
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
        jPanel1 = new javax.swing.JPanel();
        linePlot = new org.bm.blaise.specto.line.LinePlotComponent();
        linePlot2 = new org.bm.blaise.specto.line.LinePlotComponent();
        linePlot3 = new org.bm.blaise.specto.line.LinePlotComponent();

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

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        org.jdesktop.layout.GroupLayout linePlotLayout = new org.jdesktop.layout.GroupLayout(linePlot);
        linePlot.setLayout(linePlotLayout);
        linePlotLayout.setHorizontalGroup(
            linePlotLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 572, Short.MAX_VALUE)
        );
        linePlotLayout.setVerticalGroup(
            linePlotLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 74, Short.MAX_VALUE)
        );

        jPanel1.add(linePlot);

        org.jdesktop.layout.GroupLayout linePlot2Layout = new org.jdesktop.layout.GroupLayout(linePlot2);
        linePlot2.setLayout(linePlot2Layout);
        linePlot2Layout.setHorizontalGroup(
            linePlot2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 572, Short.MAX_VALUE)
        );
        linePlot2Layout.setVerticalGroup(
            linePlot2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 74, Short.MAX_VALUE)
        );

        jPanel1.add(linePlot2);

        org.jdesktop.layout.GroupLayout linePlot3Layout = new org.jdesktop.layout.GroupLayout(linePlot3);
        linePlot3.setLayout(linePlot3Layout);
        linePlot3Layout.setHorizontalGroup(
            linePlot3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 572, Short.MAX_VALUE)
        );
        linePlot3Layout.setVerticalGroup(
            linePlot3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 74, Short.MAX_VALUE)
        );

        jPanel1.add(linePlot3);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("Here");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TestLineVisometry().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private org.bm.blaise.specto.line.LinePlotComponent linePlot;
    private org.bm.blaise.specto.line.LinePlotComponent linePlot2;
    private org.bm.blaise.specto.line.LinePlotComponent linePlot3;
    private gui.RollupPanel rollupPanel1;
    // End of variables declaration//GEN-END:variables
}
