package view;

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

public class AddLessonFrame extends AbstractAddFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4379958406580048097L;
    
    
    public AddLessonFrame(final JFrame frame) throws HeadlessException {
        super(frame);
    }


    @Override
    public JPanel setFields() {
        final JPanel panelNord = new JPanel();
        panelNord.setLayout(new GridBagLayout());
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        for (int i = 0; i < AddFields.values().length; i++) {
            final JLabel label = new JLabel(AddFields.getName(i));
            panelNord.add(label, cnst);
            final JComboBox<String> field = new JComboBox<>(/*elenco del campo che contiene già valori(tramite array di stringhe)*/);
            field.setEditable(AddFields.getBool(i));
            boxList.add(field);
            panelNord.add(field, cnst);
            cnst.gridy++;
        }
        return panelNord;
    }


    @Override
    public JButton setOkButton() {
        final JButton button = new JButton("Ok");
        button.addActionListener(e -> {
            final List<String> retValue = new ArrayList<>();
            for (final JComboBox<String> a : this.boxList) {
                retValue.add(a.getSelectedItem().toString());
            }
            //chiamata al controller che passa la lista di stringhe ottenuta, l'ordine dei tipi delle stringhe è uguale all'ordine dei tipi di liste passate prima
            this.setVisible(false);
        });
        return button;
    }
}
