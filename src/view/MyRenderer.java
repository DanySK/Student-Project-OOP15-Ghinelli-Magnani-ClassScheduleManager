package view;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;

import model.Lesson;

public class MyRenderer extends DefaultTableCellRenderer {

    /**
     * 
     */
    private static final long serialVersionUID = 6439423550121913327L;
    
    private final JTextPane cell = new JTextPane();
    //considerare cell all'interno di un JScrollPane
    
    @Override
    public Component getTableCellRendererComponent(final JTable table,
            final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        if (value instanceof Lesson) {
            cell.setText(((Lesson) value).getSubject().getName());
            //cell.setBackground(((Lesson) value));
        } else {
            cell.setText(value.toString());
        }
        
        final int h = cell.getPreferredSize().height;
        final int w = cell.getPreferredSize().width;
        
        if (table.getColumnModel().getColumn(column).getWidth() < w) {
            table.getColumnModel().getColumn(column).setPreferredWidth(w + Character.SIZE);
        }
        
        if (table.getRowHeight(row) < h) { 
            table.setRowHeight(row, h);
        }
        
        return this.cell;
    }

}
