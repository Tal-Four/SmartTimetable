package smarttimetable;

import java.sql.ResultSet;

/**
 *
 * @author Adam-PC
 */
public class Event {
    
    private String eventName, description;
    private int day, colourCode, eventID;
    private double startTime, endTime;
    public final int MON = 1, TUE = 2, WED = 3, THU = 4, FRI = 5, SAT = 6, SUN = 7, START = 0, END = 1;

    public Event() {

    }

    //Method to retrieve event details from the database given an ID
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

    //Method to retrieve event details from the database given a name
    public void readFromDB(String eventName) {
        this.eventName = eventName;

        String sql = "SELECT * FROM event WHERE EventName = '" + this.eventName + "' AND UserID = " + User.getUserID();
        ResultSet rs = DatabaseHandle.query(sql);

        try {
            if (rs.next()) {
                this.eventID = rs.getInt("EventID");
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
    public void createEvent(String eventName, String description, int colour, int day, double endTime, double startTime) {
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

        new Popup("Event " + this.eventName + " created.").setVisible(true);
    }

    //Updates an existing record with new values
    public void editEvent(String eventName, String description, int colour, int day, double endTime, double startTime) {
        this.eventName = eventName;
        this.description = description;
        this.colourCode = colour;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;

        String sql = "UPDATE smarttimetabledb.event SET EventName = '" + this.eventName + "', Description = '" + this.description + "', Colour = " + this.colourCode + ", Day = " + this.day + ", StartTime = " + this.startTime + ", EndTime  = " + this.endTime + " WHERE EventID = " + this.eventID + " AND UserID = " + User.getUserID();
        DatabaseHandle.update(sql);

        new Popup("Event " + this.eventName + " edited.").setVisible(true);
    }

    //Converts the String of a day, eg. Monday, into a representive number, eg. Monday --> 1
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

    //Deletes this event from the DB
    public void deleteEvent() {
        String sql = "DELETE FROM smarttimetabledb.`event` WHERE UserID = " + User.getUserID() + " AND EventID = " + this.eventID;
        DatabaseHandle.update(sql);
    }

    public String[] timeToString(int mode) {
        double time = 0;
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
    }//</editor-fold>

}
