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

public class NewCustomer extends JDialog
{
    int controller = 0;
    
    public NewCustomer (JFrame parent, boolean modal)
    {
        super(parent, modal);
        setBounds(280, 137, 380, 301);
        setLayout(new BorderLayout());
        setTitle("New Customer");
        
        JPanel pnlNewCustomer = new JPanel();
        pnlNewCustomer.setPreferredSize(new Dimension(350, 300));
        pnlNewCustomer.setLayout(new FlowLayout(FlowLayout.CENTER, 10,15));
        
        JLabel lblNewCustomer = new JLabel("New Customer");
        lblNewCustomer.setPreferredSize(new Dimension(300, 30));
        lblNewCustomer.setFont(new Font("Verdana", Font.PLAIN, 20));
        lblNewCustomer.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel lblFirstName = new JLabel("First name: ");
        lblFirstName.setPreferredSize(new Dimension(100, 30));
        lblFirstName.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        JLabel lblLastName = new JLabel("Last name: ");
        lblLastName.setPreferredSize(new Dimension(100, 30));
        lblLastName.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        JLabel lblSSN = new JLabel("SSN: ");
        lblSSN.setPreferredSize(new Dimension(100, 30));
        lblSSN.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        
        JTextField txtFirstName = new JTextField();
        txtFirstName.setPreferredSize(new Dimension(240, 30));
        txtFirstName.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        JTextField txtLastName = new JTextField();
        txtLastName.setPreferredSize(new Dimension(240, 30));
        txtLastName.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        JTextField txtSSN = new JTextField();
        txtSSN.setPreferredSize(new Dimension(240, 30));
        txtSSN.setFont(new Font("Verdana", Font.PLAIN, 14));
        
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                NewCustomer.this.setVisible(false);
            }
        });
        
        JButton btnCreate = new JButton("Create");
        btnCreate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                String firstName = txtFirstName.getText();
                String lastName = txtLastName.getText();
                String SSN = txtSSN.getText();
                
                Long parseSSN = 0L;
                if (SSN.length() == 12)
                {
                    parseSSN = Long.parseLong(SSN);
                    for (int i = 0; i < Bank.getCustomerList().size(); i++)
                    {
                        if (Bank.getCustomerList().get(i).getPersonalNumber() == parseSSN)
                        {
                            parseSSN = 0L;
                        }
                    }
                } else
                {
                    System.out.println("Customer already exist");
                }
                
                if(parseSSN > 0 && firstName.length() > 0 && lastName.length() > 0)
                {
                    try
                    {
                        Bank.addCustomer(firstName, lastName, parseSSN, 0);
                    } catch (IOException ex)
                    {
                        Logger.getLogger(NewCustomer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    controller = 1;
                    NewCustomer.this.setVisible(false);
                    System.out.println("succses");
                } else
                {
                    System.out.println("wrong input");
                }
            }
        });
        
        lblNewCustomer.setBackground(new java.awt.Color(220,220,220));
        lblFirstName.setBackground(new java.awt.Color(220,220,220));
        txtFirstName.setBackground(new java.awt.Color(220,220,220));
        lblLastName.setBackground(new java.awt.Color(220,220,220));
        txtLastName.setBackground(new java.awt.Color(220,220,220));
        lblSSN.setBackground(new java.awt.Color(220,220,220));
        txtSSN.setBackground(new java.awt.Color(220,220,220));
        btnCancel.setBackground(new java.awt.Color(220,220,220));
        btnCreate.setBackground(new java.awt.Color(220,220,220));
        
        pnlNewCustomer.add(lblNewCustomer);
        pnlNewCustomer.add(lblFirstName);
        pnlNewCustomer.add(txtFirstName);
        pnlNewCustomer.add(lblLastName);
        pnlNewCustomer.add(txtLastName);
        pnlNewCustomer.add(lblSSN);
        pnlNewCustomer.add(txtSSN);
        pnlNewCustomer.add(btnCancel);
        pnlNewCustomer.add(btnCreate);
        add(pnlNewCustomer);
    }

    public int getController()
    {
        return controller;
    }
}
