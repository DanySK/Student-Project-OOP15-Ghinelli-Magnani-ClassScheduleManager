package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;

import model.Lesson;
import view.utility.ColorUtility;

public class MyRenderer extends DefaultTableCellRenderer {

    /**
     * 
     */
    private static final long serialVersionUID = 6439423550121913327L;
    
    private final JTextPane cell = new JTextPane();
    
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        if (value instanceof Lesson) {
            cell.setText(((Lesson) value).getSubject().getName() + System.getProperties().getProperty("line.separator") + ((Lesson) value).getProfessor().getName());
            ColorUtility.getColorsByYear().forEach(x -> {
                if (x.getX().equals(((Lesson) value).getSubject().getYear())) {
                    cell.setBackground(x.getY());
                }
            });
        } else {
            cell.setText(value.toString());
            cell.setBackground(Color.WHITE);
        }
        
        final int h = cell.getPreferredSize().height;
        final int w = cell.getPreferredSize().width;
        
        if (table.getColumnModel().getColumn(column).getWidth() < w) {
            table.getColumnModel().getColumn(column).setPreferredWidth(w + Character.SIZE);
        }
        
        if (table.getRowHeight(row) < h) { 
            table.setRowHeight(row, h);
        }
        if (isSelected) {
            cell.setBackground(Color.WHITE);
        }
        return this.cell;
    }

}
