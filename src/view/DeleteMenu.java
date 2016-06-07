package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class DeleteMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = -5935578034798900068L;
    
    public DeleteMenu(final JFrame frame) {
        super("Delete");
        JMenuItem menuItem = new JMenuItem("Delete teaching");
        menuItem.addActionListener(e -> {
            new DeleteTeachingDialog(frame);
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Delete professor");
        menuItem.addActionListener(e -> {
            new DeleteProfessorDialog(frame);
        });
        this.add(menuItem);
    }

}
