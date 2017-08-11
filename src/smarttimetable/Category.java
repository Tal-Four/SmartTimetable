package smarttimetable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Adam-PC
 */
public class Category {

    private String name;
    private int categoryID, taskCount;
    private float modifier;

    public Category(String name) {
        this.name = name;

        boolean categoryExists = false;
        String sql = "SELECT * FROM category WHERE UserID = " + User.getUserID();
        ResultSet rs = DatabaseHandle.query(sql);
        try {
            while (rs != null && !categoryExists) {
                rs.next();
                if (name.equals(rs.getString("Name"))) {
                    categoryExists = true;
                    this.categoryID = rs.getInt("CategoryID");
                    this.modifier = rs.getFloat("Modifier");
                    this.taskCount = rs.getInt("TasksCompleted");
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        if (!categoryExists) {
            sql = "SELECT CategoryID FROM category WHERE UserID = " + User.getUserID() + " ORDER BY CategoryID";
            rs = DatabaseHandle.query(sql);
            this.categoryID = 0;
            try {
                do {
                    this.categoryID++;
                    rs.next();
                } while (rs.getInt("CategoryID") == this.categoryID);
            } catch (SQLException e) {
                System.err.println(e);
            }

            sql = "INSERT INTO smarttimetabledb.`category` (`CategoryID`, `UserID`, `Name`) VALUES(" + this.categoryID + ", " + User.getUserID() + ", '" + name + "')";
            DatabaseHandle.update(sql);
            new Popup("Category " + name + " created").setVisible(true);
        }

    }

    public Category(int categoryID) {

    }

}
