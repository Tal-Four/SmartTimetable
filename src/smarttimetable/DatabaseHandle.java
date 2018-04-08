package smarttimetable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A static class used to handle any database interactions in the program, these
 * consist of SQL updates and SQL queries. The class is static so any other
 * class can access the methods in the class without having to create an object
 * of it.
 *
 * @author AdamPlatt
 */
public class DatabaseHandle {

    //The connection to the database
    private static Connection connection;
    //The username the program uses to connect to the database
    private static final String USERNAME = "timetableuser";
    //The password used to access the database
    private static final String PASSWORD = "timetablepassword";
    //The address of the database the program attempts to connect to
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/smarttimetabledb?useSSL=false";

    /**
     * Attempts to connect the program to the database if there is no connection
     *
     * @return
     */
    private static boolean connect() {
        boolean connected = true;
        try {
            //Checks to see if database is not connected
            if (connection == null || !connection.isValid(1)) {
                try {
                    //Connects to the database
                    connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
                    connected = true;
                } catch (SQLException e) {
                    connected = false;
                }
            }
        } catch (SQLException e) {
            connected = false;
        }
        return connected;
    }

    /**
     * Runs an query with the given SQL that returns a result set
     *
     * @param sql
     * @return
     */
    public static ResultSet query(String sql) {
        //Checks to see if connected and if not attempts to connect
        boolean connected = connect();
        //If connected to the database it runs the query, otherwise it creates an error window
        if (connected) {
            try {
                //Executing the query
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //Returning the results of the query
                return rs;
            } catch (SQLException e) {
                new Popup("An error occured while interacting with the database, please try again.").setVisible(true);
            }
        } else {
            new Popup("Couldn't connect to the database. Please try again.").setVisible(true);
        }
        //A return of null indicates to the program that the database couldn't be connected to
        return null;
    }

    /**
     * Runs an update with the given SQL that returns a result set
     *
     * @param sql
     * @return
     */
    public static int update(String sql) {
        //Checks to see if connected and if not attempts to connect
        boolean connected = connect();
        //If connected to the database it runs the query, otherwise it creates an error window
        if (connected) {
            try {
                //Executing the update
                Statement stmt = connection.createStatement();
                int rows = stmt.executeUpdate(sql);
                //Returning the number of rows affected by the SQL
                return rows;
            } catch (SQLException e) {
                new Popup("An error occured while interacting with the database, please try again.").setVisible(true);
            }
        } else {
            new Popup("Couldn't connect to the database. Please try again.").setVisible(true);
        }
        //A return of null indicates to the program that the database couldn't be connected to
        return 0;
    }

    /**
     * Creates an ID for the specified table
     *
     * @param table
     * @param idColumnName
     * @return
     */
    public static int createID(String table, String idColumnName) {
        int newID = 0;
        //Selects all the IDs for the table
        String sql = "SELECT " + table + "." + idColumnName + "\n"
                + "FROM user INNER JOIN " + table + " ON user.UserID = " + table + ".UserID\n"
                + "WHERE (((user.UserID)=" + User.getUserID() + "));";
        ResultSet rs = query(sql);
        try {
            //Loops through results until it finds an ID not being used
            do {
                newID++;
            } while (rs.next() && rs.getInt(idColumnName) == newID);
        } catch (SQLException e) {
        }
        //Returns the ID to the line that called the method
        return newID;
    }
}
