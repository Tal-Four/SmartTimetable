package smarttimetable;

/**
 *
 * @author Adam-PC
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        
        //Connects to the database
        DatabaseHandle.connect();

        //Brings up the first GUI element
        new Login().setVisible(true);
    }

}
