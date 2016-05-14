package view;
  
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

public class AddCourseDialog extends AbstractAddDialog { //da riassumere con un semplice ciclo una volta ottenuta la mappa dal controller
    /**
     * 
     */
    private static final long serialVersionUID = -6489822172457272017L;
    
    public AddCourseDialog(final JFrame frame) {
        super(frame);
    }

    @Override
    public JPanel setFields() {
        final JPanel panelNord = new JPanel();
        panelNord.setLayout(new GridBagLayout());
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        try {
            Controller.getController().getCoursesValues().forEach((x, y) -> {
                final JLabel label = new JLabel(x.getX());
                cnst.anchor = GridBagConstraints.WEST;
                panelNord.add(label, cnst);
                final JComboBox<String> field = new JComboBox<>();
                y.forEach(z -> {
                    field.addItem(z);
                });
                field.setPrototypeDisplayValue("aaaaaaaaaa"); //non visualizza totalemente l'oggetto nell'elenco
                field.setEditable(x.getY());
                super.getBoxList().add(field);
                cnst.anchor = GridBagConstraints.EAST;
                panelNord.add(field, cnst);
                cnst.gridy++;
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return panelNord;
    }

    @Override
    public JButton setOkButton() {
        final JButton button = new JButton("Ok");
        button.addActionListener(e -> {
            final List<String> retValue = new ArrayList<>();
            for (final JComboBox<String> a : super.getBoxList()) {
                retValue.add(a.getSelectedItem().toString());
            }
            //chiamata al controller che passa la lista di stringhe ottenuta, l'ordine dei tipi delle stringhe � uguale all'ordine dei tipi di liste passate prima
            this.setVisible(false);
        });
        return button;
    }
}
