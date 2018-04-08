package smarttimetable;

import javax.swing.table.DefaultTableModel;

/**
 * A class that extends DefaultTableModel and is used to store the data of
 * JTable in Timetable.
 *
 * @author AdamPlatt
 */
public class CustomTableModel extends DefaultTableModel {

    //The data to be displayed in the table
    private Object[][] data;
    //The headers of the table
    private String[] headers;

    /**
     * Sets up a blank table
     */
    public CustomTableModel() {
        //Setting the headers
        this.headers = new String[]{
            "Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
        };
        //Initialising an empty table
        this.clear();
    }

    /**
     * Empties the data from the table except first column
     */
    public final void clear() {
        //Setting the data array to only contain time values
        this.data = new Object[][]{
            {"00:00", null, null, null, null, null, null, null},
            {"00:30", null, null, null, null, null, null, null},
            {"01:00", null, null, null, null, null, null, null},
            {"01:30", null, null, null, null, null, null, null},
            {"02:00", null, null, null, null, null, null, null},
            {"02:30", null, null, null, null, null, null, null},
            {"03:00", null, null, null, null, null, null, null},
            {"03:30", null, null, null, null, null, null, null},
            {"04:00", null, null, null, null, null, null, null},
            {"04:30", null, null, null, null, null, null, null},
            {"05:00", null, null, null, null, null, null, null},
            {"05:30", null, null, null, null, null, null, null},
            {"06:00", null, null, null, null, null, null, null},
            {"06:30", null, null, null, null, null, null, null},
            {"07:00", null, null, null, null, null, null, null},
            {"07:30", null, null, null, null, null, null, null},
            {"08:00", null, null, null, null, null, null, null},
            {"08:30", null, null, null, null, null, null, null},
            {"09:00", null, null, null, null, null, null, null},
            {"09:30", null, null, null, null, null, null, null},
            {"10:00", null, null, null, null, null, null, null},
            {"10:30", null, null, null, null, null, null, null},
            {"11:00", null, null, null, null, null, null, null},
            {"11:30", null, null, null, null, null, null, null},
            {"12:00", null, null, null, null, null, null, null},
            {"12:30", null, null, null, null, null, null, null},
            {"13:00", null, null, null, null, null, null, null},
            {"13:30", null, null, null, null, null, null, null},
            {"14:00", null, null, null, null, null, null, null},
            {"14:30", null, null, null, null, null, null, null},
            {"15:00", null, null, null, null, null, null, null},
            {"15:30", null, null, null, null, null, null, null},
            {"16:00", null, null, null, null, null, null, null},
            {"16:30", null, null, null, null, null, null, null},
            {"17:00", null, null, null, null, null, null, null},
            {"17:30", null, null, null, null, null, null, null},
            {"18:00", null, null, null, null, null, null, null},
            {"18:30", null, null, null, null, null, null, null},
            {"19:00", null, null, null, null, null, null, null},
            {"19:30", null, null, null, null, null, null, null},
            {"20:00", null, null, null, null, null, null, null},
            {"20:30", null, null, null, null, null, null, null},
            {"21:00", null, null, null, null, null, null, null},
            {"21:30", null, null, null, null, null, null, null},
            {"22:00", null, null, null, null, null, null, null},
            {"22:30", null, null, null, null, null, null, null},
            {"23:00", null, null, null, null, null, null, null},
            {"23:30", null, null, null, null, null, null, null}
        };
        //Loading the data and headers into the table
        setDataVector(this.data, this.headers);
    }

    /**
     * Sets the table to be uneditable
     *
     * @param row
     * @param column
     * @return
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    //Getters and setters
    public void setData(Object[][] data) {
        this.data = data;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public Object[][] getData() {
        return data;
    }

    public String[] getHeaders() {
        return headers;
    }
}
