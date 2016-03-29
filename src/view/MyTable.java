package view;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class MyTable extends JTable implements ITable {

    /**
     * 
     */
    private static final long serialVersionUID = -1657558881615210816L;
    
    //probabilmente classe inutile, JTable potrebbe risultare molto semplice da modellare all'interno della classe MyView

    public MyTable(TableModel tm) {
        super(tm);
        this.setDefaultRenderer(Object.class, new MyRenderer());
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.setTableHeader(null);
        this.setFillsViewportHeight(true); //da usare forse, capire bene di cosa si tratta
        this.setFocusable(false);
        this.setRowSelectionAllowed(false);
    }

    @Override
    public void searchBy(/*tipo di richerca*/, /*elemento della ricerca*/) {
        // TODO Auto-generated method stub

    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub

    }

    @Override
    public void editMode(boolean flag) {
        // TODO Auto-generated method stub
        
    }

}
