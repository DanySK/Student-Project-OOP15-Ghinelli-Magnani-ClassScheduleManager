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
    private int nColumns = 9;
    private int nRows = 5*4; // ci va aggiunto il numero delle classi
    private Object[][] base;

    @Override
    public int getColumnCount() {
        return nColumns;
    }

    @Override
    public int getRowCount() {
        return nRows;
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        return base[rowIndex][columnIndex];
    }
    
    public void setModel(final Object[][] list) { // da modificare anche il numero di colonne e di righe se serve
        if (list == null) {
                throw new IllegalArgumentException("The TableModel can't be null!");
        }
        base = list;
        fireTableDataChanged();
    }
    
}
