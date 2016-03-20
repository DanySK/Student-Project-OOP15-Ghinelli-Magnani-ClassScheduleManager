package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

public class ScheletonGUI extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 6251123285697956614L;
    private JTable table = new JTable(50, 50);
    private JScrollPane fullTable = new JScrollPane(this.table);
    private JToolBar optionBar = new JToolBar();
    private JButton search = new JButton("Search");
    private JButton edit = new JButton("Edit");
    private JButton load = new JButton("Load");
    private JButton save = new JButton("Save");
    
    ScheletonGUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Orario Lezioni");
        
        this.optionBar.setFloatable(false);
        this.optionBar.add(this.search);
        this.optionBar.add(this.edit);
        this.optionBar.add(this.load);
        this.optionBar.add(this.save);
        
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        JPanel legenda = new JPanel(new GridBagLayout());
        legenda.setBorder(new TitledBorder("Legenda"));
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        JLabel prova = new JLabel("  ");
        prova.setOpaque(true);
        prova.setBackground(Color.BLACK);
        legenda.add(prova, cnst);
        legenda.add(new JLabel("Questa è di prova"), cnst);
        cnst.gridy++;
        
        
        
        this.getContentPane().add(this.optionBar, BorderLayout.NORTH);
        this.getContentPane().add(this.fullTable, BorderLayout.CENTER);
        this.getContentPane().add(legenda, BorderLayout.EAST);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        this.setVisible(true);
        
    }

}
