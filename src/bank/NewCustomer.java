package bank;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewCustomer extends JDialog
{
    
    public NewCustomer (JFrame parent, boolean modal)
    {
        super(parent, modal);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
    }

    public void initComponents()
        {
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
        JButton btnCreate = new JButton("Create");
        btnCreate.setEnabled(false);

        btnCancel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
              dispose(); 
            }
        });
        
        
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
        setSize(380, 300);
        setVisible(true);
    }
}
