package smarttimetable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class CategoryViewer extends javax.swing.JFrame {

    /**
     * Creates new form CategoryViewer
     */
    private final LinkedList categoryIDList = new LinkedList();

    public CategoryViewer() {
        frameSetup();
    }

    private void frameSetup() {
        initComponents();

        //Loads the categories into the list
        setUpList();

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

        sortButtonGroup = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        categoryPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        categoryList = new javax.swing.JList<>();
        sortLabel = new javax.swing.JLabel();
        nameSortButton = new javax.swing.JRadioButton();
        timeModifierSortButton = new javax.swing.JRadioButton();
        ascDescSortButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        detailsPanel = new javax.swing.JPanel();
        colourPreview = new javax.swing.JPanel();
        colourLabel = new javax.swing.JLabel();
        timeModifierLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nameVariableLabel = new javax.swing.JLabel();
        timeModifierVariableLabel = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        mainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Category Viewer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        backButton.setText("Back");
        backButton.setToolTipText("Return to the menu.");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.setToolTipText("Closes the program.");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        categoryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Categories"));

        categoryList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        categoryList.setToolTipText("Selecting a category from this list will display its details below.");
        categoryList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        categoryList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                categoryListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(categoryList);

        sortLabel.setText("Sort By:");

        sortButtonGroup.add(nameSortButton);
        nameSortButton.setSelected(true);
        nameSortButton.setText("Name");
        nameSortButton.setToolTipText("Sorts the list of categories by their name.");
        nameSortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameSortButtonActionPerformed(evt);
            }
        });

        sortButtonGroup.add(timeModifierSortButton);
        timeModifierSortButton.setText("Time Modifier");
        timeModifierSortButton.setToolTipText("Sorts the list of categories by their time modifier.");
        timeModifierSortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeModifierSortButtonActionPerformed(evt);
            }
        });

        ascDescSortButton.setText("Ascending");
        ascDescSortButton.setToolTipText("Specifies whether the sort is ascending or descending, the currently shown text is what the current sort is ordered by.");
        ascDescSortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascDescSortButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout categoryPanelLayout = new javax.swing.GroupLayout(categoryPanel);
        categoryPanel.setLayout(categoryPanelLayout);
        categoryPanelLayout.setHorizontalGroup(
            categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(categoryPanelLayout.createSequentialGroup()
                        .addComponent(sortLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(categoryPanelLayout.createSequentialGroup()
                                .addComponent(nameSortButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeModifierSortButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(ascDescSortButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        categoryPanelLayout.setVerticalGroup(
            categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, categoryPanelLayout.createSequentialGroup()
                .addGroup(categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sortLabel)
                    .addComponent(nameSortButton)
                    .addComponent(timeModifierSortButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ascDescSortButton, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addContainerGap())
        );

        deleteButton.setText("Delete");
        deleteButton.setToolTipText("Delete the selected category.");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit Selected");
        editButton.setToolTipText("Edit the selected category.");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        detailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        colourPreview.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        colourPreview.setToolTipText("The colour of  the selected category.");
        colourPreview.setMinimumSize(new java.awt.Dimension(10, 27));

        javax.swing.GroupLayout colourPreviewLayout = new javax.swing.GroupLayout(colourPreview);
        colourPreview.setLayout(colourPreviewLayout);
        colourPreviewLayout.setHorizontalGroup(
            colourPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 118, Short.MAX_VALUE)
        );
        colourPreviewLayout.setVerticalGroup(
            colourPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        colourLabel.setText("Colour:");

        timeModifierLabel.setText("Time Modifier:");

        nameLabel.setText("Name:");

        nameVariableLabel.setToolTipText("The name of the selected category.");

        timeModifierVariableLabel.setToolTipText("The time modifier of the selected category.");

        javax.swing.GroupLayout detailsPanelLayout = new javax.swing.GroupLayout(detailsPanel);
        detailsPanel.setLayout(detailsPanelLayout);
        detailsPanelLayout.setHorizontalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(colourLabel)
                    .addComponent(timeModifierLabel)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(colourPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timeModifierVariableLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameVariableLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(63, 63, 63))
        );
        detailsPanelLayout.setVerticalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailsPanelLayout.createSequentialGroup()
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detailsPanelLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(nameLabel))
                    .addGroup(detailsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nameVariableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(timeModifierLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timeModifierVariableLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(colourPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 16, Short.MAX_VALUE)
                    .addComponent(colourLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(categoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(detailsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                            .addComponent(backButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(exitButton))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                            .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(325, 325, 325))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(categoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(detailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(editButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void categoryListIndexSelected() {
        if (categoryList.getSelectedIndex() != -1) {
            nameVariableLabel.setText(categoryList.getSelectedValue());

            String sql = "SELECT Modifier, Colour FROM category, user WHERE user.UserID = category.UserID AND user.UserID = "
                    + User.getUserID() + " AND CategoryID = " + this.categoryIDList.getDataAt(categoryList.getSelectedIndex());
            ResultSet rs = DatabaseHandle.query(sql);
            try {
                if (rs.next()) {
                    timeModifierVariableLabel.setText("" + (rs.getInt("Modifier")));
                    colourPreview.setBackground(new Color(rs.getInt("Colour")));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }

        }
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if (categoryList.getSelectedValue() != null) {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + categoryList.getSelectedValue(), "Delete category", JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                Category category = new Category(categoryIDList.getDataAt(categoryList.getSelectedIndex()));
                String sql = "UPDATE category INNER JOIN user ON category.UserID = user.UserID SET category.Hidden = True\n"
                        + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((category.CategoryID)=" + category.getCategoryID() + "));";
                DatabaseHandle.update(sql);
            }
        } else {
            new Popup("Please select a category first.").setVisible(true);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if (categoryList.getSelectedValue() != null) {
            this.setVisible(false);
            new CategoryEditor(categoryIDList.getDataAt(categoryList.getSelectedIndex()), this).setVisible(true);
        } else {
            new Popup("Please select a category first.").setVisible(true);
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.setVisible(false);
        new Menu().setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void timeModifierSortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeModifierSortButtonActionPerformed
        setUpList();
    }//GEN-LAST:event_timeModifierSortButtonActionPerformed

    private void nameSortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameSortButtonActionPerformed
        setUpList();
    }//GEN-LAST:event_nameSortButtonActionPerformed

    private void categoryListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_categoryListValueChanged
        categoryListIndexSelected();
    }//GEN-LAST:event_categoryListValueChanged

    private void ascDescSortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascDescSortButtonActionPerformed
        if (ascDescSortButton.getText().equals("Ascending")) {
            ascDescSortButton.setText("Descending");
        } else {
            ascDescSortButton.setText("Ascending");
        }
        this.setUpList();
    }//GEN-LAST:event_ascDescSortButtonActionPerformed

    private void updateIDList() {
        this.categoryIDList.clear();

        String sort = "";
        if (nameSortButton.isSelected()) {
            sort = "Name";
        } else if (timeModifierSortButton.isSelected()) {
            sort = "Modifier";
        }
        if (ascDescSortButton.getText().equals("Descending")){
            sort = sort + " DESC";
        }
        String sql = "SELECT category.CategoryID\n"
                + "FROM user INNER JOIN category ON user.UserID = category.UserID\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((category.Hidden)=False))\n"
                + "ORDER BY category." + sort + ";";
        ResultSet rs = DatabaseHandle.query(sql);
        try {
            while (rs.next()) {
                this.categoryIDList.addNode(rs.getInt("CategoryID"));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    private void setUpList() {
        updateIDList();
        DefaultListModel dlm = new DefaultListModel();

        for (int count = 0; count < this.categoryIDList.Length(); count++) {
            String sql = "SELECT Name FROM category, user WHERE category.UserID = user.UserID AND user.UserID = "
                    + User.getUserID() + " AND CategoryID = " + this.categoryIDList.getDataAt(count);
            ResultSet rs = DatabaseHandle.query(sql);
            try {
                while (rs.next()) {
                    dlm.addElement(rs.getString("Name"));
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

        categoryList.setModel(dlm);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ascDescSortButton;
    private javax.swing.JButton backButton;
    private javax.swing.JList<String> categoryList;
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JLabel colourLabel;
    private javax.swing.JPanel colourPreview;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel detailsPanel;
    private javax.swing.JButton editButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JRadioButton nameSortButton;
    private javax.swing.JLabel nameVariableLabel;
    private javax.swing.ButtonGroup sortButtonGroup;
    private javax.swing.JLabel sortLabel;
    private javax.swing.JLabel timeModifierLabel;
    private javax.swing.JRadioButton timeModifierSortButton;
    private javax.swing.JLabel timeModifierVariableLabel;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables
}
