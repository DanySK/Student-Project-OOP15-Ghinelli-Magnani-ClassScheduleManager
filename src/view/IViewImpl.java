package view;


import java.awt.BorderLayout; 
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import controller.Controller;
import controller.utility.Pair;
import model.Lesson;
import view.utility.ColorUtility;
import view.utility.StructCreatorUtility;

public class IViewImpl extends JFrame implements IView {
    
    /**
     * 
     */
    private static final long serialVersionUID = -7339167500714323687L;
    private final JMenuBar menuBar = new JMenuBar();
    private final BaseMenu menu = new BaseMenu(this);
    private final JMenu menu2 = new AddMenu(this);
    private final JMenu menu3 = new SemesterMenu(); //
    private final MyTableModel model = new MyTableModel();
    private final JTable table = new JTable(model);
    private final JScrollPane fullTable = new JScrollPane(this.table);
    private final JPanel combo = new JPanel(new BorderLayout());
    private final JPanel legenda = new JPanel(new GridBagLayout());
    private final JPanel editing = new JPanel();
    private final JButton keep = new JButton("Keep");
    private final JButton delete = new JButton("Delete");
    private final JButton done = new JButton("Done");
    private Pair<Integer, Integer> cellCoordinates = new Pair<>(0, 0);
    

    public IViewImpl() {
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
        this.menuBar.add(menu3);
        
        this.legenda.setBorder(new TitledBorder("Legenda"));
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        for (int i = 0; i < ColorUtility.getColorsByYear().size(); i++) {
            final JLabel color = new JLabel("  ");
            color.setOpaque(true);
            color.setBackground(ColorUtility.getColorsByYear().get(i).getY());
            this.legenda.add(color, cnst);
            this.legenda.add(new JLabel(ColorUtility.getColorsByYear().get(i).getX()), cnst);
            cnst.gridy++;
        }
        this.combo.add(legenda, BorderLayout.NORTH);
        this.keep.addActionListener(e -> {
            /*final int colVal = this.table.getSelectedColumn();
            final int rowVal = this.table.getSelectedRow();
            this.table.getValueAt(rowVal, colVal);*/
        });
        this.keep.setEnabled(false);
        this.editing.add(keep);
        this.delete.addActionListener(e -> {
            final int colVal = this.cellCoordinates.getY();
            final int rowVal = this.cellCoordinates.getX();
            if (colVal == -1 || rowVal == -1) {
                this.errorDialog("No element selected");
            } else {
                final Object lesson = this.table.getValueAt(rowVal, colVal);
                if (lesson instanceof Lesson) {
                    if (JOptionPane.showConfirmDialog(this, "Are you sure to delete this lesson?") == JOptionPane.YES_OPTION) {
                        Controller.getController().deleteLesson((Lesson) lesson);
                    }
                } else {
                    Controller.getController().errorMessage("You can't delete this element, it's not a lesson!");
                }
            }
        });
        this.delete.setEnabled(false);
        this.editing.add(delete);
        this.done.addActionListener(e -> {
            this.editMode(false);
        });
        this.done.setEnabled(false);
        this.editing.add(done);
        this.combo.add(editing, BorderLayout.CENTER);
        
        this.getContentPane().add(menuBar, BorderLayout.NORTH);
        this.getContentPane().add(fullTable, BorderLayout.CENTER);
        this.getContentPane().add(combo, BorderLayout.EAST);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getSize().width) / 2, (dim.height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    @Override
    public void addData(final int type, final List<Lesson> list) {
        this.model.setModel(StructCreatorUtility.getStruct(type, list));
    }

    @Override
    public void editMode(final boolean set) {
        this.keep.setEnabled(set);
        this.delete.setEnabled(set);
        this.done.setEnabled(set);
        this.table.setCellSelectionEnabled(set);
        if (set) {
            this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            final MouseListener clickCell = new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    cellCoordinates = new Pair<>(table.rowAtPoint(e.getPoint()), table.columnAtPoint(e.getPoint()));
                    
                }
            };
            this.table.addMouseListener(clickCell);
        }
    }

    @Override
    public void refreshSearchList() {
        this.menu.refreshSearchList();
    }

    @Override
    public void errorDialog(final String message) {
        JOptionPane.showMessageDialog(this, message);
    }

}
