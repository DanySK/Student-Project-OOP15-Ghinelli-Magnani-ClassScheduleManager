package view.dialog;

import java.awt.GridBagConstraints;    
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import view.utility.DialogManager;

/**
 * 
 * Dialog used to add the lesson to the table.
 *
 */

public class AddLessonDialog extends AbstractDialog {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4379958406580048097L;
    
    /**
     * Constructor of the dialog.
     * @param frame The main frame of the program.
     */
    
    public AddLessonDialog(final JFrame frame) {
        super(frame);
    }


    @Override
    public JPanel setFields() {
        final JPanel panelNord = new JPanel();
        panelNord.setLayout(new GridBagLayout());
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        DialogManager.getLessonsValues().forEach((x, y) -> {
            final JLabel label = new JLabel(x.getX());
            cnst.anchor = GridBagConstraints.WEST;
            panelNord.add(label, cnst);
            final JComboBox<String> field = new JComboBox<>();
            y.forEach(z -> {
                field.addItem(z);
            });
            field.setEditable(x.getY());
            super.getBoxList().add(field);
            cnst.anchor = GridBagConstraints.EAST;
            panelNord.add(field, cnst);
            cnst.gridy++;
        });
        return panelNord;
    }


    @Override
    public ActionListener setOkListener() {
        return e -> {
            final List<String> retValue = new ArrayList<>();
            for (final JComboBox<String> a : super.getBoxList()) {
                retValue.add(a.getSelectedItem().toString());
            }
            Controller.getController().addLesson(retValue);
            this.setVisible(false);
        };
    }
}
