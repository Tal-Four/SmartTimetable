package smarttimetable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 *
 * @author crazy
 */
public class GenerateTimetable {

    public GenerateTimetable(int workEnd, int workStart) {

        if (checkTaskAssigningPossible(true, workEnd, workStart)) {
            boolean highPriority = !checkTaskAssigningPossible(false, workEnd, workStart);

            archivePreviousTables();

            String sql;
            String todayDate = getDate(new GregorianCalendar());

            if (highPriority) {
                sql = "SELECT COUNT(*)\n"
                        + "FROM task INNER JOIN user ON task.UserID = user.UserID\n"
                        + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=False) AND ((task.HighPriority)=True) AND ((task.DateDue)>'" + todayDate + "'))\n"
                        + "ORDER BY task.DateDue;";
            } else {
                sql = "SELECT COUNT(*)\n"
                        + "FROM task INNER JOIN user ON task.UserID = user.UserID\n"
                        + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=False) AND ((task.DateDue)>'" + todayDate + "'))\n"
                        + "ORDER BY task.DateDue;";
            }

            ResultSet rs = DatabaseHandle.query(sql);

            int arraySize = 0;
            try {
                if (rs.next()) {
                    arraySize = rs.getInt("COUNT(*)");
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        DatabaseHandle.disconnect();

            Task[] taskArray = new Task[arraySize];

            if (highPriority) {
                sql = "SELECT task.TaskID\n"
                        + "FROM task INNER JOIN user ON task.UserID = user.UserID\n"
                        + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=False) AND ((task.HighPriority)=True) AND ((task.DateDue)>'" + todayDate + "'))\n"
                        + "ORDER BY task.DateDue;";
            } else {
                sql = "SELECT task.TaskID\n"
                        + "FROM task INNER JOIN user ON task.UserID = user.UserID\n"
                        + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=False) AND ((task.DateDue)>'" + todayDate + "'))\n"
                        + "ORDER BY task.DateDue;";
            }

            rs = DatabaseHandle.query(sql);

            try {
                int counter = 0;
                while (rs.next()) {
                    taskArray[counter] = new Task(rs.getInt("TaskID"));
                    counter++;
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        DatabaseHandle.disconnect();

            for (Task task : taskArray) {
                task.resetSlots();
            }

            boolean firstWeek = true;
            GregorianCalendar calendar = new GregorianCalendar();
            int timetableID = createNewTimetable(getMondayDate(getDate(calendar)));

            while (!plotTasks(taskArray, timetableID, workEnd, workStart, firstWeek)) {
                firstWeek = false;
                calendar.add(GregorianCalendar.WEEK_OF_YEAR, 1);
                timetableID = createNewTimetable(getMondayDate(getDate(calendar)));
            }
        }
    }

    private void archivePreviousTables() {
        String sql = "UPDATE user INNER JOIN timetable ON user.UserID = timetable.UserID SET timetable.Hidden = 1\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + "));";

        DatabaseHandle.update(sql);
    }

    private boolean plotTasks(Task[] taskList, int timetableID, int workEnd, int workStart, boolean firstWeek) {
        boolean done = false;

        boolean[][] slotsFilled = new boolean[7][48];

        for (int sleepCounter = 0; sleepCounter < 7; sleepCounter++) {
            for (int sleepStartCounter = workEnd; sleepStartCounter < 48; sleepStartCounter++) {
                slotsFilled[sleepCounter][sleepStartCounter] = true;
            }
            for (int sleepEndCounter = 0; sleepEndCounter < workStart; sleepEndCounter++) {
                slotsFilled[sleepCounter][sleepEndCounter] = true;
            }
        }

        String sql = "SELECT timetableslot.Day, timetableslot.Time\n"
                + "FROM user INNER JOIN (timetableslot INNER JOIN timetable ON (timetableslot.TimetableID = timetable.TimetableID) AND (timetableslot.UserID = timetable.UserID)) ON user.UserID = timetable.UserID\n"
                + "WHERE (((timetableslot.TaskID) Is Null) AND ((user.UserID)=" + User.getUserID() + ") AND ((timetable.TimetableID)=" + timetableID + "));";

        ResultSet rs = DatabaseHandle.query(sql);

        try {
            while (rs.next()) {
                slotsFilled[rs.getInt("Day") - 1][rs.getInt("Time")] = true;
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        DatabaseHandle.disconnect();

        int freeSlots = 0;
        int weekStartDay = 0;
        int currentTime = 0;

        if (firstWeek) {
            GregorianCalendar calendar = new GregorianCalendar();
            weekStartDay = ((calendar.get(GregorianCalendar.DAY_OF_WEEK) + 5) % 7);
            currentTime = (int) (this.getCurrentTime() * 2) + 1;
        }

        sql = "SELECT timetable.StartDay\n"
                + "FROM timetable INNER JOIN user ON timetable.UserID = user.UserID\n"
                + "WHERE (((timetable.TimetableID)=" + timetableID + ") AND ((user.UserID)=" + User.getUserID() + "));";

        rs = DatabaseHandle.query(sql);

        Date weekStart = null;

        for (int dayCounter = weekStartDay; dayCounter < 7; dayCounter++) {
            int freeSlotCount = 0;
            for (int slotCounter = 0; slotCounter < 48; slotCounter++) {
                if (!slotsFilled[dayCounter][slotCounter] && (!firstWeek || dayCounter > weekStartDay || slotCounter >= currentTime)) {
                    freeSlotCount++;
                    if (freeSlotCount == 5) {
                        freeSlotCount = 0;
                        slotsFilled[dayCounter][slotCounter] = true;
                    }
                }
            }
        }

        for (int dayCounter = weekStartDay; dayCounter < 7; dayCounter++) {
            for (int timeCounter = workStart; timeCounter < workEnd; timeCounter++) {
                if (!slotsFilled[dayCounter][timeCounter] && (currentTime < timeCounter || dayCounter > weekStartDay || !firstWeek)) {
                    freeSlots++;
                }
            }
        }

        try {
            if (rs.next()) {
                weekStart = rs.getDate("StartDay");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        DatabaseHandle.disconnect();

        int counter = 0;
        Random rand = new Random();
        while (!done && freeSlots > 0 && counter < taskList.length) {
            int slotsFreePerTask = freeSlots;
            int difference = getDayDifference(weekStart, taskList[counter].getDateDue()) - weekStartDay;
            if (difference < (6 - weekStartDay)) {
                slotsFreePerTask = 0;
                for (int dayCounter = weekStartDay; dayCounter <= weekStartDay + difference; dayCounter++) {
                    for (int timeCounter = workStart; timeCounter < workEnd; timeCounter++) {
                        if (slotsFilled[dayCounter][timeCounter] && (!firstWeek || currentTime < timeCounter || dayCounter > weekStartDay)) {
                            slotsFreePerTask++;
                        }
                    }
                }
            }
            int slotsNeeded = (int) (taskList[counter].roundToHalf(taskList[counter].getTimeModified()) * 2);
            while (slotsNeeded > taskList[counter].getSlotsAssigned() && slotsFreePerTask > 0) {
                int assignedSlot = rand.nextInt(slotsFreePerTask);
                int dayCounter = weekStartDay;
                int freeCounter = 0;
                boolean assigned = false;

                while (dayCounter < 7 && dayCounter - weekStartDay < difference && !assigned) {

                    int slotCounter = workStart;
                    if (firstWeek && dayCounter == weekStartDay) {
                        slotCounter = currentTime;
                    }

                    while (slotCounter < workEnd && !assigned) {

                        if (!slotsFilled[dayCounter][slotCounter]) {
                            if (freeCounter == assignedSlot) {
                                sql = "INSERT INTO timetableslot (UserID, TaskID, TimetableID, Day, Time) \n"
                                        + "VALUES (" + User.getUserID() + ", " + taskList[counter].getTaskID() + ", "
                                        + timetableID + ", " + (dayCounter + 1) + ", " + slotCounter + ");";

                                DatabaseHandle.update(sql);

                                taskList[counter].newSlotAssigned();
                                assigned = true;
                                freeSlots--;
                                slotsFreePerTask--;
                                slotsFilled[dayCounter][slotCounter] = true;
                            }
                            freeCounter++;
                        }
                        slotCounter++;
                    }
                    dayCounter++;
                }

            }
            done = true;
            for (Task task : taskList) {
                slotsNeeded = (int) (task.roundToHalf(task.getTimeModified()) * 2);
                if (slotsNeeded > task.getSlotsAssigned()) {
                    done = false;
                }
            }

            counter++;
        }

        return done;
    }

    //Returns today's date in the format YYYY-MM-DD
    private String getDate(GregorianCalendar calendar) {
        int day = calendar.get(GregorianCalendar.DATE);
        String date;
        if (day <= 9) {
            date = "0" + day;
        } else {
            date = "" + day;
        }
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        if (month <= 9) {
            date = "0" + month + "-" + date;
        } else {
            date = month + "-" + date;
        }
        int year = calendar.get(GregorianCalendar.YEAR);
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

    //<editor-fold defaultstate="collapsed" desc=" Event Insertion ">
    private void plotEvents(int timetableID) {

        plotRecurringEvents(timetableID);

        plotSingleEvents(timetableID);
    }

    private void plotRecurringEvents(int timetableID) {
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
        DatabaseHandle.disconnect();

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
        DatabaseHandle.disconnect();
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
            } catch (SQLException e) {
                System.err.println(e);
            }
        DatabaseHandle.disconnect();
            calendar.add(GregorianCalendar.DAY_OF_YEAR, 1);
        }

    }
    //</editor-fold>

    private boolean checkTaskAssigningPossible(boolean highPriority, int workEnd, int workStart) {
        String sql;
        GregorianCalendar calendar = new GregorianCalendar();
        String todayDate = calendar.get(GregorianCalendar.YEAR) + "-"
                + (calendar.get(GregorianCalendar.MONTH) + 1) + "-"
                + calendar.get(GregorianCalendar.DAY_OF_MONTH);
        if (highPriority) {
            //Only checks to see whether the tasks flagged as high priority can be plotted successfully.
            sql = "SELECT task.TaskID\n"
                    + "FROM task INNER JOIN user ON task.UserID = user.UserID\n"
                    + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=False) AND ((task.HighPriority)=True) AND ((task.DateDue)>'" + todayDate + "'))\n"
                    + "ORDER BY task.DateDue;";
        } else {
            //Checks to see if all tasks can be plotted successfully regardless of priority flag.
            sql = "SELECT task.TaskID\n"
                    + "FROM task INNER JOIN user ON task.UserID = user.UserID\n"
                    + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=False) AND ((task.DateDue)>'" + todayDate + "'))\n"
                    + "ORDER BY task.DateDue;";
        }

        ResultSet taskRS = DatabaseHandle.query(sql);
        int slotsUsedByPreviousTasks = 0;
        double currentTime = getCurrentTime();
        boolean possible = true;
        try {
            while (taskRS.next() && possible) {
                Task task = new Task(taskRS.getInt("task.TaskID"));
                int slotsToPlot = (int) ((task.getTimeModified() - task.getTimeUsed()) * 2);
                int dayDifference = getDayDifference(new Date(), task.sqlDateToTextFormat(task.getDateDue()));
                int totalSlotsRemainingToday = 48 - (int) (currentTime * 2);
                int totalSlotsBeforeDeadline = (dayDifference * 48) + totalSlotsRemainingToday;
                int slotsUsedByEvents = getSlotsUsedByEventsBeforeDate(dayDifference, workEnd, workStart);
                int slotsAvailableBeforeDeadline = totalSlotsBeforeDeadline - (slotsUsedByEvents + slotsUsedByPreviousTasks);
                slotsAvailableBeforeDeadline = ((slotsAvailableBeforeDeadline / 5) * 4) + (slotsAvailableBeforeDeadline % 5);
                if (slotsAvailableBeforeDeadline >= slotsToPlot) {
                    slotsUsedByPreviousTasks = slotsUsedByPreviousTasks + slotsToPlot;
                } else {
                    possible = false;
                }
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        DatabaseHandle.disconnect();
        return possible;
    }

    private int getSlotsUsedByEventsBeforeDate(int dayDifference, int workEnd, int workStart) {

        int slotsUsed = 0;

        int days = dayDifference + 1;
        int numberOfWeeks = days / 7;
        int numberOfExtraDays = days % 7;

        //Get slots used by "Sleep" events first
        int sleepSlotsPerDay = (48 - workEnd) + workStart;

        GregorianCalendar cal = new GregorianCalendar();
        slotsUsed = slotsUsed + (sleepSlotsPerDay * days);

        //Get slots used by reccuring events second
        String inStatement;
        if (numberOfExtraDays == 0) {
            inStatement = "NULL";
        } else {
            inStatement = "" + (cal.get(GregorianCalendar.DAY_OF_WEEK) - 1);

            for (int count = 0; count < numberOfExtraDays - 1; count++) {
                cal.add(GregorianCalendar.DAY_OF_WEEK, 1);
                if (cal.get(GregorianCalendar.DAY_OF_WEEK) == 1) {
                    inStatement = inStatement + ",7";
                } else {
                    inStatement = inStatement + "," + (cal.get(GregorianCalendar.DAY_OF_WEEK) - 1);
                }
            }
            cal.add(GregorianCalendar.DAY_OF_WEEK, -(numberOfExtraDays - 1));
        }
        String sql = "SELECT StartTime, EndTime\n"
                + "FROM event INNER JOIN user ON event.UserID = user.UserID\n"
                + "WHERE (((event.Date) Is Null) AND ((user.UserID)=" + User.getUserID() + ") AND ((event.Day) In (" + inStatement + ")));";

        ResultSet rs = DatabaseHandle.query(sql);

        try {
            while (rs.next()) {
                int counter = rs.getInt("StartTime");
                if (counter < workStart) {
                    counter = workStart;
                }
                int endTime = rs.getInt("EndTime");
                while (counter < endTime && counter < workEnd) {
                    slotsUsed++;
                    counter++;
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        DatabaseHandle.disconnect();

        sql = "SELECT StartTime, EndTime\n"
                + "FROM event INNER JOIN user ON event.UserID = user.UserID\n"
                + "WHERE (((event.Date) Is Null) AND ((user.UserID)=" + User.getUserID() + "));";

        rs = DatabaseHandle.query(sql);

        try {
            while (rs.next()) {
                int startTime = rs.getInt("StartTime");
                int endTime = rs.getInt("EndTime");
                if (startTime < workStart) {
                    startTime = workStart;
                }
                for (int weekCounter = 0; weekCounter < numberOfWeeks; weekCounter++) {
                    int counter = startTime;
                    while (counter < endTime && counter < workEnd) {
                        slotsUsed++;
                        counter++;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        DatabaseHandle.disconnect();

        //Get slots used by single events third
        String todayDate = getDate(cal);

        cal.add(GregorianCalendar.DAY_OF_MONTH, dayDifference);

        String endDate = cal.get(GregorianCalendar.YEAR) + "-"
                + cal.get(GregorianCalendar.MONTH) + "-"
                + cal.get(GregorianCalendar.DAY_OF_MONTH);

        sql = "SELECT StartTime, EndTime\n"
                + "FROM event INNER JOIN user ON event.UserID = user.UserID\n"
                + "WHERE (((event.Day) Is Null) AND ((user.UserID)=" + User.getUserID() + ") AND ((event.Date) Between (CAST('" + todayDate + "' AS DATE)) And (CAST('" + endDate + "' AS DATE))));";

        rs = DatabaseHandle.query(sql);

        try {
            while (rs.next()) {
                int counter = rs.getInt("StartTime");
                if (counter < workStart) {
                    counter = workStart;
                }
                int endTime = rs.getInt("EndTime");
                while (counter < endTime && counter < workEnd) {
                    slotsUsed++;
                    counter++;
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        DatabaseHandle.disconnect();
        //Take away events already passed today
        int currentTime = (int) (getCurrentTime() * 2);
        int day = 1 + ((new GregorianCalendar().get(GregorianCalendar.DAY_OF_WEEK) + 5) % 7);
        sql = "SELECT event.StartTime, event.EndTime\n"
                + "FROM event INNER JOIN user ON event.UserID = user.UserID\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((event.Hidden) = True) AND ((event.StartTime)<" + currentTime + ") AND (((event.Day)=" + day + ") OR ((event.Date)='" + todayDate + "')));";

        rs = DatabaseHandle.query(sql);
        try {
            while (rs.next()) {
                int startTime = (int) (Double.parseDouble(rs.getString("StartTime")) * 2);
                int endTime = (int) (Double.parseDouble(rs.getString("EndTime")) * 2);
                if (endTime > currentTime) {
                    slotsUsed = slotsUsed - (currentTime - startTime);
                } else {
                    slotsUsed = slotsUsed - (endTime - startTime);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        DatabaseHandle.disconnect();

        if (currentTime > workStart) {
            slotsUsed = slotsUsed - workStart;
            if (currentTime > workEnd) {
                slotsUsed = slotsUsed - (currentTime - workEnd);
            }
        } else {
            slotsUsed = slotsUsed - currentTime;
        }

        return slotsUsed;
    }

    private int getDayDifference(Date startDate, String endDate) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (endDate.charAt(4) == '-') {
            endDate = endDate.substring(8, 10) + "/" + endDate.substring(5, 7) + "/" + endDate.substring(0, 4);
        }
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
        if (calendar.get(GregorianCalendar.MINUTE) >= 30) {
            time = time + 0.5;
        }
        return time;
    }
}
