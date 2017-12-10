package smarttimetable;

import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author crazy
 */
public class CustomRenderer extends DefaultTableCellRenderer {

    private int timetableID;

    public CustomRenderer(int timetableID) {
        this.timetableID = timetableID;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        //Cells are by default rendered as a JLabel.
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        if (value != null && col != 0 && timetableID != 0) {
            String sql = "SELECT task.Colour\n"
                    + "FROM task INNER JOIN ((timetableslot INNER JOIN user ON timetableslot.UserID = user.UserID) INNER JOIN timetable ON (timetableslot.TimetableID = timetable.TimetableID) AND (timetable.UserID = user.UserID) AND (timetableslot.UserID = timetable.UserID)) ON (task.UserID = timetableslot.UserID) AND (task.TaskID = timetableslot.TaskID) AND (task.UserID = user.UserID)\n"
                    + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((timetable.TimetableID)=" + this.timetableID + ") AND ((timetableslot.Day)=" + col + ") AND ((timetableslot.Time)=" + row + "));";
            ResultSet rs = DatabaseHandle.query(sql);
            try {
                if (rs.next()) {
                    Color background = new Color(rs.getInt("task.Colour"));
                    cell.setBackground(background);

                    float[] hsb = Color.RGBtoHSB(background.getRed(), background.getGreen(), background.getBlue(), null);
                    float brightness = hsb[2];

                    if (brightness < 0.5) {
                        cell.setForeground(Color.WHITE);
                    } else {
                        cell.setForeground(Color.BLACK);
                    }
                } else {
                    sql = "SELECT event.Colour\n"
                            + "FROM (timetableslot INNER JOIN (event INNER JOIN user ON event.UserID = user.UserID) ON (timetableslot.UserID = user.UserID) AND (timetableslot.UserID = event.UserID) AND (timetableslot.EventID = event.EventID)) INNER JOIN timetable ON (timetableslot.TimetableID = timetable.TimetableID) AND (timetable.UserID = user.UserID) AND (timetableslot.UserID = timetable.UserID)\n"
                            + "WHERE (((user.UserID)=" + User.getUserID() + ") AND ((timetable.TimetableID)=" + this.timetableID + ") AND ((timetableslot.Day)=" + col + ") AND ((timetableslot.Time)=" + row + "));";
                    rs = DatabaseHandle.query(sql);
                    if (rs.next()) {
                        Color background = new Color(rs.getInt("event.Colour"));
                        cell.setBackground(background);

                        float[] hsb = Color.RGBtoHSB(background.getRed(), background.getGreen(), background.getBlue(), null);
                        float brightness = hsb[2];

                        if (brightness < 0.5) {
                            cell.setForeground(Color.WHITE);
                        } else {
                            cell.setForeground(Color.BLACK);
                        }

                    } else {
                        cell.setBackground(Color.WHITE);
                        cell.setForeground(Color.RED);
                    }
                }

            } catch (Exception ex) {
                System.err.println(ex);
            }
        } else {
            cell.setBackground(Color.WHITE);
            cell.setForeground(Color.BLACK);
        }
        //Return the JLabel which renders the cell.
        return cell;

    }
}
