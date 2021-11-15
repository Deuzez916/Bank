package bank;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CustomerNotFound  extends JDialog
{
    private JLabel label;
    private JButton btn; 
    
    public CustomerNotFound (JFrame parent, boolean modal)
    {
        super(parent, modal);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setTitle("ERROR");
    }
    
    public void initComp()
        {
        label = new JLabel("Customer Not Found");
        label.setFont(new Font("Verdana", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        btn = new JButton("Ok");
    
        btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
              dispose(); 
            }
        });
        add(label, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
        
        setSize(300, 200);
        setVisible(true);
        }

}
