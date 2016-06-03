package view;


import java.awt.BorderLayout;  
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
import model_interface.ILesson;
import view.utility.ColorUtility;
import view.utility.ObjectManager;

public class ViewImpl extends JFrame implements IView {
    
    /**
     * 
     */
    private static final long serialVersionUID = -7339167500714323687L;
    private final JMenuBar menuBar = new JMenuBar();
    private final BaseMenu menu = new BaseMenu(this);
    private final JMenu addMenu = new AddMenu(this);
    private final JMenu semesterMenu = new SemesterMenu();
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
    private ILesson lessonSlot1; //
    private ILesson lessonSlot2; //    controllare per bene questi campi
    private int searchType; //
    

    public ViewImpl() {
        super();
        this.addData(0, null);
        Controller.getController().setView(this);
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
        this.menuBar.add(addMenu);
        this.menuBar.add(semesterMenu);
        
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
            final int rowValTmp = this.cellCoordinates.getX();
            final int colValTmp = this.cellCoordinates.getY();
            if (colValTmp == 0 && rowValTmp == 0) {
                this.errorDialog("No element selected");
            } else {
                final Object lessonTmp = this.table.getValueAt(rowValTmp, colValTmp);
                if (lessonTmp instanceof ILesson) {
                    if (!this.slot1.isVisible()) {
                        this.table.setValueAt("", rowValTmp, colValTmp);
                        this.slot1.setText(((ILesson) lessonTmp).getSubject().getName() + "/" + ((ILesson) lessonTmp).getProfessor().getName());
                        this.slot1.setVisible(true);
                        this.lessonSlot1 = (ILesson) lessonTmp;
                    } else {
                        if (!this.slot2.isVisible()) {
                            this.table.setValueAt("", rowValTmp, colValTmp);
                            this.slot2.setText(((ILesson) lessonTmp).getSubject().getName() + "/" + ((ILesson) lessonTmp).getProfessor().getName());
                            this.slot2.setVisible(true);
                            this.lessonSlot2 = (ILesson) lessonTmp;
                        } else {
                            Controller.getController().errorMessage("You can't take another lesson, place one of which you got at least!");
                        }
                    }
                } else {
                    Controller.getController().errorMessage("You can't take this element, it's not a lesson!");
                }
            }
        });
        this.slot1.addActionListener(e1 -> {
            final int rowVal = this.cellCoordinates.getX();
            final int colVal = this.cellCoordinates.getY();
            final Object lesson = this.table.getValueAt(rowVal, colVal);
            if (lesson instanceof ILesson) {
                Controller.getController().errorMessage("You can't place this lesson over another one!");
            } else {
                if (!lesson.toString().equals("")) {
                    Controller.getController().errorMessage("You can't place this lesson here!");
                } else {
                    this.table.setValueAt(ObjectManager.setNewLessonValues(this.searchType, rowVal, colVal, this.lessonSlot1), rowVal, colVal);
                    this.slot1.setVisible(false);
                }
            }
        });
        this.slot2.addActionListener(e2 -> {
            final int rowVal = this.cellCoordinates.getX();
            final int colVal = this.cellCoordinates.getY();
            final Object lesson = this.table.getValueAt(rowVal, colVal);
            if (lesson instanceof ILesson) {
                Controller.getController().errorMessage("You can't place this lesson over another one!");
            } else {
                if (!lesson.toString().equals("")) {
                    Controller.getController().errorMessage("You can't place this lesson here!");
                } else {
                    this.table.setValueAt(ObjectManager.setNewLessonValues(this.searchType, rowVal, colVal, this.lessonSlot2), rowVal, colVal);
                    this.slot2.setVisible(false);
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
                if (lesson instanceof ILesson) {
                    if (JOptionPane.showConfirmDialog(this, "Are you sure to delete this lesson?") == JOptionPane.YES_OPTION) {
                        Controller.getController().deleteLesson((ILesson) lesson);
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
            final List<ILesson> changements = new ArrayList<>();
            for (int i = 0; i < this.table.getColumnCount(); i++) {
                for (int y = 0; y < this.table.getRowCount(); y++) {
                    if (this.table.getValueAt(y, i) instanceof ILesson) {
                        changements.add((ILesson) this.table.getValueAt(y, i));
                    }
                }
            }
            Controller.getController().setChangements(changements);
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
    public void addData(final int type, final List<ILesson> list) {
        this.searchType = type;
        this.tableModel.setModel(ObjectManager.getStruct(type, list));
    }

    @Override
    public void editMode(final boolean set) {
        this.menu.setEnabled(!set);
        this.addMenu.setEnabled(!set);
        this.semesterMenu.setEnabled(!set);
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
