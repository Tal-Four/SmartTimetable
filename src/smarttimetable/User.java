package smarttimetable;

public class User {

    private static int userID;
    private static String username, password;

    //Constructer never used as static doesn't need to be initialisd
    private User() {
    }

    //Changes the variables to the variables provided
    static public void newUser(int userID, String username, String password) {
        User.userID = userID;
        User.username = username;
        User.password = password;
    }

    //Changes the variables to null
    static public void logoutUser() {
        User.userID = 0;
        User.username = null;
        User.password = null;
    }

    //<editor-fold defaultstate="collapsed" desc=" Getters & Setters ">
    static int getUserID() {
        return User.userID;
    }

    static String getUsername() {
        return User.username;
    }

    static String getPassword() {
        return User.password;
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
    //</editor-fold>

}
