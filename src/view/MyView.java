package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

public class MyView extends JFrame implements IView {
    
    /**
     * 
     */
    private static final long serialVersionUID = -7339167500714323687L;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new MenuBrutto();
    private JMenu menu2 = new MenuAddBrutto(this);
    private MyTableModel model = new MyTableModel();
    private JTable table = new JTable(model);
    private JScrollPane fullTable = new JScrollPane(this.table);
    private JPanel legenda = new JPanel(new GridBagLayout());

    public MyView() {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Orario Lezioni");
        
        this.table.setDefaultRenderer(Object.class, new MyRenderer());
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.setTableHeader(null);
        this.table.setFillsViewportHeight(true); //da usare forse, capire bene di cosa si tratta
        this.table.setFocusable(false);
        this.table.setRowSelectionAllowed(false);
        
        this.menuBar.add(menu);
        this.menuBar.add(menu2);
        
        legenda.setBorder(new TitledBorder("Legenda"));
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        for (int i = 0; i < 3/*numero colori da usare*/; i++) {
            JLabel color = new JLabel("  ");
            color.setOpaque(true);
            color.setBackground(Color.CYAN/*colore da usare*/);
            legenda.add(color, cnst);
            legenda.add(new JLabel("Prova"/*valore assegnato al colore*/), cnst);
            cnst.gridy++;
        }
        
        this.getContentPane().add(this.menuBar, BorderLayout.NORTH);
        this.getContentPane().add(this.fullTable, BorderLayout.CENTER);
        this.getContentPane().add(legenda, BorderLayout.EAST);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        this.setVisible(true);
    }

    @Override
    public void addData(List<Object> list) {
        this.model.setModel(list);
    }

    @Override
    public void clearData() {
        //pensare a come voler intendere tabella vuota
    }

    @Override
    public void refreshSearchList() {
        //this.menu.refreshSearchList();
    }

}
