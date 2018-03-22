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
    private int colourCode;

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
                    this.colourCode = rs.getInt("Colour");
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        
    }

    public Category(String name, int colourCode) {
        this.categoryID = DatabaseHandle.createID("category", "CategoryID");
        this.modifier = 1;
        this.taskCount = 0;
        this.name = name;
        this.colourCode = colourCode;
        
        String sql = "INSERT INTO `category` (`CategoryID`, `UserID`, `Name`, `Colour`) VALUES(" + this.categoryID + ", " + User.getUserID() + ", '" + this.name + "', " + this.colourCode + ")";
        DatabaseHandle.update(sql);
        new Popup("Category " + this.name + " created").setVisible(true);
    }
    
    public int editCategory(String name, int colourCode) {
        this.colourCode = colourCode;
        this.name = name;
        
        String sql = "UPDATE category, user SET Name = '" + this.name + "', Colour = " + this.colourCode + " WHERE CategoryID = " + this.categoryID + " AND category.UserID = user.UserID AND user.UserID = " + User.getUserID();
        int linesChanged = DatabaseHandle.update(sql);
        new Popup(this.name + " edited.").setVisible(true);
        return linesChanged;
    }

    public void taskComplete(Task task) {
        this.taskCount++;
        calculateModifier(task);
        String sql = "UPDATE category "
                + "SET TasksCompleted = " + this.taskCount + ", Modifier = " + this.modifier + " "
                + "WHERE CategoryID = " + this.categoryID + " AND UserID = " + User.getUserID();
        DatabaseHandle.update(sql);
        if (this.modifier >= 4) {
            new Popup("You have amassed a modifier greater than 4 for " + this.name + ". It is advised you seek help with " + this.name + ".").setVisible(true);
        }
    }
    
    public void taskTodo(Task task){
        this.taskCount--;
        Double timeMultiplier = task.getTimeUsed() / task.getTimeSet();
        Double oldMeanTotal = this.modifier * (this.taskCount + 1);
        this.modifier = (oldMeanTotal - timeMultiplier) / this.taskCount;
        
        String sql = "UPDATE category "
                + "SET TasksCompleted = " + this.taskCount + ", Modifier = " + this.modifier + " "
                + "WHERE CategoryID = " + this.categoryID + " AND UserID = " + User.getUserID();
        DatabaseHandle.update(sql);
    }

    private void calculateModifier(Task task) {
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
