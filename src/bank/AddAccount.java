package bank;

import static bank.Bank.addCreditAccount;
import static bank.Bank.addSavingsAccount;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AddAccount extends JDialog
{
    int controller = 0;
    
    AddAccount (JFrame parent, boolean modal, Customer customer)
    {
        super(parent, modal);
        setBounds(225, 137, 450, 301);
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 45));
        
        JButton btnAddSavingsAccount = new JButton("<html>Add<br/>Savings Account<html>");
        btnAddSavingsAccount.setPreferredSize(new Dimension(150, 75));
        btnAddSavingsAccount.setBackground(new java.awt.Color(192,192,192));
        
        btnAddSavingsAccount.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                try
                {
                    addSavingsAccount(customer);
                } 
                catch (IOException ex)
                {
                    Logger.getLogger(BankGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                controller = 1;
                AddAccount.this.setVisible(false);
            }
        });
        
        JButton btnAddCreditsAccount = new JButton("<html>Add<br/>Credits Account<html>");
        btnAddCreditsAccount.setPreferredSize(new Dimension(150, 75));
        btnAddCreditsAccount.setBackground(new java.awt.Color(192,192,192));
        
        btnAddCreditsAccount.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                try
                {
                    addCreditAccount(customer);
                } 
                catch (IOException ex)
                {
                    Logger.getLogger(BankGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                controller = 1;
                AddAccount.this.setVisible(false);
            }
        });
        
        JButton btnExit = new JButton("Exit");
        btnExit.setPreferredSize(new Dimension(350, 75));
        btnExit.setBackground(new java.awt.Color(192,192,192));
        
        btnExit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                AddAccount.this.setVisible(false);
            }
        });
        
        panel.add(btnAddSavingsAccount);
        panel.add(btnAddCreditsAccount);
        panel.add(btnExit);
        add(panel);
    }

    public int getController()
    {
        return controller;
    }
}
