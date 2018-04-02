package smarttimetable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author AdamPlatt
 */
public class CategoryEditor extends javax.swing.JFrame {

    private boolean edit;
    private Category editedCategory;
    private JFrame lastPanel;

    /**
     * Creates new form CategoryEditor
     *
     * @param lastPanel
     */
    public CategoryEditor(JFrame lastPanel) {
        this.edit = false;
        frameSetup(lastPanel);
    }

    /**
     * Creates new form CategoryEditor Also reads in the passed in category's
     * details into all the correct fields
     *
     * @param categoryID
     * @param lastPanel
     */
    public CategoryEditor(int categoryID, JFrame lastPanel) {
        this.edit = true;
        this.editedCategory = new Category(categoryID);
        frameSetup(lastPanel);

        String sql = "SELECT category.Name, category.Colour\n"
                + "FROM user INNER JOIN category ON user.UserID = category.UserID\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((category.CategoryID)=" + categoryID + "));";
        ResultSet rs = DatabaseHandle.query(sql);
        try {
            if (rs.next()) {
                //Setting the fields to the category's details
                this.colourChooser.setColor(new Color(rs.getInt("Colour")));
                this.categoryNameField.setText(rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        nameCharCount();
    }

    /**
     * Runs the standard frame setup
     *
     * @param lastPanel
     */
    private void frameSetup(JFrame lastPanel) {
        initComponents();

        this.lastPanel = lastPanel;

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

        mainPanel = new javax.swing.JPanel();
        colourChooser = new javax.swing.JColorChooser();
        backButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        colourLabel = new javax.swing.JLabel();
        categoryNameField = new javax.swing.JTextField();
        categoryNameCharsUsed = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        mainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Category Editor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        colourChooser.setToolTipText("Pick a colour to represent the category.");

        backButton.setText("Back");
        backButton.setToolTipText("Returns to the previous screen without saving the currently entered details.");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        saveButton.setText("Save");
        saveButton.setToolTipText("Saves the details to the database.");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        nameLabel.setText("Name:");

        colourLabel.setText("Colour:");

        categoryNameField.setToolTipText("Enter a name for the category. Maximum 15 characters.");
        categoryNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                categoryNameFieldKeyReleased(evt);
            }
        });

        categoryNameCharsUsed.setText("0 out of 15 characters used");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(colourChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(backButton)
                        .addGap(0, 0, 0)
                        .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(saveButton))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(colourLabel)
                            .addComponent(nameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoryNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoryNameCharsUsed)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(categoryNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoryNameCharsUsed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colourLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colourChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton)
                    .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Asks the user if they wish to leave the form, if yes returns them to the
     * previous screen. Otherwise carries on with this frame
     *
     * @param evt
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Are you sure? Unsaved changes will be lost.", "Return to Menu", JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            this.setVisible(false);
            this.lastPanel.setVisible(true);
        }
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * Attempts to save the category in the database after error checking
     *
     * @param evt
     */
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        int colourCode = this.colourChooser.getColor().getRGB();
        String name = this.categoryNameField.getText();

        //Checking the name is not blank or too long
        if (name.length() != 0 && name.length() <= 15) {
            if (edit) {
                editedCategory.editCategory(name, colourCode);
                ((CategoryViewer) lastPanel).update();
            } else {
                new Category(name, colourCode);
            }
            this.setVisible(false);
            this.lastPanel.setVisible(true);
        } else {
            new Popup("Cannot create a category without a name.").setVisible(true);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    /**
     * Runs nameCharCount: Displays the number of characters used in the name
     * field and if longer than 15 characters it sets the name to be the first
     * 15 characters
     *
     * @param evt
     */
    private void categoryNameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_categoryNameFieldKeyReleased
        nameCharCount();
    }//GEN-LAST:event_categoryNameFieldKeyReleased

    /**
     * Displays the number of characters used in the name field and if longer
     * than 15 characters it sets the name to be the first 15 characters
     */
    private void nameCharCount() {
        int length = categoryNameField.getText().length();
        if (length > 15) {
            categoryNameField.setText(categoryNameField.getText().substring(0, 15));
            length = categoryNameField.getText().length();
        }
        categoryNameCharsUsed.setText(length + " out of 15 characters used");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel categoryNameCharsUsed;
    private javax.swing.JTextField categoryNameField;
    private javax.swing.JColorChooser colourChooser;
    private javax.swing.JLabel colourLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
