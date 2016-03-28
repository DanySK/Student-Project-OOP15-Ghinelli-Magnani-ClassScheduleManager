package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextPane;
import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    
    private List<Object> base = new ArrayList<>();
    
    MyTableModel() {
        this.base.add(Arrays.asList("Prova di stringa1", "Prova di stringa2"));
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public int getRowCount() {
        return (1/*riga giorno e orari*/+11/*numero classi*/) * 5;
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        //da avere una lista completamente piena da cui pescare
        return this.base.get(0);
    }
    
    @Override
    public void setValueAt(Object value, int row, int column) {
        //lista dei giorni,aule,ore (per ora solo esempio)
        this.base.set(/*da modificare*/row*column, (String)value);
        fireTableCellUpdated(row, column);
    }
    
    

}
