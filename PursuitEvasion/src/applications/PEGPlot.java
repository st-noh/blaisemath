/*
 * PEGPlot.java
 * Created on September 8, 2007, 8:59 AM
 */

// TODO synchronize timers in the two different plot windows.

package applications;

import scio.coordinate.R2;
import sequor.editor.ComboBoxEditor;
import analysis.Statistics;
import analysis.DataLog;
import metrics.Valuation;
import sequor.Settings;
import simulation.Team;

/**
 *
 * @author  ae3263
 */
public class PEGPlot extends javax.swing.JFrame {
    
    /** Creates new form PEGPlot */
    @SuppressWarnings("unchecked")
    public PEGPlot() {
        initComponents();
        plot2D1.getVisometry().setBounds(new R2(-70,-70),new R2(70,70));
        plot2D2.getVisometry().setBounds(new R2(-10,-100),new R2(200,100));
        plot2D2.synchronizeTimerWith(plot2D1);
        dataLog1.initialize(simulation1, plot2D1, plot2D2);
        simulation1.run();
        settingsMenu.add(simulation1.getMenu("Simulation"));
        jComboBox1.setModel(new ComboBoxEditor(simulation1.getGameTypeModel()));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuSimModeGroup = new javax.swing.ButtonGroup();
        numBatchRunsModel = new sequor.model.IntegerRangeModel(100,0,100000);
        simulation1 = new simulation.Simulation();
        dataLog1 = new analysis.DataLog();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        simulationSettingsPanel1 = new applications.SimulationSettingsPanel(simulation1.ss);
        infoPane = new javax.swing.JTabbedPane();
        plot2D2 = new specto.euclidean2.Plot2D();
        jScrollPane5 = new javax.swing.JScrollPane();
        logWindow = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataWindow = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        codeWindow = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        plot2D1 = new specto.euclidean2.Plot2D();
        statusBar = new javax.swing.JPanel();
        statusText = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        randomizeButton = new javax.swing.JButton();
        batchButton = new javax.swing.JButton();
        numBatchRunsSpinner = Settings.getSpinner(numBatchRunsModel);
        cooperationButton = new javax.swing.JButton();
        startingPositionsButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        addDotsButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        simulationMenu = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        settingsMenu = new javax.swing.JMenu();
        ModeMenu = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        appearanceMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        simulation1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulation1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pursuit-Evasion Games");

        jSplitPane2.setDividerLocation(450);

        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setTopComponent(simulationSettingsPanel1);

        infoPane.setToolTipText("See information regarding the simulations.");
        infoPane.setMaximumSize(new java.awt.Dimension(450, 600));

        javax.swing.GroupLayout plot2D2Layout = new javax.swing.GroupLayout(plot2D2);
        plot2D2.setLayout(plot2D2Layout);
        plot2D2Layout.setHorizontalGroup(
            plot2D2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );
        plot2D2Layout.setVerticalGroup(
            plot2D2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );

        infoPane.addTab("Metrics", plot2D2);

        logWindow.setColumns(20);
        logWindow.setEditable(false);
        logWindow.setRows(5);
        logWindow.setToolTipText("See information about simulations which have been run.");
        jScrollPane5.setViewportView(logWindow);

        infoPane.addTab("Log", jScrollPane5);

        dataWindow.setColumns(20);
        dataWindow.setRows(5);
        dataWindow.setToolTipText("See a table of data obtained from the last simulation.");
        jScrollPane1.setViewportView(dataWindow);

        infoPane.addTab("Data", jScrollPane1);

        codeWindow.setColumns(20);
        codeWindow.setRows(5);
        jScrollPane2.setViewportView(codeWindow);

        infoPane.addTab("Code", jScrollPane2);

        jScrollPane3.setToolTipText("Communications network of the teams.");
        infoPane.addTab("Network View", jScrollPane3);

        jSplitPane1.setRightComponent(infoPane);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        );

        jSplitPane2.setLeftComponent(jPanel1);

