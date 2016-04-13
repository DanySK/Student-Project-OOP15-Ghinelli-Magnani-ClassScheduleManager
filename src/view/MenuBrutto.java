package view;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import view_utility.SearchModes;

public class MenuBrutto extends JMenu {  //solo per quello base, per le aggiunte ne creo uno a parte, ovviamente dopo vanno messi iniseme ma queto è solo un prototipo brutto

    /**
     * 
     */
    private static final long serialVersionUID = 6771132861200476380L;
    private JMenu subMenu;
    private JMenu subSubMenu;
    private JMenuItem menuItem;
    private JFileChooser fileChooser = new JFileChooser();

    public MenuBrutto() {
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
        for (int i = 0; i < SearchModes.values().length; i++) {
            this.subSubMenu = new JMenu("By " + SearchModes.getName(i));
            for (int y = 0; y < 3/*lunghezza della collezione di quella particolare ricerca*/; y++) {
                this.menuItem = new JMenuItem("Prova"/*elemento della particolare collezione*/); //confrontarsi con il controller per gestire bene la faccenda di elementi mancanti
                        this.menuItem.addActionListener(e -> {
                            //chiamata alla funzione che dice il tipo di ricerca, elemento della particolare collezione;
                        });
                this.subSubMenu.add(menuItem);
            }
            this.subMenu.add(subSubMenu); 
        }
        this.add(subMenu);
        
    }

    
}
