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

    private Category category;
    private int taskID, colourCode;
    private double timeSet, timeModified, timeUsed;
    private String description, name, dateSet, dateDue;

    public Task() {
    }

    //Method when called adds a task to the database
    public void createNewTask(String name, String description, String category, String dateDueText, int colourCode, double timeSet) {
        this.category = new Category(category);
        this.name = name;
        this.description = description;
        this.dateDue = dateTextToSQLFormat(dateDueText);
        this.colourCode = colourCode;
        this.timeSet = timeSet;
        this.taskID = DatabaseHandle.createID("task", "TaskID");
        this.timeModified = calcModifiedTime();
        Date currentDate = new Date();
        this.dateSet = dateToSQLFormat(currentDate);
        this.timeUsed = 0;

        String sql = "INSERT INTO smarttimetabledb.task (`TaskID`, `Name`, `Description`, `UserID`, `CategoryID`, `DateSet`, `DateDue`, `Colour`, `TimeSet`, `TimeModified`) "
                + "VALUES (" + this.taskID + ", '" + this.name + "', '" + this.description + "', " + User.getUserID() + ", " + this.category.getCategoryID() + ", '" + this.dateSet + "', '" + this.dateDue + "', " + this.colourCode + ", " + this.timeSet + ", " + this.timeModified + ")";
        DatabaseHandle.update(sql);
        new Popup("Task " + this.name + " created.").setVisible(true);
    }

    //Method when called edits a task already in the database
    public void editTask(String name, String description, String category, String dateDueText, int colourCode, double timeSet, int taskID) {
        this.category = new Category(category);
        this.taskID = taskID;
        this.name = name;
        this.description = description;
        this.dateDue = dateTextToSQLFormat(dateDueText);
        this.colourCode = colourCode;
        this.timeSet = timeSet;
        this.timeModified = calcModifiedTime();

        String sql = "UPDATE smarttimetabledb.task SET Name = '" + this.name + "', Description = '" + this.description + "', dateDue = '" + this.dateDue + "', Colour = " + this.colourCode + ", CategoryID = " + this.category.getCategoryID() + ", TimeSet = " + this.timeSet + " WHERE UserID = " + User.getUserID() + " AND TaskID = " + this.taskID;
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
                this.category = new Category(rs.getInt("CategoryID"));
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
                this.category = new Category(rs.getInt("CategoryID"));
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

    //Deletes this task from the DB
    public void deleteTask() {
        String sql = "DELETE FROM smarttimetabledb.`task` WHERE UserID = " + User.getUserID() + " AND TaskID = " + this.taskID;
        DatabaseHandle.update(sql);
    }

    //Calculates the modified time
    private double calcModifiedTime() {
        double timeModded = this.timeSet * this.category.getModifier();
        timeModded = roundToHalf(timeModded);
        return timeModded;
    }

    private double roundToHalf(double number) {
        return (Math.round(number * 2) / 2.0);
    }

    // <editor-fold defaultstate="collapsed" desc=" Setters & Getters "> 
    public void setCategory(Category category) {
        this.category = category;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setColourCode(int colourCode) {
        this.colourCode = colourCode;
    }

    public void setTimeSet(double timeSet) {
        this.timeSet = timeSet;
    }

    public void setTimeModified(double timeModified) {
        this.timeModified = timeModified;
    }

    public void setTimeUsed(double timeUsed) {
        this.timeUsed = timeUsed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateSet(String dateSet) {
        this.dateSet = dateSet;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }

    public int getTaskID() {
        return this.taskID;
    }

    public Category getCategory() {
        return this.category;
    }

    public int getColourCode() {
        return this.colourCode;
    }

    public double getTimeSet() {
        return this.timeSet;
    }

    public double getTimeModified() {
        return this.timeModified;
    }

    public double getTimeUsed() {
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
