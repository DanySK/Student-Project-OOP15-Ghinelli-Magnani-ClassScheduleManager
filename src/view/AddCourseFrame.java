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

import controller.Controller;

public class AddCourseFrame extends AbstractAddFrame { //da riassumere con un semplice ciclo una volta ottenuta la mappa dal controller
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
        cnst.anchor = GridBagConstraints.WEST;
        panelNord.add(label, cnst);
        JComboBox<String> field = new JComboBox<>();
        for (final String a : Controller.getController().getCourseName()) {
            field.addItem(a);
        }
        field.setEditable(true);
        super.getBoxList().add(field);
        cnst.anchor = GridBagConstraints.EAST;
        panelNord.add(field, cnst);
        cnst.gridy++;
        
        label = new JLabel("Year");
        cnst.anchor = GridBagConstraints.WEST;
        panelNord.add(label, cnst);
        field = new JComboBox<>();
        for (final String a : Controller.getController().getYears()) {
            field.addItem(a);
        }
        field.setEditable(true);
        super.getBoxList().add(field);
        cnst.anchor = GridBagConstraints.EAST;
        panelNord.add(field, cnst);
        cnst.gridy++;
        
        label = new JLabel("Semester");
        cnst.anchor = GridBagConstraints.WEST;
        panelNord.add(label, cnst);
        field = new JComboBox<>();
        field.setPrototypeDisplayValue("aaaaaaaaaa");
        field.addItem("1");
        field.addItem("2");
        field.setEditable(false);
        super.getBoxList().add(field);
        cnst.anchor = GridBagConstraints.EAST;
        panelNord.add(field, cnst);
        
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
            //chiamata al controller che passa la lista di stringhe ottenuta, l'ordine dei tipi delle stringhe è uguale all'ordine dei tipi di liste passate prima
            this.setVisible(false);
        });
        return button;
    }
}
