package smarttimetable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author crazy
 */
public class GenerateTimetable {

    public GenerateTimetable() {
        createNewTimetable(getMondayDate(getTodayDate()));

    }

    //Returns today's date in the format YYYY-MM-DD
    private String getTodayDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        int day = calendar.get(calendar.DATE);
        String date;
        if (day <= 9) {
            date = "0" + day;
        } else {
            date = "" + day;
        }
        int month = calendar.get(calendar.MONTH) + 1;
        if (month <= 9) {
            date = "0" + month + "-" + date;
        } else {
            date = month + "-" + date;
        }
        int year = calendar.get(calendar.YEAR);
        date = year + "-" + date;
        return date;
    }

    //Returns the date in the format YYYY-MM-DD of the monday of the week containing the date YYYY-MM-DD
    private String getMondayDate(String date) {
        int inputYear = Integer.parseInt(date.substring(0, 4));
        int inputMonth = Integer.parseInt(date.substring(5, 7)) - 1;
        int inputDay = Integer.parseInt(date.substring(8, 10));

        GregorianCalendar calendar = new GregorianCalendar(inputYear, inputMonth, inputDay);
        calendar.add(GregorianCalendar.DAY_OF_YEAR, -((calendar.get(GregorianCalendar.DAY_OF_WEEK) + 5) % 7));
        int day = calendar.get(GregorianCalendar.DATE);
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        int year = calendar.get(GregorianCalendar.YEAR);

        String monday = year + "-" + month + "-" + day;
        return monday;
    }

    private int createNewTimetable(String startDate) {
        int timetableID = DatabaseHandle.createID("timetable", "TimetableID");
        String sql = "INSERT INTO `timetable` (`UserID`, `TimetableID`, `StartDay`) VALUES (" + User.getUserID() + ", " + timetableID + ", '" + startDate + "')";
        DatabaseHandle.update(sql);
        plotEvents(timetableID);
        return timetableID;
    }

    private void plotEvents(int timetableID) {

        plotReccuringEvents(timetableID);

        plotSingleEvents(timetableID);

    }

    private void plotReccuringEvents(int timetableID) {
        String sql = "SELECT event.* FROM event, user WHERE event.Date = NULL"
                + " AND user.UserID = event.UserID AND user.UserID = " + User.getUserID()
                + " ORDER BY event.Day, event.StartTime";
        ResultSet rs = DatabaseHandle.query(sql);

        try {
            while (rs.next()) {

                int day = rs.getInt("Day");
                int startTime = (int) (rs.getFloat("StartTime") * 2);
                int endTime = (int) (rs.getFloat("EndTime") * 2);
                int eventID = rs.getInt("EventID");

                for (int counter = startTime; counter <= endTime; counter++) {
                    sql = "INSERT INTO timetableslot (UserID, TimetableID, Day, Time, EventID) "
                            + "VALUES (" + User.getUserID() + ", " + timetableID + ", " + (day - 1)
                            + ", " + counter + ", " + eventID + ")";
                    DatabaseHandle.update(sql);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    private void plotSingleEvents(int timetableID) {

        //Selects the start day of a timetable with the given ID
        String sql = "SELECT timetable.StartDay FROM user, timetable WHERE timetable.UserID = user.UserID "
                + "AND user.UserID = " + User.getUserID() + " AND timetable.Hidden = 0 "
                + "AND timetable.TimetableID = " + timetableID;
        ResultSet rs = DatabaseHandle.query(sql);

        //Creates a calendar that is set to the day the parsed timetable starts
        Date startDay = null;
        GregorianCalendar calendar = new GregorianCalendar();
        try {
            rs.next();
            startDay = rs.getDate("StartDay");
        } catch (SQLException e) {
            System.err.println(e);
        }
        calendar.setTime(startDay);
        String date;

        //Selects the events that have dates that fall within the week of the timetable starting
        for (int day = 0; day < 7; day++) {
            date = calendar.get(GregorianCalendar.YEAR) + "-"
                    + (1 + calendar.get(GregorianCalendar.MONTH)) + "-"
                    + calendar.get(GregorianCalendar.DATE);
            sql = "SELECT event.* FROM event, user"
                    + " WHERE user.UserID = event.UserID AND user.UserID = " + User.getUserID()
                    + " AND event.Date = '" + date + "' ORDER BY event.StartTime";
            rs = DatabaseHandle.query(sql);

            try {
                while (rs.next()) {

                    int startTime = (int) (rs.getFloat("StartTime") * 2);
                    int endTime = (int) (rs.getFloat("EndTime") * 2);
                    int eventID = rs.getInt("EventID");

                    //Inserts events into the timetableSlot table
                    //For loop loops through all the slots required to fill the gap between start and finish
                    for (int slotCounter = startTime; slotCounter <= endTime; slotCounter++) {
                        sql = "INSERT INTO timetableslot (UserID, TimetableID, Day, Time, EventID) "
                                + "VALUES (" + User.getUserID() + ", " + timetableID + ", " + day
                                + ", " + slotCounter + ", " + eventID + ")";
                        DatabaseHandle.update(sql);
                    }
                }
            } catch (Exception e) {
                System.err.println(e);
            }
            calendar.add(GregorianCalendar.DAY_OF_YEAR, 1);
        }

    }

}
