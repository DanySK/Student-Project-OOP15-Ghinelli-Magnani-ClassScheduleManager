package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

public class ScheletonGUI extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 6251123285697956614L;
    private JTable table = new JTable(new MyTableModel());//<
    private JScrollPane fullTable = new JScrollPane(this.table);//      tutto per la tabella >
    private JMenuBar menuBar = new JMenuBar();//<
    private JMenu menu = new JMenu("Menù");//
    private JMenu subMenu;//
    private JMenu subSubMenu;//         tutto per il menù
    private JMenuItem menuItem;//
    private JFileChooser fileChooser = new JFileChooser();//>
    
    ScheletonGUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Orario Lezioni");
        
        
        
        this.menuBar.add(menu);
        this.menuItem = new JMenuItem("Open");
        this.menuItem.addActionListener(e -> {
            int retVal = this.fileChooser.showOpenDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                //controller.openFile(this.fileChooser.getSelectedFile());
            }
        });
        this.menu.add(menuItem);
        this.menuItem = new JMenuItem("Save");
        this.menuItem.addActionListener(e -> {
            int retVal = this.fileChooser.showSaveDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                //controller.saveFile(this.fileChooser.getSelectedFile());
            }
        });
        this.menu.add(menuItem);
        this.menuItem = new JMenuItem("Edit");
        this.menuItem.addActionListener(e -> {
            //da sistemare, pensare bene come si vuole editare
        });
        this.menu.add(menuItem);
        
        this.subMenu = new JMenu("Search");
        this.subSubMenu = new JMenu("By Professor");
        this.menuItem = new JMenuItem("Prof. Prova");//ricordarsi di aggiungere i professori dinamicamente
        this.menuItem.addActionListener(e -> {
            //da fare
        });
        this.subSubMenu.add(menuItem);
        this.subMenu.add(subSubMenu);
        this.menu.add(subMenu);
        this.subSubMenu = new JMenu("By Class");
        this.menuItem = new JMenuItem("Class prova");//mancano il resto delle classi
        this.menuItem.addActionListener(e -> {
            //da fare
        });
        this.subSubMenu.add(menuItem);
        this.subMenu.add(subSubMenu);
        this.menu.add(subMenu);//ovviamente manca il resto ma lo sistemo una volta pronto per bene quello che serve
        
        
        
        
        
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //metterla o toglierla sta opzione? pensarci bene
        this.table.setTableHeader(null);
        this.table.setFillsViewportHeight(true); //da usare forse, capire bene di cosa si tratta
        this.table.setFocusable(false);
        this.table.setRowSelectionAllowed(false);
        
        
        
        JPanel legenda = new JPanel(new GridBagLayout());
        legenda.setBorder(new TitledBorder("Legenda"));
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        JLabel prova = new JLabel("  ");//in mezzo ad un for
        prova.setOpaque(true);
        prova.setBackground(Color.BLACK);
        legenda.add(prova, cnst);
        legenda.add(new JLabel("Questa è di prova"), cnst);
        cnst.gridy++;
        
        
        
        this.getContentPane().add(this.menuBar, BorderLayout.NORTH);
        this.getContentPane().add(this.fullTable, BorderLayout.CENTER);
        this.getContentPane().add(legenda, BorderLayout.EAST);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        this.setVisible(true);
        
    }

}
