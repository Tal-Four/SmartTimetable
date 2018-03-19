/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttimetable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author Adam-PC
 */
public class EventEditor extends javax.swing.JFrame {

    private final boolean edit;
    private int oldEventID;
    private JFrame lastPanel;

    //Creates a blank EventEditor form
    public EventEditor(JFrame lastPanel) {
        initialise(lastPanel);
        this.edit = false;
    }

    //Creates a prefilled EventEditor form
    public EventEditor(int oldEventID, JFrame lastPanel) {
        initialise(lastPanel);
        this.edit = true;
        this.oldEventID = oldEventID;

        //Setting values of fields
        Event event = new Event(this.oldEventID);
        eventNameField.setText(event.getEventName());
        descriptionText.setText(event.getDescription());
        daySelection.setSelectedItem(event.dayIntToString(event.getDay()));
        colourChooser.setColor(new Color(event.getColourCode()));
        startHourDropdown.setSelectedItem(event.timeToString(0)[0]);
        startMinuteDropdown.setSelectedItem(event.timeToString(0)[1]);
        endHourDropdown.setSelectedItem(event.timeToString(1)[0]);
        endMinuteDropdown.setSelectedItem(event.timeToString(1)[1]);
    }

    private void initialise(JFrame lastPanel) {
        initComponents();

        this.lastPanel = lastPanel;

        this.dateField.setEnabled(false);

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

        dayDateButtonGroup = new javax.swing.ButtonGroup();
        mainDetailsPanel = new javax.swing.JPanel();
        eventNameLabel = new javax.swing.JLabel();
        startTimeLabel = new javax.swing.JLabel();
        colourLabel = new javax.swing.JLabel();
        endTimeLabel = new javax.swing.JLabel();
        colourChooser = new javax.swing.JColorChooser();
        eventNameField = new javax.swing.JTextField();
        daySelection = new javax.swing.JComboBox<>();
        startHourDropdown = new javax.swing.JComboBox<>();
        startMinuteDropdown = new javax.swing.JComboBox<>();
        endHourDropdown = new javax.swing.JComboBox<>();
        endMinuteDropdown = new javax.swing.JComboBox<>();
        eventNameCharsUsed = new javax.swing.JLabel();
        dayRadioButton = new javax.swing.JRadioButton();
        dateRadioButton = new javax.swing.JRadioButton();
        dateField = new javax.swing.JTextField();
        dateFormatLabel = new javax.swing.JLabel();
        descriptionPanel = new javax.swing.JPanel();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionText = new javax.swing.JTextArea();
        jFrameTitle = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        mainDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        eventNameLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        eventNameLabel.setText("Event Name:");

        startTimeLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        startTimeLabel.setText("Start Time:");

        colourLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        colourLabel.setText("Colour:");

        endTimeLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        endTimeLabel.setText("End Time:");

        colourChooser.setToolTipText("Pick a colour to represent the event.");

        eventNameField.setToolTipText("The name of the event being created or edited.");
        eventNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                eventNameFieldKeyReleased(evt);
            }
        });

        daySelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }));
        daySelection.setToolTipText("Selects the day of the week the event occurs on.");

        startHourDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        startHourDropdown.setToolTipText("Enter the time the event starts.");

        startMinuteDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "30" }));
        startMinuteDropdown.setToolTipText("Enter the time the event starts.");

        endHourDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        endHourDropdown.setToolTipText("Enter the time the event ends.");

        endMinuteDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "30" }));
        endMinuteDropdown.setToolTipText("Enter the time the event ends.");

        eventNameCharsUsed.setText("0 out of 20 characters used");

        dayDateButtonGroup.add(dayRadioButton);
        dayRadioButton.setSelected(true);
        dayRadioButton.setText("Day:");
        dayRadioButton.setToolTipText("If selected, the event occurs every week on the selected day.");
        dayRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayRadioButtonActionPerformed(evt);
            }
        });

        dayDateButtonGroup.add(dateRadioButton);
        dateRadioButton.setText("Date");
        dateRadioButton.setToolTipText("If selected then the event will occur once on the date specified.");
        dateRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateRadioButtonActionPerformed(evt);
            }
        });

        dateField.setToolTipText("Enter the date the event will occur on in dd/mm/yyyy format.");

        dateFormatLabel.setText("dd/mm/yyyy format");

        javax.swing.GroupLayout mainDetailsPanelLayout = new javax.swing.GroupLayout(mainDetailsPanel);
        mainDetailsPanel.setLayout(mainDetailsPanelLayout);
        mainDetailsPanelLayout.setHorizontalGroup(
            mainDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainDetailsPanelLayout.createSequentialGroup()
                        .addGroup(mainDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eventNameLabel)
                            .addComponent(startTimeLabel)
                            .addComponent(endTimeLabel)
                            .addComponent(colourLabel)
                            .addComponent(dayRadioButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainDetailsPanelLayout.createSequentialGroup()
                                .addGroup(mainDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(startHourDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(endHourDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(endMinuteDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(startMinuteDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(mainDetailsPanelLayout.createSequentialGroup()
                                .addComponent(eventNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eventNameCharsUsed))
                            .addGroup(mainDetailsPanelLayout.createSequentialGroup()
                                .addComponent(daySelection, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dateRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateFormatLabel))))
                    .addComponent(colourChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainDetailsPanelLayout.setVerticalGroup(
            mainDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainDetailsPanelLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(mainDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventNameLabel)
                    .addComponent(eventNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eventNameCharsUsed))
                .addGap(18, 18, 18)
                .addGroup(mainDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(daySelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayRadioButton)
                    .addComponent(dateRadioButton)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateFormatLabel))
                .addGap(18, 18, 18)
                .addGroup(mainDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startTimeLabel)
                    .addComponent(startHourDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startMinuteDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(endTimeLabel)
                    .addComponent(endHourDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endMinuteDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(colourLabel)
                .addGap(4, 4, 4)
                .addComponent(colourChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        descriptionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Description"));

        descriptionText.setColumns(20);
        descriptionText.setRows(5);
        descriptionText.setToolTipText("Enter a description of the event.");
        descriptionScrollPane.setViewportView(descriptionText);

        javax.swing.GroupLayout descriptionPanelLayout = new javax.swing.GroupLayout(descriptionPanel);
        descriptionPanel.setLayout(descriptionPanelLayout);
        descriptionPanelLayout.setHorizontalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
        );
        descriptionPanelLayout.setVerticalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
        );

        jFrameTitle.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jFrameTitle.setText("Event Editor");

        saveButton.setText("Save");
        saveButton.setToolTipText("Saves the event with the current details to the database.");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        backButton.setText("Back");
        backButton.setToolTipText("Returns to the previous screen.");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
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
                        .addComponent(saveButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFrameTitle)
                            .addComponent(mainDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jFrameTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mainDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(descriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed

        //Checking to see if the name is valid
        if (eventNameField.getText().equals("") || eventNameField.getText().length() > 20) {
            new Popup("Name over 20 characters or blank").setVisible(true);
        } else if (this.dateRadioButton.isSelected() && !(dateField.getText().length() == 10 && dateField.getText().charAt(2) == '/' && dateField.getText().charAt(5) == '/')) {
            new Popup("Invalid date format").setVisible(true);
        } else {
            Event event = new Event();

            //Assigning the value of day based on the dropdown
            int day = event.dayStringToInt(daySelection.getSelectedItem().toString());
            String date = dateField.getText();
            //Changing the start and end times to the needed format based off the dropdowns
            double endTime, startTime;
            startTime = dropdownsToDecimal(startHourDropdown, startMinuteDropdown);
            endTime = dropdownsToDecimal(endHourDropdown, endMinuteDropdown);

            if (edit) {
                //Edits an existing record
                event = new Event(this.oldEventID);
                if (this.dateRadioButton.isSelected()) {
                    event.editEvent(eventNameField.getText(), descriptionText.getText(), colourChooser.getColor().getRGB(), date, endTime, startTime);
                } else {
                    event.editEvent(eventNameField.getText(), descriptionText.getText(), colourChooser.getColor().getRGB(), day, endTime, startTime);
                }
                ((EventViewer) lastPanel).update();
            } else {
                //Entering the event into the database
                if (this.dateRadioButton.isSelected()) {
                    new Event(eventNameField.getText(), descriptionText.getText(), colourChooser.getColor().getRGB(), date, endTime, startTime);
                } else {
                    new Event(eventNameField.getText(), descriptionText.getText(), colourChooser.getColor().getRGB(), day, endTime, startTime);
                }
            }

            //Returning to previous screen
            this.setVisible(false);
            this.lastPanel.setVisible(true);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    //Converts the dropdown options to a time
    private double dropdownsToDecimal(JComboBox hour, JComboBox minute) {
        double decimalTime = Integer.parseInt(hour.getSelectedItem().toString()) + minutesToDecimal(minute.getSelectedItem().toString());
        return decimalTime;
    }

    //Changes minutes to decimal
    private double minutesToDecimal(String numberString) {
        double numberDouble = 0;
        //Attempts to parse the String to an int and then divides it by 60
        try {
            numberDouble = Double.parseDouble(numberString) / 60;
        } catch (NumberFormatException e) {
            System.err.println(e);
        }
        return numberDouble;
    }

    //Returns the user to the menu screen
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.setVisible(false);
        this.lastPanel.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    //After the key press it tells the user how many characters they can use and restricts taskName to 20 characters    
    private void eventNameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eventNameFieldKeyReleased
        int length = eventNameField.getText().length();
        if (length > 20) {
            eventNameField.setText(eventNameField.getText().substring(0, 20));
            length = eventNameField.getText().length();
        }
        eventNameCharsUsed.setText(length + " out of 20 characters used");
    }//GEN-LAST:event_eventNameFieldKeyReleased

    private void dayRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayRadioButtonActionPerformed
        this.dateField.setEnabled(false);
        this.daySelection.setEnabled(true);
    }//GEN-LAST:event_dayRadioButtonActionPerformed

    private void dateRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateRadioButtonActionPerformed
        this.daySelection.setEnabled(false);
        this.dateField.setEnabled(true);
    }//GEN-LAST:event_dateRadioButtonActionPerformed

    //<editor-fold defaultstate="collapsed" desc=" jFrame variables ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JColorChooser colourChooser;
    private javax.swing.JLabel colourLabel;
    private javax.swing.JTextField dateField;
    private javax.swing.JLabel dateFormatLabel;
    private javax.swing.JRadioButton dateRadioButton;
    private javax.swing.ButtonGroup dayDateButtonGroup;
    private javax.swing.JRadioButton dayRadioButton;
    private javax.swing.JComboBox<String> daySelection;
    private javax.swing.JPanel descriptionPanel;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextArea descriptionText;
    private javax.swing.JComboBox<String> endHourDropdown;
    private javax.swing.JComboBox<String> endMinuteDropdown;
    private javax.swing.JLabel endTimeLabel;
    private javax.swing.JLabel eventNameCharsUsed;
    private javax.swing.JTextField eventNameField;
    private javax.swing.JLabel eventNameLabel;
    private javax.swing.JLabel jFrameTitle;
    private javax.swing.JPanel mainDetailsPanel;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> startHourDropdown;
    private javax.swing.JComboBox<String> startMinuteDropdown;
    private javax.swing.JLabel startTimeLabel;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
