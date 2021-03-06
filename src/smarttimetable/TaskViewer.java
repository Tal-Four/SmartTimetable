package smarttimetable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * A form used to manage and display details about the user’s tasks.
 *
 * @author AdamPlatt
 */
public class TaskViewer extends javax.swing.JFrame {

    private final LinkedList taskIDList = new LinkedList();

    /**
     * Creates new form TaskViewer
     */
    public TaskViewer() {
        initComponents();

        //Sets todo to be selected by default
        todoButton.setSelected(true);

        //Centers the frame to the centre of the monitor
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        //Sets the user's name on screen and loads the list of events                
        userLabel.setText("Logged in as: " + User.getUsername());
        setUpList();
    }

    /**
     * Sorts the list of tasks by the new sort
     */
    private void updateIDList() {
        //Clears the list of IDs
        this.taskIDList.clear();

        //Retrieving the selected sort from form fields
        String selectedSort = sortDropdown.getSelectedItem().toString();
        String sql;
        //Retrieving whether or not completed tasks are being loaded
        Boolean archive = archivedButton.isSelected();
        String sort = "";
        //Determining the order of the sort section of the SQL statement
        if (ascDescSortButton.getText().equals("Descending")) {
            sort = " DESC";
        }
        //Determining what the SQL statement is sorted by
        //Checking if the selected sort is by category
        if (!selectedSort.equals("Category")) {
            switch (selectedSort) {
                case ("Name"):
                    selectedSort = "Name";
                    break;
                case ("Date set"):
                    selectedSort = "DateSet";
                    break;
                case ("Date due"):
                    selectedSort = "DateDue";
                    break;
                case ("Time allotted"):
                    selectedSort = "TimeModified";
                    break;
                case ("Time used"):
                    selectedSort = "TimeUsed";
                    break;
                default:
                    selectedSort = "Name";
                    break;
            }

            //Constructing SQL statement from the selected sort and sort order determined above
            sql = "SELECT task.TaskID\n"
                    + "FROM user INNER JOIN task ON user.UserID = task.UserID\n"
                    + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=" + archive + "))\n"
                    + "ORDER BY task." + selectedSort + sort + ";";
        } else {
            //Category needs it's own sort as SQL has to access category properties
            //Constructing SQL statement from the selected sort and sort order determined above
            sql = "SELECT task.TaskID\n"
                    + "FROM task INNER JOIN (user INNER JOIN category ON user.UserID = category.UserID) ON (user.UserID = task.UserID) AND (task.CategoryID = category.CategoryID) AND (task.UserID = category.UserID)\n"
                    + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=" + archive + "))\n"
                    + "ORDER BY category.Name " + sort + ";";
        }

        ResultSet rs = DatabaseHandle.query(sql);
        //Checking SQL executed successfully
        if (rs != null) {
            try {
                //Adding items to the list
                while (rs.next()) {
                    this.taskIDList.addNode(rs.getInt("TaskID"));
                }
            } catch (SQLException e) {
            }
        }
    }

    /**
     * Sets the taskList to the user's tasks given an order (eg. alphabetical)
     *
     */
    private void setUpList() {
        //Refreshes the list of IDs
        updateIDList();
        DefaultListModel dlm = new DefaultListModel();

        //Loops through each ID in the ID list
        for (int count = 0; count < this.taskIDList.length(); count++) {
            //Selecting the name of the task associated with the current ID in taskIDList
            String sql = "SELECT task.Name\n"
                    + "FROM user INNER JOIN task ON user.UserID = task.UserID\n"
                    + "WHERE (((task.TaskID)=" + this.taskIDList.getDataAt(count) + ") AND ((user.UserID)=" + User.getUserID() + "));";
            ResultSet rs = DatabaseHandle.query(sql);
            //Checking SQL executed successfully
            if (rs != null) {
                try {
                    while (rs.next()) {
                        //Adding task to the list of tasks displayed to the user
                        dlm.addElement(rs.getString("Name"));
                    }
                } catch (SQLException e) {
                }
            }
        }
        //Setting the list of tasks shown in taskList to be the list of tasks constructed above
        this.taskList.setModel(dlm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        todoOrArchivedButtonGroup = new javax.swing.ButtonGroup();
        backButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        jFrameTitleLabel = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();
        sortLabel = new javax.swing.JLabel();
        sortDropdown = new javax.swing.JComboBox<>();
        taskPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taskList = new javax.swing.JList<>();
        todoButton = new javax.swing.JRadioButton();
        archivedButton = new javax.swing.JRadioButton();
        descriptionPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionBox = new javax.swing.JTextArea();
        deleteButton = new javax.swing.JButton();
        categoryLabel = new javax.swing.JLabel();
        dateSetLabel = new javax.swing.JLabel();
        dateDueLabel = new javax.swing.JLabel();
        timeAllottedLabel = new javax.swing.JLabel();
        timeUsedLabel = new javax.swing.JLabel();
        completeButton = new javax.swing.JButton();
        colourPreview = new javax.swing.JPanel();
        ascDescSortButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        backButton.setText("Back");
        backButton.setToolTipText("Returns to the main menu.");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        exitButton.setText("Exit");
        exitButton.setToolTipText("Closes the program.");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jFrameTitleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jFrameTitleLabel.setText("Task Viewer");

        editButton.setText("Edit Selected");
        editButton.setToolTipText("Edit the selected task.");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        sortLabel.setText("Sort By:");

        sortDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Date set", "Date due", "Category", "Time allotted", "Time used" }));
        sortDropdown.setToolTipText("Defines what the list of tasks is sorted by.");
        sortDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortDropdownActionPerformed(evt);
            }
        });

        taskPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Tasks"));

        taskList.setToolTipText("Select a task from the list to have its details displayed on the right.");
        taskList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                taskListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(taskList);

        todoOrArchivedButtonGroup.add(todoButton);
        todoButton.setText("To-do");
        todoButton.setToolTipText("Loads the list of incomplete tasks.");
        todoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todoButtonActionPerformed(evt);
            }
        });

        todoOrArchivedButtonGroup.add(archivedButton);
        archivedButton.setText("Archived");
        archivedButton.setToolTipText("Loads the list of complete tasks.");
        archivedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archivedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout taskPanelLayout = new javax.swing.GroupLayout(taskPanel);
        taskPanel.setLayout(taskPanelLayout);
        taskPanelLayout.setHorizontalGroup(
            taskPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(taskPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(taskPanelLayout.createSequentialGroup()
                        .addComponent(todoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(archivedButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                .addContainerGap())
        );
        taskPanelLayout.setVerticalGroup(
            taskPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taskPanelLayout.createSequentialGroup()
                .addGroup(taskPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(todoButton)
                    .addComponent(archivedButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        descriptionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Description"));

        descriptionBox.setEditable(false);
        descriptionBox.setColumns(20);
        descriptionBox.setRows(5);
        descriptionBox.setToolTipText("Displays the description of the selected task.");
        jScrollPane2.setViewportView(descriptionBox);

        javax.swing.GroupLayout descriptionPanelLayout = new javax.swing.GroupLayout(descriptionPanel);
        descriptionPanel.setLayout(descriptionPanelLayout);
        descriptionPanelLayout.setHorizontalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        descriptionPanelLayout.setVerticalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );

        deleteButton.setText("Delete");
        deleteButton.setToolTipText("Deletes the selected task.");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        categoryLabel.setText("Category:");
        categoryLabel.setToolTipText("Displays the category of the selected task.");

        dateSetLabel.setText("Date Set:");
        dateSetLabel.setToolTipText("Displays the date the selected task was set on.");

        dateDueLabel.setText("Date Due:");
        dateDueLabel.setToolTipText("Displays the date the selected task is due to be done for.");

        timeAllottedLabel.setText("Time Allotted:");
        timeAllottedLabel.setToolTipText("Displays the time allocated by the program in hours to the task.");

        timeUsedLabel.setText("Time Used:");
        timeUsedLabel.setToolTipText("Displays the number of hours that have been marked as completed for the selected task.");

        completeButton.setText("Complete");
        completeButton.setToolTipText("Completes the selected task.");
        completeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completeButtonActionPerformed(evt);
            }
        });

        colourPreview.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout colourPreviewLayout = new javax.swing.GroupLayout(colourPreview);
        colourPreview.setLayout(colourPreviewLayout);
        colourPreviewLayout.setHorizontalGroup(
            colourPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        colourPreviewLayout.setVerticalGroup(
            colourPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        ascDescSortButton.setText("Ascending");
        ascDescSortButton.setToolTipText("Defines whether the list of tasks is sorted in ascending or descending order. The current text is the current order.");
        ascDescSortButton.setMaximumSize(new java.awt.Dimension(101, 26));
        ascDescSortButton.setMinimumSize(new java.awt.Dimension(101, 26));
        ascDescSortButton.setPreferredSize(new java.awt.Dimension(101, 26));
        ascDescSortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascDescSortButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21)
                        .addComponent(exitButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jFrameTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ascDescSortButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(taskPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateDueLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateSetLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(categoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)
                                .addComponent(colourPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(descriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(completeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(editButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(deleteButton)))
                                    .addComponent(timeAllottedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(timeUsedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ascDescSortButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFrameTitleLabel)
                        .addComponent(sortDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sortLabel)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(taskPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(descriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(categoryLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateSetLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateDueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeAllottedLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeUsedLabel))
                            .addComponent(colourPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteButton)
                            .addComponent(completeButton)
                            .addComponent(editButton))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(backButton)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Returns to main menu
     *
     * @param evt
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        //Getting user confirmation
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?", "Back", JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            //Showing the menu screen
            this.setVisible(false);
            new Menu().setVisible(true);
        }
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * Closes the program
     *
     * @param evt
     */
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        //Getting user confirmation
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to close the program?", "Close Program", JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            //Closes the program
            System.exit(0);
        }
    }//GEN-LAST:event_exitButtonActionPerformed

    /**
     * Edits the selected task
     *
     * @param evt
     */
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        //Making sure the user is trying to edit tasks that are not complete
        if (this.todoButton.isSelected()) {
            //Creates a task editor form passing in the task being edited and closes this one
            this.setVisible(false);
            Task task = new Task(this.taskIDList.getDataAt(this.taskList.getSelectedIndex()));
            new TaskEditor(task, this).setVisible(true);
        } else {
            new Popup("Cannot edit an archived task.").setVisible(true);
        }
    }//GEN-LAST:event_editButtonActionPerformed

    /**
     * Sorts the list of tasks
     *
     * @param evt
     */
    private void sortDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortDropdownActionPerformed
        setUpList();
    }//GEN-LAST:event_sortDropdownActionPerformed

    /**
     * Deletes the selected task if the user confirms the choice
     *
     * @param evt
     */
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        //Checking an item from the list is selected
        if (taskList.getSelectedValue() != null) {
            //Getting user confirmation
            int yesNo = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + taskList.getSelectedValue(), "Delete task", JOptionPane.YES_NO_OPTION);
            if (yesNo == 0) {
                Task task = new Task(this.taskIDList.getDataAt(this.taskList.getSelectedIndex()));
                //Task is deleted
                task.deleteTask();
                //List of tasks is refreshed
                setUpList();
            }
        } else {
            new Popup("No task selected.").setVisible(true);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    /**
     * Removes the task from the database and recalculates the category modifier
     *
     * @param evt
     */
    private void completeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completeButtonActionPerformed
        //Checking a task has been selected from the list
        if (taskList.getSelectedValue() != null) {
            //Loading task's details
            Task task = new Task(this.taskIDList.getDataAt(this.taskList.getSelectedIndex()));
            //Checking whether the user is attempting to unarchive a task
            if (this.completeButton.getText().equals("To-Do")) {
                //Marking task as to-do
                task.markAsTodo();
                //Refreshing task list
                this.setUpList();
            } else {
                //Creating a OptionPane to get the user's decision
                Object[] options = {"Mark As Complete", "Complete Hours", "Cancel"};
                int result = JOptionPane.showOptionDialog(this, "Mark as complete to finish the task.\nComplete hours to add how much time you've completed.", "Task Completion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                //checking which option the user chose
                if (result == 1) {
                    //User chose to complete hours so an complete hours form is created
                    new CompleteHours(this, task).setVisible(true);
                    this.setVisible(false);
                } else if (result == 0) {
                    //User chose to mark as complete so task is marked as complete
                    task.complete();
                    setUpList();
                }
            }
        } else {
            new Popup("Please select an item.").setVisible(true);
        }
    }//GEN-LAST:event_completeButtonActionPerformed

    /**
     * Reloads the list of tasks and the details of the current task
     */
    public void update() {
        //Reloading details
        this.loadDetails();
        //Reloading list of tasks
        this.setUpList();
    }

    /**
     * Loads the details of the selected task into the details section
     */
    private void loadDetails() {
        Task selectedTask = new Task(this.taskIDList.getDataAt(this.taskList.getSelectedIndex()));

        //Setting the labels
        categoryLabel.setText("Category: " + selectedTask.getCategory().getName());
        dateDueLabel.setText("Date Due: " + selectedTask.sqlDateToTextFormat(selectedTask.getDateDue()));
        dateSetLabel.setText("Date Set: " + selectedTask.sqlDateToTextFormat(selectedTask.getDateSet()));
        timeAllottedLabel.setText("Time Allotted: " + selectedTask.getTimeModified());
        timeUsedLabel.setText("Time Used: " + selectedTask.getTimeUsed());
        descriptionBox.setText(selectedTask.getDescription());
        colourPreview.setBackground(new Color(selectedTask.getColourCode()));
    }

    /**
     * Loads the list of to-do tasks
     *
     * @param evt
     */
    private void todoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todoButtonActionPerformed
        this.completeButton.setText("Complete");
        setUpList();
    }//GEN-LAST:event_todoButtonActionPerformed

    /**
     * Loads the list of complete tasks
     *
     * @param evt
     */
    private void archivedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archivedButtonActionPerformed
        this.completeButton.setText("To-Do");
        setUpList();
    }//GEN-LAST:event_archivedButtonActionPerformed

    /**
     * Changes the order of the list of tasks
     *
     * @param evt
     */
    private void ascDescSortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascDescSortButtonActionPerformed
        //Switches text of sort order button to the other order
        if (ascDescSortButton.getText().equals("Ascending")) {
            ascDescSortButton.setText("Descending");
        } else {
            ascDescSortButton.setText("Ascending");
        }
        //Refreshes list of tasks
        this.setUpList();
    }//GEN-LAST:event_ascDescSortButtonActionPerformed

    /**
     * Loads the details of the selected task
     *
     * @param evt
     */
    private void taskListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_taskListValueChanged
        loadDetails();
    }//GEN-LAST:event_taskListValueChanged

    //<editor-fold defaultstate="collapsed" desc=" jFrame variables ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton archivedButton;
    private javax.swing.JButton ascDescSortButton;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JPanel colourPreview;
    private javax.swing.JButton completeButton;
    private javax.swing.JLabel dateDueLabel;
    private javax.swing.JLabel dateSetLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextArea descriptionBox;
    private javax.swing.JPanel descriptionPanel;
    private javax.swing.JButton editButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jFrameTitleLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> sortDropdown;
    private javax.swing.JLabel sortLabel;
    private javax.swing.JList<String> taskList;
    private javax.swing.JPanel taskPanel;
    private javax.swing.JLabel timeAllottedLabel;
    private javax.swing.JLabel timeUsedLabel;
    private javax.swing.JRadioButton todoButton;
    private javax.swing.ButtonGroup todoOrArchivedButtonGroup;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
