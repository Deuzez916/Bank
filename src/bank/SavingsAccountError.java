package bank;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SavingsAccountError extends JDialog
{
    private JLabel lblError;
    private JButton btnOk; 
    
    public SavingsAccountError (JFrame parent, boolean modal)
    {
        super(parent, modal);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setTitle("ERROR");
    }
    
    public void initComp()
        {       
        lblError = new JLabel("Withdrawal sum is to big");
        lblError.setFont(new Font("Verdana", Font.PLAIN, 20));
        lblError.setHorizontalAlignment(JLabel.CENTER);
        btnOk = new JButton("Ok");
    
        btnOk.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
              dispose(); 
            }
        });
        
        //Buttons Color-------------------------------------------------------------
        btnOk.setBackground(new java.awt.Color(220,220,220));
        
        add(lblError, BorderLayout.CENTER);
        add(btnOk, BorderLayout.SOUTH);
        
        setSize(300, 200);
        setVisible(true);
        }

}