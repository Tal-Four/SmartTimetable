package smarttimetable;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author crazy
 */
public class CompleteHours extends javax.swing.JFrame {

    private JFrame lastFrame;
    private Task task;
    private final boolean timetable;

    /**
     * Creates new form CompleteHours
     *
     * @param lastFrame
     * @param task
     */
    public CompleteHours(TaskViewer lastFrame, Task task) {
        this.timetable = false;
        this.initialise(lastFrame, task);
    }

    public CompleteHours(Timetable lastFrame, Task task) {
        this.timetable = true;
        this.initialise(lastFrame, task);
    }

    private void initialise(JFrame lastFrame, Task task) {
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
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        completeHoursPanel = new javax.swing.JPanel();
        continueButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        firstLineLabel = new javax.swing.JLabel();
        secondLineLabel = new javax.swing.JLabel();
        inputField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        completeHoursPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Complete Hours", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        continueButton.setText("Continue");
        continueButton.setToolTipText("Completes the number of specified hours.");
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtonActionPerformed(evt);
            }
        });

        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cancelButton.setText("Cancel");
        cancelButton.setToolTipText("Returns to the previous screen.");
        cancelButton.setMaximumSize(new java.awt.Dimension(84, 26));
        cancelButton.setMinimumSize(new java.awt.Dimension(84, 26));
        cancelButton.setPreferredSize(new java.awt.Dimension(84, 26));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        firstLineLabel.setText("Input the number of hours you'd like");

        secondLineLabel.setText("to complete on the selected task:");

        inputField.setToolTipText("Enter the number of hours you'd like to complete for this task.");

        javax.swing.GroupLayout completeHoursPanelLayout = new javax.swing.GroupLayout(completeHoursPanel);
        completeHoursPanel.setLayout(completeHoursPanelLayout);
        completeHoursPanelLayout.setHorizontalGroup(
            completeHoursPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(completeHoursPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(completeHoursPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, completeHoursPanelLayout.createSequentialGroup()
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(continueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputField)
                    .addGroup(completeHoursPanelLayout.createSequentialGroup()
                        .addGroup(completeHoursPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstLineLabel)
                            .addComponent(secondLineLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        completeHoursPanelLayout.setVerticalGroup(
            completeHoursPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, completeHoursPanelLayout.createSequentialGroup()
                .addComponent(firstLineLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(secondLineLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(completeHoursPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(continueButton)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(completeHoursPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(completeHoursPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void continueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueButtonActionPerformed
        double input = 0;
        boolean accepted = true;
        try {
            input = Double.parseDouble(this.inputField.getText());
        } catch (NumberFormatException e) {
            accepted = false;
            System.err.println(e);
        }

        if (input < 0) {
            accepted = false;
        }

        if (accepted) {
            task.setTimeUsed(task.getTimeUsed() + input);
            String sql = "UPDATE user INNER JOIN task ON user.UserID = task.UserID "
                    + "SET task.TimeUsed = " + task.getTimeUsed() + "\n"
                    + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.TaskID)=" + task.getTaskID() + "));";
            DatabaseHandle.update(sql);
            if (task.getTimeModified() <= task.getTimeUsed()) {
                Object[] options = {"Mark as Complete", "Add Time", "Do Nothing"};
                int result = JOptionPane.showOptionDialog(this, "The time spent on the task exceeds the allotted time.\nWould you like to mark the task as complete or add more time?\nTime exceeded by: " + (task.getTimeUsed() - task.getTimeModified()) + " hours", "Exceeded Time Set", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                switch (result) {
                    case (0):
                        task.complete();
                        this.lastFrame.setVisible(true);
                        if (timetable) {
                            ((Timetable) (this.lastFrame)).reloadTimetable();
                        } else {
                            ((TaskViewer) (this.lastFrame)).setUpList();
                        }
                        this.dispose();
                        break;
                    case (1):
                        new AddTime(this.lastFrame, this.task).setVisible(true);
                        this.dispose();
                        break;
                    default:
                        this.lastFrame.setVisible(true);
                        this.dispose();
                        break;
                }
            } else {
                this.lastFrame.setVisible(true);
                this.dispose();
            }
        } else {
            new Popup("Enter a number above 0.").setVisible(true);
        }
    }//GEN-LAST:event_continueButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        lastFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel completeHoursPanel;
    private javax.swing.JButton continueButton;
    private javax.swing.JLabel firstLineLabel;
    private javax.swing.JTextField inputField;
    private javax.swing.JLabel secondLineLabel;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
