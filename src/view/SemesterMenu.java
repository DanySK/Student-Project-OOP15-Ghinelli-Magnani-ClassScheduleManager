package view;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

public class SemesterMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = -8405288929095052779L;
    private ButtonGroup group = new ButtonGroup();
    private JRadioButtonMenuItem sem;
    
    public SemesterMenu() {
        super("Semester");
        this.sem = new JRadioButtonMenuItem("1st", true);
        this.sem.addActionListener(e -> {
            // controller.show1stSem;
        });
        group.add(sem);
        this.add(sem);
        this.sem = new JRadioButtonMenuItem("2st");
        this.sem.addActionListener(e -> {
            // controller.show1stSem;
        });
        group.add(sem);
        this.add(sem);
    }
    

}
