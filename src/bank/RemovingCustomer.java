package bank;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class RemovingCustomer extends JDialog
{
    private JLabel lblEndingAccount;
    private JList<String> lstEndingAccount;
    private DefaultListModel<String> endingAccountModel;
    private JButton btnOk; 
    
    public RemovingCustomer (JFrame parent, boolean modal, Customer customer)
    {
        super(parent, modal);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setTitle("Removing Customer - " + customer.getFirstName() + " " 
                + customer.getLastName() + " - " + customer.getPersonalNumber());
    }

    public void initComp(Customer customer) throws IOException
    {
        lblEndingAccount = new JLabel("Closing Account");
        lstEndingAccount = new JList<>();
        lstEndingAccount.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstEndingAccount.setFont(new Font(lstEndingAccount.getFont().getName(), Font.PLAIN, 14));
        lstEndingAccount.setVisibleRowCount(15);

        endingAccountModel = new DefaultListModel<>();
        for (int i = 0; i < customer.getSavingAccountList().size();)
        {
            customer.removeSavingsAccount(customer.getSavingAccountList().get(i).getAccountNumber());
            
            String[] s = customer.getTransactionList().get(
                    customer.getTransactionList().size() - 1).getTransactionString().split("!");
            String transactionItem = "<html>" + s[0] + " - Date: " + s[2] + "<br/>" + s[1] + "<br/>"
                    + "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ";
            endingAccountModel.addElement(transactionItem);
        }

        for (int i = 0; i < customer.getCreditAccountList().size();)
        {
            customer.removeCreditAccount(customer.getCreditAccountList().get(i).getAccountNumber());
            
            String[] s = customer.getTransactionList().get(
                    customer.getTransactionList().size() - 1).getTransactionString().split("!");
            String transactionItem = "<html>" + s[0] + " - Date: " + s[2] + "<br/>" + s[1] + "<br/>"
                    + "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ";
            endingAccountModel.addElement(transactionItem);
        }
        
        lstEndingAccount.setModel(endingAccountModel);

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
        btnOk.setBackground(new java.awt.Color(220, 220, 220));

        add(lblEndingAccount, BorderLayout.NORTH);
        add(new JScrollPane(lstEndingAccount), BorderLayout.CENTER);
        add(btnOk, BorderLayout.SOUTH);

        setSize(410, 500);
        setVisible(true);
    }
}
