package view;

import javax.swing.ButtonGroup; 
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

import controller.Controller;

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
            Controller.getController().setSemester(1);
        });
        group.add(sem);
        this.add(sem);
        sem = new JRadioButtonMenuItem("2st");
        sem.addActionListener(e -> {
            Controller.getController().setSemester(2);
        });
        group.add(sem);
        this.add(sem);
    }
    

}
