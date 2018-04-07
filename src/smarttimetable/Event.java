package smarttimetable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class used to manipulate and store data about the event records in the
 * database.
 *
 * @author AdamPlatt
 */
public class Event {

    private String eventName, description, date;
    private int day, colourCode, eventID;
    private double startTime, endTime;
    /**
     * Recurring events in the database are given a number for the day they are
     * on, 1 --> 7 for Monday --> Sunday respectively. Therefore constants are
     * used to represent these numbers.
     */
    public final int MON = 1, TUE = 2, WED = 3, THU = 4, FRI = 5, SAT = 6, SUN = 7;

    /**
     * Creates a blank event
     */
    public Event() {

    }

    /**
     * Constructor to retrieve event details from the database given an ID
     *
     * @param eventID
     */
    public Event(int eventID) {
        this.eventID = eventID;

        String sql = "SELECT event.*\n"
                + "FROM user INNER JOIN event ON user.UserID = event.UserID\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((event.EventID)=" + this.eventID + "));";
        ResultSet rs = DatabaseHandle.query(sql);
        if (rs != null) {
            try {
                //Filling out the attributes of the object
                if (rs.next()) {
                    this.eventName = rs.getString("EventName");
                    this.description = rs.getString("Description");
                    this.colourCode = rs.getInt("Colour");
                    this.day = rs.getInt("Day");
                    this.startTime = rs.getFloat("StartTime");
                    this.endTime = rs.getFloat("EndTime");
                    this.date = rs.getString("Date");
                }
            } catch (SQLException e) {
                
            }
        }
    }

    /**
     * Creates a recurring event and inserts into a database given variables
     *
     * @param eventName
     * @param description
     * @param colour
     * @param day
     * @param endTime
     * @param startTime
     */
    public Event(String eventName, String description, int colour, int day, double endTime, double startTime) {
        this.eventName = eventName;
        this.description = description;
        this.colourCode = colour;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventID = DatabaseHandle.createID("event", "EventID");

        String sql = "INSERT INTO event (`EventID`, `UserID`, `EventName`, `Description`, `Day`, `Colour`, `StartTime`, `EndTime`) "
                + "VALUES (" + this.eventID + ", " + User.getUserID() + ", '" + this.eventName + "', '" + this.description + "', " + this.day + ", " + this.colourCode + ", " + this.startTime + ", " + this.endTime + ")";
        int rowsAffected = DatabaseHandle.update(sql);

        if (rowsAffected != 0) {
            new Popup("Event " + this.eventName + " created.").setVisible(true);
        }
    }

    /**
     * Creates a one-off event and inserts into a database given variables
     *
     * @param eventName
     * @param description
     * @param colour
     * @param date
     * @param endTime
     * @param startTime
     */
    public Event(String eventName, String description, int colour, String date, double endTime, double startTime) {
        this.eventName = eventName;
        this.description = description;
        this.colourCode = colour;
        this.date = dateTextToSQLFormat(date);
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventID = DatabaseHandle.createID("event", "EventID");

        String sql = "INSERT INTO event (`EventID`, `UserID`, `EventName`, `Description`, `Date`, `Colour`, `StartTime`, `EndTime`) "
                + "VALUES (" + this.eventID + ", " + User.getUserID() + ", '" + this.eventName + "', '" + this.description + "', " + this.date + ", " + this.colourCode + ", " + this.startTime + ", " + this.endTime + ")";
        int rowsAffected = DatabaseHandle.update(sql);

        if (rowsAffected != 0) {
            new Popup("Event " + this.eventName + " created.").setVisible(true);
        }
    }

    /**
     * Updates a recurring existing record with new values
     *
     * @param eventName
     * @param description
     * @param colour
     * @param day
     * @param endTime
     * @param startTime
     */
    public void editEvent(String eventName, String description, int colour, int day, double endTime, double startTime) {
        this.eventName = eventName;
        this.description = description;
        this.colourCode = colour;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = null;

        String sql = "UPDATE user INNER JOIN event ON user.UserID = event.UserID SET EventName = '" + this.eventName + "', Description = '" + this.description + "', Colour = " + this.colourCode + ", Day = " + this.day + ", Date = NULL , StartTime = " + this.startTime + ", EndTime  = " + this.endTime + "\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((event.EventID)=" + this.eventID + "));";
        int rowsAffected = DatabaseHandle.update(sql);

        if (rowsAffected != 0) {
            new Popup("Event " + this.eventName + " edited.").setVisible(true);
        }
    }

