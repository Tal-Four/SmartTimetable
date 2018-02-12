/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttimetable;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Adam-PC
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        
        //Centers the frame to the centre of the monitor
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        //Displays the user logged in
        userLabel.setText("Logged in as: " + User.getUsername());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exitButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        mainMenuPanel = new javax.swing.JPanel();
        createEventsButton = new javax.swing.JButton();
        viewEditEvents = new javax.swing.JButton();
        generateTimetableButton = new javax.swing.JButton();
        viewEditTimetable = new javax.swing.JButton();
        createTaskButton = new javax.swing.JButton();
        viewEditTasks = new javax.swing.JButton();
        createCategoryButton = new javax.swing.JButton();
        viewEditCategoryButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        exitButton.setText("Exit");
        exitButton.setToolTipText("Exit the program");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        logoutButton.setText("Logout");
        logoutButton.setToolTipText("Logout and return to the login screen");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        mainMenuPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Main Menu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        createEventsButton.setText("Create Event");
        createEventsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createEventsButtonActionPerformed(evt);
            }
        });

        viewEditEvents.setText("View/Edit Events");
        viewEditEvents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewEditEventsActionPerformed(evt);
            }
        });

        generateTimetableButton.setText("Generate Timetable");
        generateTimetableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateTimetableButtonActionPerformed(evt);
            }
        });

        viewEditTimetable.setText("View/Edit Timetable");
        viewEditTimetable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewEditTimetableActionPerformed(evt);
            }
        });

        createTaskButton.setText("Create Task");
        createTaskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTaskButtonActionPerformed(evt);
            }
        });

        viewEditTasks.setText("View/Edit Tasks");
        viewEditTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewEditTasksActionPerformed(evt);
            }
        });

        createCategoryButton.setText("Create Category");
        createCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCategoryButtonActionPerformed(evt);
            }
        });

        viewEditCategoryButton.setText("View/Edit Category");
        viewEditCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewEditCategoryButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainMenuPanelLayout = new javax.swing.GroupLayout(mainMenuPanel);
        mainMenuPanel.setLayout(mainMenuPanelLayout);
        mainMenuPanelLayout.setHorizontalGroup(
            mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(createCategoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewEditCategoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainMenuPanelLayout.createSequentialGroup()
                        .addComponent(createTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createEventsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generateTimetableButton))
                    .addGroup(mainMenuPanelLayout.createSequentialGroup()
                        .addComponent(viewEditTasks, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewEditEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewEditTimetable)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainMenuPanelLayout.setVerticalGroup(
            mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateTimetableButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createEventsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createTaskButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewEditCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewEditTasks, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewEditEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewEditTimetable, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainMenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoutButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(mainMenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(logoutButton)
                        .addComponent(exitButton)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        //Stops the program
        User.logoutUser();
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void generateTimetableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateTimetableButtonActionPerformed
        //Creates a timetable for the user
        new WorkHoursInput(this).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_generateTimetableButtonActionPerformed

    private void viewEditTimetableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewEditTimetableActionPerformed
        //Shows the timetable screen
        new Timetable().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_viewEditTimetableActionPerformed

    private void viewEditEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewEditEventsActionPerformed
        //Shows the event viewer screen
        this.setVisible(false);
        new EventViewer().setVisible(true);
    }//GEN-LAST:event_viewEditEventsActionPerformed

    private void createEventsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createEventsButtonActionPerformed
        //Shows the event editor screen
        this.setVisible(false);
        new EventEditor(this).setVisible(true);
    }//GEN-LAST:event_createEventsButtonActionPerformed

    private void viewEditTasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewEditTasksActionPerformed
        //Shows the task viewer screen
        this.setVisible(false);
        new TaskViewer().setVisible(true);
    }//GEN-LAST:event_viewEditTasksActionPerformed

    private void createTaskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTaskButtonActionPerformed
        //Shows the task editor screen
        this.setVisible(false);
        new TaskEditor(this).setVisible(true);
    }//GEN-LAST:event_createTaskButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        //Logs out user and returns to login screen
        User.logoutUser();
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void createCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createCategoryButtonActionPerformed
        this.setVisible(false);
        new CategoryEditor(this).setVisible(true);
    }//GEN-LAST:event_createCategoryButtonActionPerformed

    private void viewEditCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewEditCategoryButtonActionPerformed
        this.setVisible(false);
        new CategoryViewer().setVisible(true);
    }//GEN-LAST:event_viewEditCategoryButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createCategoryButton;
    private javax.swing.JButton createEventsButton;
    private javax.swing.JButton createTaskButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton generateTimetableButton;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainMenuPanel;
    private javax.swing.JLabel userLabel;
    private javax.swing.JButton viewEditCategoryButton;
    private javax.swing.JButton viewEditEvents;
    private javax.swing.JButton viewEditTasks;
    private javax.swing.JButton viewEditTimetable;
    // End of variables declaration//GEN-END:variables
}
