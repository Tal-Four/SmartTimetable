package smarttimetable;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author crazy
 */
public class SecurityQuestionCreate extends javax.swing.JFrame {

    /**
     * Creates new form SecurityQuestionAnswer
     */
    public SecurityQuestionCreate(String username, String password) {
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

        confirmButton = new javax.swing.JButton();
        questionCreatePanel = new javax.swing.JPanel();
        questionScrollPane = new javax.swing.JScrollPane();
        questionTextArea = new javax.swing.JTextArea();
        securityQuestionLabel = new javax.swing.JLabel();
        answerLabel = new javax.swing.JLabel();
        answerField = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        confirmButton.setText("Confirm");
        confirmButton.setToolTipText("");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        questionCreatePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Create Security Question", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        questionTextArea.setColumns(20);
        questionTextArea.setLineWrap(true);
        questionTextArea.setRows(5);
        questionScrollPane.setViewportView(questionTextArea);

        securityQuestionLabel.setText("Security Question:");

        answerLabel.setText("Answer:");

        javax.swing.GroupLayout questionCreatePanelLayout = new javax.swing.GroupLayout(questionCreatePanel);
        questionCreatePanel.setLayout(questionCreatePanelLayout);
        questionCreatePanelLayout.setHorizontalGroup(
            questionCreatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionCreatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(questionCreatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(questionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                    .addGroup(questionCreatePanelLayout.createSequentialGroup()
                        .addGroup(questionCreatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(securityQuestionLabel)
                            .addComponent(answerLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(answerField))
                .addContainerGap())
        );
        questionCreatePanelLayout.setVerticalGroup(
            questionCreatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(questionCreatePanelLayout.createSequentialGroup()
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(questionCreatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(confirmButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(questionCreatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        fin
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.setVisible(false);
        User.logoutUser();
        new Login().setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField answerField;
    private javax.swing.JLabel answerLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JPanel questionCreatePanel;
    private javax.swing.JScrollPane questionScrollPane;
    private javax.swing.JTextArea questionTextArea;
    private javax.swing.JLabel securityQuestionLabel;
    // End of variables declaration//GEN-END:variables
}
