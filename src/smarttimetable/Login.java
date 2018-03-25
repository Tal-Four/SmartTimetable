/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttimetable;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JOptionPane;

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

        //Centers the frame to the centre of the monitor
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
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
        changePasswordButton = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        existingUserPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Existing User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        existingUserPanel.setToolTipText("");
        existingUserPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        existingUserPanel.setName("gdsg\\"); // NOI18N

            existingPasswordField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
            existingPasswordField.setToolTipText("Enter the password of the account.");
            existingPasswordField.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    existingPasswordFieldActionPerformed(evt);
                }
            });

            loginButton.setText("Login");
            loginButton.setToolTipText("Attempts to log in with the given username and password.");
            loginButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    loginButtonActionPerformed(evt);
                }
            });

            existingUsernameLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            existingUsernameLabel.setLabelFor(existingUsernameField);
            existingUsernameLabel.setText("Username");

            existingUsernameField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
            existingUsernameField.setToolTipText("Enter the username of an existing account.");

            exisitingPasswordLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            exisitingPasswordLabel.setLabelFor(existingPasswordField);
            exisitingPasswordLabel.setText("Password");

            changePasswordButton.setText("Change Password");
            changePasswordButton.setToolTipText("Begins the change password process with the given username.");
            changePasswordButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    changePasswordButtonActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout existingUserPanelLayout = new javax.swing.GroupLayout(existingUserPanel);
            existingUserPanel.setLayout(existingUserPanelLayout);
            existingUserPanelLayout.setHorizontalGroup(
                existingUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(existingUserPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(existingUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(existingUserPanelLayout.createSequentialGroup()
                            .addGroup(existingUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(existingUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(existingUsernameLabel))
                            .addGap(18, 18, 18)
                            .addGroup(existingUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(existingUserPanelLayout.createSequentialGroup()
                                    .addComponent(existingPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(existingUserPanelLayout.createSequentialGroup()
                                    .addComponent(exisitingPasswordLabel)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, existingUserPanelLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(changePasswordButton)))
                    .addContainerGap())
            );
            existingUserPanelLayout.setVerticalGroup(
                existingUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(existingUserPanelLayout.createSequentialGroup()
                    .addGroup(existingUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(existingUsernameLabel)
                        .addComponent(exisitingPasswordLabel))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(existingUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(existingUsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(existingPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loginButton))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(changePasswordButton)
                    .addGap(0, 12, Short.MAX_VALUE))
            );

            newUserPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "New User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N
            newUserPanel.setToolTipText("");
            newUserPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            newUserPanel.setName("gdsg\\"); // NOI18N

                createAccountButton.setText("Create Account");
                createAccountButton.setToolTipText("Creates a new account with the given username and password.");
                createAccountButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        createAccountButtonActionPerformed(evt);
                    }
                });

                newPasswordField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                newPasswordField.setToolTipText("Enter a password.");

                newUsernameField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                newUsernameField.setToolTipText("Enter a new username.");

                newUsernameLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                newUsernameLabel.setLabelFor(newUsernameField);
                newUsernameLabel.setText("Username");

                newConfirmPasswordLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                newConfirmPasswordLabel.setLabelFor(newConfirmPasswordField);
                newConfirmPasswordLabel.setText("Confirm Password");
                newConfirmPasswordLabel.setToolTipText("");

                newConfirmPasswordField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                newConfirmPasswordField.setToolTipText("Enter the same password.");
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
                    .addGroup(newUserPanelLayout.createSequentialGroup()
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
                        .addGap(0, 12, Short.MAX_VALUE))
                );

                exitButton.setText("Exit");
                exitButton.setToolTipText("Closes the program.");
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(existingUserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(newUserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitButton)
                        .addContainerGap())
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to close the program?", "Close Program", JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            User.logoutUser();
            System.exit(0);
        }
    }//GEN-LAST:event_exitButtonActionPerformed

    //Attempts to log in the user with the details provided
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed

        String username, password;

        username = existingUsernameField.getText().toLowerCase();
        password = existingPasswordField.getText();

        User.loadUser(username);

        //Checking to see if correct password entered
        if (password.equals(User.getPassword())) {
            new Menu().setVisible(true);
            this.dispose();
        } else {
            System.err.println("Incorrect password entered");
            User.logoutUser();
            new Popup("Incorrect password entered").setVisible(true);
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    //Attemps to create a new account
    private void createAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAccountButtonActionPerformed

        String username, password, passwordConfirm;

        username = newUsernameField.getText().toLowerCase();
        password = newPasswordField.getText();
        passwordConfirm = newConfirmPasswordField.getText();

        if (!username.equals("") && username.length() <= 15) {

            if (password.equals(passwordConfirm) && password.length() <= 15) {

                //Checking to see if record in database has the same username
                String sql = "SELECT * FROM user WHERE Username = '" + username + "'";
                ResultSet rs = DatabaseHandle.query(sql);
                String UsernameCheck = null;
                try {
                    if (rs.next()) {
                        UsernameCheck = rs.getString("Username").toLowerCase();
                    }
                } catch (SQLException e) {
                    System.err.println(e);
                }

                //Checking to see if username is available
                if (!username.equals(UsernameCheck)) {

                    new SecurityQuestionCreate(username, password).setVisible(true);
                    this.dispose();

                } else {
                    //Username taken
                    System.err.println("Username taken");
                    new Popup("Username taken").setVisible(true);
                }
            } else {
                //Passwords don't match or are too long
                System.err.println("Passwords do not match or are too long");
                new Popup("Passwords do not match or are over 15 characters").setVisible(true);
            }
        } else {
            //No username entered or username too long
            System.err.println("No username entered or username too long");
            new Popup("Username must be 15 characters or less and not blank").setVisible(true);
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

    private void changePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordButtonActionPerformed
        if (!this.existingUsernameField.getText().equals("")) {
            String sql = "SELECT user.UserID\n"
                    + "FROM user\n"
                    + "WHERE (((user.Username)=\"" + this.existingUsernameField.getText() + "\"));";
            ResultSet rs = DatabaseHandle.query(sql);
            try {
                if (rs.next()) {
                    new PasswordReset(rs.getInt("UserID")).setVisible(true);
                    this.dispose();
                } else {
                    new Popup("That user does not exist, please create a new user.").setVisible(true);
                }
            } catch (SQLException e) {
                System.err.println(e);
            }

        } else {
            new Popup("Please enter a username.").setVisible(true);
        }
    }//GEN-LAST:event_changePasswordButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        DatabaseHandle.connect();
        new Login().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changePasswordButton;
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
