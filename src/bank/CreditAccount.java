package bank;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Scanner;

public class CreditAccount
{

    private long personalNumber;
    private int accountNumber;
    private String accountType = "c";
    private double accountSum = 0;

    CreditAccount(long personalNumber) throws FileNotFoundException
    {
        getAccountNumberFromFile();
        this.personalNumber = personalNumber;
    }
    
    CreditAccount(int accountNumber, double accountSum)
    {
        this.accountNumber = accountNumber;
        this.accountSum = accountSum;
    }

    private void getAccountNumberFromFile() throws FileNotFoundException
    {
        Scanner in = new Scanner(new File("AccountNumber.txt"));
        String s = in.nextLine();
        int konto = Integer.parseInt(s);
        this.accountNumber = konto;
        PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream("AccountNumber.txt")));
        konto++;
        p.println(konto);
        p.close();
    }

    public double getAccountSum()
    {
        return accountSum;
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public String getAccountType()
    {
        return accountType;
    }
    
    public void addMoney(double transactionSum)
    {
        accountSum += transactionSum;
    }
    
    public void withdrawMoney(double transactionSum)
    {
        accountSum -= transactionSum;
    }

    public String toString()
    {
        return "Credit Account" + " ".repeat(15) + accountNumber + " ".repeat(25) + accountSum;
    }
}
