package smarttimetable;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author crazy
 */
public class PasswordReset extends javax.swing.JFrame {

    /**
     * Creates new form PasswordReset
     *
     * @param userID
     */
    public PasswordReset(int userID) {
        initComponents();

        User.loadUser(userID);
        
        
        //Centers the frame to the centre of the monitor
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        //Sets the user's name on screen and loads the list of events                
        userLabel.setText("Reseting password for: " + User.getUsername());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        passwordResetPanel = new javax.swing.JPanel();
        questionScrollPane = new javax.swing.JScrollPane();
        questionTextArea = new javax.swing.JTextArea();
        securityQuestionLabel = new javax.swing.JLabel();
        answerLabel = new javax.swing.JLabel();
        answerField = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        passwordResetPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Password Reset", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        questionTextArea.setEditable(false);
        questionTextArea.setColumns(20);
        questionTextArea.setLineWrap(true);
        questionTextArea.setRows(5);
        questionScrollPane.setViewportView(questionTextArea);

        securityQuestionLabel.setText("Security Question:");

        answerLabel.setText("Answer:");

        javax.swing.GroupLayout passwordResetPanelLayout = new javax.swing.GroupLayout(passwordResetPanel);
        passwordResetPanel.setLayout(passwordResetPanelLayout);
        passwordResetPanelLayout.setHorizontalGroup(
            passwordResetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordResetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(passwordResetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(questionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                    .addGroup(passwordResetPanelLayout.createSequentialGroup()
                        .addGroup(passwordResetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(securityQuestionLabel)
                            .addComponent(answerLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(answerField))
                .addContainerGap())
        );
        passwordResetPanelLayout.setVerticalGroup(
            passwordResetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordResetPanelLayout.createSequentialGroup()
                .addComponent(securityQuestionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(answerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(answerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        confirmButton.setText("Confirm");
        confirmButton.setToolTipText("");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordResetPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(confirmButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(passwordResetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(confirmButton, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(backButton)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.setVisible(false);
        User.logoutUser();
        new Login().setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        if (answerField.getText().equals(User.getAnswer())) {
            new NewPassword().setVisible(true);
            this.dispose();
        } else {
            new Popup("Incorrect answer.").setVisible(true);
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PasswordReset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PasswordReset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PasswordReset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PasswordReset.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PasswordReset(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField answerField;
    private javax.swing.JLabel answerLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JPanel passwordResetPanel;
    private javax.swing.JScrollPane questionScrollPane;
    private javax.swing.JTextArea questionTextArea;
    private javax.swing.JLabel securityQuestionLabel;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
