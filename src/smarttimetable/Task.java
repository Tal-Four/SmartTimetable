package smarttimetable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adam-PC
 */
public class Task {

    private int taskID, userID, categoryID, colourCode;
    private float timeSet, timeModified, timeUsed;
    private String description, name, dateSet, dateDue;

    public Task() {

    }

    public void createNewTask(String name, String description, String category, String dateDueText, int colourCode, float timeSet) {
        this.name = name;
        this.description = description;
        this.categoryID = findCategoryID(category);
        this.dateDue = dateTextToSQLFormat(dateDueText);
        this.colourCode = colourCode;
        this.timeSet = timeSet;

        
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

    private int findCategoryID(String category) {
        int categoryID;
        
        boolean categoryExists = false;
        String sql = "SELECT * FROM category WHERE UserID = " + User.getUserID();
        ResultSet rs = DatabaseHandle.query(sql);
        categoryID = 0;
        try {
            while (rs != null && !categoryExists) {
                categoryID++;
                rs.next();
                if (category.equals(rs.getString("Name"))) {
                    categoryExists = true;
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        if (!categoryExists) {
            sql = "INSERT INTO smarttimetabledb.`category` (`CategoryID`, `UserID`, `Name`) VALUES(" + categoryID + ", " + User.getUserID() + ", '" + category + "')";
            DatabaseHandle.update(sql);
            new Popup("Category " + category + " created").setVisible(true);
        }
        
        return categoryID;
    }
}
