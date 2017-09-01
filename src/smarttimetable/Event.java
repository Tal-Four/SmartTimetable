package smarttimetable;

import java.sql.ResultSet;

/**
 *
 * @author Adam-PC
 */
public class Event {

    private String eventName, description;
    private int day, colourCode, eventID;
    private float startTime, endTime;
    public final int MON = 1, TUE = 2, WED = 3, THU = 4, FRI = 5, SAT = 6, SUN = 7;

    public Event() {

    }

    //Method to retrieve event details from the database
    public void readFromDB(int eventID) {
        this.eventID = eventID;

        String sql = "SELECT * from event where EventID == " + this.eventID + " AND UserID = " + User.getUserID();
        ResultSet rs = DatabaseHandle.query(sql);

        try {
            if (rs.next()) {
                this.eventName = rs.getString("EventName");
                this.description = rs.getString("Description");
                this.colourCode = rs.getInt("Colour");
                this.day = rs.getInt("Day");
                this.startTime = rs.getFloat("StartTime");
                this.endTime = rs.getFloat("EndTime");
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    //Creates an event and inserts into a database given variables
    public void createEvent(String eventName, String description, int colour, int day, float endTime, float startTime) {
        this.eventName = eventName;
        this.description = description;
        this.colourCode = colour;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventID = DatabaseHandle.createID("event", "EventID");

        String sql = "INSERT INTO smarttimetabledb.event (`EventID`, `UserID`, `EventName`, `Description`, `Day`, `Colour`, `StartTime`, `EndTime`) "
                + "VALUES (" + this.eventID + ", " + User.getUserID() + ", '" + this.eventName + "', '" + this.description + "', " + this.day + ", " + this.colourCode + ", " + this.startTime + ", " + this.endTime + ")";
        DatabaseHandle.update(sql);
    }

    //Converts the String of a day, eg. Monday, into a representive number, eg. Monday --> 1
    public int dayStringToInt(String dayString) {
        int dayInt = 0;
        switch (dayString) {
            case ("Monday"):
                day = MON;
                break;
            case ("Tuesday"):
                day = TUE;
                break;
            case ("Wednesday"):
                day = WED;
                break;
            case ("Thursday"):
                day = THU;
                break;
            case ("Friday"):
                day = FRI;
                break;
            case ("Saturday"):
                day = SAT;
                break;
            case ("Sunday"):
                day = SUN;
                break;
        }
        return dayInt;
    }

    //Converts the int of a day, eg. 1, into the represented String, eg. 1 --> Monday
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

    public float getStartTime() {
        return startTime;
    }

    public float getEndTime() {
        return endTime;
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

    public void setStartTime(float startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(float endTime) {
        this.endTime = endTime;
    }//</editor-fold>

}