        javax.swing.GroupLayout plot2D1Layout = new javax.swing.GroupLayout(plot2D1);
        plot2D1.setLayout(plot2D1Layout);
        plot2D1Layout.setHorizontalGroup(
            plot2D1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 474, Short.MAX_VALUE)
        );
        plot2D1Layout.setVerticalGroup(
            plot2D1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(plot2D1);

        getContentPane().add(jSplitPane2, java.awt.BorderLayout.CENTER);

        statusBar.setBackground(new java.awt.Color(255, 255, 255));
        statusBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        statusText.setText("Status: nonfunctional status bar.");

        javax.swing.GroupLayout statusBarLayout = new javax.swing.GroupLayout(statusBar);
        statusBar.setLayout(statusBarLayout);
        statusBarLayout.setHorizontalGroup(
            statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusText, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
        );
        statusBarLayout.setVerticalGroup(
            statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusText)
        );

        getContentPane().add(statusBar, java.awt.BorderLayout.SOUTH);

        jToolBar1.setRollover(true);

        jLabel1.setText("Game Preset:  ");
        jToolBar1.add(jLabel1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setMaximumSize(new java.awt.Dimension(150, 20));
        jToolBar1.add(jComboBox1);
        jToolBar1.add(jSeparator1);

        randomizeButton.setFont(new java.awt.Font("Tahoma", 1, 12));
        randomizeButton.setText("RANDOMIZE");
        randomizeButton.setToolTipText("Click here to reset the simulation and re-randomize starting positions.");
        randomizeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomizeButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(randomizeButton);

        batchButton.setFont(new java.awt.Font("Tahoma", 1, 12));
        batchButton.setText("BATCH");
        batchButton.setFocusable(false);
        batchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        batchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        batchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(batchButton);

        numBatchRunsSpinner.setMaximumSize(new java.awt.Dimension(70, 32767));
        jToolBar1.add(numBatchRunsSpinner);

        cooperationButton.setFont(new java.awt.Font("Tahoma", 1, 12));
        cooperationButton.setText("COOPERATION");
        cooperationButton.setFocusable(false);
        cooperationButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cooperationButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cooperationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cooperationButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(cooperationButton);

        startingPositionsButton.setFont(new java.awt.Font("Tahoma", 1, 12));
        startingPositionsButton.setText("GET START POSITIONS");
        startingPositionsButton.setFocusable(false);
        startingPositionsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        startingPositionsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        startingPositionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startingPositionsButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(startingPositionsButton);
        jToolBar1.add(jSeparator3);

        addDotsButton.setText("Add Dots");
        addDotsButton.setFocusable(false);
        addDotsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addDotsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addDotsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDotsButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addDotsButton);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        fileMenu.setText("File");

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("New");
        fileMenu.add(jMenuItem9);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setText("Open");
        fileMenu.add(jMenuItem11);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("Save");
        fileMenu.add(jMenuItem10);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setText("Quit");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem12);

        jMenuBar1.add(fileMenu);

        simulationMenu.setText("Simulation");

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem13.setText("Randomize Starting Locations");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomizeButtonActionPerformed(evt);
            }
        });
        simulationMenu.add(jMenuItem13);

        jMenuBar1.add(simulationMenu);

        settingsMenu.setText("Settings");

        ModeMenu.setText("Simulation Mode");

        jRadioButtonMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuSimModeGroup.add(jRadioButtonMenuItem1);
        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("One Simulation (Dynamic)");
        ModeMenu.add(jRadioButtonMenuItem1);

        jRadioButtonMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuSimModeGroup.add(jRadioButtonMenuItem2);
        jRadioButtonMenuItem2.setText("Two Simulations (Comparison)");
        ModeMenu.add(jRadioButtonMenuItem2);

        jRadioButtonMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuSimModeGroup.add(jRadioButtonMenuItem3);
        jRadioButtonMenuItem3.setText("Multiple Simulations (Statistical)");
        ModeMenu.add(jRadioButtonMenuItem3);

        settingsMenu.add(ModeMenu);

        jMenuBar1.add(settingsMenu);

        appearanceMenu.setText("Appearance");

        jMenuItem1.setText("Plot Window");
        appearanceMenu.add(jMenuItem1);

        jMenuItem2.setText("Points");
        appearanceMenu.add(jMenuItem2);

        jMenuItem7.setText("Paths");
        appearanceMenu.add(jMenuItem7);

        jMenuBar1.add(appearanceMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void batchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchButtonActionPerformed
    simulation1.runSeveral(numBatchRunsModel.getValue());
}//GEN-LAST:event_batchButtonActionPerformed

