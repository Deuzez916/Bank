package bank;

import static bank.Bank.closeAccount;
import static bank.Bank.deposit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BankGUI extends Bank
{

    public void ADMIN_SCREEN()
    {
        //JFrame--------------------------------------------------------------------
        JFrame customerScreen = new JFrame("Admin");
        customerScreen.setSize(900, 575);
        customerScreen.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));

        //Rightside Panel-----------------------------------------------------------
        JPanel rightSidePanel = new JPanel();
        rightSidePanel.setPreferredSize(new Dimension(450, 385));
        rightSidePanel.setLayout(new BorderLayout());

        //Account List--------------------------------------------------------------
        JLabel lblCustomerList = new JLabel("First Name" + " ".repeat(18) + "Last Name"
                + " ".repeat(20) + "SSN");
        lblCustomerList.setFont(new Font(lblCustomerList.getFont().getName(), Font.PLAIN, 14));

        JList<String> lstAccountList = new JList<>();
        DefaultListModel<String> model = new DefaultListModel<>();
        lstAccountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstAccountList.setFont(new Font(lstAccountList.getFont().getName(), Font.PLAIN, 14));

        for (int i = 0; i < getCustomerList().size(); i++)
        {
            model.addElement(getCustomerList().get(i).toString());
        }
        lstAccountList.setModel(model);
        lstAccountList.setVisibleRowCount(15);

        //Adding to Rightside Panel-------------------------------------------------
        rightSidePanel.add(lblCustomerList, BorderLayout.NORTH);
        rightSidePanel.add(new JScrollPane(lstAccountList), BorderLayout.CENTER);

        //Leftside Panel------------------------------------------------------------
        JPanel leftSidePanel = new JPanel();
        leftSidePanel.setPreferredSize(new Dimension(400, 450));
        leftSidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 52));

        //Leftside Buttons----------------------------------------------------------
        JButton btnAddCustomer = new JButton("Add Customer");
        btnAddCustomer.setPreferredSize(new Dimension(300, 52));

        JButton btnRemoveCustomer = new JButton("Remove Customer");
        btnRemoveCustomer.setPreferredSize(new Dimension(300, 52));
        btnRemoveCustomer.setEnabled(false);

        JButton btnManageCustomer = new JButton("Manage Customer");
        btnManageCustomer.setPreferredSize(new Dimension(300, 52));
        btnManageCustomer.setEnabled(false);

        JButton btnExit = new JButton("Exit");
        btnExit.setPreferredSize(new Dimension(300, 52));

        //Buttons Events------------------------------------------------------------
        btnAddCustomer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("btnAddCustomer.addActionListener");
            }
        });

        btnRemoveCustomer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("btnRemoveCutomer.addActionListener");
            }
        });

        btnManageCustomer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("btnLoadCustomer.addActionListener");
            }
        });

        btnExit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("btnExit.addActionListener");
            }
        });

        //Set Button Enables--------------------------------------------------------
        lstAccountList.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                btnRemoveCustomer.setEnabled(true);
                btnManageCustomer.setEnabled(true);
            }
        });

        //Adding to Leftside Panel--------------------------------------------------
        leftSidePanel.add(btnAddCustomer);
        leftSidePanel.add(btnRemoveCustomer);
        leftSidePanel.add(btnManageCustomer);
        leftSidePanel.add(btnExit);

        //Adding to JFrame----------------------------------------------------------
        customerScreen.add(leftSidePanel);
        customerScreen.add(rightSidePanel);
        customerScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerScreen.setVisible(true);

    }

    public void LOAD_CUSTOMER(Customer customer)
    {
        //JFrame--------------------------------------------------------------------
        JFrame customerScreen = new JFrame("Customer");
        customerScreen.setSize(900, 575);
        customerScreen.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        //Rightside Panel-----------------------------------------------------------
        JPanel rightSidePanel = new JPanel();
        rightSidePanel.setPreferredSize(new Dimension(450, 385));
        rightSidePanel.setLayout(new BorderLayout());

        //Account List--------------------------------------------------------------
        JLabel lblAccountList = new JLabel("Account Typ" + " ".repeat(28) + "AccountNumber"
                + " ".repeat(21) + "Balance");

        JList<String> lstAccountList = new JList<>();
        DefaultListModel<String> model = new DefaultListModel<>();
        lstAccountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstAccountList.setFont(new Font(lstAccountList.getFont().getName(), Font.PLAIN, 14));

        for (int i = 0; i < customer.getSavingAccountList().size(); i++)
        {
            model.addElement(customer.getSavingAccountList().get(i).toString());
        }
        for (int i = 0; i < customer.getCreditAccountList().size(); i++)
        {
            model.addElement(customer.getCreditAccountList().get(i).toString());
        }
        lstAccountList.setModel(model);
        lstAccountList.setVisibleRowCount(15);

        //Adding to Rightside Panel-------------------------------------------------
        rightSidePanel.add(lblAccountList, BorderLayout.NORTH);
        rightSidePanel.add(new JScrollPane(lstAccountList), BorderLayout.CENTER);

        //Leftside Panel------------------------------------------------------------
        JPanel leftSidePanel = new JPanel();
        leftSidePanel.setPreferredSize(new Dimension(400, 535));
        leftSidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 38));

        //Customer Info Label-------------------------------------------------------
        JLabel lblPersonalInfo = new JLabel();
        lblPersonalInfo.setFont(new Font(lblPersonalInfo.getFont().getName(), lblPersonalInfo.getFont().getStyle(), 15));
        lblPersonalInfo.setText("<html>Name - " + customer.getName()
                + "<br/>Lastname - " + customer.getLastName()
                + "<br/>SSN - " + customer.getPersonalNumber() + "<html>");
        lblPersonalInfo.setPreferredSize(new Dimension(300, 55));

        //Leftside Buttons----------------------------------------------------------
        JButton btnAddAccount = new JButton("<html>Add<br/>Account<html>");
        btnAddAccount.setPreferredSize(new Dimension(125, 100));

        JButton btnChangeName = new JButton("<html>Change<br/>Name<html>");
        btnChangeName.setPreferredSize(new Dimension(125, 100));

        JButton btnRemoveAccount = new JButton("<html>Remove<br/>Account<html>");
        btnRemoveAccount.setPreferredSize(new Dimension(125, 100));
        //btnRemoveAccount.setEnabled(false);

        JButton btnViewTransactions = new JButton("<html>View<br/>Transactions<html>");
        btnViewTransactions.setPreferredSize(new Dimension(125, 100));
        //btnViewTransactions.setEnabled(false);

        JButton btnExit = new JButton("Exit");
        btnExit.setPreferredSize(new Dimension(300, 50));

        //Buttons Events------------------------------------------------------------
        btnAddAccount.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("btnAddAccount.addActionListener");
            }
        });

        btnChangeName.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("btnChangeName.addActionListener");
            }
        });

        btnRemoveAccount.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int index = lstAccountList.getSelectedIndex();
                int accountId = 0;

                if (index < customer.getSavingAccountList().size())
                {
                    accountId = customer.getSavingAccountList().get(index).getAccountNumber();
                } else if ((index - customer.getSavingAccountList().size()) <= customer.getCreditAccountList().size())
                {
                    accountId = customer.getCreditAccountList().get(index - customer.getSavingAccountList().size()).getAccountNumber();
                }

                try
                {
                    closeAccount(customer.getPersonalNumber(), accountId);
                } catch (IOException ex)
                {
                    Logger.getLogger(BankGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                model.removeElementAt(index);
                btnRemoveAccount.setEnabled(false);
                btnViewTransactions.setEnabled(false);
            }
        });

        btnViewTransactions.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("btnViewTransaction.addActionListener");
            }
        });

        btnExit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("btnExit.addActionListener");
            }
        });

        //Set Button Enables--------------------------------------------------------
        btnRemoveAccount.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btnRemoveAccount.setEnabled(false);
            }
        });

        btnViewTransactions.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btnViewTransactions.setEnabled(false);
            }
        });

        lstAccountList.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                btnRemoveAccount.setEnabled(true);
                btnViewTransactions.setEnabled(true);
            }
        });

        //Adding to Leftside Panel--------------------------------------------------
        leftSidePanel.add(lblPersonalInfo);
        leftSidePanel.add(btnAddAccount);
        leftSidePanel.add(btnChangeName);
        leftSidePanel.add(btnRemoveAccount);
        leftSidePanel.add(btnViewTransactions);
        leftSidePanel.add(btnExit);

        //Adding to JFrame----------------------------------------------------------
        customerScreen.add(leftSidePanel);
        customerScreen.add(rightSidePanel);
        customerScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerScreen.setVisible(true);
    }

    public void CUSTOMER_SCREEN(Customer customer) throws FileNotFoundException
    {
        JFrame customerScreen = new JFrame("Customer - " + customer.getName()
                + " " + customer.getLastName() + " - " + customer.getPersonalNumber());
        customerScreen.setSize(900, 575);
        customerScreen.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

    //Rightside Panel-----------------------------------------------------------
        JPanel rightSidePanel = new JPanel();
        rightSidePanel.setPreferredSize(new Dimension(450, 385));
        rightSidePanel.setLayout(new BorderLayout());

    //Account List--------------------------------------------------------------
        JLabel lblAccountList = new JLabel("Account Typ" + " ".repeat(28) + "AccountNumber"
                + " ".repeat(21) + "Balance");

        JList<String> lstAccountList = new JList<>();
        DefaultListModel<String> model = new DefaultListModel<>();
        lstAccountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstAccountList.setFont(new Font(lstAccountList.getFont().getName(), Font.PLAIN, 14));

        for (int i = 0; i < customer.getSavingAccountList().size(); i++)
        {
            model.addElement(customer.getSavingAccountList().get(i).toString());
        }
        for (int i = 0; i < customer.getCreditAccountList().size(); i++)
        {
            model.addElement(customer.getCreditAccountList().get(i).toString());
        }
        lstAccountList.setModel(model);
        lstAccountList.setVisibleRowCount(10);

    //Adding to Rightside Panel-------------------------------------------------
        rightSidePanel.add(lblAccountList, BorderLayout.NORTH);
        rightSidePanel.add(new JScrollPane(lstAccountList), BorderLayout.CENTER);

    //Leftside Panel------------------------------------------------------------
        JPanel leftSidePanel = new JPanel();
        leftSidePanel.setPreferredSize(new Dimension(400, 535));
        leftSidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 23));

    //Instruction Label---------------------------------------------------------    
        JLabel lblAmount = new JLabel();
        lblAmount.setText("<html>1. Choose an account from the list"
                + "<br/>2. Type in the transaction amount in the field below"
                + "<br/>3. Press Add or Withdraw to make an transaction<html>");
        lblAmount.setPreferredSize(new Dimension(300, 42));

    //Transaction Sum TextField-------------------------------------------------   
        JTextField txtAmount = new JTextField();
        txtAmount.setPreferredSize(new Dimension(300, 45));
        txtAmount.setFont(new Font(txtAmount.getFont().getName(), txtAmount.getFont().getStyle(), 30));
        txtAmount.setEnabled(false);

    //Credit Limit Label--------------------------------------------------------    
        JLabel lblCreditLimit = new JLabel("<html>Credit Limit = - 5000 "
                + "<br/>Current Credit = " + 0 + "<html>");
        lblCreditLimit.setPreferredSize(new Dimension(300, 28));

    //Leftside Buttons----------------------------------------------------------    
        JButton btnAddMoney = new JButton("<html>Add<br/>Money<html>");
        btnAddMoney.setPreferredSize(new Dimension(125, 100));
        btnAddMoney.setEnabled(false);

        JButton btnWithdrawMoney = new JButton("<html>Withdraw<br/>Money<html>");
        btnWithdrawMoney.setPreferredSize(new Dimension(125, 100));
        btnWithdrawMoney.setEnabled(false);

        JButton btnViewTransactions = new JButton("View Transactions");
        btnViewTransactions.setPreferredSize(new Dimension(300, 50));
        btnViewTransactions.setEnabled(false);

        JButton btnExit = new JButton("Exit");
        btnExit.setPreferredSize(new Dimension(300, 50));

    //Buttons Events------------------------------------------------------------    
        btnAddMoney.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                int index = lstAccountList.getSelectedIndex();
                int accountIndex = index;
                int accountId = 0;
                String accountType = "";
                
                if (index < customer.getSavingAccountList().size())
                {
                    accountId = customer.getSavingAccountList().get(index).getAccountNumber();
                    accountType = "s";
                } else if ((index - customer.getSavingAccountList().size()) <= customer.getCreditAccountList().size())
                {
                    accountIndex -= customer.getSavingAccountList().size();
                    accountId = customer.getCreditAccountList().get(accountIndex).getAccountNumber();
                    accountType = "c";
                }

                double transactionSum = 0;
                
                try
                {
                    transactionSum = Double.parseDouble(txtAmount.getText());
                    deposit(accountType, customer.getPersonalNumber(), accountIndex, transactionSum);
                    
                    if (accountType.equalsIgnoreCase("s"))
                    {
                        accountType = customer.getSavingAccountList().get(index).toString();
                    } else if (accountType.equalsIgnoreCase("c"))
                    {
                        accountType = customer.getCreditAccountList().get(index - customer.getSavingAccountList().size()).toString();
                    }
                    model.setElementAt(accountType, index);
                    
                }catch (Exception e)
                {
                    System.err.println("ups, this was not castable to double");
                }
            }
        });

        btnWithdrawMoney.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                int index = lstAccountList.getSelectedIndex();
                int accountId = 0;
                String uppdateList = "";
                
                if (index < customer.getSavingAccountList().size())
                {
                    accountId = customer.getSavingAccountList().get(index).getAccountNumber();
                    uppdateList = "s";
                } else if ((index - customer.getSavingAccountList().size()) <= customer.getCreditAccountList().size())
                {
                    accountId = customer.getCreditAccountList().get(index - customer.getSavingAccountList().size()).getAccountNumber();
                    uppdateList = "c";
                }
                
                double transactionSum = 0;
                
                try
                {
                    transactionSum = Double.parseDouble(txtAmount.getText());
                    withdraw(customer.getPersonalNumber(), accountId, transactionSum);
                    
                    if (uppdateList.equalsIgnoreCase("s"))
                    {
                        uppdateList = customer.getSavingAccountList().get(index).toString();
                    } else if (uppdateList.equalsIgnoreCase("c"))
                    {
                        uppdateList = customer.getCreditAccountList().get(index - customer.getSavingAccountList().size()).toString();
                    }
                    model.setElementAt(uppdateList, index);
                    
                }catch (Exception e)
                {
                    System.err.println("ups, this was not castable to double");
                }
            }
        });

        btnViewTransactions.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("btnViewTransactions.addActionListener");
            }
        });

        btnExit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("btnExit.addActionListener");
            }
        });

    //Set Button Enables--------------------------------------------------------    
        txtAmount.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                txtAmount.setEnabled(false);
            }
        });

        lstAccountList.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                txtAmount.setEnabled(true);
                btnViewTransactions.setEnabled(true);
            }
        });

        txtAmount.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                btnAddMoney.setEnabled(true);
                btnWithdrawMoney.setEnabled(true);
            }
        });

    //Adding to Leftside Panel--------------------------------------------------    
        leftSidePanel.add(lblAmount);
        leftSidePanel.add(txtAmount);
        leftSidePanel.add(btnAddMoney);
        leftSidePanel.add(btnWithdrawMoney);
        leftSidePanel.add(btnViewTransactions);
        leftSidePanel.add(lblCreditLimit);
        leftSidePanel.add(btnExit);

    //Adding to JFrame----------------------------------------------------------
        customerScreen.add(leftSidePanel);
        customerScreen.add(rightSidePanel);
        customerScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerScreen.setVisible(true);
    }

}
