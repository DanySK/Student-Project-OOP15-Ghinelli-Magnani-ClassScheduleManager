package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddLessonFrame extends JFrame {
    
    private JFrame mainFrame;
    private JLabel label;
    private JComboBox<String> field;
    private List<JComboBox<String>> boxList = new ArrayList<>();
    private JButton button;
    private final JPanel panelNord = new JPanel();
    private final JPanel panelSud = new JPanel();
    
    
    public AddLessonFrame(JFrame frame) throws HeadlessException {
        super("Add Lesson");//non si vede il titolo, pensare se si vuole risolvere o meno, non sembra importante
        this.mainFrame = frame;
        this.setLayout(new BorderLayout());
        this.panelNord.setLayout(new GridBagLayout());
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        for(int i = 0; i < 3/*n campi da compilare*/; i++) {
            this.label = new JLabel("Prova"/*nome campo*/);
            this.panelNord.add(label, cnst);
            this.field = new JComboBox<>(/*elenco del campo che contiene già valori(tramite array di stringhe)*/);
            this.field.setEditable(true);
            this.boxList.add(field);
            this.panelNord.add(field, cnst);
            cnst.gridy++;
        }
        this.button = new JButton("Ok");
        this.button.addActionListener(e -> {
            final List<String> retValue = new ArrayList<>();
            for (JComboBox<String> a : this.boxList) {
                retValue.add(a.getSelectedItem().toString());
            }
            //chiamata al controller che passa la lista di stringhe ottenuta
            this.setVisible(false);
        });
        this.panelSud.add(button);
        this.button = new JButton("Cancel");
        this.button.addActionListener(e -> {
            this.setVisible(false);
        });
        this.panelSud.add(button);
        this.add(panelNord, BorderLayout.NORTH);
        this.add(panelSud, BorderLayout.SOUTH);
        this.pack();
        this.setLocation(this.mainFrame.getX() + this.mainFrame.getWidth()/2, this.mainFrame.getY() + this.mainFrame.getHeight()/2);
        this.setVisible(true);
    }
    
    

}
