
package smarttimetable;

/**
 *
 * @author Adam-PC
 */
public class SmartTimetable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DatabaseConnection dbConnect = new DatabaseConnection();
        
        dbConnect.connect();
    }
    
}
