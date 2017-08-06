package smarttimetable;

/**
 *
 * @author Adam-PC
 */
public class SmartTimetable {

    /**
     * @param args the command line arguments
     */
    static DatabaseHandle dbHandle = new DatabaseHandle();

    public static void main(String[] args) {

        //Connects to the database
        dbHandle.connect();

        new Login().setVisible(true);

    }

}
