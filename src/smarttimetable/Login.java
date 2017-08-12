/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttimetable;

import java.sql.*;

/**
 *
 * @author Adam-PC
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        existingUserPanel = new javax.swing.JPanel();
        existingPasswordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        existingUsernameLabel = new javax.swing.JLabel();
        existingUsernameField = new javax.swing.JTextField();
        exisitingPasswordLabel = new javax.swing.JLabel();
        newUserPanel = new javax.swing.JPanel();
        createAccountButton = new javax.swing.JButton();
        newPasswordField = new javax.swing.JPasswordField();
        newUsernameField = new javax.swing.JTextField();
        newUsernameLabel = new javax.swing.JLabel();
        newConfirmPasswordLabel = new javax.swing.JLabel();
        newConfirmPasswordField = new javax.swing.JPasswordField();
        newPasswordLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        loginLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        existingUserPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Existing User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        existingUserPanel.setToolTipText("");
        existingUserPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        existingUserPanel.setName("gdsg\\"); // NOI18N

            existingPasswordField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
            existingPasswordField.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    existingPasswordFieldActionPerformed(evt);
                }
            });

            loginButton.setText("Login");
            loginButton.setToolTipText("Login with an existing account");
            loginButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    loginButtonActionPerformed(evt);
                }
            });

            existingUsernameLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            existingUsernameLabel.setLabelFor(existingUsernameField);
            existingUsernameLabel.setText("Username");

            existingUsernameField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

            exisitingPasswordLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            exisitingPasswordLabel.setLabelFor(existingPasswordField);
            exisitingPasswordLabel.setText("Password");

            javax.swing.GroupLayout existingUserPanelLayout = new javax.swing.GroupLayout(existingUserPanel);
            existingUserPanel.setLayout(existingUserPanelLayout);
            existingUserPanelLayout.setHorizontalGroup(
                existingUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(existingUserPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(existingUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(existingUserPanelLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(loginButton)
                            .addContainerGap())
                        .addGroup(existingUserPanelLayout.createSequentialGroup()
                            .addGroup(existingUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(existingUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(existingUsernameLabel))
                            .addGap(18, 18, 18)
                            .addGroup(existingUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(existingPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(exisitingPasswordLabel))
                            .addGap(0, 0, Short.MAX_VALUE))))
            );
            existingUserPanelLayout.setVerticalGroup(
                existingUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(existingUserPanelLayout.createSequentialGroup()
                    .addContainerGap(43, Short.MAX_VALUE)
                    .addGroup(existingUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(existingUsernameLabel)
                        .addComponent(exisitingPasswordLabel))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(existingUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(existingUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(existingPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(loginButton)
                    .addContainerGap())
            );

            newUserPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "New User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
            newUserPanel.setToolTipText("");
            newUserPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            newUserPanel.setName("gdsg\\"); // NOI18N

                createAccountButton.setText("Create Acount");
                createAccountButton.setToolTipText("Creates a new account");
                createAccountButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        createAccountButtonActionPerformed(evt);
                    }
                });

                newPasswordField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

                newUsernameField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

                newUsernameLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                newUsernameLabel.setLabelFor(newUsernameField);
                newUsernameLabel.setText("Username");

                newConfirmPasswordLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                newConfirmPasswordLabel.setLabelFor(newConfirmPasswordField);
                newConfirmPasswordLabel.setText("Confirm Password");
                newConfirmPasswordLabel.setToolTipText("");

                newConfirmPasswordField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                newConfirmPasswordField.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        newConfirmPasswordFieldActionPerformed(evt);
                    }
                });

                newPasswordLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                newPasswordLabel.setLabelFor(newPasswordField);
                newPasswordLabel.setText("Password");

                javax.swing.GroupLayout newUserPanelLayout = new javax.swing.GroupLayout(newUserPanel);
                newUserPanel.setLayout(newUserPanelLayout);
                newUserPanelLayout.setHorizontalGroup(
                    newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newUserPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createAccountButton)
                        .addContainerGap())
                    .addGroup(newUserPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(newUsernameLabel))
                        .addGap(18, 18, 18)
                        .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(newPasswordLabel))
                        .addGap(18, 18, 18)
                        .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newConfirmPasswordLabel)
                            .addComponent(newConfirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                newUserPanelLayout.setVerticalGroup(
                    newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newUserPanelLayout.createSequentialGroup()
                        .addContainerGap(47, Short.MAX_VALUE)
                        .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newUsernameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(newConfirmPasswordLabel)
                                .addComponent(newPasswordLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(newUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(newPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(newConfirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(createAccountButton)
                        .addContainerGap())
                );

                exitButton.setText("Exit");
                exitButton.setToolTipText("Exit the program");
                exitButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        exitButtonActionPerformed(evt);
                    }
                });

                loginLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
                loginLabel.setText("Login");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(existingUserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(exitButton))
                            .addComponent(newUserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(loginLabel)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(loginLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(existingUserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newUserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitButton)
                        .addContainerGap())
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        User.logoutUser();
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    //Attempts to log in the user with the details provided
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed

        String username, password;

        username = existingUsernameField.getText().toLowerCase();
        password = existingPasswordField.getText();
        boolean valid = false;

        //Retrieving user's record
        String sql = "SELECT * FROM user WHERE Username = '" + username + "'";
        ResultSet rs = DatabaseHandle.query(sql);

        String storedPassword = null;
        int userID = 0;

        //Fetching password and userID from database
        try {
            rs.next();
            storedPassword = rs.getString("Password");
            userID = rs.getInt("UserID");
        } catch (SQLException e) {
            System.err.println(e);
        }

        //Checking to see if correct password entered
        if (password.equals(storedPassword)) {
            valid = true;

            //Setting values of User to the user's values
            User.newUser(userID, username, password);
        } else {
            System.err.println("Incorrect password entered");
            valid = false;
            new Popup("Incorrect password entered").setVisible(true);
        }

        //Showing next screen
        if (valid) {
            this.setVisible(false);
            new Menu().setVisible(true);
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    //Attemps to create a new account
    private void createAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAccountButtonActionPerformed

        String username, password, passwordConfirm;

        username = newUsernameField.getText().toLowerCase();
        password = newPasswordField.getText();
        passwordConfirm = newConfirmPasswordField.getText();
        boolean valid = false;

        if (!username.equals("")) {
            if (password.equals(passwordConfirm)) {

                //Checking to see if record in database has the same username
                String sql = "SELECT * FROM user WHERE Username = '" + username + "'";
                ResultSet rs = DatabaseHandle.query(sql);
                String UsernameCheck = null;
                try {
                    rs.next();
                    UsernameCheck = rs.getString("Username").toLowerCase();
                } catch (SQLException e) {
                    System.err.println(e);
                }

                //Checking to see if username is available
                if (!username.equals(UsernameCheck)) {

                    //Looping through existing IDs until a ID without an record is found
                    sql = "SELECT * FROM user ORDER BY UserID";
                    rs = DatabaseHandle.query(sql);
                    int userID = 0;
                    try {
                        do {
                            userID++;
                            rs.next();
                        } while (rs.getInt("UserID") == userID);
                    } catch (SQLException e) {
                        System.err.println(e);
                    }

                    //Setting values of User to relevant values
                    User.newUser(userID, username, password);

                    if (password == null) {
                        sql = "INSERT INTO smarttimetabledb.`user` (`UserID`, `Username`, `Password`) VALUES(" + userID + ", '" + username + "'  , NULL)";
                    } else {
                        sql = "INSERT INTO smarttimetabledb.`user` (`UserID`, `Username`,`Password`) VALUES(" + userID + ", '" + username + "'  , '" + password + "')";
                    }

                    //Adding record to database
                    DatabaseHandle.update(sql);
                    valid = true;

                } else {
                    //Username taken
                    System.err.println("Username taken");
                    valid = false;
                    new Popup("Username taken").setVisible(true);
                }
            } else {
                //Passwords don't match
                System.err.println("Passwords do not match");
                valid = false;
                new Popup("Passwords do not match").setVisible(true);
            }
        } else {
            //No username entered
            System.err.println("No username entered");
            valid = false;
            new Popup("No username entered").setVisible(true);
        }

        //Displaying next screen
        if (valid) {
            this.setVisible(false);
            new Menu().setVisible(true);
        }
    }//GEN-LAST:event_createAccountButtonActionPerformed

    //Runs login when enter is pressed in the existingPasswordField
    private void existingPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_existingPasswordFieldActionPerformed
        loginButtonActionPerformed(evt);
    }//GEN-LAST:event_existingPasswordFieldActionPerformed

    //Runs createAccount when enter is pressed in the newConfirmPasswordField
    private void newConfirmPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newConfirmPasswordFieldActionPerformed
        createAccountButtonActionPerformed(evt);
    }//GEN-LAST:event_newConfirmPasswordFieldActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createAccountButton;
    private javax.swing.JLabel exisitingPasswordLabel;
    private javax.swing.JPasswordField existingPasswordField;
    private javax.swing.JPanel existingUserPanel;
    private javax.swing.JTextField existingUsernameField;
    private javax.swing.JLabel existingUsernameLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPasswordField newConfirmPasswordField;
    private javax.swing.JLabel newConfirmPasswordLabel;
    private javax.swing.JPasswordField newPasswordField;
    private javax.swing.JLabel newPasswordLabel;
    private javax.swing.JPanel newUserPanel;
    private javax.swing.JTextField newUsernameField;
    private javax.swing.JLabel newUsernameLabel;
    // End of variables declaration//GEN-END:variables
}