private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
    System.exit(0);
}//GEN-LAST:event_jMenuItem12ActionPerformed

private void randomizeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomizeButtonActionPerformed
    logWindow.append("\nNEW SIMULATION\n");
    simulation1.initStartingLocations();
    simulation1.run();
}//GEN-LAST:event_randomizeButtonActionPerformed

    @SuppressWarnings("unchecked")
private void simulation1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simulation1ActionPerformed
    //System.out.println("pegplot action performed: "+evt.getActionCommand());
    if(evt.getActionCommand().equals("redraw")){
        plot2D1.repaint();
        plot2D2.repaint();
    }
    else if(evt.getActionCommand().equals("recolor")){
        dataLog1.recolor();
        plot2D1.repaint();
        plot2D2.repaint();
        simulationSettingsPanel1.repaint();
    }
    // simulation has changed in some fundamental way
    else if(evt.getActionCommand().equals("reset")){
        plot2D1.clearPlottables();
        plot2D2.clearPlottables();
        plot2D1.rebuildOptionsMenu();
        plot2D2.rebuildOptionsMenu();
        dataLog1.initialize(simulation1, plot2D1, plot2D2);
        simulationSettingsPanel1.setTree(simulation1.ss);
    }
    else if(evt.getActionCommand().equals("log")){
        if(evt.getSource() instanceof DataLog){
            ((DataLog)evt.getSource()).output(logWindow);
        }else if(evt.getSource() instanceof Statistics){
            ((Statistics)evt.getSource()).output(logWindow);
            ((Statistics)evt.getSource()).outputData(dataWindow);
        }
    }
    else{
        logWindow.append(evt.getActionCommand()+"\n");
        statusText.setText(evt.getActionCommand());
    }
}//GEN-LAST:event_simulation1ActionPerformed

    private void addDotsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDotsButtonActionPerformed
        //PlaneFunction2D pf=new Statistics().getInitialPositionTestPlot(simulation1);
        //pf.style.setValue(PlaneFunction2D.DOTS);
        //plot2D1.add(pf);
}//GEN-LAST:event_addDotsButtonActionPerformed

    private void cooperationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cooperationButtonActionPerformed
        for (Team t : simulation1.getTeams()) {
            if (t.victory != null) {
                logWindow.append(t.victory.getCooperationMetric(simulation1).toString());
            }
            for (Valuation v : t.metrics) {
                logWindow.append(v.getCooperationMetric(simulation1).toString());
            }
        }
}//GEN-LAST:event_cooperationButtonActionPerformed

private void startingPositionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startingPositionsButtonActionPerformed
    dataLog1.printStartingLocations(logWindow, codeWindow);
}//GEN-LAST:event_startingPositionsButtonActionPerformed
    
    /**
     * @param args the command lineSegment arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PEGPlot().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ModeMenu;
    private javax.swing.JButton addDotsButton;
    private javax.swing.JMenu appearanceMenu;
    private javax.swing.JButton batchButton;
    private javax.swing.JTextArea codeWindow;
    private javax.swing.JButton cooperationButton;
    private analysis.DataLog dataLog1;
    private javax.swing.JTextArea dataWindow;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JTabbedPane infoPane;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextArea logWindow;
    private javax.swing.ButtonGroup menuSimModeGroup;
    private sequor.model.IntegerRangeModel numBatchRunsModel;
    private javax.swing.JSpinner numBatchRunsSpinner;
    private specto.euclidean2.Plot2D plot2D1;
    private specto.euclidean2.Plot2D plot2D2;
    private javax.swing.JButton randomizeButton;
    private javax.swing.JMenu settingsMenu;
    private simulation.Simulation simulation1;
    private javax.swing.JMenu simulationMenu;
    private applications.SimulationSettingsPanel simulationSettingsPanel1;
    private javax.swing.JButton startingPositionsButton;
    private javax.swing.JPanel statusBar;
    private javax.swing.JLabel statusText;
    // End of variables declaration//GEN-END:variables
}
