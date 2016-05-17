package view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser; 
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import controller.Controller;

public class MenuBrutto extends JMenu {  // considerare l'idea di rendere dinamico il menu di ricerca semplicemente ricreandolo da capo e rimetterlo nella view

    /**
     * 
     */
    private static final long serialVersionUID = 6771132861200476380L;
    private final JFileChooser fileChooser = new JFileChooser();

    public MenuBrutto(final IView frame) {
        super("Menu");
        JMenuItem menuItem = new JMenuItem("Open");
        menuItem.addActionListener(e -> {
            final int retVal = this.fileChooser.showOpenDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                try {
                    Controller.getController().loadData(this.fileChooser.getSelectedFile());
                } catch (IOException e1) {
                    Logger.getGlobal().log(Level.SEVERE, "Error:", e);
                }
            }
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Save");
        menuItem.addActionListener(e -> {
            final int retVal = this.fileChooser.showSaveDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                try {
                    Controller.getController().saveData(this.fileChooser.getSelectedFile());
                } catch (IOException e1) {
                    Logger.getGlobal().log(Level.SEVERE, "Error:", e);
                }
            }
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Edit");
        menuItem.addActionListener(e -> {
            frame.editMode();
        });
        this.add(menuItem);
        
        final JMenu subMenu = new JMenu("Search");
        menuItem = new JMenuItem("Total");
        menuItem.addActionListener(e -> {
            Controller.getController().searchBy("Total", "Total");
        });
        subMenu.add(menuItem);
        Controller.getController().getSearchValues().forEach((x, y) -> {
            final JMenu subSubMenu = new JMenu(x);
            y.forEach(z -> {
                final JMenuItem menuItem2 = new JMenuItem(z);
                menuItem2.addActionListener(e -> {
                    Controller.getController().searchBy(x, z);
                });
                subSubMenu.add(menuItem2);
            });
            subMenu.add(subSubMenu);
        });
        this.add(subMenu);
    }

    
}
