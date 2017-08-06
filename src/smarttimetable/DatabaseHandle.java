package smarttimetable;

import java.sql.*;

/**
 *z
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

    //Method called when an account is created
    public static boolean createAccount(String username, String password, String passwordConfirm) {

        if (password.equals(passwordConfirm)) {
            
            //Checking to see if record in database has the same username
            String sql = "SELECT * FROM user WHERE Username = '" + username + "'";
            ResultSet rs = query(sql);
            String UsernameCheck = null;
            try {
                rs.next();
                UsernameCheck = rs.getString("Username").toLowerCase();
            } catch (SQLException e) {
                System.err.println(e);
            }
            
            if (!username.equals(UsernameCheck)) {
                //Valid username
                sql = "SELECT * FROM user";
                rs = query(sql);
                int id = 0;
                try {
                    do {
                        id++;
                        rs.next();
                    } while (rs.getInt("UserID") == id);
                } catch (SQLException e) {
                    System.err.println(e);
                }
                
                if (password == null) {
                    sql = "INSERT INTO smarttimetabledb.`user` (`UserID`, `Username`, `Password`) VALUES(" + id + ", '" + username + "'  , NULL)";
                } else { 
                    sql = "INSERT INTO smarttimetabledb.`user` (`UserID`, `Username`,`Password`) VALUES(" + id + ", '" + username + "'  , '" + password + "')";
                }
                
                update(sql);
                
                return true;
            } else {
                //Username taken
                System.err.println("Username taken");
            }
        } else {
            //Passwords don't match
            System.err.println("Passwords do not match");
        }
        return false;
    }

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
