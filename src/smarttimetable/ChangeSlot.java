package smarttimetable;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * A form used to alter the contents of a cell in the timetable to be another
 * task, category, or an empty slot.
 *
 * @author AdamPlatt
 */
public class ChangeSlot extends javax.swing.JFrame {

    //The timetable that is returned to when this frame closes
    private final Timetable timetable;
    //The ID list of items, either task or event, the user can pick from
    private final LinkedList idList = new LinkedList();
    //The timetable the slot belongs to and the position of the slot
    private final int day, time, timetableID;
    //The previous contents of the slot
    private final String previousContents;

    /**
     * Creates new form ChangeSlot
     *
     * @param timetable
     * @param previousContents
     * @param day
     * @param time
     * @param timetableID
     */
    public ChangeSlot(Timetable timetable, String previousContents, int day, int time, int timetableID) {
        initComponents();

        this.day = day;
        this.time = time;
        this.timetable = timetable;
        this.timetableID = timetableID;
        this.previousContents = previousContents;

        //Centers the frame to the centre of the monitor
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        //Displays the user logged in
        userLabel.setText("Logged in as: " + User.getUsername());

        //Setting the text of the field that displays the previous contents to the right value
        if (previousContents.equals("")) {
            this.slotContainsResultText.setText("Empty");
        } else {
            this.slotContainsResultText.setText(previousContents);
        }

        //Selecting the frame to display a list of tasks by default
        this.taskButton.setSelected(true);
        this.taskButtonPressed();
        this.setAlwaysOnTop(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        eventOrTaskGroup = new javax.swing.ButtonGroup();
        changeSlotPanel = new javax.swing.JPanel();
        slotContainsText = new javax.swing.JLabel();
        slotContainsResultText = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        changeButton = new javax.swing.JButton();
        changeToPanel = new javax.swing.JPanel();
        taskEventListScrollPanel = new javax.swing.JScrollPane();
        taskEventList = new javax.swing.JList<>();
        taskButton = new javax.swing.JRadioButton();
        eventButton = new javax.swing.JRadioButton();
        emptyRadioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        changeSlotPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Change Slot", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        slotContainsText.setText("Slot currently contains:");

        backButton.setText("Back");
        backButton.setToolTipText("Returns to the timetable.");
        backButton.setMaximumSize(new java.awt.Dimension(77, 26));
        backButton.setMinimumSize(new java.awt.Dimension(77, 26));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        changeButton.setText("Change");
        changeButton.setToolTipText("Changes the previously selected slot to the slot specified above.");
        changeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeButtonActionPerformed(evt);
            }
        });

        changeToPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Change to:"));

        taskEventList.setToolTipText("Select the task or event to replace the current slot.");
        taskEventListScrollPanel.setViewportView(taskEventList);

        eventOrTaskGroup.add(taskButton);
        taskButton.setText("Task");
        taskButton.setToolTipText("Loads the list of tasks.");
        taskButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taskButtonActionPerformed(evt);
            }
        });

        eventOrTaskGroup.add(eventButton);
        eventButton.setText("Event");
        eventButton.setToolTipText("Loads the list of events.");
        eventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventButtonActionPerformed(evt);
            }
        });

        eventOrTaskGroup.add(emptyRadioButton);
        emptyRadioButton.setText("Empty");
        emptyRadioButton.setToolTipText("Replace the selected slot with an empty slot.");
        emptyRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptyRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout changeToPanelLayout = new javax.swing.GroupLayout(changeToPanel);
        changeToPanel.setLayout(changeToPanelLayout);
        changeToPanelLayout.setHorizontalGroup(
            changeToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeToPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(changeToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(taskEventListScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(changeToPanelLayout.createSequentialGroup()
                        .addComponent(taskButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eventButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emptyRadioButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        changeToPanelLayout.setVerticalGroup(
            changeToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changeToPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(changeToPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(taskButton)
                    .addComponent(eventButton)
                    .addComponent(emptyRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskEventListScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout changeSlotPanelLayout = new javax.swing.GroupLayout(changeSlotPanel);
        changeSlotPanel.setLayout(changeSlotPanelLayout);
        changeSlotPanelLayout.setHorizontalGroup(
            changeSlotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeSlotPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(changeSlotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(changeToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changeSlotPanelLayout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeButton))
                    .addGroup(changeSlotPanelLayout.createSequentialGroup()
                        .addComponent(slotContainsText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slotContainsResultText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        changeSlotPanelLayout.setVerticalGroup(
            changeSlotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeSlotPanelLayout.createSequentialGroup()
                .addGroup(changeSlotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slotContainsText)
                    .addComponent(slotContainsResultText, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changeToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changeSlotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changeSlotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(changeButton))
                    .addComponent(userLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(changeSlotPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(changeSlotPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Returns user to the timetable
     *
     * @param evt
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        //Getting user confirmation
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?", "Back", JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            //Showing the timetable screen
            this.timetable.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * Changes the contents of the selected item in the frame
     *
     * @param evt
     */
    private void changeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeButtonActionPerformed
        //Making sure there is an item from the list selected
        if (!this.taskEventList.isSelectionEmpty()) {

            //Retrieving whether or not the item selected is a event or not
            boolean event = this.eventButton.isSelected();
            //Retrieving the ID of the item selected from the list
            int id = this.idList.getDataAt(this.taskEventList.getSelectedIndex());

            String sql;

            //Checking if slot record already exists (I.E. not blank)
            if (!this.previousContents.equals("")) {
                //Constructing the start and end clauses to the SQL as they are the same independant of whether it is an event or task
                String sqlStart = "UPDATE (timetable INNER JOIN timetableslot ON (timetableslot.TimetableID = timetable.TimetableID) AND (timetable.UserID = timetableslot.UserID)) INNER JOIN user ON timetable.UserID = user.UserID ";
                String sqlEnd = "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((timetable.TimetableID)= " + this.timetableID + ") AND ((timetableslot.Day)=" + this.day + ") AND ((timetableslot.Time)=" + this.time + "));";
                if (event) {
                    //Constructing the central clause of the SQL for an event
                    sql = sqlStart + "SET timetableslot.EventID = " + id + ", timetableslot.TaskID = Null\n" + sqlEnd;
                } else {
                    //Constructing the central clause of the SQL for a task
                    sql = sqlStart + "SET timetableslot.EventID = Null, timetableslot.TaskID = " + id + "\n" + sqlEnd;
                }
            } else {
                if (event) {
                    //Inserting a new record with an event ID
                    sql = "INSERT INTO timetableslot (UserID, EventID, TimetableID, Day, Time)\n"
                            + "VALUES(" + User.getUserID() + ", " + id + ", " + this.timetableID + ", " + this.day + ", " + this.time + ");";
                } else {
                    //Inserting a new record with a task ID
                    sql = "INSERT INTO timetableslot (UserID, TaskID, TimetableID, Day, Time)\n"
                            + "VALUES(" + User.getUserID() + ", " + id + ", " + this.timetableID + ", " + this.day + ", " + this.time + ");";
                }
            }

            int rowsAffected = DatabaseHandle.update(sql);

            //Checking there were no errors in the SQL
            if (rowsAffected != 0) {
                //Returning to timetable
                this.timetable.setVisible(true);
                this.timetable.reloadTimetable();
                this.dispose();
            }

            //Checking to see if the user wants to empty the slot
        } else if (this.emptyRadioButton.isSelected()) {
            int rowsAffected = -1;
            //If already empty then no record exists so don't have to delete anything
            if (!this.previousContents.equals("")) {
                String sql = "DELETE FROM timetableSlot\n"
                        + "WHERE (((UserID)=" + User.getUserID() + ") AND ((TimetableID)=" + this.timetableID + ") AND ((Day)=" + this.day + ") AND ((Time)= " + this.time + "));";

                rowsAffected = DatabaseHandle.update(sql);
            }
            //Checking to see there was no error with the SQL
            if (rowsAffected != 0) {
                //Returning to timetable
                this.timetable.setVisible(true);
                this.timetable.reloadTimetable();
                this.dispose();
            }
        } else {
            new Popup("Please select an item from the list.").setVisible(true);
        }
    }//GEN-LAST:event_changeButtonActionPerformed

    /**
     * Runs taskButtonPressed
     *
     * @param evt
     */
    private void taskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taskButtonActionPerformed
        taskButtonPressed();
    }//GEN-LAST:event_taskButtonActionPerformed

    /**
     * Loads the list of tasks
     */
    private void taskButtonPressed() {
        String sql = "SELECT task.TaskID AS ID, task.Name\n"
                + "FROM user INNER JOIN task ON user.UserID = task.UserID\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=False));";

        updateList(sql);
    }

    /**
     * Loads the items (events/tasks) given by the SQL into both the idList and
     * the list shown to the user
     *
     * @param sql
     */
    private void updateList(String sql) {
        //Running the SQL passed into this method
        ResultSet rs = DatabaseHandle.query(sql);

        //Checking that a result set was returned
        if (rs != null) {
            DefaultListModel dlm = new DefaultListModel();

            //Clearing the ID list
            idList.clear();
            try {
                while (rs.next()) {
                    //Loading names into dlm
                    dlm.addElement(rs.getString("Name"));
                    //Loading IDs into linked list
                    idList.addNode(rs.getInt("ID"));
                }
            } catch (SQLException e) {
            }

            //Setting the list of items shown to the user to be dlm
            this.taskEventList.setModel(dlm);
        }
    }

    /**
     * Loads the list of events
     *
     * @param evt
     */
    private void eventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventButtonActionPerformed
        String sql = "SELECT event.EventID AS ID, event.EventName AS Name\n"
                + "FROM event INNER JOIN user ON event.UserID = user.UserID\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((event.Hidden)=False));";

        updateList(sql);
    }//GEN-LAST:event_eventButtonActionPerformed

    /**
     * Empties both lists
     */
    private void emptyRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emptyRadioButtonActionPerformed
        this.taskEventList.setModel(new DefaultListModel());
        this.idList.clear();
    }//GEN-LAST:event_emptyRadioButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton changeButton;
    private javax.swing.JPanel changeSlotPanel;
    private javax.swing.JPanel changeToPanel;
    private javax.swing.JRadioButton emptyRadioButton;
    private javax.swing.JRadioButton eventButton;
    private javax.swing.ButtonGroup eventOrTaskGroup;
    private javax.swing.JLabel slotContainsResultText;
    private javax.swing.JLabel slotContainsText;
    private javax.swing.JRadioButton taskButton;
    private javax.swing.JList<String> taskEventList;
    private javax.swing.JScrollPane taskEventListScrollPanel;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
