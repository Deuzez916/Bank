package bank;

import static bank.Bank.closeAccount;
import static bank.Bank.deposit;
import static bank.Bank.getCustomerList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    public void runBankGUI()
    {
        Starting_Screen(getJFrame());
    }
    
    public JFrame getJFrame()
    {
        JFrame frame = new JFrame();
        frame.setSize(900, 575);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        return frame;
    }

    public void Starting_Screen(JFrame winFrame)
    {
        //JFrame--------------------------------------------------------------------
        winFrame.setTitle("The AFLM Bank");
        winFrame.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        
        //JPanel--------------------------------------------------------------------
        JPanel pnlStartingScreenLeftPanel = new JPanel();
        JPanel plnStartingScreenRightPanel = new JPanel();
        
        pnlStartingScreenLeftPanel.setPreferredSize(new Dimension(400, 535));
        pnlStartingScreenLeftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100,100));
        
        plnStartingScreenRightPanel.setPreferredSize(new Dimension(400, 365));
        plnStartingScreenRightPanel.setLayout(new FlowLayout(FlowLayout.CENTER,500,15));
        
        //JButtons & JTextFields-----------------------------------------------------------------
        JButton btnAdmin = new JButton("ADMIN");
        btnAdmin.setPreferredSize(new Dimension(300, 300));
        btnAdmin.setFont(new Font(btnAdmin.getFont().getName(),btnAdmin.getFont().getStyle(),37));
        
        btnAdmin.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                winFrame.remove(pnlStartingScreenLeftPanel);
                winFrame.remove(plnStartingScreenRightPanel);
                winFrame.setVisible(false);
                Admin_Screen(winFrame);
            }
        });
        
        
        JButton btnCustomer = new JButton("CUSTOMER");
        btnCustomer.setPreferredSize(new Dimension(300,212));
        btnCustomer.setFont(new Font(btnCustomer.getFont().getName(),btnCustomer.getFont().getStyle(),37));
        btnCustomer.setEnabled(false);
        
        JTextField txtSSN = new JTextField("SSN");
        txtSSN.setPreferredSize(new Dimension(300, 73));
        txtSSN.setHorizontalAlignment(JTextField.CENTER);
        txtSSN.setFont(new Font(txtSSN.getFont().getName(),txtSSN.getFont().getStyle(),42));
        
        txtSSN.addKeyListener(new KeyAdapter()
        {
           public void keyPressed(KeyEvent ke)
           {
               if(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode()== '\b')
               {
                   txtSSN.setEditable(true);
               }
               else
               {
                   txtSSN.setEditable(false);
               }
           }
           public void keyTyped(KeyEvent e)
           {
               if(txtSSN.getText().length() >= 12)
                   e.consume();
               if(txtSSN.getText().length() > 10)
               {
                   btnCustomer.setEnabled(true);
               }
               else
               {
                   btnCustomer.setEnabled(false);
               }
           }
        });
        
        txtSSN.addMouseListener(new MouseAdapter()
        {
           public void mouseClicked(MouseEvent e)
           {
               txtSSN.setText("");
               btnCustomer.setEnabled(false);
           }
        });
        
        btnCustomer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                long ssn = Long.parseLong(txtSSN.getText());
                
                for (int i = 0; i < getCustomerList().size(); i++)
                {
                    if (ssn == getCustomerList().get(i).getPersonalNumber())
                    {
                        winFrame.remove(pnlStartingScreenLeftPanel);
                        winFrame.remove(plnStartingScreenRightPanel);
                        winFrame.setVisible(false);
                        try
                        {
                            Customer_Screen(winFrame, getCustomerList().get(i));
                        } catch (FileNotFoundException ex)
                        {
                            Logger.getLogger(BankGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    } else 
                    {
                        System.out.println("Hitta inte kund");
                        break;
                    }
                } 
            }
        });
        
        //SSN.setMaximumSize(maximumSize MaxAndMinimumSize);
        //SSN.setMinimumSize(minimumSize 12);
       
        //Adding JPanels------------------------------------------------------------
        pnlStartingScreenLeftPanel.add(btnAdmin);
        
        plnStartingScreenRightPanel.add(btnCustomer);
        plnStartingScreenRightPanel.add(txtSSN);
        
        //Bakgrunds färg
        //STARTING_SCREEN.setBackground(new java.awt.Color(192,192,192));
        //leftpanel.setBackground(new java.awt.Color(192,192,192));
        //rightpanel.setBackground(new java.awt.Color(192,192,192));
        
            // 238 knapp färg
        //Buttons Color-------------------------------------------------------------
        btnAdmin.setBackground(new java.awt.Color(192,192,192));
        btnCustomer.setBackground(new java.awt.Color(192,192,192));
        txtSSN.setBackground(new java.awt.Color(192,192,192));
        
        //Adding to JFrame----------------------------------------------------------
        winFrame.add(pnlStartingScreenLeftPanel);
        winFrame.add(plnStartingScreenRightPanel);
        winFrame.setVisible(true);
    }
    
    public void Admin_Screen(JFrame winFrame)
    {
        //JFrame--------------------------------------------------------------------
        winFrame.setTitle("The AFLM Bank - Admin");
        winFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));

        //Rightside Panel-----------------------------------------------------------
        JPanel pnlCustomerList = new JPanel();
        pnlCustomerList.setPreferredSize(new Dimension(450, 385));
        pnlCustomerList.setLayout(new BorderLayout());

        //Account List--------------------------------------------------------------
        JLabel lblCustomerList = new JLabel("SSN" + " ".repeat(43) + "Name");
        lblCustomerList.setFont(new Font(lblCustomerList.getFont().getName(), Font.PLAIN, 14));

        JList<String> lstCustomerList = new JList<>();
        DefaultListModel<String> model = new DefaultListModel<>();
        lstCustomerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstCustomerList.setFont(new Font(lstCustomerList.getFont().getName(), Font.PLAIN, 14));

        for (int i = 0; i < getCustomerList().size(); i++)
        {
            model.addElement(getCustomerList().get(i).toString());
        }
        lstCustomerList.setModel(model);
        lstCustomerList.setVisibleRowCount(15);

        //Adding to Rightside Panel-------------------------------------------------
        pnlCustomerList.add(lblCustomerList, BorderLayout.NORTH);
        pnlCustomerList.add(new JScrollPane(lstCustomerList), BorderLayout.CENTER);

        //Leftside Panel------------------------------------------------------------
        JPanel pnlAdminMeny = new JPanel();
        pnlAdminMeny.setPreferredSize(new Dimension(400, 450));
        pnlAdminMeny.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 52));

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
                NewCustomer nc = new NewCustomer(winFrame, true);
                nc.setModal(true);
                nc.setVisible(true);
                if (nc.getController() == 1)
                {
                    DefaultListModel<String> model = new DefaultListModel<>();
                    for (int i = 0; i < getCustomerList().size(); i++)
                    {
                        model.addElement(getCustomerList().get(i).toString());
                    }
                    lstCustomerList.setModel(model);
                }
            }
        });

        btnRemoveCustomer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                try
                {
                    removeCustomer(lstCustomerList.getSelectedIndex());
                } catch (IOException ex)
                {
                    Logger.getLogger(BankGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                DefaultListModel<String> model = new DefaultListModel<>();
                for (int i = 0; i < getCustomerList().size(); i++)
                {
                    model.addElement(getCustomerList().get(i).toString());
                }
                lstCustomerList.setModel(model);
            }
        });

        btnManageCustomer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                winFrame.remove(pnlAdminMeny);
                winFrame.remove(pnlCustomerList);
                winFrame.setVisible(false);
                Manage_Customer(winFrame, getCustomerList().get(lstCustomerList.getSelectedIndex()));
            }
        });

        btnExit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                winFrame.remove(pnlAdminMeny);
                winFrame.remove(pnlCustomerList);
                winFrame.setVisible(false);
                Starting_Screen(winFrame);
            }
        });

        //Set Button Enables--------------------------------------------------------
        lstCustomerList.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                btnRemoveCustomer.setEnabled(true);
                btnManageCustomer.setEnabled(true);
            }
        });

        //Adding to Leftside Panel--------------------------------------------------
        pnlAdminMeny.add(btnAddCustomer);
        pnlAdminMeny.add(btnRemoveCustomer);
        pnlAdminMeny.add(btnManageCustomer);
        pnlAdminMeny.add(btnExit);

        //Adding to JFrame----------------------------------------------------------
        winFrame.add(pnlAdminMeny);
        winFrame.add(pnlCustomerList);
        winFrame.setVisible(true);

    }

    public void Manage_Customer(JFrame winFrame, Customer customer)
    {
        //JFrame--------------------------------------------------------------------
        winFrame.setTitle("The AFLM Bank - Admin - Manage: " + customer.getFirstName() + " " 
                + customer.getLastName() + " - " + customer.getPersonalNumber());
        winFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        //Rightside Panel-----------------------------------------------------------
        JPanel pnlManageCustomerAccountList = new JPanel();
        pnlManageCustomerAccountList.setPreferredSize(new Dimension(450, 385));
        pnlManageCustomerAccountList.setLayout(new BorderLayout());

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
        pnlManageCustomerAccountList.add(lblAccountList, BorderLayout.NORTH);
        pnlManageCustomerAccountList.add(new JScrollPane(lstAccountList), BorderLayout.CENTER);

        //Leftside Panel------------------------------------------------------------
        JPanel pnlManageCustomerMeny = new JPanel();
        pnlManageCustomerMeny.setPreferredSize(new Dimension(400, 535));
        pnlManageCustomerMeny.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 38));

        //Customer Info Label-------------------------------------------------------
        JLabel lblPersonalInfo = new JLabel();
        lblPersonalInfo.setFont(new Font(lblPersonalInfo.getFont().getName(), lblPersonalInfo.getFont().getStyle(), 15));
        lblPersonalInfo.setText("<html>Name - " + customer.getFirstName()
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
        btnRemoveAccount.setEnabled(false);

        JButton btnViewTransactions = new JButton("<html>View<br/>Transactions<html>");
        btnViewTransactions.setPreferredSize(new Dimension(125, 100));

        JButton btnExit = new JButton("Exit");
        btnExit.setPreferredSize(new Dimension(300, 50));

        //Listener------------------------------------------------------------------
        
        btnAddAccount.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                AddAccount aa = new AddAccount(winFrame, true, customer);
                aa.setModal(true);
                aa.setVisible(true);
                if (aa.getController() == 1)
                {
                    DefaultListModel<String> model = new DefaultListModel<>();
                    for (int i = 0; i < customer.getSavingAccountList().size(); i++)
                    {
                        model.addElement(customer.getSavingAccountList().get(i).toString());
                    }
                    for (int i = 0; i < customer.getCreditAccountList().size(); i++)
                    {
                        model.addElement(customer.getCreditAccountList().get(i).toString());
                    }
                    lstAccountList.setModel(model);
                }
            }
        });
        
        btnChangeName.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                ChangeName ch = new ChangeName(winFrame, true, customer);
                ch.setModal(true);
                ch.setVisible(true);
                if (ch.getController() == 1)
                {
                    winFrame.setTitle("The AFLM Bank - Admin - Manage: " + customer.getFirstName() + " "
                            + customer.getLastName() + " - " + customer.getPersonalNumber());

                    lblPersonalInfo.setText("<html>Name - " + customer.getFirstName()
                            + "<br/>Lastname - " + customer.getLastName()
                            + "<br/>SSN - " + customer.getPersonalNumber() + "<html>");
                }
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
                
                DefaultListModel<String> model = new DefaultListModel<>();
                for (int i = 0; i < customer.getSavingAccountList().size(); i++)
                {
                    model.addElement(customer.getSavingAccountList().get(i).toString());
                }
                for (int i = 0; i < customer.getCreditAccountList().size(); i++)
                {
                    model.addElement(customer.getCreditAccountList().get(i).toString());
                }
                lstAccountList.setModel(model);
                btnRemoveAccount.setEnabled(false);
            }
        });

        btnViewTransactions.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                winFrame.remove(pnlManageCustomerAccountList);
                winFrame.remove(pnlManageCustomerMeny);
                winFrame.setVisible(false);
                Transaction_Screen(winFrame, customer, "m");
            }
        });
        
        lstAccountList.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                btnRemoveAccount.setEnabled(true);
            }
        });
        
        btnExit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                winFrame.remove(pnlManageCustomerAccountList);
                winFrame.remove(pnlManageCustomerMeny);
                winFrame.setVisible(false);
                Admin_Screen(winFrame);
            }
        });

        //Adding to Leftside Panel--------------------------------------------------
        pnlManageCustomerMeny.add(lblPersonalInfo);
        pnlManageCustomerMeny.add(btnAddAccount);
        pnlManageCustomerMeny.add(btnChangeName);
        pnlManageCustomerMeny.add(btnRemoveAccount);
        pnlManageCustomerMeny.add(btnViewTransactions);
        pnlManageCustomerMeny.add(btnExit);

        //Adding to JFrame----------------------------------------------------------
        winFrame.add(pnlManageCustomerMeny);
        winFrame.add(pnlManageCustomerAccountList);
        winFrame.setVisible(true);
    }

    public void Customer_Screen(JFrame winFrame, Customer customer) throws FileNotFoundException
    {
        winFrame.setTitle("The AFLM Bank - " + customer.getFirstName()+ " " 
                + customer.getLastName() + " - " + customer.getPersonalNumber());
        winFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

    //Rightside Panel-----------------------------------------------------------
        JPanel pnlCustomerAccountList = new JPanel();
        pnlCustomerAccountList.setPreferredSize(new Dimension(450, 385));
        pnlCustomerAccountList.setLayout(new BorderLayout());

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
        pnlCustomerAccountList.add(lblAccountList, BorderLayout.NORTH);
        pnlCustomerAccountList.add(new JScrollPane(lstAccountList), BorderLayout.CENTER);

    //Leftside Panel------------------------------------------------------------
        JPanel pnlCustomerMenyPanel = new JPanel();
        pnlCustomerMenyPanel.setPreferredSize(new Dimension(400, 535));
        pnlCustomerMenyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 23));

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
        JLabel lblCreditLimit = new JLabel("<html>Credit Limit = -5000"
                + "<br/>Current Credit = " + customer.getCurrentCredit() + "<html>");
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
                        lblCreditLimit.setText("<html>Credit Limit = -5000"
                                + "<br/>Current Credit = " + customer.getCurrentCredit() + "<html>");
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
                    boolean answer = withdraw(customer, accountType, accountIndex, transactionSum);
                    
                    if (accountType.equalsIgnoreCase("s") && answer == true)
                    {
                        accountType = customer.getSavingAccountList().get(index).toString();
                        model.setElementAt(accountType, index);
                    } else if (accountType.equalsIgnoreCase("c") && answer == true)
                    {
                        accountType = customer.getCreditAccountList().get(index - customer.getSavingAccountList().size()).toString();
                        model.setElementAt(accountType, index);
                        lblCreditLimit.setText("<html>Credit Limit = -5000"
                                + "<br/>Current Credit = " + customer.getCurrentCredit() + "<html>");
                    } else
                    {
                        SavingsAccountError error = new SavingsAccountError(winFrame, true);
                        error.initComp();
                    }
                    
                }catch (Exception e)
                {
                    SavingsAccountError error = new SavingsAccountError(winFrame, true);
                }
            }
        });

        btnViewTransactions.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                winFrame.remove(pnlCustomerAccountList);
                winFrame.remove(pnlCustomerMenyPanel);
                winFrame.setVisible(false);
                Transaction_Screen(winFrame, customer, "c");
            }
        });

        btnExit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                winFrame.remove(pnlCustomerAccountList);
                winFrame.remove(pnlCustomerMenyPanel);
                winFrame.setVisible(false);
                Starting_Screen(winFrame);
            }
        });

    //Set Button Enables--------------------------------------------------------    
        
        txtAmount.addKeyListener(new KeyAdapter()
        {
           public void keyPressed(KeyEvent ke)
           {
               if(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode()== '\b' 
                       || ke.getKeyCode()== '.' && !txtAmount.getText().contains("."))
               {
                   txtAmount.setEditable(true);
               }
               else
               {
                   txtAmount.setEditable(false);
               }
           }
           
           public void keyTyped(KeyEvent e)
           {
               if(txtAmount.getText().length() >= 17)
                   e.consume();
           }
        });

        lstAccountList.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                txtAmount.setEnabled(true);
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
        pnlCustomerMenyPanel.add(lblAmount);
        pnlCustomerMenyPanel.add(txtAmount);
        pnlCustomerMenyPanel.add(btnAddMoney);
        pnlCustomerMenyPanel.add(btnWithdrawMoney);
        pnlCustomerMenyPanel.add(btnViewTransactions);
        pnlCustomerMenyPanel.add(lblCreditLimit);
        pnlCustomerMenyPanel.add(btnExit);

    //Adding to JFrame----------------------------------------------------------
        winFrame.add(pnlCustomerMenyPanel);
        winFrame.add(pnlCustomerAccountList);
        winFrame.setVisible(true);
    }
    
    public void Transaction_Screen(JFrame winFrame, Customer customer, String exitTo)
    {
        winFrame.setTitle("Transactions - " + customer.getFirstName()+ " " 
                + customer.getLastName() + " - " + customer.getPersonalNumber());
        winFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        //Rightside Panel-----------------------------------------------------------
        JPanel pnlCustomerAccountList = new JPanel();
        pnlCustomerAccountList.setPreferredSize(new Dimension(400, 300));
        pnlCustomerAccountList.setLayout(new BorderLayout());

    //Account List--------------------------------------------------------------
        JLabel lblAccountList = new JLabel("Account Typ" + " ".repeat(28) + "AccountNumber"
                + " ".repeat(21) + "Balance");

        JList<String> lstAccountList = new JList<>();
        DefaultListModel<String> accountModel = new DefaultListModel<>();
        lstAccountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstAccountList.setFont(new Font(lstAccountList.getFont().getName(), Font.PLAIN, 14));

        for (int i = 0; i < customer.getSavingAccountList().size(); i++)
        {
            accountModel.addElement(customer.getSavingAccountList().get(i).toString());
        }
        for (int i = 0; i < customer.getCreditAccountList().size(); i++)
        {
            accountModel.addElement(customer.getCreditAccountList().get(i).toString());
        }
        accountModel.addElement("Closed Accounts");
        lstAccountList.setModel(accountModel);
        lstAccountList.setVisibleRowCount(15);

    //Adding to Rightside Panel-------------------------------------------------
        pnlCustomerAccountList.add(lblAccountList, BorderLayout.NORTH);
        pnlCustomerAccountList.add(new JScrollPane(lstAccountList), BorderLayout.CENTER);
        
        //Leftside Panel-----------------------------------------------------------
        JPanel transactionList = new JPanel();
        transactionList.setPreferredSize(new Dimension(410, 500));
        transactionList.setLayout(new BorderLayout());
        
        JLabel lblTransactions = new JLabel("Transactions");
        
        JList<String> lstTransaction = new JList<>();
        DefaultListModel<String> transactionModel = new DefaultListModel<>();
        lstTransaction.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstTransaction.setFont(new Font(lstTransaction.getFont().getName(), Font.PLAIN, 14));
        lstTransaction.setVisibleRowCount(15);

        JButton btnExit = new JButton("Exit");
        //Action Listeners-----------------------------------------------------------
        lstAccountList.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                transactionModel.removeAllElements();
                int index = lstAccountList.getSelectedIndex();
                int accountIndex = index;
                int accountId = 0;
                String accountType = "";
                
                if (lstAccountList.getSelectedValue().equalsIgnoreCase("Closed Accounts"))
                {
                    accountType = "r";
                } else if (index < customer.getSavingAccountList().size())
                {
                    accountId = customer.getSavingAccountList().get(index).getAccountNumber();
                    accountType = "s";
                } else if ((index - customer.getSavingAccountList().size()) <= customer.getCreditAccountList().size())
                {
                    accountIndex -= customer.getSavingAccountList().size();
                    accountId = customer.getCreditAccountList().get(accountIndex).getAccountNumber();
                    accountType = "c";
                }
                
                DefaultListModel<String> transactionModel = new DefaultListModel<>();
                if (accountType.equalsIgnoreCase("s"))
                {
                    accountId = customer.getSavingAccountList().get(index).getAccountNumber();
                    for (int i = customer.getTransactionList().size() - 1; i >= 0; i--)
                    {
                        if (customer.getTransactionList().get(i).getAccountNumber() == accountId)
                        {
                            String[] s = customer.getTransactionList().get(i).getTransactionString().split("!");
                            String transactionItem = "<html>" + s[0] + " - Date: " + s[2] + "<br/>" + s[1] + "<br/>"
                                    + "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ";
                            transactionModel.addElement(transactionItem);
                        }
                    }
                } else if (accountType.equalsIgnoreCase("c"))
                {
                    accountId = customer.getCreditAccountList().get(accountIndex).getAccountNumber();
                    for (int i = customer.getTransactionList().size() - 1; i >= 0; i--)
                    {
                        if (customer.getTransactionList().get(i).getAccountNumber() == accountId)
                        {
                            String[] s = customer.getTransactionList().get(i).getTransactionString().split("!");
                            String transactionItem = "<html>" + s[0] + " - Date: " + s[2] + "<br/>" + s[1] + "<br/>"
                                    + "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ";
                            transactionModel.addElement(transactionItem);
                        }
                    }
                }else if (accountType.equalsIgnoreCase("r"))
                {
                    for (int i = customer.getTransactionList().size() - 1; i >= 0; i--)
                    {
                        if (customer.getTransactionList().get(i).getTransactionType().equalsIgnoreCase("r"))
                        {
                            String[] s = customer.getTransactionList().get(i).getTransactionString().split("!");
                            String transactionItem = "<html>" + s[0] + " - Date: " + s[2] + "<br/>" + s[1] + "<br/>"
                                    + "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ";
                            transactionModel.addElement(transactionItem);
                        }
                    }
                }

                lstTransaction.setModel(transactionModel);
            }
        });
        
        btnExit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                winFrame.remove(transactionList);
                winFrame.remove(pnlCustomerAccountList);
                winFrame.setVisible(false);
                try
                {
                    if (exitTo.equalsIgnoreCase("c"))
                    {
                        Customer_Screen(winFrame, customer);
                    } else if (exitTo.equalsIgnoreCase("m"))
                    {
                        Manage_Customer(winFrame, customer);
                    }
                } catch (FileNotFoundException ex)
                {
                    Logger.getLogger(BankGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        lstTransaction.setModel(transactionModel);
        transactionList.add(lblTransactions);
        transactionList.add(new JScrollPane(lstTransaction), BorderLayout.CENTER);
        transactionList.add(btnExit, BorderLayout.SOUTH);
        
        winFrame.add(transactionList);
        winFrame.add(pnlCustomerAccountList);
        winFrame.setVisible(true);
    }
    
}