/*
 * NewJFrame.java
 *
 * Created on November 12, 2007, 3:21 PM
 */

package curro;

import java.awt.BorderLayout;
import specto.PlotInputPanel;

/**
 *
 * @author  ae3263
 */
public class NewClass extends javax.swing.JFrame {
    
    /** Creates new form NewJFrame */
    public NewClass() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        plotInputPanel1 = new specto.PlotInputPanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(plotInputPanel1);
        pack();
    }// </editor-fold>
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewClass().setVisible(true);
            }
        });
    }
    

    private specto.PlotInputPanel plotInputPanel1;
    // End of variables declaration
    
}
