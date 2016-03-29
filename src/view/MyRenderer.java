package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;

public class MyRenderer extends DefaultTableCellRenderer {

    /**
     * 
     */
    private static final long serialVersionUID = 6439423550121913327L;
    
    private JTextPane cell = new JTextPane();
    //considerare cell all'interno di un JScrollPane
    
    @Override
    public Component getTableCellRendererComponent(final JTable table,
            final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        
        cell.setText("Stringa di prova");//acchiappare il valore del determinato oggetto della lista passata al model
        cell.setBackground(Color.GREEN);//acchiappare il colore del determinato oggetto della lista passata al model
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
