/*
 * BlaiseGraphicsTestView.java
 */

package org.blaise.graphics.demo;

import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import org.blaise.graphics.GraphicComponent;
import org.blaise.graphics.GraphicSelector;
import org.blaise.visometry.plane.PlanePlotComponent;

/**
 * The application's main frame.
 */
public class BlaiseGraphicsTestView extends FrameView {

    public BlaiseGraphicsTestView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }
    
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        canvas1 = new GraphicComponent();
        new GraphicSelector(canvas1);
        canvas2 = new PlanePlotComponent();
        new GraphicSelector(canvas2);
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setLayout(new java.awt.GridLayout(1, 2));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(BlaiseGraphicsTest.class).getContext().getResourceMap(BlaiseGraphicsTestView.class);
        canvas1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("canvas1.border.title"))); // NOI18N
        canvas1.setName("canvas1"); // NOI18N

        javax.swing.GroupLayout canvas1Layout = new javax.swing.GroupLayout(canvas1);
        canvas1.setLayout(canvas1Layout);
        canvas1Layout.setHorizontalGroup(
            canvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );
        canvas1Layout.setVerticalGroup(
            canvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );

        mainPanel.add(canvas1);

        canvas2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("canvas2.border.title"))); // NOI18N
        canvas2.setName("canvas2"); // NOI18N

        javax.swing.GroupLayout canvas2Layout = new javax.swing.GroupLayout(canvas2);
        canvas2.setLayout(canvas2Layout);
        canvas2Layout.setHorizontalGroup(
            canvas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );
        canvas2Layout.setVerticalGroup(
            canvas2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 266, Short.MAX_VALUE)
        );

        mainPanel.add(canvas2);

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(BlaiseGraphicsTest.class).getContext().getActionMap(BlaiseGraphicsTestView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        jMenuItem7.setAction(actionMap.get("addPoint")); // NOI18N
        jMenuItem7.setName("jMenuItem7"); // NOI18N
        jMenu1.add(jMenuItem7);

        jMenuItem8.setAction(actionMap.get("addPointSet")); // NOI18N
        jMenuItem8.setName("jMenuItem8"); // NOI18N
        jMenu1.add(jMenuItem8);

        jMenuItem14.setAction(actionMap.get("editPointSetStyle")); // NOI18N
        jMenuItem14.setName("jMenuItem14"); // NOI18N
        jMenu1.add(jMenuItem14);

        jMenuItem9.setAction(actionMap.get("addSegment")); // NOI18N
        jMenuItem9.setName("jMenuItem9"); // NOI18N
        jMenu1.add(jMenuItem9);

        jMenuItem10.setAction(actionMap.get("addRectangle")); // NOI18N
        jMenuItem10.setName("jMenuItem10"); // NOI18N
        jMenu1.add(jMenuItem10);

        jMenuItem11.setAction(actionMap.get("addString")); // NOI18N
        jMenuItem11.setName("jMenuItem11"); // NOI18N
        jMenu1.add(jMenuItem11);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jMenu1.add(jSeparator1);

        jMenuItem5.setAction(actionMap.get("addDelegatingPointSet")); // NOI18N
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        jMenu1.add(jMenuItem5);

        jMenuItem22.setAction(actionMap.get("addDelegatingPointSet2")); // NOI18N
        jMenuItem22.setName("jMenuItem22"); // NOI18N
        jMenu1.add(jMenuItem22);

        jMenuItem6.setAction(actionMap.get("addDelegatingGraph")); // NOI18N
        jMenuItem6.setName("jMenuItem6"); // NOI18N
        jMenu1.add(jMenuItem6);

//        jSeparator2.setName("jSeparator2"); // NOI18N
//        jMenu1.add(jSeparator2);
//
//        jMenuItem3.setAction(actionMap.get("addArrow")); // NOI18N
//        jMenuItem3.setName("jMenuItem3"); // NOI18N
//        jMenu1.add(jMenuItem3);
//
//        jMenuItem4.setAction(actionMap.get("addLabeledPoint")); // NOI18N
//        jMenuItem4.setName("jMenuItem4"); // NOI18N
//        jMenu1.add(jMenuItem4);
//
//        jMenuItem12.setAction(actionMap.get("addRuler")); // NOI18N
//        jMenuItem12.setName("jMenuItem12"); // NOI18N
//        jMenu1.add(jMenuItem12);
//
//        jMenuItem13.setAction(actionMap.get("add2Point")); // NOI18N
//        jMenuItem13.setName("jMenuItem13"); // NOI18N
//        jMenu1.add(jMenuItem13);
//
//        jSeparator3.setName("jSeparator3"); // NOI18N
//        jMenu1.add(jSeparator3);
//
//        jMenuItem1.setAction(actionMap.get("addRay")); // NOI18N
//        jMenuItem1.setName("jMenuItem1"); // NOI18N
//        jMenu1.add(jMenuItem1);
//
//        jMenuItem2.setAction(actionMap.get("addLine")); // NOI18N
//        jMenuItem2.setName("jMenuItem2"); // NOI18N
//        jMenu1.add(jMenuItem2);
//
//        jMenuItem21.setAction(actionMap.get("addLabeledPointSet")); // NOI18N
//        jMenuItem21.setName("jMenuItem21"); // NOI18N
//        jMenu1.add(jMenuItem21);

        menuBar.add(jMenu1);

        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N

        jMenuItem15.setAction(actionMap.get("addPlanePoint")); // NOI18N
        jMenuItem15.setName("jMenuItem15"); // NOI18N
        jMenu2.add(jMenuItem15);

        jMenuItem16.setAction(actionMap.get("addPlanePointSet")); // NOI18N
        jMenuItem16.setName("jMenuItem16"); // NOI18N
        jMenu2.add(jMenuItem16);

        jMenuItem17.setAction(actionMap.get("addPlanePolygonalPath")); // NOI18N
        jMenuItem17.setName("jMenuItem17"); // NOI18N
        jMenu2.add(jMenuItem17);

        jMenuItem18.setAction(actionMap.get("addPlaneGraph")); // NOI18N
        jMenuItem18.setName("jMenuItem18"); // NOI18N
        jMenu2.add(jMenuItem18);

        jSeparator4.setName("jSeparator4"); // NOI18N
        jMenu2.add(jSeparator4);

        jMenuItem19.setAction(actionMap.get("addCustomPlanePointSet")); // NOI18N
        jMenuItem19.setName("jMenuItem19"); // NOI18N
        jMenu2.add(jMenuItem19);

        jMenuItem20.setAction(actionMap.get("addCustomPlaneGraph")); // NOI18N
        jMenuItem20.setName("jMenuItem20"); // NOI18N
        jMenu2.add(jMenuItem20);

        menuBar.add(jMenu2);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 601, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jButton1.setAction(actionMap.get("clear1")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jButton2.setAction(actionMap.get("clear2")); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
        setToolBar(jToolBar1);
    }

    GraphicComponent canvas1;
    PlanePlotComponent canvas2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
