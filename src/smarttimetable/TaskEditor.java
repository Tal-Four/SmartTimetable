/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttimetable;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Adam-PC
 */
public class TaskEditor extends javax.swing.JFrame {

    boolean edit;
    String oldTaskName;

    // Creates new form TaskEditor
    public TaskEditor() {
        initialise();
        edit = false;
    }

    // Creates new form TaskEditor with given variables
    public TaskEditor(String taskName) {
        initialise();
        edit = true;
        oldTaskName = taskName;

        //Setting the values of the GUI to the selected tasks values
        Task task = new Task();
        task.readTaskFromDB(taskName);
        nameField.setText(taskName);

        String sql = "SELECT Name FROM category WHERE UserID = " + User.getUserID() + " AND CategoryID = " + task.getCategory().getCategoryID();
        ResultSet rs = DatabaseHandle.query(sql);
        try {
            if (rs.next()) {
                categoryDropdown.setSelectedItem(rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        deadlineField.setText(task.sqlDateToTextFormat(task.getDateDue()));
        descriptionBox.setText(task.getDescription());
        timeField.setText(task.getTimeSet() + "");
        colourChooser.setColor(new Color(task.getColourCode()));

    }

    private void initialise() {
        initComponents();

        //Displays the user logged in
        userLabel.setText("Logged in as: " + User.getUsername());

        //Setting the combo box up
        String sql = "SELECT * FROM category WHERE UserID = " + User.getUserID();
        ResultSet rs = DatabaseHandle.query(sql);
        try {
            while (rs.next()) {
                categoryDropdown.addItem(rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        deadlineLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        colourLabel = new javax.swing.JLabel();
        categoryLabel = new javax.swing.JLabel();
        colourChooser = new javax.swing.JColorChooser();
        nameField = new javax.swing.JTextField();
        timeField = new javax.swing.JTextField();
        deadlineField = new javax.swing.JTextField();
        categoryDropdown = new javax.swing.JComboBox<>();
        nameCharsUsed = new javax.swing.JLabel();
        timeNeededFormatLabel = new javax.swing.JLabel();
        deadlineFormatLabel = new javax.swing.JLabel();
        descriptionPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionBox = new javax.swing.JTextArea();
        titleLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        mainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        nameLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        nameLabel.setText("Task Name:");

        deadlineLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        deadlineLabel.setText("Deadline:");

        timeLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        timeLabel.setText("Time Needed:");

        colourLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        colourLabel.setText("Colour:");

        categoryLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        categoryLabel.setText("Category:");

        nameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameFieldKeyReleased(evt);
            }
        });

        categoryDropdown.setEditable(true);

        nameCharsUsed.setText("0 out of 20 characters used");

        timeNeededFormatLabel.setText("Enter time in hours");

        deadlineFormatLabel.setText("(DD/MM/YYYY) format");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(timeLabel)
                            .addComponent(nameLabel)
                            .addComponent(deadlineLabel)
                            .addComponent(categoryLabel)
                            .addComponent(colourLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(categoryDropdown, 0, 200, Short.MAX_VALUE)
                            .addComponent(deadlineField)
                            .addComponent(timeField)
                            .addComponent(nameField, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameCharsUsed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(timeNeededFormatLabel)
                                    .addComponent(deadlineFormatLabel))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(colourChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameCharsUsed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameLabel)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeLabel)
                    .addComponent(timeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeNeededFormatLabel))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deadlineFormatLabel)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(deadlineLabel)
                        .addComponent(deadlineField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryLabel)
                    .addComponent(categoryDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(colourLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colourChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        descriptionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Description"));

        descriptionBox.setColumns(20);
        descriptionBox.setLineWrap(true);
        descriptionBox.setRows(5);
        descriptionBox.setWrapStyleWord(true);
        jScrollPane1.setViewportView(descriptionBox);

        javax.swing.GroupLayout descriptionPanelLayout = new javax.swing.GroupLayout(descriptionPanel);
        descriptionPanel.setLayout(descriptionPanelLayout);
        descriptionPanelLayout.setHorizontalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
        );
        descriptionPanelLayout.setVerticalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        titleLabel.setText("Task Editor");

        saveButton.setText("Save");
        saveButton.setToolTipText("");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel)
                            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(descriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(backButton)
                        .addComponent(saveButton))
                    .addComponent(userLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Attempts to create a task with given variables 
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed

        //Retrieving variable values from GUI
        boolean valid = true;
        String taskName = nameField.getText();
        String description = descriptionBox.getText();
        String category = categoryDropdown.getSelectedItem().toString();
        String dateDueText = deadlineField.getText();
        int colourCode = colourChooser.getColor().getRGB();
        float timeSet = 0;

        //<editor-fold defaultstate="collapsed" desc=" Valid checks ">
        //Checks to see if name is empty
        if (taskName.equals("")) {
            valid = false;
            new Popup("No task name entered").setVisible(true);
        }

        //Checks to see if category is empty
        if (category.equals("")) {
            valid = false;
            new Popup("No category entered").setVisible(true);
        }

        //Attemps to read the float. If it fails (eg. a letter entered) it doesn't create a task and creates a popup
        try {
            timeSet = Float.parseFloat(timeField.getText());
        } catch (Exception e) {
            System.err.println(e);
            valid = false;
            new Popup("Invalid time set").setVisible(true);
        }

        //Checks to see if date entered is in the correct format (DD/MM/YYYY)
        if (!(dateDueText.length() == 10 && dateDueText.charAt(2) == '/' && dateDueText.charAt(5) == '/')) {
            valid = false;
            new Popup("Invalid date format").setVisible(true);
        }//</editor-fold>

        //Creates the task and changes screen back to the menu if the variables are valid
        if (valid) {
            Task newTask = new Task();
            if (edit) {
                newTask.readTaskFromDB(oldTaskName);
                newTask.editTask(taskName, description, category, dateDueText, colourCode, timeSet, newTask.getTaskID());
            } else {
                newTask.createNewTask(taskName, description, category, dateDueText, colourCode, timeSet);
            }
            this.setVisible(false);
            new Menu().setVisible(true);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    //Returns back to menu screen, nothing is saved
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.setVisible(false);
        new Menu().setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    //After the key press it tells the user how many characters they can use and restricts taskName to 20 characters
    private void nameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameFieldKeyReleased
        int length = nameField.getText().length();
        if (length > 20) {
            nameField.setText(nameField.getText().substring(0, 20));
            length = nameField.getText().length();
        }
        nameCharsUsed.setText(length + " out of 20 characters used");
    }//GEN-LAST:event_nameFieldKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TaskEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaskEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaskEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaskEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TaskEditor().setVisible(true);
            }
        });
    }

    //<editor-fold defaultstate="collapsed" desc=" jFrame variables ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> categoryDropdown;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JColorChooser colourChooser;
    private javax.swing.JLabel colourLabel;
    private javax.swing.JTextField deadlineField;
    private javax.swing.JLabel deadlineFormatLabel;
    private javax.swing.JLabel deadlineLabel;
    private javax.swing.JTextArea descriptionBox;
    private javax.swing.JPanel descriptionPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel nameCharsUsed;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField timeField;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel timeNeededFormatLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
