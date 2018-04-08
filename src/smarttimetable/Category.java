package smarttimetable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class used to manipulate and store details about category entities in the
 * database.
 *
 * @author AdamPlatt
 */
public class Category {

    //The name of the category
    private String name;
    //The ID of the category and the number of tasks completed in this category
    private int categoryID, taskCount;
    //The modifier for the category
    private double modifier;
    //The colour of the category
    private int colourCode;

    /**
     * Loads the category with the given ID into the class's fields
     *
     * @param categoryID
     */
    public Category(int categoryID) {
        this.categoryID = categoryID;
        //Constructing the SQL statement
        String sql = "SELECT category.*\n"
                + "FROM user INNER JOIN category ON user.UserID = category.UserID\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((category.CategoryID)=" + this.categoryID + "));";
        ResultSet rs = DatabaseHandle.query(sql);
        if (rs != null) {
            try {
                //Retrieving the details of the category
                if (rs.next()) {
                    this.name = rs.getString("Name");
                    this.modifier = rs.getFloat("Modifier");
                    this.taskCount = rs.getInt("TasksCompleted");
                    this.colourCode = rs.getInt("Colour");
                }
            } catch (SQLException e) {
            }
        }
    }

    /**
     * Adds a category with the given details to the database
     *
     * @param name
     * @param colourCode
     */
    public Category(String name, int colourCode) {
        this.categoryID = DatabaseHandle.createID("category", "CategoryID");
        this.modifier = 1;
        this.taskCount = 0;
        this.name = name;
        this.colourCode = colourCode;

        //Inserting the record into the database
        String sql = "INSERT INTO `category` (`CategoryID`, `UserID`, `Name`, `Colour`) VALUES(" + this.categoryID + ", " + User.getUserID() + ", '" + this.name + "', " + this.colourCode + ")";
        int rowsAffected = DatabaseHandle.update(sql);
        //Checking to see if any rows were affected
        if (rowsAffected != 0) {
            new Popup("Category " + this.name + " created").setVisible(true);
        }
    }

    /**
     * Updates this category to the given details and corrects the database
     *
     * @param name
     * @param colourCode
     * @return
     */
    public int editCategory(String name, int colourCode) {
        this.colourCode = colourCode;
        this.name = name;
        //Constructing the SQL statement
        String sql = "UPDATE user INNER JOIN category ON user.UserID = category.UserID SET category.Name = \"" + this.name + "\", category.Colour = " + this.colourCode + "\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((category.CategoryID)=" + this.categoryID + "));";
        int linesChanged = DatabaseHandle.update(sql);
        //Checking the SQL actually changed a line.
        if (linesChanged != 0) {
            new Popup(this.name + " edited.").setVisible(true);
        }
        return linesChanged;
    }

    /**
     * Increases the taskCount, corrects the modifier and then updates the
     * database record
     *
     * @param task
     */
    public void taskComplete(Task task) {
        this.taskCount++;
        //Recalculating the category's modifier
        calculateModifier(task);
        //Constructing the SQL
        String sql = "UPDATE user INNER JOIN category ON user.UserID = category.UserID SET category.TasksCompleted =" + this.taskCount + ", category.Modifier =" + this.modifier
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((category.CategoryID)=" + this.categoryID + "));";

        int rowsAffected = DatabaseHandle.update(sql);
        //Checking to see that SQL worked
        if (rowsAffected != 0) {
            if (this.modifier >= 4) {
                //Suggests the user should seek aid with the category as they seem to be struggling
                new Popup("You have amassed a modifier greater than 4 for " + this.name + ". It is advised you seek help with " + this.name + ".").setVisible(true);
            }
        }
    }

    /**
     * Decreases the taskCount, changes the modifier so that it doesn't include
     * the task marked as todo in the modifier, then updates the database with
     * these new values
     *
     * @param task
     */
    public void taskTodo(Task task) {
        //Reverting the modifier to what it would be without the task being marked as todo
        this.taskCount--;
        Double timeMultiplier = task.getTimeUsed() / task.getTimeSet();
        Double oldMeanTotal = this.modifier * (this.taskCount + 1);
        this.modifier = (oldMeanTotal - timeMultiplier) / this.taskCount;

        //Constructing and running SQL
        String sql = "UPDATE user INNER JOIN category ON user.UserID = category.UserID SET category.TasksCompleted =" + this.taskCount + ", category.Modifier =" + this.modifier
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((category.CategoryID)=" + this.categoryID + "));";
        DatabaseHandle.update(sql);
    }

    /**
     * Averages the ratio of time used to time set for a task with the
     * categories modifier
     *
     * @param task
     */
    private void calculateModifier(Task task) {
        //Calculating modifier for the single task
        double timeMultiplier = task.getTimeUsed() / task.getTimeSet();
        //Calculating the total of modifiers for previously completed tasks in this category
        double oldMeanTotal = this.modifier * (this.taskCount - 1);
        //Averaging the single modifier with the old modifiers
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

    public int getColourCode() {
        return colourCode;
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
    
    public void setColourCode(int colourCode) {
        this.colourCode = colourCode;
    }
    // </editor-fold>
}
