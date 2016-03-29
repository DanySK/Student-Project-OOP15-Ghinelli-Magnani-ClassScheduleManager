package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextPane;
import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    /*la lista presente qui contiene tutti gli elementi della tabella,
     *  compresi gli spazi bianchi(null tipo), il renderer si occuperà di farli apparire come dovrebbero
     */
    /**
     * 
     */
    private static final long serialVersionUID = 9137709835141518432L;
    private List<Object> base = new ArrayList<>();
    private static final int COL_NUM = 10;

    @Override
    public int getColumnCount() {
        return COL_NUM;
    }

    @Override
    public int getRowCount() {
        return this.base.size() / COL_NUM;
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        return base.get(COL_NUM * rowIndex + columnIndex);
    }
    
    public void setModel(final List<Object> list) {
        if (list == null) {
                throw new IllegalArgumentException("The TableModel can't be null!");
        }
        base = list;
        fireTableDataChanged();
    }
    
}
