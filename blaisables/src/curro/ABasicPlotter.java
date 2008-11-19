/*
 * ABasicPlotter.java
 *
 * Created on February 1, 2008, 4:19 PM
 */

package curro;

import sequor.model.FunctionTreeModel;
import specto.euclidean2.Function2D;

/**
 *
 * @author  ae3263
 */
public class ABasicPlotter extends javax.swing.JApplet {
    
    /** Initializes the applet ABasicPlotter */
    public void init() {
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                    plot2D1.add(function2D1);                 
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        function2D1 = new specto.euclidean2.Function2D();
        functionTreeModel1 = new sequor.model.FunctionTreeModel();
        jLabel1 = new javax.swing.JLabel();
        functionTreeTextField1 = new sequor.editor.FunctionTextField();
        plot2D1 = new specto.euclidean2.Plot2D();

        function2D1.setAnimationOn(true);
        function2D1.setFunctionModel(functionTreeModel1);

        jLabel1.setText("f(x)=");

        functionTreeTextField1.setFunctionTreeModel(functionTreeModel1);

        javax.swing.GroupLayout plot2D1Layout = new javax.swing.GroupLayout(plot2D1);
        plot2D1.setLayout(plot2D1Layout);
        plot2D1Layout.setHorizontalGroup(
            plot2D1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        plot2D1Layout.setVerticalGroup(
            plot2D1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 227, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(functionTreeTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
            .addComponent(plot2D1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(plot2D1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(functionTreeTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private specto.euclidean2.Function2D function2D1;
    private sequor.model.FunctionTreeModel functionTreeModel1;
    private sequor.editor.FunctionTextField functionTreeTextField1;
    private javax.swing.JLabel jLabel1;
    private specto.euclidean2.Plot2D plot2D1;
    // End of variables declaration//GEN-END:variables
    
}