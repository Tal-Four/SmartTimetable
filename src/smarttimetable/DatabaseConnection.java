package smarttimetable;

import java.sql.*;

/**
 *
 * @author Adam-PC
 */
public class DatabaseConnection {

    private Connection connection;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/smarttimetabledb";

    //Constructor
    public DatabaseConnection() {

    }

    public void connect() {

        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
            System.out.println("Database succesfully connected.");
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e);
        }
    }

}
