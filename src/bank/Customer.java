package bank;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class Customer {

    ArrayList<SavingsAccount> savingsAccountList = new ArrayList<>();
    ArrayList<CreditAccount> creditAccountList = new ArrayList<>();
    
    Customer(String name, String lastName, long ssn) throws FileNotFoundException
    {
        createAccountsList(ssn);
    }
    
    void createAccountsList(long ssn) throws FileNotFoundException
    {
        PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream(ssn + "Savings.txt")));
        p = new PrintStream(new BufferedOutputStream(new FileOutputStream(ssn + "Credit.txt")));
        p.close();
    }
}
