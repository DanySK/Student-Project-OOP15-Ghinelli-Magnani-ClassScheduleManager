package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SearchScheletonGUI extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 6251123285697956614L;
    private JTable table = new JTable();
    private JScrollPane fullTable = new JScrollPane(this.table);
    private GridBagConstraints options = new GridBagConstraints();
    
    SearchScheletonGUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Orario Lezioni");
        
    }

}
