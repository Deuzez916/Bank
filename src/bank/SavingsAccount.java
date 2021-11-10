package bank;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class SavingsAccount
{

    private long personalNumber;
    private int accountNumber;
    private String accountType = "s";
    private double accountSum = 0;

    SavingsAccount(long personalNumber) throws FileNotFoundException
    {
        getAccountNumberFromFile();
        this.personalNumber = personalNumber;
    }
    
    SavingsAccount(int accountNumber, double accountSum)
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
        return "Savings Account" + " ".repeat(12) + accountNumber + " ".repeat(25) + accountSum;
    }
}
