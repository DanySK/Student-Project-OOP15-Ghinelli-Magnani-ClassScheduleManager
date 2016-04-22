package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractAddFrame extends JFrame { //probabilmente da eliminare o cambiare

    /**
     * 
     */
    private static final long serialVersionUID = -3695151833842350492L;
    private JLabel label = new JLabel();
    private JComboBox<String> field = new JComboBox<>();
    private final JButton ok = new JButton("Ok");
    private final JButton cancel = new JButton("Cancel");
    private JPanel lay = new JPanel();
    
    public abstract void setFrame(JFrame frame);

}
