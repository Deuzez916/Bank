package bank;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class BankGUI extends JFrame
{
    SavingsAccount s = new SavingsAccount(199506207423L);
    
    BankGUI() throws FileNotFoundException
    {
        JFrame customerScreen = new JFrame("Customer");
        customerScreen.setSize(900, 575);
        customerScreen.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        
        //List Handeling--------------------------------------------------------
        JPanel listPanel = new JPanel();
        listPanel.setPreferredSize(new Dimension(450, 385));
        listPanel.setLayout(new BorderLayout());
        
        JLabel lblAccountList = new JLabel("Account Typ" + " ".repeat(28) + "AccountNumber" 
                + " ".repeat(21) + "Balance"); 
        
        JList<String> lstAccountList = new JList<>();
        DefaultListModel<String> model = new DefaultListModel<>();
        lstAccountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	lstAccountList.setFont(new Font(lstAccountList.getFont().getName(), Font.PLAIN, 14));
        
        lstAccountList.setModel(model);
        model.addElement(s.toString());
        
        
        lstAccountList.setVisibleRowCount(10);
        listPanel.add(lblAccountList, BorderLayout.NORTH);
        listPanel.add(new JScrollPane(lstAccountList), BorderLayout.CENTER);
        
        //Leftside Handeling----------------------------------------------------
        JPanel leftSide = new JPanel();
        leftSide.setPreferredSize(new Dimension(400, 535));
        leftSide.setLayout(new FlowLayout(FlowLayout.CENTER,50,25));
        
        JLabel lblAmount = new JLabel();
        lblAmount.setText("<html>1. Choose an account from the list"
                + "<br/>2. Then type in the amount in field below"
                + "<br/>3. Press Add or Withdraw <html>");
        lblAmount.setPreferredSize(new Dimension(300, 42));
        
        
        JTextField txtAmount = new JTextField();
        txtAmount.setPreferredSize(new Dimension(300, 50));
        txtAmount.setFont(new Font(txtAmount.getFont().getName(),txtAmount.getFont().getStyle(),30));
        
        JButton btnAddMoney = new JButton("<html>Add<br/>Money<html>");
        btnAddMoney.setPreferredSize(new Dimension(125, 100));
        
        JButton btnWithdrawMoney = new JButton("<html>Withdraw<br/>Money<html>");
        btnWithdrawMoney.setPreferredSize(new Dimension(125, 100));
        
        JButton btnViewTransactions = new JButton("View Transactions");
        btnViewTransactions.setPreferredSize(new Dimension(300, 50));
        
        JLabel lblCreditLimit = new JLabel("<html>Credit Limit = - 5000 "
                + "<br/>Current Credit = " + 0 + "<html>"); 
        lblCreditLimit.setPreferredSize(new Dimension(300, 28));
        
        JButton btnExit = new JButton("Exit");
        btnExit.setPreferredSize(new Dimension(300, 50));
        
        leftSide.add(lblAmount);
        leftSide.add(txtAmount);
        leftSide.add(btnAddMoney);
        leftSide.add(btnWithdrawMoney);
        leftSide.add(btnViewTransactions);
        leftSide.add(lblCreditLimit);
        leftSide.add(btnExit);
        
        //----------------------------------------------------------------------
        customerScreen.add(leftSide);
        customerScreen.add(listPanel);
        customerScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerScreen.setVisible(true);
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {
        new BankGUI();
    }
}
