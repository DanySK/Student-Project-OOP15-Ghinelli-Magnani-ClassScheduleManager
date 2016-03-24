package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    
    private List<String> base = new ArrayList<>();
    
    public MyTableModel() {
        //da usare una lista di oggetti tipo JTextPane per avere una lista completa di oggetti in cui far scrivere poi all'interno di essi
        base.add("Prova semplice stringa");
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
