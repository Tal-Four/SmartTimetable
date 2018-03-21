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
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Adam-PC
 */
public class EventViewer extends javax.swing.JFrame {

    private final LinkedList eventIDList = new LinkedList();

    /**
     * Creates new form TaskViewer
     */
    public EventViewer() {
        initComponents();

        //Sets the user's name on screen and loads the list of events
        userLabel.setText("Logged in as: " + User.getUsername());
        setUpList();

        //Centers the frame to the centre of the monitor
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    private void updateIDList() {
        this.eventIDList.clear();

        String selectedSort = sortDropdown.getSelectedItem().toString();
        String sql;
        String sort = "";
        if (ascDescSortButton.getText().equals("Descending")) {
            sort = " DESC";
        }

        //Setting the correct SQL
        switch (selectedSort) {
            case ("Name"):
                selectedSort = "EventName";
                break;
            case ("Date/Day"):
                selectedSort = "Date, event.Day";
                break;
            case ("Start time"):
                selectedSort = "StartTime";
                break;
            case ("End time"):
                selectedSort = "EndTime";
                break;
            default:
                selectedSort = "EventName";
                break;
        }
        sql = "SELECT event.EventID\n"
                + "FROM user INNER JOIN event ON user.UserID = event.UserID\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((event.Hidden)=False))\n"
                + "ORDER BY event." + selectedSort + sort + ";";
        ResultSet rs = DatabaseHandle.query(sql);
        try {
            while (rs.next()) {
                this.eventIDList.addNode(rs.getInt("EventID"));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
    }

    //Sets the taskList to the user's tasks given an order (eg. alphabetical)
    private void setUpList() {
        updateIDList();
        DefaultListModel dlm = new DefaultListModel();

        for (int count = 0; count < this.eventIDList.length(); count++) {
            String sql = "SELECT EventName FROM event, user WHERE event.UserID = user.UserID AND user.UserID = "
                    + User.getUserID() + " AND event.EventID = " + this.eventIDList.getDataAt(count);
            ResultSet rs = DatabaseHandle.query(sql);
            try {
                while (rs.next()) {
                    dlm.addElement(rs.getString("EventName"));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            
        }

        this.eventList.setModel(dlm);
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
        userLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        jFrameTitleLabel = new javax.swing.JLabel();
        sortLabel = new javax.swing.JLabel();
        sortDropdown = new javax.swing.JComboBox<>();
        eventPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        eventList = new javax.swing.JList<>();
        descriptionPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionBox = new javax.swing.JTextArea();
        endTimeLabel = new javax.swing.JLabel();
        startTimeLabel = new javax.swing.JLabel();
        dayDateLabel = new javax.swing.JLabel();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        colourPreview = new javax.swing.JPanel();
        dayDateContentsLabel = new javax.swing.JLabel();
        endTimeContentsLabel = new javax.swing.JLabel();
        startTimeContentsLabel = new javax.swing.JLabel();
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
        jFrameTitleLabel.setText("Event Viewer");

        sortLabel.setText("Sort By:");

        sortDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Date/Day", "Start time", "End time" }));
        sortDropdown.setToolTipText("Select the characteristic that you would like to sort the list of event by.");
        sortDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortDropdownActionPerformed(evt);
            }
        });

        eventPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Events"));

        eventList.setToolTipText("Select an event for its details to be loaded into the right side.");
        eventList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                eventListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(eventList);

        javax.swing.GroupLayout eventPanelLayout = new javax.swing.GroupLayout(eventPanel);
        eventPanel.setLayout(eventPanelLayout);
        eventPanelLayout.setHorizontalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eventPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );
        eventPanelLayout.setVerticalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, eventPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addContainerGap())
        );

        descriptionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Description"));

