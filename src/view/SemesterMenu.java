package view;

import javax.swing.ButtonGroup; 
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

public class SemesterMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = -8405288929095052779L;
    private final ButtonGroup group = new ButtonGroup();
    
    public SemesterMenu() {
        super("Semester");
        JRadioButtonMenuItem sem = new JRadioButtonMenuItem("1st", true);
        sem.addActionListener(e -> {
            // controller.show1stSem;
        });
        group.add(sem);
        this.add(sem);
        sem = new JRadioButtonMenuItem("2st");
        sem.addActionListener(e -> {
            // controller.show1stSem;
        });
        group.add(sem);
        this.add(sem);
    }
    

}
