package smarttimetable;

import java.sql.*;

/**
 *
 * @author Adam-PC
 */
public class DatabaseHandle {

    private static Connection connection;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/smarttimetabledb";

    //Constructor
    private DatabaseHandle() {
    }

    //Connects the program to the database
    public static void connect() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
            System.out.println("Database succesfully connected.");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e);
        }
    }
    
    //Runs an SQL query that returns a result set
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

    //Runs an SQL update
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
}
