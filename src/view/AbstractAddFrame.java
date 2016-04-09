package view;

import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractAddFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -3695151833842350492L;
    private JLabel label = new JLabel();
    private JComboBox field = new JComboBox();
    private final JButton ok = new JButton("Ok");
    private final JButton cancel = new JButton("Cancel");
    private JPanel lay = new JPanel();
    
    public abstract void setFrame(JFrame frame);

}
