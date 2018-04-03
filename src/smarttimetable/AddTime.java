package smarttimetable;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * A form used to add time to a specific task that is passed into the class.
 *
 * @author AdamPlatt
 */
public class AddTime extends javax.swing.JFrame {

    private final JFrame lastFrame;
    private final Task task;

    /**
     * Creates new form AddTime
     *
     * @param lastFrame
     * @param task
     */
    public AddTime(JFrame lastFrame, Task task) {
        initComponents();

        this.lastFrame = lastFrame;
        this.task = task;

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

        addTimePanel = new javax.swing.JPanel();
        continueButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        textLabel = new javax.swing.JLabel();
        inputField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addTimePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Time", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        continueButton.setText("Continue");
        continueButton.setToolTipText("Adds the specified time to the respective task.");
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtonActionPerformed(evt);
            }
        });

        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cancelButton.setText("Cancel");
        cancelButton.setToolTipText("Cancels adding time and returns to the previous screen.");
        cancelButton.setMaximumSize(new java.awt.Dimension(84, 26));
        cancelButton.setMinimumSize(new java.awt.Dimension(84, 26));
        cancelButton.setPreferredSize(new java.awt.Dimension(84, 26));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        textLabel.setText("How many hours do you want to add?");

        inputField.setToolTipText("Type the number of hours you want to add to the task here.");

        javax.swing.GroupLayout addTimePanelLayout = new javax.swing.GroupLayout(addTimePanel);
        addTimePanel.setLayout(addTimePanelLayout);
        addTimePanelLayout.setHorizontalGroup(
            addTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTimePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(addTimePanelLayout.createSequentialGroup()
                        .addGroup(addTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(addTimePanelLayout.createSequentialGroup()
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(continueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inputField))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        addTimePanelLayout.setVerticalGroup(
            addTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addTimePanelLayout.createSequentialGroup()
                .addComponent(textLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(continueButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addTimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addTimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Attempts to add the entered number of hours into the database
     *
     * @param evt
     */
    private void continueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueButtonActionPerformed
        double input = 0;
        boolean accepted = true;

        //Checking to see if the data entered is a number.
        try {
            input = Double.parseDouble(this.inputField.getText());
        } catch (NumberFormatException e) {
            accepted = false;
        }

        //Checking to see if number is positive
        if (input < 0) {
            accepted = false;
        }

        //Updating the database record
        if (accepted) {
            double time = task.getTimeModified() + input;
            String sql = "UPDATE user INNER JOIN task ON user.UserID = task.UserID SET task.TimeModified = " + time + "\n"
                    + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.TaskID)=" + task.getTaskID() + "));";
            int rowsAffected = DatabaseHandle.update(sql);
            if (rowsAffected != 0) {
                this.lastFrame.setVisible(true);
                this.dispose();
            }
        } else {
            new Popup("Please enter a number above 0.").setVisible(true);
        }
    }//GEN-LAST:event_continueButtonActionPerformed

    /**
     * Returns the user to the last screen
     *
     * @param evt
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.lastFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addTimePanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton continueButton;
    private javax.swing.JTextField inputField;
    private javax.swing.JLabel textLabel;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
