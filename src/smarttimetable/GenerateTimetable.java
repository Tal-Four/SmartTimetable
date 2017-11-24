package smarttimetable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

/**
 *
 * @author crazy
 */
public class GenerateTimetable {

    public GenerateTimetable() {
        int timetableID = createNewTimetable(getMondayDate(getTodayDate()));
        
    }

    //Returns today's date in the format YYYY-MM-DD
    private String getTodayDate(){
        GregorianCalendar calendar = new GregorianCalendar();
        int day = calendar.get(calendar.DATE);
        int month = calendar.get(calendar.MONTH) + 1;
        int year = calendar.get(calendar.YEAR);
        String date = year + "-" + month + "-" + day;
        return date;
    }
       

    //Returns the date in the format YYYY-MM-DD of the monday of the week containing the date YYYY-MM-DD
    private String getMondayDate(String date) {
        int inputYear = Integer.parseInt(date.substring(0, 4));
        int inputMonth = Integer.parseInt(date.substring(5, 7)) - 1;
        int inputDay = Integer.parseInt(date.substring(8, 10));
        GregorianCalendar calendar = new GregorianCalendar(inputYear, inputMonth, inputDay);
        int day = calendar.get(calendar.DATE) - ((calendar.get(calendar.DAY_OF_WEEK) + 5) % 7);
        int month = calendar.get(calendar.MONTH) + 1;
        int year = calendar.get(calendar.YEAR);
        String monday = year + "-" + month + "-" + day;
        return monday;
    }

    private int createNewTimetable(String startDate) {
        int timetableID = DatabaseHandle.createID("timetable", "TimetableID");
        String sql = "INSERT INTO `timetable` (`UserID`, `TimetableID`, `StartDay`) VALUES (" + User.getUserID() + ", " + timetableID + ", '" + startDate + "')";
        DatabaseHandle.update(sql);
        return timetableID;
    }

    private void plotEvents(int timetableID) {
        String sql = "SELECT event.* FROM event, user WHERE user.UserID = event.UserID AND user.UserID = " + User.getUserID() + " ORDER BY event.Day, event.StartTime";
        ResultSet rs = DatabaseHandle.query(sql);
        try {
            while (rs.next()) {
                int day = rs.getInt("Day");
                sql = "INSERT INTO timetableslot (UserID, TimetableID, Day, Time, EventID) VALUES (" + User.getUserID() + ", " + timetableID + ", ";
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

}
