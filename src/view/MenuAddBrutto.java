package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuAddBrutto extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = 4790546772035194942L;
    private JMenuItem menuItem;
    
    
    public MenuAddBrutto(JFrame frame) {
        super("Add");
        this.menuItem = new JMenuItem("Add course");
        this.menuItem.addActionListener(e -> {
            // aprire finestra di aggiunta corso
        });
        this.add(menuItem);
        this.menuItem = new JMenuItem("Add lesson");
        this.menuItem.addActionListener(e -> {
            new AddLessonFrame(frame);
        });
        this.add(menuItem);
    }

}
