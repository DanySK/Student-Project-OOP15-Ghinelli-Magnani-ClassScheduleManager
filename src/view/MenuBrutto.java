package view;

import javax.swing.JFileChooser; 
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import view.utility.SearchModes;

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
                //controller.openFile(this.fileChooser.getSelectedFile());
            }
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Save");
        menuItem.addActionListener(e -> {
            final int retVal = this.fileChooser.showSaveDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                
            }
        });
        this.add(menuItem);
        menuItem = new JMenuItem("Edit");
        menuItem.addActionListener(e -> {
            frame.editMode();
        });
        this.add(menuItem);
        
        final JMenu subMenu = new JMenu("Search");
        for (int i = 0; i < SearchModes.values().length; i++) {
            final JMenu subSubMenu = new JMenu("By " + SearchModes.getName(i));
            for (int y = 0; y < 3/*lunghezza della collezione di quella particolare ricerca*/; y++) {
                menuItem = new JMenuItem("Prova"/*elemento della particolare collezione*/); //confrontarsi con il controller per gestire bene la faccenda di elementi mancanti
                        menuItem.addActionListener(e -> {
                            //chiamata alla funzione che dice il tipo di ricerca, elemento della particolare collezione;
                        });
                subSubMenu.add(menuItem);
            }
            subMenu.add(subSubMenu); 
        }
        this.add(subMenu);
        
    }

    
}
