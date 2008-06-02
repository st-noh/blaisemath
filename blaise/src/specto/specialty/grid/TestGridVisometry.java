/*
 * TestGridVisometry.java
 *
 * Created on April 8, 2008, 4:18 PM
 */

package specto.specialty.grid;

import scio.coordinate.Z2;
import sequor.control.PlusMinusBox;
import specto.specialty.grid.plottable.GridArc;
import specto.specialty.grid.plottable.ArcGroup;
import specto.specialty.grid.plottable.GridVertex;

/**
 *
 * @author  ae3263
 */
public class TestGridVisometry extends javax.swing.JFrame {
    
    /** Creates new form TestGridVisometry */
    public TestGridVisometry() {
        initComponents();
        
        grid2Panel1.add(new GridVertex(new Z2(2,2),50.));
        
        grid2Panel1.add(new GridArc(0,5,90,1,4,180));
        
        ArcGroup ag = new ArcGroup();
        ag.add(new GridArc(3,3,150,5,1,70));
        grid2Panel1.add(ag);
        
        grid2Panel1.add(new PlusMinusBox(50,50,grid2Panel1.getVisometry().xBounds.getMaxModel()),3,3);
        grid2Panel1.add(new PlusMinusBox(50,50,grid2Panel1.getVisometry().xBounds.getMinModel()),3,7);
        grid2Panel1.add(new PlusMinusBox(50,50,grid2Panel1.getVisometry().yBounds.getMinModel()),3,1);
        grid2Panel1.add(new PlusMinusBox(50,50,grid2Panel1.getVisometry().yBounds.getMaxModel()),3,5);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grid2Panel1 = new specto.specialty.grid.Grid2Panel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout grid2Panel1Layout = new javax.swing.GroupLayout(grid2Panel1);
        grid2Panel1.setLayout(grid2Panel1Layout);
        grid2Panel1Layout.setHorizontalGroup(
            grid2Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        grid2Panel1Layout.setVerticalGroup(
            grid2Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(grid2Panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(grid2Panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestGridVisometry().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private specto.specialty.grid.Grid2Panel grid2Panel1;
    // End of variables declaration//GEN-END:variables
    
}