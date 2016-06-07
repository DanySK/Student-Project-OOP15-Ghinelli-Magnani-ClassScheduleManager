package view;

import java.awt.GridBagConstraints; 
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

public class DeleteProfessorDialog extends AbstractDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 754603152453800261L;

    public DeleteProfessorDialog(final JFrame frame) throws HeadlessException {
        super(frame);
    }

    @Override
    public JPanel setFields() {
        final JPanel panelNord = new JPanel();
        panelNord.setLayout(new GridBagLayout());
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        Controller.getController().deleteProfessorValues().forEach((x, y) -> {
            final JLabel label = new JLabel(x);
            cnst.anchor = GridBagConstraints.WEST;
            panelNord.add(label, cnst);
            final JComboBox<String> field = new JComboBox<>();
            y.forEach(z -> {
                field.addItem(z);
            });
            field.setEditable(false);
            super.getBoxList().add(field);
            cnst.anchor = GridBagConstraints.EAST;
            panelNord.add(field, cnst);
            cnst.gridy++;
        });
        return panelNord;
    }

    @Override
    public JButton setOkButton() {
        final JButton button = new JButton("Ok");
        button.addActionListener(e -> {
            final String value = super.getBoxList().get(0).getSelectedItem().toString();
            // controller delete prof
            this.setVisible(false);
        });
        return button;
    }

}
