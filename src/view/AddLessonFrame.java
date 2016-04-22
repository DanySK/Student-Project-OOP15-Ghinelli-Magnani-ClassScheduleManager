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

import view.utility.AddFields;

public class AddLessonFrame extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4379958406580048097L;
    private final List<JComboBox<String>> boxList = new ArrayList<>();
    private final JPanel panelNord = new JPanel();
    private final JPanel panelSud = new JPanel();
    
    
    public AddLessonFrame(final JFrame frame) throws HeadlessException {
        super("Add Lesson"); //non si vede il titolo, pensare se si vuole risolvere o meno, non sembra importante
        this.setLayout(new BorderLayout());
        this.panelNord.setLayout(new GridBagLayout());
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        for (int i = 0; i < AddFields.values().length; i++) {
            final JLabel label = new JLabel(AddFields.getName(i));
            this.panelNord.add(label, cnst);
            final JComboBox<String> field = new JComboBox<>(/*elenco del campo che contiene già valori(tramite array di stringhe)*/);
            field.setEditable(true);
            this.boxList.add(field);
            this.panelNord.add(field, cnst);
            cnst.gridy++;
        }
        JButton button = new JButton("Ok");
        button.addActionListener(e -> {
            final List<String> retValue = new ArrayList<>();
            for (final JComboBox<String> a : this.boxList) {
                retValue.add(a.getSelectedItem().toString());
            }
            //chiamata al controller che passa la lista di stringhe ottenuta
            this.setVisible(false);
        });
        this.panelSud.add(button);
        button = new JButton("Cancel");
        button.addActionListener(e -> {
            this.setVisible(false);
        });
        this.panelSud.add(button);
        this.add(panelNord, BorderLayout.NORTH);
        this.add(panelSud, BorderLayout.SOUTH);
        this.pack();
        this.setLocation(frame.getX() + frame.getWidth() / 2, frame.getY() + frame.getHeight() / 2);
        this.setVisible(true);
    }
}
