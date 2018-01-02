package smarttimetable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author crazy
 */
public class GenerateTimetable {

    public GenerateTimetable() {
        int createdTimetableID = createNewTimetable(getMondayDate(getTodayDate()));
        taskInsertion(createdTimetableID);
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
        // plotEvents(timetableID);
        return timetableID;
    }

    //<editor-fold defaultstate="collapsed" desc=" Event Insertion ">
    private void plotEvents(int timetableID) {

        plotReccuringEvents(timetableID);

        plotSingleEvents(timetableID);
    }

    private void plotReccuringEvents(int timetableID) {
        String sql = "SELECT event.EventID, event.Day, event.StartTime, event.EndTime\n"
                + "FROM event INNER JOIN user ON event.UserID = user.UserID\n"
                + "WHERE (((user.UserID)= " + User.getUserID() + ") AND ((event.Date) Is Null) AND ((event.Hidden)=False))\n"
                + "ORDER BY event.Day, event.StartTime;";
        ResultSet rs = DatabaseHandle.query(sql);

        try {
            while (rs.next()) {

                int day = rs.getInt("Day");
                int startTime = (int) (rs.getFloat("StartTime") * 2);
                int endTime = (int) (rs.getFloat("EndTime") * 2);
                int eventID = rs.getInt("EventID");

                for (int counter = startTime; counter <= endTime; counter++) {
                    sql = "INSERT INTO timetableslot (UserID, TimetableID, Day, Time, EventID) "
                            + "VALUES (" + User.getUserID() + ", " + timetableID + ", " + day
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
        for (int day = 1; day <= 7; day++) {
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
    //</editor-fold>

    private boolean checkTaskAssigningPossible(boolean highPriority) {
        String sql = "";
        if (highPriority) {
            //Only checks to see whether the tasks flagged as high priority can be plotted successfully.
            sql = "SELECT task.TaskID\n"
                    + "FROM task INNER JOIN user ON task.UserID = user.UserID\n"
                    + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=False) AND ((task.HighPriority)=True))\n"
                    + "ORDER BY task.DateDue;";
        } else {
            //Checks to see if all tasks can be plotted successfully regardless of priority flag.
            sql = "SELECT task.TaskID\n"
                    + "FROM task INNER JOIN user ON task.UserID = user.UserID\n"
                    + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=False))\n"
                    + "ORDER BY task.DateDue;";
        }

        ResultSet taskRS = DatabaseHandle.query(sql);
        int slotsNeeded = 0;
        double currentTime = getCurrentTime();
        try {
            while (taskRS.next()) {
                Task task = new Task(taskRS.getInt("task.TaskID"));
                int slotsToPlot = (int) ((task.getTimeModified() - task.getTimeUsed()) * 2);
                int dayDifference = getDayDifference(new Date(), task.sqlDateToTextFormat(task.getDateDue()));
                int slotsRemainingToday = 48 - (int) (currentTime * 2);
                int slotsBeforeDeadline = (dayDifference * 48) + slotsRemainingToday;
                //FINISH TASK ASSIGNMENT AND MODIFY VARS SO LOOP HAS LESS PROCESSING

            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return true;
    }

    private int getSlotsUsedByEventsBeforeDate(Date date, int dayDifference) {

        int slotsUsed = 0;
        //Get slots used by "Sleep" events first

        //Get slots used by reccuring events second
        int days = dayDifference + 1;
        int numberOfWeeks = days / 7;
        int numberOfExtraDays = days % 7;

        String inStatement = "";

        if (numberOfExtraDays == 0) {
            inStatement = "NULL";
        } else {
            GregorianCalendar calendar = new GregorianCalendar();
            inStatement = "" + (calendar.get(GregorianCalendar.DAY_OF_WEEK) - 1);
            for (int count = 0; count < numberOfExtraDays - 1; count++) {
                calendar.add(GregorianCalendar.DAY_OF_WEEK, 1);
                if (calendar.get(GregorianCalendar.DAY_OF_WEEK) == 1) {
                    inStatement = inStatement + ",7";
                } else {
                    inStatement = inStatement + "," + (calendar.get(GregorianCalendar.DAY_OF_WEEK) - 1);
                }
            }
        }

        String sql = "SELECT Sum(StartTime) AS StartSum, Sum(EndTime) AS EndSum\n"
                + "FROM event INNER JOIN user ON event.UserID = user.UserID\n"
                + "WHERE (((event.Date) Is Null) AND ((user.UserID)=" + User.getUserID() + ") AND ((event.Day) In (" + inStatement + ")));";

        ResultSet rs = DatabaseHandle.query(sql);

        try {
            if (rs.next()) {
                double startSum = Double.parseDouble(rs.getString("StartSum"));
                double endSum = Double.parseDouble(rs.getString("EndSum"));
                int difference = (int) ((endSum - startSum) * 2);
                slotsUsed = slotsUsed + difference;
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        sql = "SELECT Sum(StartTime) AS StartSum, Sum(EndTime) AS EndSum\n"
                + "FROM event INNER JOIN user ON event.UserID = user.UserID\n"
                + "WHERE (((event.Date) Is Null) AND ((user.UserID)=" + User.getUserID() + "));";

        rs = DatabaseHandle.query(sql);

        try {
            if (rs.next()) {
                double startSum = Double.parseDouble(rs.getString("StartSum"));
                double endSum = Double.parseDouble(rs.getString("EndSum"));
                int difference = (int) ((endSum - startSum) * 2);
                slotsUsed = slotsUsed + (difference * numberOfWeeks);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        //Get slots used by single events thrid
        //Take away events already passed today
        return slotsUsed;
    }

    private void taskInsertion(int timetableID) {
        if (checkTaskAssigningPossible(true)) {
            if (checkTaskAssigningPossible(false)) {

            }
        }
    }

    private int getDayDifference(Date startDate, String endDate) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        long before = calendar.getTimeInMillis();
        try {
            calendar.setTime(df.parse(endDate));
        } catch (ParseException e) {
            System.err.println(e);
        }
        long after = calendar.getTimeInMillis();
        long difference = after - before;
        int dayDiffernce = (int) (difference / (24 * 1000 * 60 * 60));
        return dayDiffernce;
    }

    //Returns current time in hours rounded up to the nearest half hour
    private double getCurrentTime() {
        GregorianCalendar calendar = new GregorianCalendar();
        double time = calendar.get(GregorianCalendar.HOUR_OF_DAY);
        System.out.println(time);
        if (calendar.get(GregorianCalendar.MINUTE) >= 30) {
            time = time + 0.5;
        }
        return time;
    }

}
