package smarttimetable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class used to store details about and manipulate task records in the
 * database.
 *
 * @author AdamPlatt
 */
public class Task {

    private Category category;
    private Boolean highPriority;
    private int taskID, colourCode, slotsAssigned;
    private double timeSet, timeModified, timeUsed;
    private String description, name, dateSet, dateDue;

    /**
     * Constructor that reads a task from the database given an ID
     *
     * @param taskID
     */
    public Task(int taskID) {
        this.taskID = taskID;
        String sql = "SELECT task.*\n"
                + "FROM user INNER JOIN task ON user.UserID = task.UserID\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.TaskID)=" + this.taskID + "));";
        ResultSet rs = DatabaseHandle.query(sql);
        if (rs != null) {
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
                    this.highPriority = rs.getBoolean("HighPriority");
                }
            } catch (SQLException e) {
                
            }
        }
    }

    /**
     * Constructor when called adds a task to the database
     *
     * @param name
     * @param description
     * @param category
     * @param dateDueText
     * @param colourCode
     * @param timeSet
     * @param highPriority
     */
    public Task(String name, String description, int category, String dateDueText, int colourCode, double timeSet, Boolean highPriority) {
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
        this.highPriority = highPriority;

        String sql = "INSERT INTO task (`TaskID`, `Name`, `Description`, `UserID`, `CategoryID`, `DateSet`, `DateDue`, `Colour`, `TimeSet`, `TimeModified`, `HighPriority`) "
                + "VALUES (" + this.taskID + ", '" + this.name + "', '" + this.description + "', " + User.getUserID() + ", " + this.category.getCategoryID() + ", '" + this.dateSet + "', '" + this.dateDue + "', " + this.colourCode + ", " + this.timeSet + ", " + this.timeModified + ", " + this.highPriority + ")";
        int rowsAffected = DatabaseHandle.update(sql);
        if (rowsAffected != 0) {
            new Popup("Task " + this.name + " created.").setVisible(true);
        }
    }

    /**
     * Method when called edits a task already in the database
     *
     * @param name
     * @param description
     * @param categoryID
     * @param dateDueText
     * @param colourCode
     * @param timeSet
     * @param highPriority
     */
    public void editTask(String name, String description, int categoryID, String dateDueText, int colourCode, double timeSet, Boolean highPriority) {
        this.category = new Category(categoryID);
        this.name = name;
        this.description = description;
        this.dateDue = dateTextToSQLFormat(dateDueText);
        this.colourCode = colourCode;
        this.timeSet = timeSet;
        this.timeModified = calcModifiedTime();
        this.highPriority = highPriority;

        String sql = "UPDATE user INNER JOIN task ON user.UserID = task.UserID SET Name = '" + this.name + "', Description = '" + this.description + "', dateDue = '" + this.dateDue + "', Colour = " + this.colourCode + ", CategoryID = " + this.category.getCategoryID() + ", TimeSet = " + this.timeSet + ", TimeModified = " + this.timeModified + ", HighPriority = " + this.highPriority + "\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.TaskID)=" + this.taskID + "));";
        int rowsAffected = DatabaseHandle.update(sql);

        if (rowsAffected != 0) {
            new Popup("Task " + this.name + " edited.").setVisible(true);
        }
    }

    /**
     * Converting date object to SQL date format
     *
     * @param date
     * @return
     */
    private String dateToSQLFormat(Date date) {
        return (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    }

    /**
     * Converting raw text to SQL date format
     *
     * @param dateText
     * @return
     */
    private String dateTextToSQLFormat(String dateText) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = df.parse(dateText);
        } catch (ParseException e) {
            
        }
        return dateToSQLFormat(date);
    }

    /**
     * Formats the string from YYYY-MM-DD to DD/MM/YYYY
     *
     * @param sqlDate
     * @return
     */
    public String sqlDateToTextFormat(String sqlDate) {
        return sqlDate.substring(8, 10) + "/" + sqlDate.substring(5, 7) + "/" + sqlDate.substring(0, 4);
    }

    /**
     * Deletes this task from the DB
     *
     */
    public void deleteTask() {
        String sql = "DELETE FROM timetableslot WHERE (((UserID)=" + User.getUserID() + ") AND ((TaskID)=" + this.taskID + "));";
        DatabaseHandle.update(sql);
        sql = "DELETE FROM task WHERE (((UserID)=" + User.getUserID() + ") AND ((TaskID)=" + this.taskID + "));";
        DatabaseHandle.update(sql);
    }

    /**
     * Calculates the modified time
     *
     * @return
     */
    private double calcModifiedTime() {
        double timeModded = this.timeSet * this.category.getModifier();
        timeModded = roundToHalf(timeModded);
        return timeModded;
    }

    /**
     * Rounds the given double to the nearest half
     *
     * @param number
     * @return
     */
    public double roundToHalf(double number) {
        return (Math.round(number * 2) / 2.0);
    }

    /**
     * Increments the number of slots assigned
     */
    public void newSlotAssigned() {
        this.slotsAssigned++;
        String sql = "UPDATE user INNER JOIN task ON user.UserID = task.UserID SET task.SlotsAssigned = " + this.slotsAssigned + "\n"
                + "WHERE (((task.UserID)=" + User.getUserID() + ") AND ((task.TaskID)=" + this.taskID + "));";

        DatabaseHandle.update(sql);
    }

    /**
     * Sets the number of slotsAssigned equal to the timeUsed * 2
     */
    public void resetSlots() {
        this.slotsAssigned = (int) (this.timeUsed * 2);
        String sql = "UPDATE user INNER JOIN task ON user.UserID = task.UserID SET task.SlotsAssigned = " + this.slotsAssigned + "\n"
                + "WHERE (((task.UserID)=" + User.getUserID() + ") AND ((task.TaskID)=" + this.taskID + "));";

        DatabaseHandle.update(sql);
    }

    /**
     * Marks the task as hidden and adjusts the category's modifier and removes
     * any timetable slots containing it
     */
    public void complete() {
        String sql = "UPDATE user INNER JOIN task ON user.UserID = task.UserID SET task.Hidden = True\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.TaskID)=" + this.taskID + "));";
        int rowsAffected = DatabaseHandle.update(sql);
        if (rowsAffected != 0) {

            this.category.taskComplete(this);

            sql = "DELETE FROM timetableslot WHERE (((UserID)=" + User.getUserID() + ") AND ((TaskID)=" + this.taskID + "));";
            DatabaseHandle.update(sql);
        }
    }

    /**
     * Marks the task as un-hidden and corrects the category modifier
     */
    public void markAsTodo() {
        String sql = "UPDATE user INNER JOIN task ON user.UserID = task.UserID SET task.Hidden = False\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.TaskID)=" + this.taskID + "));";
        int rowsAffected = DatabaseHandle.update(sql);

        if (rowsAffected != 0) {
            this.category.taskTodo(this);
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" Setters & Getters "> 
    public void setCategory(Category category) {
        this.category = category;
    }

    public void setHighPriority(Boolean highPriority) {
        this.highPriority = highPriority;
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

    public void setSlotsAssigned(int slotsASsigned) {
        this.slotsAssigned = slotsASsigned;
    }

    public int getTaskID() {
        return this.taskID;
    }

    public Boolean getHighPriority() {
        return highPriority;
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
    }

    public int getSlotsAssigned() {
        return slotsAssigned;
    }// </editor-fold>

}
