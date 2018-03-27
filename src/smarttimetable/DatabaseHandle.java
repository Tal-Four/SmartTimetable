package smarttimetable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Adam-PC
 */
public class DatabaseHandle {

    //Sets variables for connection
    private static Connection connection;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/smarttimetabledb?useSSL=false";

    /**
     * Connects the program to the database
     *
     */
    public static void connect() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            new Popup("Couldn't connect to the database. Please try again.").setVisible(true);
        }
    }

    /**
     * Runs an query with the given SQL that returns a result set
     *
     * @param sql
     * @return
     */
    public static ResultSet query(String sql) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.err.println("Query failed: " + e);
        }
        return null;
    }

    /**
     * Runs an update with the given SQL that returns a result set
     *
     * @param sql
     * @return
     */
    public static int update(String sql) {
        try {
            Statement stmt = connection.createStatement();
            int rows = stmt.executeUpdate(sql);
            return rows;
        } catch (SQLException e) {
            System.err.println("Update failed: " + e);
        }
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
        String sql = "SELECT " + idColumnName + " FROM " + table + " WHERE UserID = " + User.getUserID() + " ORDER BY " + idColumnName;
        ResultSet rs = query(sql);
        try {
            //Loops through results until it finds an ID not being used
            do {
                newID++;
            } while (rs.next() && rs.getInt(idColumnName) == newID);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return newID;
    }
}
