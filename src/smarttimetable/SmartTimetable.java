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

        //Connects to the database
        DatabaseHandle.connect();

        new Login().setVisible(true);

    }

}
