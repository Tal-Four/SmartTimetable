package smarttimetable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Adam-PC
 */
public class Task {

    private int taskID, categoryID, colourCode;
    private float timeSet, timeModified, timeUsed;
    private String description, name, dateSet, dateDue;

    public Task() {
    }

    //Method when called adds a task to the database
    public void createNewTask(String name, String description, String category, String dateDueText, int colourCode, float timeSet) {
        Category selectedCategory = new Category(category);
        this.categoryID = selectedCategory.getCategoryID();
        this.name = name;
        this.description = description;
        this.dateDue = dateTextToSQLFormat(dateDueText);
        this.colourCode = colourCode;
        this.timeSet = timeSet;
        this.taskID = DatabaseHandle.createID("task", "TaskID");
        this.timeModified = this.timeSet * selectedCategory.getModifier();
        Date currentDate = new Date();
        this.dateSet = dateToSQLFormat(currentDate);
        this.timeUsed = 0;

        String sql = "INSERT INTO smarttimetabledb.task (`TaskID`, `Name`, `Description`, `UserID`, `CategoryID`, `DateSet`, `DateDue`, `Colour`, `TimeSet`, `TimeModified`) "
                + "VALUES (" + this.taskID + ", '" + this.name + "', '" + this.description + "', " + User.getUserID() + ", " + this.categoryID + ", '" + this.dateSet + "', '" + this.dateDue + "', " + this.colourCode + ", " + this.timeSet + ", " + this.timeModified + ")";
        DatabaseHandle.update(sql);
        new Popup("Task " + this.name + " created.").setVisible(true);
    }

    //Method when called edits a task already in the database
    public void editTask(String name, String description, String category, String dateDueText, int colourCode, float timeSet, int taskID) {
        Category selectedCategory = new Category(category);
        this.taskID = taskID;
        this.name = name;
        this.description = description;
        this.categoryID = selectedCategory.getCategoryID();
        this.dateDue = dateTextToSQLFormat(dateDueText);
        this.colourCode = colourCode;
        this.timeSet = timeSet;
        this.timeModified = this.timeSet * selectedCategory.getModifier();

        String sql = "UPDATE smarttimetabledb.task SET Name = '" + this.name + "', Description = '" + this.description + "', dateDue = '" + this.dateDue + "', Colour = " + this.colourCode + ", CategoryID = " + this.categoryID + ", TimeSet = " + this.timeSet + " WHERE UserID = " + User.getUserID() + " AND TaskID = " + this.taskID;
        DatabaseHandle.update(sql);
        new Popup("Task " + this.name + " edited.").setVisible(true);
    }

    //Method that reads a task from the database given an ID
    public void readTaskFromDB(int taskID) {
        this.taskID = taskID;
        String sql = "SELECT * FROM task WHERE TaskID = " + this.taskID + " AND UserID = " + User.getUserID();
        ResultSet rs = DatabaseHandle.query(sql);
        try {
            if (rs.next()) {
                this.categoryID = rs.getInt("CategoryID");
                this.colourCode = rs.getInt("Colour");
                this.timeSet = rs.getFloat("TimeSet");
                this.timeModified = rs.getFloat("TimeModified");
                this.timeUsed = rs.getFloat("timeUsed");
                this.description = rs.getString("Description");
                this.name = rs.getString("Name");
                this.dateSet = rs.getString("DateSet");
                this.dateDue = rs.getString("DateDue");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    //Method that reads a task from the database given a name
    public void readTaskFromDB(String name) {
        this.name = name;
        String sql = "SELECT * FROM task WHERE Name = '" + this.name + "' AND UserID = " + User.getUserID();
        ResultSet rs = DatabaseHandle.query(sql);
        try {
            if (rs.next()) {
                this.categoryID = rs.getInt("CategoryID");
                this.colourCode = rs.getInt("Colour");
                this.timeSet = rs.getFloat("TimeSet");
                this.timeModified = rs.getFloat("TimeModified");
                this.timeUsed = rs.getFloat("timeUsed");
                this.description = rs.getString("Description");
                this.taskID = rs.getInt("TaskID");
                this.dateSet = rs.getString("DateSet");
                this.dateDue = rs.getString("DateDue");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    //Converting date object to SQL date format
    private String dateToSQLFormat(Date date) {
        return (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    }

    //Converting raw text to SQL date format
    private String dateTextToSQLFormat(String dateText) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = df.parse(dateText);
        } catch (ParseException e) {
            System.err.println(e);
        }
        return dateToSQLFormat(date);
    }

    //Formats the string from YYYY-MM-DD to DD/MM/YYYY
    public String sqlDateToTextFormat(String sqlDate) {
        return sqlDate.substring(8, 10) + "/" + sqlDate.substring(5, 7) + "/" + sqlDate.substring(0, 4);
    }

    // <editor-fold defaultstate="collapsed" desc=" Getters ">                          
    public int getTaskID() {
        return this.taskID;
    }

    public int getCategoryID() {
        return this.categoryID;
    }

    public int getColourCode() {
        return this.colourCode;
    }

    public float getTimeSet() {
        return this.timeSet;
    }

    public float getTimeModified() {
        return this.timeModified;
    }

    public float getTimeUsed() {
        return this.timeUsed;
    }

    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

    public String getDateSet() {
        return this.dateSet;
    }

    public String getDateDue() {
        return this.dateDue;
    } // </editor-fold>
}
