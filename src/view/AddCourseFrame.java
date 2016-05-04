package view;
  
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddCourseFrame extends AbstractAddFrame { // praticamente uguale ad addLessonFrame, pensare a come riassumere
    /**
     * 
     */
    private static final long serialVersionUID = -6489822172457272017L;
    
    
    public AddCourseFrame(final JFrame frame) {
        super(frame);
    }

    @Override
    public JPanel setFields() {
        final JPanel panelNord = new JPanel();
        panelNord.setLayout(new GridBagLayout());
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        
        JLabel label = new JLabel("Name");
        panelNord.add(label, cnst);
        JComboBox<String> field = new JComboBox<>(/*elenco del campo che contiene già valori(tramite array di stringhe)*/);
        field.setEditable(true);
        boxList.add(field);
        panelNord.add(field, cnst);
        cnst.gridy++;
        
        label = new JLabel("Year");
        panelNord.add(label, cnst);
        field = new JComboBox<>(/*elenco del campo che contiene già valori(tramite array di stringhe)*/);
        field.setEditable(true);
        boxList.add(field);
        panelNord.add(field, cnst);
        cnst.gridy++;
        
        label = new JLabel("Semester");
        panelNord.add(label, cnst);
        field = new JComboBox<>(/*elenco del campo che contiene già valori(tramite array di stringhe)*/);
        field.setEditable(false);
        boxList.add(field);
        panelNord.add(field, cnst);
        
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
