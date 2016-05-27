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
import view.utility.ObjectManager;

public class IViewImpl extends JFrame implements IView {
    
    /**
     * 
     */
    private static final long serialVersionUID = -7339167500714323687L;
    private final JMenuBar menuBar = new JMenuBar();
    private final BaseMenu menu = new BaseMenu(this);
    private final JMenu menu2 = new AddMenu(this);
    private final JMenu menu3 = new SemesterMenu();
    private final MyTableModel tableModel = new MyTableModel();
    private final JTable table = new JTable(tableModel);
    private final JScrollPane fullTable = new JScrollPane(this.table);
    private final JPanel combo = new JPanel(new BorderLayout());
    private final JPanel legenda = new JPanel(new GridBagLayout());
    private final JPanel editing = new JPanel();
    private final JPanel slots = new JPanel(new BorderLayout());
    private final JButton keep = new JButton("Keep");
    private final JButton delete = new JButton("Delete");
    private final JButton done = new JButton("Done");
    private final JButton slot1 = new JButton();
    private final JButton slot2 = new JButton();
    private Pair<Integer, Integer> cellCoordinates = new Pair<>(0, 0);
    

    public IViewImpl() {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Orario Lezioni");
        
        this.table.setDefaultRenderer(Object.class, new MyRenderer());
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.setTableHeader(null);
        this.table.setFillsViewportHeight(true); // da ricordare a che serve
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
            final int rowVal = this.cellCoordinates.getX();
            final int colVal = this.cellCoordinates.getY();
            if (colVal == 0 && rowVal == 0) {
                this.errorDialog("No element selected");
            } else {
                final Object lesson = this.table.getValueAt(rowVal, colVal);
                if (lesson instanceof Lesson) {
                    if (!this.slot1.isVisible()) {
                        final Lesson lessonTmp = (Lesson) lesson;
                        this.table.setValueAt("", rowVal, colVal);
                        this.slot1.setText(lessonTmp.getSubject().getName() + "/" + lessonTmp.getProfessor().getName());
                        this.slot1.setVisible(true);
                        this.slot1.addActionListener(e1 -> {
                            if (lesson instanceof Lesson) { // mi entra sempre qui, capire perchè
                                Controller.getController().errorMessage("You can't place this lesson over another one!");
                            } else {
                                if (!lesson.toString().equals("")) {
                                    Controller.getController().errorMessage("You can't place this lesson here!");
                                } else {
                                    this.table.setValueAt(lessonTmp, rowVal, colVal);
                                }
                            }
                        });
                    } else {
                        if (!this.slot2.isVisible()) {
                            final Lesson lessonTmp = (Lesson) lesson;
                            this.table.setValueAt("", rowVal, colVal);
                            this.slot2.setText(lessonTmp.getSubject().getName() + "/" + lessonTmp.getProfessor().getName());
                            this.slot2.setVisible(true);
                            this.slot2.addActionListener(e2 -> {
                                if (lesson instanceof Lesson) {
                                    Controller.getController().errorMessage("You can't place this lesson over another one!");
                                } else {
                                    if (!lesson.toString().equals("")) {
                                        Controller.getController().errorMessage("You can't place this lesson here!");
                                    } else {
                                        this.table.setValueAt(lessonTmp, rowVal, colVal);
                                    }
                                }
                            });
                        } else {
                            Controller.getController().errorMessage("You can't take another lesson, place one of which you got at least!");
                        }
                    }
                } else {
                    Controller.getController().errorMessage("You can't take this element, it's not a lesson!");
                }
            }
        });
        this.keep.setEnabled(false);
        this.editing.add(keep);
        this.delete.addActionListener(e -> {
            final int rowVal = this.cellCoordinates.getX();
            final int colVal = this.cellCoordinates.getY();
            if (colVal == 0 && rowVal == 0) {
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
        this.slot1.setVisible(false);
        this.slot2.setVisible(false);
        this.slots.add(slot1, BorderLayout.NORTH);
        this.slots.add(slot2, BorderLayout.CENTER);
        this.combo.add(slots, BorderLayout.SOUTH);
        
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
        this.tableModel.setModel(ObjectManager.getStruct(type, list));
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
