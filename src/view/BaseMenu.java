package view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser; 
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import controller.Controller;

public class BaseMenu extends JMenu {

    /**
     * 
     */
    private static final long serialVersionUID = 6771132861200476380L;
    private final JFileChooser fileChooser = new JFileChooser();
    private JMenu refreshSearch;
    private static final String TOTAL = "Total";

    public BaseMenu(final IView frame) {
        super("Menu");
        JMenuItem menuItem = new JMenuItem("Open");
        menuItem.addActionListener(e -> {
            final int retVal = this.fileChooser.showOpenDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                Controller.getController().loadData(this.fileChooser.getSelectedFile());
            }
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Save");
        menuItem.addActionListener(e -> {
            final int retVal = this.fileChooser.showSaveDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                Controller.getController().saveData(this.fileChooser.getSelectedFile());
            }
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Edit");
        menuItem.addActionListener(e -> {
            frame.editMode(true);
        });
        this.add(menuItem);
        
        final JMenu subMenu = new JMenu("Search");
        this.refreshSearch = subMenu;
        menuItem = new JMenuItem(TOTAL);
        menuItem.addActionListener(e -> {
            Controller.getController().searchBy(TOTAL, TOTAL);
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
    
    public void refreshSearchList() {
        this.remove(refreshSearch);
        this.refreshSearch = new JMenu("Search");
        final JMenuItem menuItem = new JMenuItem(TOTAL);
        menuItem.addActionListener(e -> {
            Controller.getController().searchBy(TOTAL, TOTAL);
        });
        refreshSearch.add(menuItem);
        Controller.getController().getSearchValues().forEach((x, y) -> {
            final JMenu subSubMenu = new JMenu(x);
            y.forEach(z -> {
                final JMenuItem menuItem2 = new JMenuItem(z);
                menuItem2.addActionListener(e -> {
                    Controller.getController().searchBy(x, z);
                });
                subSubMenu.add(menuItem2);
            });
            refreshSearch.add(subSubMenu);
        });
        this.add(refreshSearch);
    }

    
}
