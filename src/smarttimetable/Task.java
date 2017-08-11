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
                + "VALUES (" + this.taskID + ", '" + this.name + "', '" + this.description + "', " + User.getUserID() + ", " + this.categoryID + ", '" + this.dateSet + "', '" + this.dateDue + "', " + this.colourCode + ", " + this.timeSet + ", " +this.timeModified + ")";

        DatabaseHandle.update(sql);
        
        new Popup("Task " + this.name + " created.").setVisible(true); 
    }
    
    public void readTaskFromDB(){
        //IMPLEMENT
    }

    //Converting date object to SQL date format
    public String dateToSQLFormat(Date date) {
        return (date.getYear() + 1900) + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    }

    //Converting raw text to SQL date format
    public String dateTextToSQLFormat(String dateText) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = df.parse(dateText);
        } catch (ParseException e) {
            System.err.println(e);
        }
        return dateToSQLFormat(date);
    }
}
