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

    //Connects the program to the database
    private static boolean connect() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
            return true;
        } catch (SQLException e) {
            new Popup("Couldn't connect to the database. Please try again.").setVisible(true);
        }
        return false;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    //Runs an query with the given SQL that returns a result set
    public static ResultSet query(String sql) {
        if (connect()) {
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                return rs;
            } catch (SQLException e) {
                System.err.println("Query failed: " + e);
            }
        }
        disconnect();
        return null;
    }

    //Runs an update with the given SQL that returns a result set
    public static int update(String sql) {
        if (connect()) {
            try {
                Statement stmt = connection.createStatement();
                int rows = stmt.executeUpdate(sql);
                disconnect();
                return rows;
            } catch (SQLException e) {
                System.err.println("Update failed: " + e);
            }
        }
        disconnect();
        return 0;
    }

    //Creates an ID for the specified table
    public static int createID(String table, String idColumnName) {
        int newID = 0;
        String sql = "SELECT " + idColumnName + " FROM " + table + " WHERE UserID = " + User.getUserID() + " ORDER BY " + idColumnName;
        ResultSet rs = query(sql);
        try {
            do {
                newID++;
            } while (rs.next() && rs.getInt(idColumnName) == newID);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return newID;
    }
}
