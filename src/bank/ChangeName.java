package bank;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChangeName extends JDialog
{
    int controller = 0;
    
    public ChangeName (JFrame parent, boolean modal, Customer customer)
    {
        super(parent, modal);
        setBounds(280, 137, 380, 230);
        setLayout(new BorderLayout());
        setTitle("Change Name");
        
        JPanel pnlNewCustomer = new JPanel();
        pnlNewCustomer.setPreferredSize(new Dimension(350, 220));
        pnlNewCustomer.setLayout(new FlowLayout(FlowLayout.CENTER, 10,15));
        
        JLabel lblNewCustomer = new JLabel("Change Name");
        lblNewCustomer.setPreferredSize(new Dimension(300, 30));
        lblNewCustomer.setFont(new Font("Verdana", Font.PLAIN, 20));
        lblNewCustomer.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel lblFirstName = new JLabel("First name: ");
        lblFirstName.setPreferredSize(new Dimension(100, 30));
        lblFirstName.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        JLabel lblLastName = new JLabel("Last name: ");
        lblLastName.setPreferredSize(new Dimension(100, 30));
        lblLastName.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        JTextField txtFirstName = new JTextField();
        txtFirstName.setPreferredSize(new Dimension(240, 30));
        txtFirstName.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        JTextField txtLastName = new JTextField();
        txtLastName.setPreferredSize(new Dimension(240, 30));
        txtLastName.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                ChangeName.this.setVisible(false);
            }
        });
        
        JButton btnCreate = new JButton("Change");
        btnCreate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                String firstName = txtFirstName.getText();
                String lastName = txtLastName.getText();
               
                if(firstName.length() > 0 && lastName.length() > 0)
                {
                    try
                    {
                        Bank.changeCustomerName(firstName, lastName, customer);
                        controller = 1;
                    } catch (IOException ex)
                    {
                        Logger.getLogger(ChangeName.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else
                {
                    System.out.println("wrong input");
                }
                ChangeName.this.setVisible(false);
            }
        });
        
        pnlNewCustomer.add(lblNewCustomer);
        pnlNewCustomer.add(lblFirstName);
        pnlNewCustomer.add(txtFirstName);
        pnlNewCustomer.add(lblLastName);
        pnlNewCustomer.add(txtLastName);
        pnlNewCustomer.add(btnCancel);
        pnlNewCustomer.add(btnCreate);
        add(pnlNewCustomer);
    }

    public int getController()
    {
        return controller;
    }
}
