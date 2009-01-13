/*
 * NewJApplet.java
 *
 * Created on January 3, 2009, 9:18 PM
 */

package curro;

import sequor.model.ParameterListModel;
import specto.euclidean2.Parametric2D;

/**
 *
 * @author  ae3263
 */
public class NewJApplet extends javax.swing.JApplet {
    
    String[][] functions = { { "x(t)=" , "a*cos(b*t)+c*cos(d*t)", "t" }, { "y(t)=", "a*sin(b*t)+c*sin(d*t)", "t" } };
    Object[][] parameters = { { "a", 1.0 }, { "b", Math.PI/3 }, {"c",.5},{"d",Math.PI*2} };
   

    /** Initializes the applet NewJApplet */
    public void init() {
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
       
                    parameterListModel1=new ParameterListModel(parameters, settingsPanel1, functionPanel1);
                    
                    Parametric2D pc = new specto.euclidean2.Parametric2D(functionPanel1.getFunctionModel(0), functionPanel1.getFunctionModel(1));
                    plot2D1.add(pc);
                    plot2D1.add(pc.getPointSlope());
                    
                   // plot2D1.repaint();
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        parameterListModel1 = new sequor.model.ParameterListModel();
        plot2D1 = new specto.euclidean2.Plot2D();
        settingsPanel1 = new sequor.component.SettingsPanel();
        functionPanel1 = new sequor.component.FunctionPanel(functions);

        javax.swing.GroupLayout plot2D1Layout = new javax.swing.GroupLayout(plot2D1);
        plot2D1.setLayout(plot2D1Layout);
        plot2D1Layout.setHorizontalGroup(
            plot2D1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
        );
        plot2D1Layout.setVerticalGroup(
            plot2D1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 299, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(functionPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plot2D1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settingsPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(plot2D1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(functionPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
            .addComponent(settingsPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sequor.component.FunctionPanel functionPanel1;
    private sequor.model.ParameterListModel parameterListModel1;
    private specto.euclidean2.Plot2D plot2D1;
    private sequor.component.SettingsPanel settingsPanel1;
    // End of variables declaration//GEN-END:variables

}
