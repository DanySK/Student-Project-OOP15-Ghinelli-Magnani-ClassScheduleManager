package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class AddMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = 4790546772035194942L;
    
    
    public AddMenu(final JFrame frame) {
        super("Add");
        JMenuItem menuItem = new JMenuItem("Add teaching");
        menuItem.addActionListener(e -> {
            new AddTeachingDialog(frame);
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Add lesson");
        menuItem.addActionListener(e -> {
            new AddLessonDialog(frame);
        });
        this.add(menuItem);
    }

}