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
 * A class that generates a set of timetables for the user using both their task
 * and event records and stores these timetables in the database.
 *
 * @author AdamPlatt
 */
public class GenerateTimetable {

    /**
     * Generates timetables for the logged in user's tasks between the time
     * given as parameters
     *
     * @param workEnd
     * @param workStart
     */
    public GenerateTimetable(int workEnd, int workStart) {

        //Checks to see if it is possible to plot only high priority tasks
        if (checkTaskAssigningPossible(true, workEnd, workStart)) {
            //Determines whether the program will plot all tasks or just high priortiy tasks
            boolean highPriority = !checkTaskAssigningPossible(false, workEnd, workStart);

            String sql;
            String todayDate = getDate(new GregorianCalendar());

            //Retrieving the number of tasks the program needs to plot
            if (highPriority) {
                //Could only plot high priority tasks before deadline
                sql = "SELECT COUNT(*)\n"
                        + "FROM task INNER JOIN user ON task.UserID = user.UserID\n"
                        + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=False) AND ((task.HighPriority)=True) AND ((task.DateDue)>'" + todayDate + "'))\n"
                        + "ORDER BY task.DateDue;";
            } else {
                //Can plot all tasks before deadline
                sql = "SELECT COUNT(*)\n"
                        + "FROM task INNER JOIN user ON task.UserID = user.UserID\n"
                        + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=False) AND ((task.DateDue)>'" + todayDate + "'))\n"
                        + "ORDER BY task.DateDue;";
            }

            ResultSet rs = DatabaseHandle.query(sql);

            //Checking to see that SQL executed properly
            if (rs != null) {

                int arraySize = 0;
                try {
                    if (rs.next()) {
                        //Setting the size of the array equal to the number of tasks to plot
                        arraySize = rs.getInt("COUNT(*)");
                    }
                } catch (SQLException e) {
                }

                //Checking the program has tasks to plot
                if (arraySize != 0) {

                    //Setting all the user's current timetables to be hidden
                    archivePreviousTables();

                    //Creating an array with a size equal to the number of tasks to plot
                    Task[] taskArray = new Task[arraySize];

                    //Fetching the tasks to plot
                    if (highPriority) {
                        //Only fetching high priority tasks
                        sql = "SELECT task.TaskID\n"
                                + "FROM task INNER JOIN user ON task.UserID = user.UserID\n"
                                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=False) AND ((task.HighPriority)=True) AND ((task.DateDue)>'" + todayDate + "'))\n"
                                + "ORDER BY task.DateDue;";
                    } else {
                        //Fetching all tasks
                        sql = "SELECT task.TaskID\n"
                                + "FROM task INNER JOIN user ON task.UserID = user.UserID\n"
                                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((task.Hidden)=False) AND ((task.DateDue)>'" + todayDate + "'))\n"
                                + "ORDER BY task.DateDue;";
                    }

                    rs = DatabaseHandle.query(sql);

                    //Checking SQL executed properly
                    if (rs != null) {
                        try {
                            int counter = 0;
                            //Filling the taskArray with Task objects
                            while (rs.next()) {
                                taskArray[counter] = new Task(rs.getInt("TaskID"));
                                counter++;
                            }
                        } catch (SQLException e) {
                        }

                        //Setting the number of slots assigned to each task equal to the number of hours completed
                        for (Task task : taskArray) {
                            task.resetSlots();
                        }

                        boolean firstWeek = true;
                        //Creating a new calendar object with today's date
                        GregorianCalendar calendar = new GregorianCalendar();
                        //Creating a timetable that starts on the Monday of this week
                        int timetableID = createNewTimetable(getMondayDate(getDate(calendar)));

                        //Loops while not all tasks have been plotted
                        while (!plotTasks(taskArray, timetableID, workEnd, workStart, firstWeek)) {
                            firstWeek = false;
                            //Creating a timetable a week on by adding a week to the calendar
                            calendar.add(GregorianCalendar.WEEK_OF_YEAR, 1);
                            timetableID = createNewTimetable(getMondayDate(getDate(calendar)));
                        }

                        //Notifies the user that no standard tasks were plotted if highpriority is true
                        if (highPriority) {
                            new Popup("Couldn't plot standard priority tasks before deadline, please plot these tasks manually.").setVisible(true);
                        }
                    }
                } else {
                    //The size of the array was 0
                    if (highPriority) {
                        new Popup("No high priority tasks found and couldn't plot standard priority tasks before deadline. No new timetables created. Adjust the time or deadlines for tasks.").setVisible(true);
                    } else {
                        new Popup("No tasks found to plot.").setVisible(true);
                    }
                }
            }
        } else {
            //Couldn't plot high priority before deadline
            new Popup("Couldn't plot any tasks before deadline. No new timetables created. Adjust the time or deadlines for tasks.").setVisible(true);
        }
    }

    /**
     * Sets all currently non hidden timetables to be hidden (archived)
     */
    private void archivePreviousTables() {
        String sql = "UPDATE user INNER JOIN timetable ON user.UserID = timetable.UserID SET timetable.Hidden = 1\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + "));";

        DatabaseHandle.update(sql);
    }

    /**
     * Plots the tasks in taskList into a timetable until either all available
     * slots are filled or all tasks are plotted
     *
     * @param taskList
     * @param timetableID
     * @param workEnd
     * @param workStart
     * @param firstWeek
     * @return
     */
    private boolean plotTasks(Task[] taskList, int timetableID, int workEnd, int workStart, boolean firstWeek) {
        boolean done = false;

        //Creating an array to represent all slots in the timetable and whether they're free or not
        boolean[][] slotsFilled = new boolean[7][48];

        //Marking all slots outside of working hours as unavailable
        for (int sleepCounter = 0; sleepCounter < 7; sleepCounter++) {
            //Loops from the end of work hours to the end of the day
            for (int sleepStartCounter = workEnd; sleepStartCounter < 48; sleepStartCounter++) {
                //Marks slot refenced by sleepCounter and sleepStartCounter as coordinates as unavailable (used)
                slotsFilled[sleepCounter][sleepStartCounter] = true;
            }
            //Loops through the start of the day up to workStart
            for (int sleepEndCounter = 0; sleepEndCounter < workStart; sleepEndCounter++) {
                //Marks slot refenced by sleepCounter and sleepStartCounter as coordinates as unavailable (used)
                slotsFilled[sleepCounter][sleepEndCounter] = true;
            }
        }

        //Retriving all slots already used by a timetable 
        String sql = "SELECT timetableslot.Day, timetableslot.Time\n"
                + "FROM user INNER JOIN (timetableslot INNER JOIN timetable ON (timetableslot.TimetableID = timetable.TimetableID) AND (timetableslot.UserID = timetable.UserID)) ON user.UserID = timetable.UserID\n"
                + "WHERE (((timetableslot.TaskID) Is Null) AND ((user.UserID)=" + User.getUserID() + ") AND ((timetable.TimetableID)=" + timetableID + "));";

        ResultSet rs = DatabaseHandle.query(sql);

        //Marking any slot used by an existing slot as unavailable
        try {
            while (rs.next()) {
                //Marking slots already filled as unavailable
                slotsFilled[rs.getInt("Day") - 1][rs.getInt("Time")] = true;
            }
        } catch (SQLException e) {
        }

        int freeSlots = 0;
        int weekStartDay = 0;
        int currentTime = 0;

        //If firstWeek is true then it sets the start of the week to be the current day and currentTime to the current time
        if (firstWeek) {
            GregorianCalendar calendar = new GregorianCalendar();
            //Setting the start day as today. Modifying the number by calendar to be Monday = 0, Tue = 1, etc
            weekStartDay = ((calendar.get(GregorianCalendar.DAY_OF_WEEK) + 5) % 7);
            //Getting the current time in terms of row address of the table
            currentTime = (int) (this.getCurrentTime() * 2) + 1;
        }

        //Looping through the days of the week
        for (int dayCounter = weekStartDay; dayCounter < 7; dayCounter++) {
            int freeSlotCount = 0;
            //Looping through the times of the day
            for (int slotCounter = 0; slotCounter < 48; slotCounter++) {
                //Checking whether or not the slot is available and that if it is the first week checks whether the slot is in the future and not the past
                if (!slotsFilled[dayCounter][slotCounter] && (!firstWeek || dayCounter > weekStartDay || slotCounter >= currentTime)) {
                    //Incrementing the counter of how many free slots have passed since the last assigned break.
                    freeSlotCount++;
                    //Adding a break every 5th available slot by making that 5th slot unavailable
                    if (freeSlotCount == 5) {
                        //Resetting the counter
                        freeSlotCount = 0;
                        slotsFilled[dayCounter][slotCounter] = true;
                    }
                }
            }
        }

        //Looping throgh the days
        for (int dayCounter = weekStartDay; dayCounter < 7; dayCounter++) {
            //Looping through the times of the day
            for (int timeCounter = workStart; timeCounter < workEnd; timeCounter++) {
                //Checking whether or not the slot is available and that if it is the first week checks whether the slot is in the future and not the past
                if (!slotsFilled[dayCounter][timeCounter] && (currentTime < timeCounter || dayCounter > weekStartDay || !firstWeek)) {
                    freeSlots++;
                }
            }
        }

        //Fetching the day the timetable starts on
        sql = "SELECT timetable.StartDay\n"
                + "FROM timetable INNER JOIN user ON timetable.UserID = user.UserID\n"
                + "WHERE (((timetable.TimetableID)=" + timetableID + ") AND ((user.UserID)=" + User.getUserID() + "));";

        rs = DatabaseHandle.query(sql);

        Date weekStart = null;

        try {
            if (rs.next()) {
                //Setting weekStart to be the date the timetable starts on
                weekStart = rs.getDate("StartDay");
            }
        } catch (SQLException e) {
        }

        int counter = 0;
        Random rand = new Random();
        //Loop continues plotting tasks until all slots are used or all tasks are plotted
        while (!done && freeSlots > 0 && counter < taskList.length) {

            int slotsFreePerTask = freeSlots;
            //Gets the difference in days between today and the task's deadline
            int difference = getDayDifference(weekStart, taskList[counter].getDateDue()) - weekStartDay;
            //If the deadline is in the week of the timetable then slotsFreePerTask is set to the number of available slots before the deadline day
            if (difference < (6 - weekStartDay)) {
                slotsFreePerTask = 0;
                //Looping throught the days of the week until the deadline
                for (int dayCounter = weekStartDay; dayCounter <= weekStartDay + difference; dayCounter++) {
                    //Looping through the working hours of the day
                    for (int timeCounter = workStart; timeCounter < workEnd; timeCounter++) {
                        //Checking whether or not the slot is available and that if it is the first week checks whether the slot is in the future and not the past
                        if (slotsFilled[dayCounter][timeCounter] && (!firstWeek || currentTime < timeCounter || dayCounter > weekStartDay)) {
                            //Counting the number of free slots before the deadline
                            slotsFreePerTask++;
                        }
                    }
                }
            }

            //Calculating the number of slots needed to complete the task
            int slotsNeeded = (int) (taskList[counter].roundToHalf(taskList[counter].getTimeModified()) * 2);

            //Loops while there are still slots to plot and there are slots available for plotting
            while (slotsNeeded > taskList[counter].getSlotsAssigned() && slotsFreePerTask > 0) {

                //Randomly picks a slot by stating it is to be plotted in the nth free slot where n is the random number between 0 and the number of free slots
                int assignedSlot = rand.nextInt(slotsFreePerTask);
                int dayCounter = weekStartDay;
                int freeCounter = 0;
                boolean assigned = false;

                //Loops through the days of the week until 7 is reached or the slot is assigned or the day surpasses the deadline
                while (dayCounter < 7 && dayCounter - weekStartDay < difference && !assigned) {

                    int slotCounter = workStart;
                    //If it is the first week it starts the counter at the current time to ensure no tasks are plotted in the past
                    if (firstWeek && dayCounter == weekStartDay) {
                        slotCounter = currentTime;
                    }

                    //Loops through working hours until slot is assigned or workEnd is reached
                    while (slotCounter < workEnd && !assigned) {

                        //Checks to see if the slot is filled
                        if (!slotsFilled[dayCounter][slotCounter]) {
                            //Checks to see if the slot the loop has reached is the slot assigned to the task
                            if (freeCounter == assignedSlot) {
                                //Adds the task to the slot in the database
                                sql = "INSERT INTO timetableslot (UserID, TaskID, TimetableID, Day, Time) \n"
                                        + "VALUES (" + User.getUserID() + ", " + taskList[counter].getTaskID() + ", "
                                        + timetableID + ", " + (dayCounter + 1) + ", " + slotCounter + ");";

                                DatabaseHandle.update(sql);

                                taskList[counter].newSlotAssigned();
                                //Marks the slot as unavailable
                                assigned = true;
                                //Reduces the number of free slots
                                freeSlots--;
                                slotsFreePerTask--;
                                //Marks this slots as unavailable
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

            //Checks to see if all tasks have all their slots assigned by comparing the number of slots assigend to the slots needed
            for (Task task : taskList) {
                //Retrieving the slots needed for the task
                slotsNeeded = (int) (task.roundToHalf(task.getTimeModified()) * 2);
                //Checking if the task needs more slots than it has currently been assigned
                if (slotsNeeded > task.getSlotsAssigned()) {
                    //Task needs more slots
                    done = false;
                }
            }

            counter++;
        }

        return done;
    }

    /**
     * Returns today's date in the format YYYY-MM-DD
     *
     * @param calendar
     * @return
     */
    private String getDate(GregorianCalendar calendar) {
        //Getting the day of the month
        int day = calendar.get(GregorianCalendar.DATE);
        String date;
        //Makes sure day is a 2 digit number (even if 1st digit is 0)
        if (day <= 9) {
            date = "0" + day;
        } else {
            date = "" + day;
        }
        //Gets the month of the year
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        //Makes sure month is a 2 digit number (even if 1st digit is 0) and adds it to the date string
        if (month <= 9) {
            date = "0" + month + "-" + date;
        } else {
            date = month + "-" + date;
        }
        //Gets the year and adds it to the date string
        int year = calendar.get(GregorianCalendar.YEAR);
        date = year + "-" + date;
        return date;
    }

    /**
     * Returns the date in the format YYYY-MM-DD of the Monday of the week
     * containing the date YYYY-MM-DD
     *
     * @param date
     * @return
     */
    private String getMondayDate(String date) {
        //Parses the year as an integer
        int inputYear = Integer.parseInt(date.substring(0, 4));
        //Parses the month as an integer
        int inputMonth = Integer.parseInt(date.substring(5, 7)) - 1;
        //Parses the day as an integer
        int inputDay = Integer.parseInt(date.substring(8, 10));

        //Creates a calendar for the date passed in
        GregorianCalendar calendar = new GregorianCalendar(inputYear, inputMonth, inputDay);
        //Corrects calendar so it is now the Monday of that week
        calendar.add(GregorianCalendar.DAY_OF_YEAR, -((calendar.get(GregorianCalendar.DAY_OF_WEEK) + 5) % 7));
        //Getting the day, month, and year from the corrected calendar
        int day = calendar.get(GregorianCalendar.DATE);
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        int year = calendar.get(GregorianCalendar.YEAR);

        //Returns SQL syntax date of the Monday
        String monday = year + "-" + month + "-" + day;
        return monday;
    }

    /**
     * Inserts a timetable into the database that starts on the passed in date
     *
     * @param startDate
     * @return
     */
    private int createNewTimetable(String startDate) {
        //Creates a new timetable ID
        int timetableID = DatabaseHandle.createID("timetable", "TimetableID");
        //Inserts the timetable into the database
        String sql = "INSERT INTO `timetable` (`UserID`, `TimetableID`, `StartDay`) VALUES (" + User.getUserID() + ", " + timetableID + ", '" + startDate + "')";
        DatabaseHandle.update(sql);
        //Plots events into the timetable
        plotEvents(timetableID);
        //Returns the ID of the timetable.
        return timetableID;
    }

    //<editor-fold defaultstate="collapsed" desc=" Event Insertion ">
    /**
     * Plots events into the timetable
     *
     * @param timetableID
     */
    private void plotEvents(int timetableID) {
        //Plots recurring events into the given timetable
        plotRecurringEvents(timetableID);
        //Plots single events into the given timetable
        plotSingleEvents(timetableID);
    }

    /**
     * Plots all recurring events into the timetable
     *
     * @param timetableID
     */
    private void plotRecurringEvents(int timetableID) {
        //Fetching the data of the events to plot
        String sql = "SELECT event.EventID, event.Day, event.StartTime, event.EndTime\n"
                + "FROM event INNER JOIN user ON event.UserID = user.UserID\n"
                + "WHERE (((user.UserID)= " + User.getUserID() + ") AND ((event.Date) Is Null) AND ((event.Hidden)=False))\n"
                + "ORDER BY event.Day, event.StartTime;";
        ResultSet rs = DatabaseHandle.query(sql);

        try {
            while (rs.next()) {

                //Extracting the details of each event from the result set
                int day = rs.getInt("Day");
                int startTime = (int) (rs.getFloat("StartTime") * 2);
                int endTime = (int) (rs.getFloat("EndTime") * 2);
                int eventID = rs.getInt("EventID");

                //Looping through all the slots needed by the event
                for (int counter = startTime; counter <= endTime; counter++) {
                    //Inserting the records into the database for the slots the event occupies
                    sql = "INSERT INTO timetableslot (UserID, TimetableID, Day, Time, EventID) "
                            + "VALUES (" + User.getUserID() + ", " + timetableID + ", " + day
                            + ", " + counter + ", " + eventID + ")";
                    DatabaseHandle.update(sql);
                }
            }
        } catch (SQLException e) {
        }

    }

    /**
     * Plots any single events that fall within the week into the timetable
     *
     * @param timetableID
     */
    private void plotSingleEvents(int timetableID) {

        //Selects the start day of a timetable with the given ID
        String sql = "SELECT timetable.StartDay\n"
                + "FROM user INNER JOIN timetable ON user.UserID = timetable.UserID\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((timetable.TimetableID)=" + timetableID + "));";
        ResultSet rs = DatabaseHandle.query(sql);

        //Creates a calendar that is set to the day the passed in timetable starts
        Date startDay = null;
        GregorianCalendar calendar = new GregorianCalendar();
        try {
            rs.next();
            startDay = rs.getDate("StartDay");
        } catch (SQLException e) {
        }

        //Changing the date of the calendar to that of the timetable's start
        calendar.setTime(startDay);
        String date;

        //Looping through each day of the week
        for (int day = 1; day <= 7; day++) {
            //Getting the date in SQL format
            date = calendar.get(GregorianCalendar.YEAR) + "-"
                    + (1 + calendar.get(GregorianCalendar.MONTH)) + "-"
                    + calendar.get(GregorianCalendar.DATE);
            //Selecting all a user's events that are on the date constructed above
            sql = "SELECT event.*\n"
                    + "FROM user INNER JOIN event ON user.UserID = event.UserID\n"
                    + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((event.Date)='" + date + "'))\n"
                    + "ORDER BY event.StartTime;";
            rs = DatabaseHandle.query(sql);

            try {
                while (rs.next()) {

                    //Extracting the details of each event from the result set
                    int startTime = (int) (rs.getFloat("StartTime") * 2);
                    int endTime = (int) (rs.getFloat("EndTime") * 2);
                    int eventID = rs.getInt("EventID");

                    //Looping through all the slots needed by the event
                    for (int slotCounter = startTime; slotCounter <= endTime; slotCounter++) {
                        //Inserting the records into the database for the slots the event occupies
                        sql = "INSERT INTO timetableslot (UserID, TimetableID, Day, Time, EventID) "
                                + "VALUES (" + User.getUserID() + ", " + timetableID + ", " + day
                                + ", " + slotCounter + ", " + eventID + ")";
                        DatabaseHandle.update(sql);
                    }
                }
            } catch (SQLException e) {
            }
            //Adding a day to the calendar
            calendar.add(GregorianCalendar.DAY_OF_YEAR, 1);
        }
    }
    //</editor-fold>

    /**
     * Checks to see if it is possible to plot either all tasks or only high
     * priority tasks
     *
     * @param highPriority
     * @param workEnd
     * @param workStart
     * @return
     */
    private boolean checkTaskAssigningPossible(boolean highPriority, int workEnd, int workStart) {
        String sql;
        GregorianCalendar calendar = new GregorianCalendar();
        //Constructing today's date in SQL format
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
        //Getting the current time
        double currentTime = getCurrentTime();
        boolean possible = true;
        try {
            //Loops through tasks that are being checked and calculates whether there are enough slots to plot the task in before its deadline
            while (taskRS.next() && possible) {
                //Creating a task object with the ID given by the result set.
                Task task = new Task(taskRS.getInt("task.TaskID"));
                //Calculating the number of slots that need to be plotted
                int slotsToPlot = (int) ((task.getTimeModified() - task.getTimeUsed()) * 2);
                //Getting the difference in days between today and the task's deadline
                int dayDifference = getDayDifference(new Date(), task.sqlDateToTextFormat(task.getDateDue()));
                //Calculating the number of slots remaining today
                int totalSlotsRemainingToday = 48 - (int) (currentTime * 2);
                //Calculating the total number of slots before the deadline
                int totalSlotsBeforeDeadline = (dayDifference * 48) + totalSlotsRemainingToday;
                //Calculating the slots used by events
                int slotsUsedByEvents = getSlotsUsedByEventsBeforeDate(dayDifference, workEnd, workStart);
                //Calculating the number of free slots before the deadline
                int slotsAvailableBeforeDeadline = totalSlotsBeforeDeadline - (slotsUsedByEvents + slotsUsedByPreviousTasks);
                //Removing each 5th available slot for breaks
                slotsAvailableBeforeDeadline = ((slotsAvailableBeforeDeadline / 5) * 4) + (slotsAvailableBeforeDeadline % 5);
                //Checks to see if the slots needed is less than or equal to the slots available
                if (slotsAvailableBeforeDeadline >= slotsToPlot) {
                    //Enough slots are availabe so adds the slots used by this task to the running total
                    slotsUsedByPreviousTasks = slotsUsedByPreviousTasks + slotsToPlot;
                } else {
                    //Not enough slots remaining to complete this task
                    possible = false;
                }
            }

        } catch (SQLException e) {
        }

        return possible;
    }

    /**
     * Returns the number of slots used by all events before the deadline
     *
     * @param dayDifference
     * @param workEnd
     * @param workStart
     * @return
     */
    private int getSlotsUsedByEventsBeforeDate(int dayDifference, int workEnd, int workStart) {

        int slotsUsed = 0;

        //Calculating the number of days before the deadline, including today
        int days = dayDifference + 1;
        //Calculating the number of whole weeks before the deadline
        int numberOfWeeks = days / 7;
        //Calculating the remainder of days that aren't in whole weeks before the deadline
        int numberOfExtraDays = days % 7;

        //Get the number of slots that are out of work hours
        int sleepSlotsPerDay = (48 - workEnd) + workStart;

        //Adding the "sleep slots" to the running total of slots used
        slotsUsed = slotsUsed + (sleepSlotsPerDay * days);

        GregorianCalendar cal = new GregorianCalendar();

        //Get slots used by reccuring events second
        String inStatement;
        //Checking if the number of extra days is 0
        if (numberOfExtraDays == 0) {
            //No excess days, only whole weeks so no extra days need to be selected
            inStatement = "NULL";
        } else {
            //Excess days exist so need to fetch the recurring events on those excess days
            //Checking to see if the day of the week is Sunday
            if (cal.get(GregorianCalendar.DAY_OF_WEEK) == 1) {
                //It is Sunday so add 7 to the in statement
                inStatement = "7";
            } else {
                //Isn't Sunday so can add the day of the week returned by the calendar minus 1 to the in statement
                inStatement = "" + (cal.get(GregorianCalendar.DAY_OF_WEEK) - 1);
            }
            //Looping through the excess days
            for (int count = 0; count < numberOfExtraDays - 1; count++) {
                //Adding a day to the calendar
                cal.add(GregorianCalendar.DAY_OF_WEEK, 1);
                //Checking to see if the day of the week is Sunday
                if (cal.get(GregorianCalendar.DAY_OF_WEEK) == 1) {
                    //It is Sunday so add 7 to the in statement
                    inStatement = inStatement + ",7";
                } else {
                    //Isn't Sunday so can add the day of the week returned by the calendar minus 1 to the in statement
                    inStatement = inStatement + "," + (cal.get(GregorianCalendar.DAY_OF_WEEK) - 1);
                }
            }
            //Revert the calendar to today's date
            cal.add(GregorianCalendar.DAY_OF_WEEK, -(numberOfExtraDays - 1));
        }
        //Construct an SQL statement to select the recurring events on the days specified in the in statement created above
        String sql = "SELECT StartTime, EndTime\n"
                + "FROM event INNER JOIN user ON event.UserID = user.UserID\n"
                + "WHERE (((event.Date) Is Null) AND ((user.UserID)=" + User.getUserID() + ") AND ((event.Day) In (" + inStatement + ")));";

        ResultSet rs = DatabaseHandle.query(sql);

        //Reserving slots for events
        try {
            //Looping through each event returned in the result set
            while (rs.next()) {
                int counter = rs.getInt("StartTime");
                //Makes sure only counting slots within the working hours
                if (counter < workStart) {
                    counter = workStart;
                }
                int endTime = rs.getInt("EndTime");
                //Loops through times until the end time is reached or workEnd is reached
                while (counter < endTime && counter < workEnd) {
                    //Incriments the slots used and the counter
                    slotsUsed++;
                    counter++;
                }
            }
        } catch (SQLException e) {
        }

        //Selecting weeks worth of repeated tasks
        sql = "SELECT StartTime, EndTime\n"
                + "FROM event INNER JOIN user ON event.UserID = user.UserID\n"
                + "WHERE (((event.Date) Is Null) AND ((user.UserID)=" + User.getUserID() + "));";

        rs = DatabaseHandle.query(sql);

        //Reserving slot for events for each week
        try {
            while (rs.next()) {
                int startTime = rs.getInt("StartTime");
                int endTime = rs.getInt("EndTime");
                //Makes sure only counting slots within the working hours
                if (startTime < workStart) {
                    startTime = workStart;
                }
                //Looping through for each week
                for (int weekCounter = 0; weekCounter < numberOfWeeks; weekCounter++) {
                    //Resetting the counter
                    int counter = startTime;
                    //Loops through times until the end time is reached or workEnd is reached
                    while (counter < endTime && counter < workEnd) {
                        //Incriments the slots used and the counter
                        slotsUsed++;
                        counter++;
                    }
                }
            }
        } catch (SQLException e) {
        }

        //Get slots used by single events third
        String todayDate = getDate(cal);

        //Setting the calendar to be dayDifference number of days in the future
        cal.add(GregorianCalendar.DAY_OF_MONTH, dayDifference);

        //Getting the date of the calendar
        String endDate = cal.get(GregorianCalendar.YEAR) + "-"
                + cal.get(GregorianCalendar.MONTH) + "-"
                + cal.get(GregorianCalendar.DAY_OF_MONTH);

        //Select single events that occur before now and the deadline
        sql = "SELECT StartTime, EndTime\n"
                + "FROM event INNER JOIN user ON event.UserID = user.UserID\n"
                + "WHERE (((event.Day) Is Null) AND ((user.UserID)=" + User.getUserID() + ") AND ((event.Date) Between (CAST('" + todayDate + "' AS DATE)) And (CAST('" + endDate + "' AS DATE))));";

        rs = DatabaseHandle.query(sql);

        try {
            while (rs.next()) {
                int counter = rs.getInt("StartTime");
                //Makes sure only counting slots within the working hours
                if (counter < workStart) {
                    counter = workStart;
                }
                int endTime = rs.getInt("EndTime");
                //Loops through times until the end time is reached or workEnd is reached
                while (counter < endTime && counter < workEnd) {
                    //Incriments the slots used and the counter
                    slotsUsed++;
                    counter++;
                }
            }
        } catch (SQLException e) {
        }

        //Take away events already passed today
        //Getting the current time
        int currentTime = (int) (getCurrentTime() * 2);
        //Getting the day number of today
        int day = 1 + ((new GregorianCalendar().get(GregorianCalendar.DAY_OF_WEEK) + 5) % 7);
        //Getting any event that occurs today
        sql = "SELECT event.StartTime, event.EndTime\n"
                + "FROM event INNER JOIN user ON event.UserID = user.UserID\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((event.Hidden) = True) AND ((event.StartTime)<" + currentTime + ") AND (((event.Day)=" + day + ") OR ((event.Date)='" + todayDate + "')));";

        rs = DatabaseHandle.query(sql);
        try {
            while (rs.next()) {
                //Removing slots of events from "slots used" total from slots that already happened this week
                int startTime = (int) (Double.parseDouble(rs.getString("StartTime")) * 2);
                int endTime = (int) (Double.parseDouble(rs.getString("EndTime")) * 2);
                //Checking to see if event hasn't ended yet
                if (endTime > currentTime) {
                    //Taking away time between the current time and the beginning of the event from slots used
                    slotsUsed = slotsUsed - (currentTime - startTime);
                } else {
                    //Taking away the time between the end and start of the event from slots used
                    slotsUsed = slotsUsed - (endTime - startTime);
                }
            }
        } catch (SQLException e) {
        }

        //Checking to see if the current time is greater than the time work starts
        if (currentTime > workStart) {
            //If so removing the work hours that have already passed from slots used
            slotsUsed = slotsUsed - workStart;
            //Checking to see if the current time is greater than the time work ends
            if (currentTime > workEnd) {
                //Removing the difference between the current time and the end of work from slots used
                slotsUsed = slotsUsed - (currentTime - workEnd);
            }
        } else {
            //Removing the current time from the slots used
            slotsUsed = slotsUsed - currentTime;
        }
        return slotsUsed;
    }

    /**
     * Calculates the number of days between the passed in dates
     *
     * @param startDate
     * @param endDate
     * @return
     */
    private int getDayDifference(Date startDate, String endDate) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        //Checks if date is in SQL format
        if (endDate.charAt(4) == '-') {
            //Converts SQL format to dd/mm/yyyy
            endDate = endDate.substring(8, 10) + "/" + endDate.substring(5, 7) + "/" + endDate.substring(0, 4);
        }
        
        GregorianCalendar calendar = new GregorianCalendar(); 
        //Sets the calendar to the startDate
        calendar.setTime(startDate);
        //Retrieving the time in milliseconds of the calendar
        long before = calendar.getTimeInMillis();
        try {
            //Setting the time of the calendar to be the end date
            calendar.setTime(df.parse(endDate));
        } catch (ParseException e) {
        }
        //Getting the time in milliseconds of the end date
        long after = calendar.getTimeInMillis();
        //Calculates the difference in milliseconds and converts to days.
        long difference = after - before;
        //converting milliseconds to days
        int dayDiffernce = (int) (difference / (24 * 1000 * 60 * 60));
        return dayDiffernce;
    }

    /**
     * Returns current time in hours rounded down to the nearest half hour
     *
     * @return
     */
    private double getCurrentTime() {
        //Creating a calendar
        GregorianCalendar calendar = new GregorianCalendar();
        //Getting the hour of the day
        double time = calendar.get(GregorianCalendar.HOUR_OF_DAY);
        //Getting the minutes of the time rounded down to the closest half hour
        if (calendar.get(GregorianCalendar.MINUTE) >= 30) {
            //Minutes are greater than or equal to 30 therefore must a half hour must be added to the whole hour
            time = time + 0.5;
        }
        return time;
    }
}
