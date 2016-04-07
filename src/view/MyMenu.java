package view;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MyMenu extends JMenu implements IMenu {

    /**
     * 
     */
    private static final long serialVersionUID = -6309418513306345186L;
    private JMenu subMenu;
    private JMenu subSubMenu;
    private JMenuItem menuItem;
    private JFileChooser fileChooser = new JFileChooser();

    public MyMenu() {
        
        super("Menu");
        this.menuItem = new JMenuItem("Open");
        this.menuItem.addActionListener(e -> {
            int retVal = this.fileChooser.showOpenDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                //controller.openFile(this.fileChooser.getSelectedFile());
            }
        });
        this.add(menuItem);
        this.menuItem = new JMenuItem("Save");
        this.menuItem.addActionListener(e -> {
            int retVal = this.fileChooser.showSaveDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                //controller.saveFile(this.fileChooser.getSelectedFile());
            }
        });
        this.add(menuItem);
        this.menuItem = new JMenuItem("Edit");
        this.menuItem.addActionListener(e -> {
            //da sistemare, pensare bene come si vuole editare
        });
        this.add(menuItem);
        this.subMenu = new JMenu("Search");
        this.add(subMenu);
    }

    @Override
    public void refreshSearchList() {
        
        this.subMenu = new JMenu("Search");
        for (int i = 0; i < 3/*lunghezza della collezione che contiene i tipi di ricerca*/; i++) {
            this.subSubMenu = new JMenu("By " + "Prova"/*il tipo di ricerca*/);
            for (int y = 0; y < 3/*lunghezza della collezione di quella particolare ricerca*/; y++) {
                this.menuItem = new JMenuItem("Prova"/*elemento della particolare collezione*/);//confrontarsi con il controller per gestire bene la faccenda di elementi mancanti
                        this.menuItem.addActionListener(e -> {
                            //MyTable.searchBy(tipo di ricerca, elemento della particolare collezione);
                        });
                this.subSubMenu.add(menuItem);
            }
            this.subMenu.add(subSubMenu); 
        }
    }
}