        descriptionBox.setEditable(false);
        descriptionBox.setColumns(20);
        descriptionBox.setRows(5);
        descriptionBox.setToolTipText("The description of the selected event.");
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
        );

        endTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        endTimeLabel.setText("End Time:");

        startTimeLabel.setText("Start Time:");

        dayDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dayDateLabel.setText("Day:");

        editButton.setText("Edit Selected");
        editButton.setToolTipText("Edit the selected event.");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.setToolTipText("Delete the selected event.");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        colourPreview.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        colourPreview.setToolTipText("The colour of the event.");

        javax.swing.GroupLayout colourPreviewLayout = new javax.swing.GroupLayout(colourPreview);
        colourPreview.setLayout(colourPreviewLayout);
        colourPreviewLayout.setHorizontalGroup(
            colourPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );
        colourPreviewLayout.setVerticalGroup(
            colourPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        dayDateContentsLabel.setToolTipText("The day or date the event occurs on.");

        endTimeContentsLabel.setToolTipText("The time the event ends at.");

        startTimeContentsLabel.setToolTipText("The time the event starts at.");

        ascDescSortButton.setText("Ascending");
        ascDescSortButton.setToolTipText("Defines whether the sort is done in ascending or descending order.  The current text is the current order.");
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addComponent(eventPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(startTimeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dayDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(endTimeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dayDateContentsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(startTimeContentsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(endTimeContentsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(colourPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(descriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(eventPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(descriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dayDateLabel)
                                    .addComponent(dayDateContentsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(startTimeLabel)
                                    .addComponent(startTimeContentsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(endTimeLabel)
                                    .addComponent(endTimeContentsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(colourPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteButton)
                            .addComponent(editButton))
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(backButton)
                        .addComponent(exitButton)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.setVisible(false);
        new Menu().setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void sortDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortDropdownActionPerformed
        setUpList();
    }//GEN-LAST:event_sortDropdownActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if (eventList.getSelectedValue() != null) {
            new EventEditor(this.eventIDList.getDataAt(this.eventList.getSelectedIndex()), this).setVisible(true);
            this.setVisible(false);
        } else {
            new Popup("Please select an event before attempting to edit.").setVisible(true);
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if (eventList.getSelectedValue() != null) {
            int yesNo = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + eventList.getSelectedValue(), "Delete event", JOptionPane.YES_NO_OPTION);
            if (yesNo == 0) {
                Event event = new Event(this.eventIDList.getDataAt(this.eventList.getSelectedIndex()));
                event.deleteEvent();
                setUpList();
            }
        } else {
            new Popup("Please select an event before attempting to edit.").setVisible(true);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void eventListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_eventListValueChanged
        updateDetails();
    }//GEN-LAST:event_eventListValueChanged

    private void updateDetails() {
        Event selectedEvent = new Event(this.eventIDList.getDataAt(this.eventList.getSelectedIndex()));

        if (selectedEvent.getDate() == null) {
            dayDateLabel.setText("Day:");
            dayDateContentsLabel.setText(selectedEvent.dayIntToString(selectedEvent.getDay()));
        } else {
            dayDateLabel.setText("Date:");
            dayDateContentsLabel.setText(selectedEvent.sqlDateToTextFormat(selectedEvent.getDate()));
        }
        descriptionBox.setText(selectedEvent.getDescription());
        startTimeContentsLabel.setText(selectedEvent.timeToString(0)[0] + ":" + selectedEvent.timeToString(0)[1]);
        endTimeContentsLabel.setText(selectedEvent.timeToString(1)[0] + ":" + selectedEvent.timeToString(1)[1]);
        colourPreview.setBackground(new Color(selectedEvent.getColourCode()));
    }
    
    public void update(){
        this.updateDetails();
        this.setUpList();
    }

    private void ascDescSortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascDescSortButtonActionPerformed
        if (ascDescSortButton.getText().equals("Ascending")) {
            ascDescSortButton.setText("Descending");
        } else {
            ascDescSortButton.setText("Ascending");
        }
        this.setUpList();
    }//GEN-LAST:event_ascDescSortButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ascDescSortButton;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel colourPreview;
    private javax.swing.JLabel dayDateContentsLabel;
    private javax.swing.JLabel dayDateLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextArea descriptionBox;
    private javax.swing.JPanel descriptionPanel;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel endTimeContentsLabel;
    private javax.swing.JLabel endTimeLabel;
    private javax.swing.JList<String> eventList;
    private javax.swing.JPanel eventPanel;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jFrameTitleLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> sortDropdown;
    private javax.swing.JLabel sortLabel;
    private javax.swing.JLabel startTimeContentsLabel;
    private javax.swing.JLabel startTimeLabel;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
