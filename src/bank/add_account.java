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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class add_account extends JDialog
{
    DefaultListModel<String> model;
    
    add_account (JFrame parent, boolean modal)
    {
        super(parent, modal);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
       
    }
    
    public void runAdd_Account_Modal(Customer customer)
    {
        //Creating Frame
        JFrame winAdd_Frame = new JFrame();
        winAdd_Frame.setSize(450, 300);
        winAdd_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 45));
        
        JButton btnAddSavingsAccount = new JButton("Add Savings Account");
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
                
                for (int i = 0; i < customer.getSavingAccountList().size(); i++)
                {
                    model.addElement(customer.getSavingAccountList().get(i).toString());
                }
                for (int i = 0; i < customer.getCreditAccountList().size(); i++)
                {
                    model.addElement(customer.getCreditAccountList().get(i).toString());
                }
                
                
            }
        });
        
        JButton btnAddCreditsAccount = new JButton("Add Credits Account");
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
                
                for (int i = 0; i < customer.getSavingAccountList().size(); i++)
                {
                    model.addElement(customer.getSavingAccountList().get(i).toString());
                }
                for (int i = 0; i < customer.getCreditAccountList().size(); i++)
                {
                    model.addElement(customer.getCreditAccountList().get(i).toString());
                }
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
                winAdd_Frame.dispose();
            }
        });
        
        panel.add(btnAddSavingsAccount);
        panel.add(btnAddCreditsAccount);
        panel.add(btnExit);
        
        winAdd_Frame.add(panel);
        
        winAdd_Frame.setVisible(true);
        winAdd_Frame.setResizable(false);
    }
}
