package bank;

import java.io.FileNotFoundException;
import java.io.IOException;

public class test
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        Bank bank = new Bank();
        bank.addCustomer("a", "A", 000000000003L);
        bank.addCustomer("b", "b", 000000000004L);
        bank.addCustomer("c", "c", 000000000005L);
        bank.addCustomer("Bengt", "Anderson", 195001307423L);
        bank.getCustomerList().get(3).addSavingAccountList();
        bank.getCustomerList().get(3).addSavingAccountList();
        bank.getCustomerList().get(3).addSavingAccountList();
        bank.getCustomerList().get(3).addSavingAccountList();
        bank.getCustomerList().get(3).addSavingAccountList();
        
        bank.getCustomerList().get(3).addCreditAccountList();
        bank.getCustomerList().get(3).addCreditAccountList();
        bank.getCustomerList().get(3).addCreditAccountList();
        bank.getCustomerList().get(3).addCreditAccountList();
        bank.getCustomerList().get(3).addCreditAccountList();
        
        BankGUI b = new BankGUI();
        System.out.println(bank.getCustomerList().size());
        b.LOAD_CUSTOMER(bank.getCustomerList().get(3));
        
        
    }
}