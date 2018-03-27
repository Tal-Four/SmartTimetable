package smarttimetable;

import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author crazy
 */
public class CustomRenderer extends DefaultTableCellRenderer {

    private final int timetableID;

    /**
     * Creates a CustomRenderer for the given timetable
     *
     * @param timetableID
     */
    public CustomRenderer(int timetableID) {
        this.timetableID = timetableID;
    }

    /**
     * Renders the cells of the table.
     *
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param col
     * @return
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        //Cells are by default rendered as a JLabel.
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        //Checking that the cell is not empty or belongs to the time column and that a timetable has been selected
        if (value != null && col != 0 && timetableID != 0) {
            //Attempting to get the colour of a task
            String sql = "SELECT task.Colour\n"
                    + "FROM task INNER JOIN ((timetableslot INNER JOIN user ON timetableslot.UserID = user.UserID) INNER JOIN timetable ON (timetableslot.TimetableID = timetable.TimetableID) AND (timetable.UserID = user.UserID) AND (timetableslot.UserID = timetable.UserID)) ON (task.UserID = timetableslot.UserID) AND (task.TaskID = timetableslot.TaskID) AND (task.UserID = user.UserID)\n"
                    + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((timetable.TimetableID)=" + this.timetableID + ") AND ((timetableslot.Day)=" + col + ") AND ((timetableslot.Time)=" + row + "));";
            ResultSet rs = DatabaseHandle.query(sql);
            try {
                if (rs.next()) {
                    //Setting the background colour of the cell
                    Color background = new Color(rs.getInt("task.Colour"));
                    cell.setBackground(background);

                    //Getting the brightness value of the background
                    float[] hsb = Color.RGBtoHSB(background.getRed(), background.getGreen(), background.getBlue(), null);
                    float brightness = hsb[2];

                    //Setting the colour of the text depending on the brightness
                    if (brightness < 0.5) {
                        cell.setForeground(Color.WHITE);
                    } else {
                        cell.setForeground(Color.BLACK);
                    }
                } else {
                    //Couldn't find task in that slot, now trying to find colour of event
                    sql = "SELECT event.Colour\n"
                            + "FROM (timetableslot INNER JOIN (event INNER JOIN user ON event.UserID = user.UserID) ON (timetableslot.UserID = user.UserID) AND (timetableslot.UserID = event.UserID) AND (timetableslot.EventID = event.EventID)) INNER JOIN timetable ON (timetableslot.TimetableID = timetable.TimetableID) AND (timetable.UserID = user.UserID) AND (timetableslot.UserID = timetable.UserID)\n"
                            + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((timetable.TimetableID)=" + this.timetableID + ") AND ((timetableslot.Day)=" + col + ") AND ((timetableslot.Time)=" + row + "));";
                    rs = DatabaseHandle.query(sql);
                    if (rs.next()) {
                        //Setting the background colour of the cell
                        Color background = new Color(rs.getInt("event.Colour"));
                        cell.setBackground(background);

                        //Getting the brightness value of the background
                        float[] hsb = Color.RGBtoHSB(background.getRed(), background.getGreen(), background.getBlue(), null);
                        float brightness = hsb[2];

                        //Setting the colour of the text depending on the brightness
                        if (brightness < 0.5) {
                            cell.setForeground(Color.WHITE);
                        } else {
                            cell.setForeground(Color.BLACK);
                        }

                    } else {
                        //Couldn't find either event or task in that slot so setting the text to red to show an error has occured
                        cell.setBackground(Color.WHITE);
                        cell.setForeground(Color.RED);
                    }
                }

            } catch (SQLException e) {
                System.err.println(e);
            }

        } else {
            //Slot is either empty, or time, or a timetable has not been selected
            cell.setBackground(Color.WHITE);
            cell.setForeground(Color.BLACK);
        }
        //Return the JLabel which renders the cell.
        return cell;

    }
}
