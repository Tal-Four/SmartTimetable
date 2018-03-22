/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttimetable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Adam-PC
 */
public class TaskEditor extends javax.swing.JFrame {

    private final boolean edit;
    private Task oldTask;
    private final LinkedList categoryIDList = new LinkedList();
    private JFrame lastPanel;

    //Creates new form TaskEditor
    public TaskEditor(JFrame lastPanel) {
        initialise(lastPanel);
        this.edit = false;
    }

    //Creates new form TaskEditor with given variables
    public TaskEditor(Task task, JFrame lastPanel) {
        initialise(lastPanel);
        this.edit = true;
        this.oldTask = task;

        //Setting the values of the GUI to the selected tasks values
        nameField.setText(this.oldTask.getName());
        categoryDropdown.setSelectedItem(this.oldTask.getCategory().getName());
        deadlineField.setText(this.oldTask.sqlDateToTextFormat(this.oldTask.getDateDue()));
        descriptionBox.setText(this.oldTask.getDescription());
        timeField.setText(this.oldTask.getTimeSet() + "");
        colourChooser.setColor(new Color(this.oldTask.getColourCode()));
        if (this.oldTask.getHighPriority()) {
            this.highRadioButton.setSelected(true);
            this.standardRadioButton.setSelected(false);
        }
    }

    //Initialises components and sets some text box values
    private void initialise(JFrame lastPanel) {
        initComponents();

        this.lastPanel = lastPanel;

        //Centers the frame to the centre of the monitor
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        //Displays the user logged in
        userLabel.setText("Logged in as: " + User.getUsername());

        //Setting the combo box up
        String sql = "SELECT Name, CategoryID FROM user, category WHERE user.UserID = category.UserID AND user.UserID = " + User.getUserID() + " ORDER BY Name";
        ResultSet rs = DatabaseHandle.query(sql);
        try {
            while (rs.next()) {
                categoryIDList.addNode(rs.getInt("CategoryID"));
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

        priorityGroup = new javax.swing.ButtonGroup();
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
        priorityLabel = new javax.swing.JLabel();
        highRadioButton = new javax.swing.JRadioButton();
        standardRadioButton = new javax.swing.JRadioButton();
        descriptionPanel = new javax.swing.JPanel();
        descriptionScrollPanel = new javax.swing.JScrollPane();
        descriptionBox = new javax.swing.JTextArea();
        titleLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        backButton.setText("Back");
        backButton.setToolTipText("Returns to the previous screen.");
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

        colourChooser.setToolTipText("Pick a colour to represent the task.");

        nameField.setToolTipText("Enter the name of the task.");
        nameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameFieldKeyReleased(evt);
            }
        });

        timeField.setToolTipText("Enter the time you expect to need to complete the task.");

        deadlineField.setToolTipText("Enter the date the task is due in for.");

