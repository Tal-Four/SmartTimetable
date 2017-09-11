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

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        //Cells are by default rendered as a JLabel.
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        if (value != null && col != 0) {
            String sql = "SELECT task.Colour FROM task WHERE task.Name = '" + value + "'";
            ResultSet rs = DatabaseHandle.query(sql);
            int colourCode = 0;
            try {
                if (rs.next()) {
                    colourCode = rs.getInt("task.Colour");
                    cell.setBackground(new Color(colourCode));
                    cell.setForeground(Color.BLACK);
                } else {
                    cell.setBackground(Color.WHITE);
                    cell.setForeground(Color.RED);
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
