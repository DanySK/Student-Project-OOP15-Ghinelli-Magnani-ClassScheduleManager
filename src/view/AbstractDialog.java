package view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class AbstractDialog extends JDialog implements IAddDeleteDialog {

    /**
     * 
     */
    private static final long serialVersionUID = -3695151833842350492L;
    private final JPanel panelSud = new JPanel();
    private final List<JComboBox<String>> boxList = new ArrayList<>();
    private final JFrame mainFrame;
    
    public AbstractDialog(final JFrame frame) throws HeadlessException {
        super();
        this.mainFrame = frame;
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.setLayout(new BorderLayout());
        final JPanel panelNord = this.setFields();
        JButton button = this.setOkButton();
        this.panelSud.add(button);
        button = new JButton("Cancel");
        button.addActionListener(e -> {
            this.setVisible(false);
        });
        this.panelSud.add(button);
        this.add(panelNord, BorderLayout.NORTH);
        this.add(panelSud, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(frame);
        this.setVisible(true);
    }
    
    protected List<JComboBox<String>> getBoxList() {
        return this.boxList;
    }
    
    protected JFrame getFrame() {
        return this.mainFrame;
    }
    
    @Override
    public abstract JPanel setFields();
    
    @Override
    public abstract JButton setOkButton();
    
}