    /**
     * Updates a one-off existing record with new values
     *
     * @param eventName
     * @param description
     * @param colour
     * @param date
     * @param endTime
     * @param startTime
     */
    public void editEvent(String eventName, String description, int colour, String date, double endTime, double startTime) {
        this.eventName = eventName;
        this.description = description;
        this.colourCode = colour;
        this.date = dateTextToSQLFormat(date);
        this.day = 0;
        this.startTime = startTime;
        this.endTime = endTime;

        String sql = "UPDATE user INNER JOIN event ON user.UserID = event.UserID SET EventName = '" + this.eventName + "', Description = '" + this.description + "', Colour = " + this.colourCode + ", Day = NULL, Date = " + this.date + " , StartTime = " + this.startTime + ", EndTime  = " + this.endTime + "\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((event.EventID)=" + this.eventID + "));";
        int rowsAffected = DatabaseHandle.update(sql);

        if (rowsAffected != 0) {
            new Popup("Event " + this.eventName + " edited.").setVisible(true);
        }
    }

    /**
     * Converts the String of a day, eg. Monday, into a representive number, eg.
     * Monday --> 1
     *
     * @param dayString
     * @return
     */
    public int dayStringToInt(String dayString) {
        int dayInt = 0;
        switch (dayString) {
            case ("Monday"):
                dayInt = MON;
                break;
            case ("Tuesday"):
                dayInt = TUE;
                break;
            case ("Wednesday"):
                dayInt = WED;
                break;
            case ("Thursday"):
                dayInt = THU;
                break;
            case ("Friday"):
                dayInt = FRI;
                break;
            case ("Saturday"):
                dayInt = SAT;
                break;
            case ("Sunday"):
                dayInt = SUN;
                break;
        }
        return dayInt;
    }

    /**
     * Converts the int of a day, eg. 1, into the represented String, eg. 1 -->
     * Monday
     *
     * @param dayInt
     * @return
     */
    public String dayIntToString(int dayInt) {
        String dayString = "";
        switch (dayInt) {
            case (MON):
                dayString = "Monday";
                break;
            case (TUE):
                dayString = "Tuesday";
                break;
            case (WED):
                dayString = "Wednesday";
                break;
            case (THU):
                dayString = "Thursday";
                break;
            case (FRI):
                dayString = "Friday";
                break;
            case (SAT):
                dayString = "Saturday";
                break;
            case (SUN):
                dayString = "Sunday";
                break;
        }
        return dayString;
    }

    /**
     * Deletes this event from the DB
     *
     */
    public void deleteEvent() {
        String sql = "DELETE FROM timetableSlot WHERE UserID = " + User.getUserID() + " AND EventID = " + this.eventID;
        DatabaseHandle.update(sql);
        sql = "DELETE FROM event WHERE UserID = " + User.getUserID() + " AND EventID = " + this.eventID;
        DatabaseHandle.update(sql);
    }

    /**
     * Converts time is decimal to hours and minutes text. Mode determines
     * whether startTime or endTime is processed. 0 means startTime is processed
     * and any other number means endTime is processed
     *
     * @param mode
     * @return
     */
    public String[] timeToString(int mode) {
        double time;
        if (mode == 0) {
            time = this.startTime;
        } else {
            time = this.endTime;
        }
        String[] timeString = new String[2];
        timeString[0] = (int) time + "";
        if ((time * 2) % 2 == 0.0) {
            timeString[1] = "00";
        } else {
            timeString[1] = "30";
        }
        return timeString;
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
    public final String dateTextToSQLFormat(String dateText) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateToConvert = null;
        try {
            dateToConvert = df.parse(dateText);
        } catch (ParseException e) {
            
        }
        return dateToSQLFormat(dateToConvert);
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

    // <editor-fold defaultstate="collapsed" desc=" Setters & Getters "> 
    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public int getDay() {
        return day;
    }

    public int getColourCode() {
        return colourCode;
    }

    public int getEventID() {
        return eventID;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public String getDate() {
        return date;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setColourCode(int colourCode) {
        this.colourCode = colourCode;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public void setDate(String date) {
        this.date = date;
    }//</editor-fold>

}
