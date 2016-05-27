package view;

import java.util.ArrayList; 
import java.util.List;
import javax.swing.table.AbstractTableModel;

import model.Hour;

public class MyTableModel extends AbstractTableModel {
    /**
     * 
     */
    private static final long serialVersionUID = 9137709835141518432L;
    private List<List<Object>> base = new ArrayList<>();
    private static int nCOL = Hour.values().length + 1;

    @Override
    public int getColumnCount() {
        if (base.isEmpty()) {
            return nCOL;
        } else {
            return base.get(0).size();
        }
    }

    @Override
    public int getRowCount() {
        return base.size();
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        return base.get(rowIndex).get(columnIndex);
    }
    
    @Override
    public void setValueAt(final Object value, final int row, final int column) {
        this.base.get(row).set(column, value);
        fireTableDataChanged();
    }
    
    public void setModel(final List<List<Object>> list) {
        if (list == null) {
                throw new IllegalArgumentException("The TableModel can't be null!");
        }
        base = list;
        fireTableDataChanged();
    }
    
}
