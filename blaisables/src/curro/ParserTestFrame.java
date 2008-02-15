/*
 * ParserTestFrame.java
 *
 * Created on September 21, 2007, 10:35 AM
 */

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultTreeModel;
import scribo.parser.FunctionSyntaxException;
import scribo.parser.Parser;
import scribo.tree.FunctionTreeRoot;
import specto.plottable.Function2D;
import specto.gridplottable.Grid2D;
import specto.visometry.Euclidean2;

/**
 *
 * @author  Elisha
 */
public class ParserTestFrame extends javax.swing.JFrame {
    
    FunctionTreeRoot result,dresult;
    Function2D functionPlotted,dPlotted;
    
    /** Creates new form ParserTestFrame */
    public ParserTestFrame() {
        try {
            functionPlotted = new Function2D();
            functionPlotted.setColor(Color.RED);
            dPlotted = new Function2D();
            dPlotted.setColor(Color.BLUE);
            initComponents();
            plotPanel1.add(new Grid2D());
            plotPanel1.add(functionPlotted);
            plotPanel1.add(dPlotted);
            result = new FunctionTreeRoot(Parser.parseExpression(jTextField1.getText()));
            jTextField1ActionPerformed(null);
            jTextField1.getDocument().addDocumentListener(new DocumentListener() {

                public void insertUpdate(DocumentEvent e) {
                    jTextField1ActionPerformed(null);
                }

                public void removeUpdate(DocumentEvent e) {
                    jTextField1ActionPerformed(null);
                }

                public void changedUpdate(DocumentEvent e) {
                    jTextField1ActionPerformed(null);
                }
            });
        } catch (FunctionSyntaxException ex) {
            Logger.getLogger(ParserTestFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        plotPanel1 = new specto.PlotPanel(new Euclidean2());
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree2 = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setColumns(90);
        jTextField1.setText("cos(3*5+2)");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Input:");

        jLabel2.setText("Output:");

        jTextField2.setEditable(false);

        jLabel3.setText("Value:");

        jTextField3.setEditable(false);

        jLabel4.setText("Derivative:");

        jLabel5.setText("Recycled:");

        jTextField4.setEditable(false);

        jTextField5.setEditable(false);

        jLabel6.setText("Simplified:");

        jLabel7.setText("Deriv. Simp:");

        jTextField6.setEditable(false);

        jTextField7.setEditable(false);

        jTextField8.setEditable(false);

        jTextField9.setEditable(false);

        jLabel8.setText("f(x=1):");

        jLabel9.setText("f'(x=1):");

        jScrollPane1.setViewportView(jTree1);

        jScrollPane2.setViewportView(jTree2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plotPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                                    .addComponent(jLabel5)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(25, 25, 25))
                    .addComponent(plotPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

 
private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        try {
            FunctionTreeRoot temp = new FunctionTreeRoot(Parser.parseExpression(jTextField1.getText()));
            jTree1.setModel(new DefaultTreeModel(temp.getTreeNode()));
            if (temp != null) {
                result = temp;
                dresult = new FunctionTreeRoot(temp.derivativeTree("x"));
                jTextField1.setForeground(Color.BLACK);
                try {
                    jTextField2.setText(result.toString());
                    jTextField2.setForeground(Color.BLACK);
                } catch (NullPointerException e) {
                    System.out.println("getString null pointer: " + e.getMessage());
                    jTextField1.setForeground(Color.RED);
                    jTextField2.setForeground(Color.RED);
                }
                try {
                    jTextField3.setText(Double.toString(result.getValue()));
                    jTextField3.setForeground(Color.BLACK);
                } catch (NullPointerException e) {
                    System.out.println("getValue null pointer: " + e.getMessage());
                    jTextField1.setForeground(Color.RED);
                    jTextField3.setForeground(Color.RED);
                }
                try {
                    jTextField4.setText(dresult.toString());
                    jTree2.setModel(new DefaultTreeModel(dresult.getTreeNode()));
                    jTextField4.setForeground(Color.BLACK);
                } catch (NullPointerException e) {
                    System.out.println("derivative null pointer: " + e.getMessage());
                    jTextField1.setForeground(Color.RED);
                    jTextField4.setForeground(Color.RED);
                }
                try {
                    jTextField5.setText(Parser.parseExpression(result.toString()).toString());
                    jTextField5.setForeground(Color.BLACK);
                } catch (NullPointerException e) {
                    System.out.println("recycle null pointer: " + e.getMessage());
                    jTextField1.setForeground(Color.RED);
                    jTextField5.setForeground(Color.RED);
                }
                try {
                    jTextField6.setText(result.fullSimplified().toString());
                    jTextField6.setForeground(Color.BLACK);
                } catch (NullPointerException e) {
                    System.out.println("simplify null pointer: " + e.getMessage());
                    jTextField1.setForeground(Color.RED);
                    jTextField6.setForeground(Color.RED);
                }
                try {
                    jTextField7.setText(dresult.fullSimplified().toString());
                    jTextField7.setForeground(Color.BLACK);
                } catch (NullPointerException e) {
                    System.out.println("d-simplify null pointer: " + e.getMessage());
                    jTextField1.setForeground(Color.RED);
                    jTextField7.setForeground(Color.RED);
                }
                try {
                    jTextField8.setText(Double.toString(result.getValue("x", 1.0)));
                    jTextField8.setForeground(Color.BLACK);
                } catch (NullPointerException e) {
                    System.out.println("f(1) null pointer: " + e.getMessage());
                    jTextField1.setForeground(Color.RED);
                    jTextField8.setForeground(Color.RED);
                }
                try {
                    jTextField9.setText(Double.toString(dresult.getValue("x", 1.0)));
                    jTextField9.setForeground(Color.BLACK);
                } catch (NullPointerException e) {
                    System.out.println("f'(1) null pointer: " + e.getMessage());
                    jTextField1.setForeground(Color.RED);
                    jTextField9.setForeground(Color.RED);
                }
            } else {
                jTextField1.setForeground(Color.RED);
            }
            functionPlotted.setFunction(result);
            dPlotted.setFunction(dresult);
            plotPanel1.repaint();
        } catch (FunctionSyntaxException ex) {
            Logger.getLogger(ParserTestFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_jTextField1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParserTestFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTree jTree1;
    private javax.swing.JTree jTree2;
    private specto.PlotPanel plotPanel1;
    // End of variables declaration//GEN-END:variables
    
}
