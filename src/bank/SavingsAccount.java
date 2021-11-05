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
    int accountNumber;
    String accountType = "s";
    double accountSum = 0;

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

    public void setAccountSum(double accountSum)
    {
        this.accountSum = accountSum;
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public String toString()
    {
        return accountNumber + accountType + accountSum;
    }
}
