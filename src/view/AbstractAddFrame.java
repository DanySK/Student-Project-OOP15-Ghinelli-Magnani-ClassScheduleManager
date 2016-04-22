package view;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class AbstractAddFrame extends JFrame implements IAddFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -3695151833842350492L;
    private final JPanel panelSud = new JPanel();
    
    public AbstractAddFrame(final JFrame frame) throws HeadlessException {
        super();
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
        this.setLocation(frame.getX() + frame.getWidth() / 2, frame.getY() + frame.getHeight() / 2);
        this.setVisible(true);
    }
    
    @Override
    public abstract JPanel setFields();
    
    @Override
    public abstract JButton setOkButton();
}
