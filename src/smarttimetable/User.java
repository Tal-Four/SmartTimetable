package smarttimetable;

public class User {

    private static int userID;
    private static String username, password;

    private User() {
    }

    static public void newUser(int userID, String username, String password){
        User.userID = userID;
        User.username = username;
        User.password = password;
    } 
    
    static public void logoutUser(){
        User.userID = 0;
        User.username = null;
        User.password = null;
    }

    static int getUserID() {
        return User.userID;
    }
    
    static String getUsername() {
        return User.username;
    }

    static String getPassword() {
        return User.password;
    }

}