        categoryDropdown.setToolTipText("Pick the category the task belongs to.");
        categoryDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryDropdownActionPerformed(evt);
            }
        });

        nameCharsUsed.setText("0 out of 20 characters used");

        timeNeededFormatLabel.setText("Enter time in hours");

        deadlineFormatLabel.setText("(DD/MM/YYYY) format");

        priorityLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        priorityLabel.setText("Priority:");

        priorityGroup.add(highRadioButton);
        highRadioButton.setText("High");
        highRadioButton.setToolTipText("If selected this task willl be plotted automatically if not all tasks can be plotted.");

        priorityGroup.add(standardRadioButton);
        standardRadioButton.setSelected(true);
        standardRadioButton.setText("Standard");
        standardRadioButton.setToolTipText("If selected this task willl need to be plotted manually if not all tasks can be plotted.");

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
                            .addComponent(colourLabel)
                            .addComponent(priorityLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(categoryDropdown, 0, 200, Short.MAX_VALUE)
                                .addComponent(deadlineField)
                                .addComponent(timeField)
                                .addComponent(nameField, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(standardRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(highRadioButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameCharsUsed, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
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
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameLabel)
                    .addComponent(nameField)
                    .addComponent(nameCharsUsed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priorityLabel)
                    .addComponent(standardRadioButton)
                    .addComponent(highRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(colourLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colourChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        descriptionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Description"));

        descriptionBox.setColumns(20);
        descriptionBox.setLineWrap(true);
        descriptionBox.setRows(5);
        descriptionBox.setToolTipText("Enter a description of the task.");
        descriptionBox.setWrapStyleWord(true);
        descriptionScrollPanel.setViewportView(descriptionBox);

        javax.swing.GroupLayout descriptionPanelLayout = new javax.swing.GroupLayout(descriptionPanel);
        descriptionPanel.setLayout(descriptionPanelLayout);
        descriptionPanelLayout.setHorizontalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(descriptionScrollPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
        );
        descriptionPanelLayout.setVerticalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(descriptionScrollPanel)
        );

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        titleLabel.setText("Task Editor");

        saveButton.setText("Save");
        saveButton.setToolTipText("Saves the task with the current details to the database.");
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
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(descriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        int categoryID = categoryIDList.getDataAt(categoryDropdown.getSelectedIndex());
        String dateDueText = deadlineField.getText();
        int colourCode = colourChooser.getColor().getRGB();
        double timeSet = 0;
        Boolean highPriority = highRadioButton.isSelected();

        //<editor-fold defaultstate="collapsed" desc=" Valid checks ">
        //Checks to see if name is empty
        if (taskName.equals("")) {
            valid = false;
            new Popup("No task name entered").setVisible(true);
        }

        //Attemps to read the double. If it fails (eg. a letter entered) it doesn't create a task and creates a popup
        try {
            timeSet = Double.parseDouble(timeField.getText());
        } catch (NumberFormatException ex) {
            System.err.println(ex);
            valid = false;
            new Popup("Invalid time set").setVisible(true);
        }

        //Checks to see if the entered name is longer than 20 characters
        if (nameField.getText().length() > 20) {
            new Popup("Name is over 20 characters").setVisible(true);
            valid = false;
        }

        //Checks to see if date entered is in the correct format (DD/MM/YYYY)
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.parse(dateDueText);
        } catch (ParseException e) {
            new Popup("Invalid date format.").setVisible(true);
            valid = false;
        }
        //</editor-fold>

        //Creates the task and changes screen back to the menu if the variables are valid
        if (valid) {

            if (edit) {
                oldTask.editTask(taskName, description, categoryID, dateDueText, colourCode, timeSet, highPriority);
                ((TaskViewer) lastPanel).update();
            } else {
                new Task(taskName, description, categoryID, dateDueText, colourCode, timeSet, highPriority);
            }
            this.setVisible(false);
            this.lastPanel.setVisible(true);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    //Returns back to menu screen, nothing is saved
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Are you sure? Unsaved changes will be lost.", "Return to Menu", JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            this.setVisible(false);
            this.lastPanel.setVisible(true);
        }
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

    private void categoryDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryDropdownActionPerformed
        String SQL = "SELECT Colour FROM category, user WHERE category.CategoryID = " + categoryIDList.getDataAt(categoryDropdown.getSelectedIndex()) + " AND category.UserID = user.UserID AND user.UserID = " + User.getUserID();
        ResultSet rs = DatabaseHandle.query(SQL);
        try {
            if (rs.next()) {
                colourChooser.setColor(new Color(rs.getInt("Colour")));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        
    }//GEN-LAST:event_categoryDropdownActionPerformed

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
    private javax.swing.JScrollPane descriptionScrollPanel;
    private javax.swing.JRadioButton highRadioButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel nameCharsUsed;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.ButtonGroup priorityGroup;
    private javax.swing.JLabel priorityLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JRadioButton standardRadioButton;
    private javax.swing.JTextField timeField;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel timeNeededFormatLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
