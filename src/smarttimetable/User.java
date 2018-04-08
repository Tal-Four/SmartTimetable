package smarttimetable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A static class used to store the details of the currently logged in user. It
 * is static as only one user can be logged in at once and so other classes can
 * retrieve details about the user without having to create an object of the
 * class.
 *
 * @author AdamPlatt
 */
public class User {

    //The ID of the user's accout
    private static int userID;
    //The username, password, security question, and security answer of the user's account
    private static String username, password, question, answer;

    /**
     * Creates a new User record and sets the variables to the variables
     * provided
     *
     * @param username
     * @param password
     * @param question
     * @param answer
     */
    public static void createNewUser(String username, String password, String question, String answer) {
        User.username = username;
        User.password = password;
        User.question = question;
        User.answer = answer;

        String sql = "SELECT * FROM user ORDER BY UserID";
        ResultSet rs = DatabaseHandle.query(sql);
        //Checking the SQL executed correctly
        if (rs != null) {
            int newUserID = 0;
            try {
                //Looping through existing IDs until a ID without an record is found
                do {
                    newUserID++;
                } while (rs.next() && rs.getInt("UserID") == newUserID);
            } catch (SQLException e) {
            }

            User.userID = newUserID;

            //Checking if the user has no password
            if (User.password == null) {
                //Inserting a user record with no password
                sql = "INSERT INTO `user` (`UserID`, `Username`, `Question`, `Answer`) "
                        + "VALUES(" + User.userID + ", '" + User.username + "', " + User.question + "', '" + User.answer + "')";
            } else {
                //Inserting a user record with a password
                sql = "INSERT INTO `user` (`UserID`, `Username`,`Password`, `Question`, `Answer`) "
                        + "VALUES(" + User.userID + ", '" + User.username + "', '" + User.password + "', '" + User.question + "', '" + User.answer + "')";
            }

            //Adding record to database
            DatabaseHandle.update(sql);
        }
    }

    /**
     * Loads that user's details based on the username
     *
     * @param username
     */
    public static void loadUser(String username) {
        //Selecting the user's details
        String sql = "SELECT * FROM user WHERE Username = '" + username + "';";
        User.username = username;
        ResultSet rs = DatabaseHandle.query(sql);
        try {
            if (rs.next()) {
                //Loading the variables from the result set
                User.answer = rs.getString("Answer");
                User.question = rs.getString("Question");
                User.userID = rs.getInt("UserID");
                User.password = rs.getString("Password");
            } else {
                new Popup("User not found.").setVisible(true);
            }
        } catch (SQLException e) {
        }
    }

    /**
     * Loads that user's details based on the userID
     *
     * @param userID
     */
    public static void loadUser(int userID) {
        //Selecting the user's details
        String sql = "SELECT * FROM user WHERE UserID = " + userID;
        User.userID = userID;
        ResultSet rs = DatabaseHandle.query(sql);
        try {
            if (rs.next()) {
                //Loading the variables from the result set
                User.answer = rs.getString("Answer");
                User.question = rs.getString("Question");
                User.username = rs.getString("Username");
                User.password = rs.getString("Password");
            } else {
                new Popup("User not found.").setVisible(true);
            }
        } catch (SQLException e) {
        }
    }

    /**
     * Changes the variables to null
     *
     */
    public static void logoutUser() {
        User.userID = 0;
        User.username = null;
        User.password = null;
        User.answer = null;
        User.question = null;
    }

    //<editor-fold defaultstate="collapsed" desc=" Getters & Setters ">
    static int getUserID() {
        return User.userID;
    }

    public static String getUsername() {
        return User.username;
    }

    public static String getPassword() {
        return User.password;
    }

    public static String getQuestion() {
        return question;
    }

    public static String getAnswer() {
        return answer;
    }

    public static void setUserID(int userID) {
        User.userID = userID;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static void setQuestion(String question) {
        User.question = question;
    }

    public static void setAnswer(String answer) {
        User.answer = answer;
    }
    //</editor-fold>
}
