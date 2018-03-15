package smarttimetable;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;

/**
 *
 * @author crazy
 */
public class ChangeSlot extends javax.swing.JFrame {

    private final Timetable timetable;
    private final LinkedList idList = new LinkedList();
    private final int day, time, timetableID;
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

        if (previousContents.equals("")) {
            this.slotContainsResultText.setText("Empty");
        } else {
            this.slotContainsResultText.setText(previousContents);
        }
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

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.timetable.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void changeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeButtonActionPerformed
        if (!this.taskEventList.isSelectionEmpty()) {

            boolean event = this.eventButton.isSelected();
            int id = this.idList.getDataAt(this.taskEventList.getSelectedIndex());

            String sql;

            if (!this.previousContents.equals("")) {
                if (event) {
                    sql = "UPDATE (timetable INNER JOIN timetableslot ON (timetableslot.TimetableID = timetable.TimetableID) AND (timetable.UserID = timetableslot.UserID)) INNER JOIN user ON timetable.UserID = user.UserID "
                            + "SET timetableslot.EventID = " + id + ", timetableslot.TaskID = Null\n"
                            + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((timetable.TimetableID)= " + this.timetableID + ") AND ((timetableslot.Day)=" + this.day + ") AND ((timetableslot.Time)=" + this.time + "));";
                } else {
                    sql = "UPDATE (timetable INNER JOIN timetableslot ON (timetableslot.TimetableID = timetable.TimetableID) AND (timetable.UserID = timetableslot.UserID)) INNER JOIN user ON timetable.UserID = user.UserID "
                            + "SET timetableslot.EventID = Null, timetableslot.TaskID = " + id + "\n"
                            + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((timetable.TimetableID)= " + this.timetableID + ") AND ((timetableslot.Day)=" + this.day + ") AND ((timetableslot.Time)=" + this.time + "));";
                }
            } else {
                if (event) {
                    sql = "INSERT INTO timetableslot (UserID, EventID, TimetableID, Day, Time)\n"
                            + "VALUES(" + User.getUserID() + ", " + id + ", " + this.timetableID + ", " + this.day + ", " + this.time + ");";
                } else {
                    sql = "INSERT INTO timetableslot (UserID, TaskID, TimetableID, Day, Time)\n"
                            + "VALUES(" + User.getUserID() + ", " + id + ", " + this.timetableID + ", " + this.day + ", " + this.time + ");";
                }
            }

            DatabaseHandle.update(sql);

            this.timetable.setVisible(true);
            this.timetable.reloadTimetable();
            this.dispose();

        } else if (this.emptyRadioButton.isSelected()) {

            String sql = "DELETE FROM timetableSlot\n"
                    + "WHERE (((UserID)=" + User.getUserID() + ") AND ((TimetableID)=" + this.timetableID + ") AND ((Day)=" + this.day + ") AND ((Time)= " + this.time + "));";

            DatabaseHandle.update(sql);

            this.timetable.setVisible(true);
            this.timetable.reloadTimetable();
            this.dispose();

        } else {
            new Popup("Please select an item from the list.").setVisible(true);
        }
    }//GEN-LAST:event_changeButtonActionPerformed

    private void taskButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taskButtonActionPerformed
        taskButtonPressed();
    }//GEN-LAST:event_taskButtonActionPerformed

    private void taskButtonPressed() {
        String sql = "SELECT task.TaskID AS ID, task.Name\n"
                + "FROM user INNER JOIN task ON user.UserID = task.UserID\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=False));";

        updateList(sql);
    }

    private void updateList(String sql) {
        ResultSet rs = DatabaseHandle.query(sql);

        DefaultListModel dlm = new DefaultListModel();

        idList.clear();
        try {
            while (rs.next()) {
                dlm.addElement(rs.getString("Name"));
                idList.addNode(rs.getInt("ID"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        this.taskEventList.setModel(dlm);
    }

    private void eventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventButtonActionPerformed
        String sql = "SELECT event.EventID AS ID, event.EventName AS Name\n"
                + "FROM event INNER JOIN user ON event.UserID = user.UserID\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((event.Hidden)=False));";

        updateList(sql);
    }//GEN-LAST:event_eventButtonActionPerformed

    private void emptyRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emptyRadioButtonActionPerformed
        this.taskEventList.setModel(new DefaultListModel());
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
