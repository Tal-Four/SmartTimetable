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
    private double modifier;

    //Constructor when given a string name
    public Category(String name) {
        this.name = name;

        boolean categoryExists = false;
        String sql = "SELECT * FROM category WHERE UserID = " + User.getUserID();
        ResultSet rs = DatabaseHandle.query(sql);
        try {
            while (rs.next() && !categoryExists) {
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

            this.categoryID = DatabaseHandle.createID("category", "CategoryID");
            this.modifier = 1;
            this.taskCount = 0;
            sql = "INSERT INTO smarttimetabledb.`category` (`CategoryID`, `UserID`, `Name`) VALUES(" + this.categoryID + ", " + User.getUserID() + ", '" + name + "')";
            DatabaseHandle.update(sql);
            new Popup("Category " + name + " created").setVisible(true);
        }

    }

    //Constructor when given a categoryID
    public Category(int categoryID) {
        this.categoryID = categoryID;
        String sql = "SELECT * FROM category WHERE UserID = " + User.getUserID();
        ResultSet rs = DatabaseHandle.query(sql);
        try {
            while (rs.next()) {
                if (this.categoryID == (rs.getInt("CategoryID"))) {
                    this.name = rs.getString("Name");
                    this.modifier = rs.getFloat("Modifier");
                    this.taskCount = rs.getInt("TasksCompleted");
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void taskComplete(Task task) {
        this.taskCount++;
        calculateModifier(task);
        String sql = "UPDATE smarttimetabledb.category SET TasksCompleted = " + taskCount + ", Modifier = " + this.modifier + " WHERE CategoryID = " + categoryID + " AND UserID = " + User.getUserID();
        DatabaseHandle.update(sql);
        task.deleteTask();
    }

    public void calculateModifier(Task task) {
        double timeMultiplier = task.getTimeUsed() / task.getTimeSet();
        double oldMeanTotal = this.modifier * (this.taskCount - 1);
        this.modifier = (oldMeanTotal + timeMultiplier) / this.taskCount;
    }

    // <editor-fold defaultstate="collapsed" desc=" Getters and Setters ">                          
    public int getCategoryID() {
        return this.categoryID;
    }

    public String getName() {
        return this.name;
    }

    public double getModifier() {
        return this.modifier;
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModifier(double modifier) {
        this.modifier = modifier;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }
    // </editor-fold>

}
