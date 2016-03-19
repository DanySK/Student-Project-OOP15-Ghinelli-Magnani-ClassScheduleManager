package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

public class ScheletonGUI extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 6251123285697956614L;
    private JTable table = new JTable();
    private JScrollPane fullTable = new JScrollPane(this.table);
    private JToolBar optionBar = new JToolBar();
    private JTabbedPane searchTypes =  new JTabbedPane();
    
    ScheletonGUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Orario Lezioni");
        
        
        this.optionBar.add(new JButton("Edit"));
        this.optionBar.add(new JButton("Load"));
        this.optionBar.add(new JButton("Save"));
        
        this.searchTypes.addTab("Totale", new JLabel());
        this.searchTypes.addTab("Primo anno", new JLabel());
        
        
        
        
        
        final JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.add(this.optionBar, BorderLayout.NORTH);
        top.add(this.searchTypes, BorderLayout.SOUTH);
        this.getContentPane().add(top, BorderLayout.NORTH);
        this.getContentPane().add(this.fullTable, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
        
    }

}
