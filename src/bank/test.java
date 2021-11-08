package bank;

import java.io.FileNotFoundException;
import java.io.IOException;

public class test
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        Bank bank = new Bank();
        bank.getCustomers();
        System.out.println(bank.getCustomerList().get(0).getName());
        
    }
}