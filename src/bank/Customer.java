package bank;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public  class Customer
{
    private final String name;
    private final String accountList;
    private final long personalNumber;
   
    
    ArrayList<SavingsAccount> storeSavingAccountList = new ArrayList<SavingsAccount>();
    ArrayList<CreditAccount> storeCreditAccountList = new ArrayList<CreditAccount>();
    ArrayList<Transaction> storeTransactionList = new ArrayList<Transaction>();

    Customer(String name, String accountList, long personalNumber) throws FileNotFoundException
    {
        this.name = name;
        this.accountList = accountList;
        this.personalNumber = personalNumber;
        createAccountsList(personalNumber);
    }
    
     void createAccountsList(long personalNumber) throws FileNotFoundException
    {
        PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream(personalNumber + "Savings.txt")));
        p = new PrintStream(new BufferedOutputStream(new FileOutputStream(personalNumber + "Credit.txt")));
        p = new PrintStream(new BufferedOutputStream(new FileOutputStream(personalNumber + "Transaction.txt")));
        p.close();
    }

    public void addStoreSavingAccountList() throws FileNotFoundException
    {
        storeSavingAccountList.add(new SavingsAccount(0, personalNumber, accountList));
    }

    public void addStoreCreditAccountList() throws FileNotFoundException
    {
        storeCreditAccountList.add(new CreditAccount(0, personalNumber, accountList));
    }
    
    public void addStoreTransactionList( int accountNumber, String accountType, double oldSum, double transactionSum, double newSum, String transactionType) throws IOException
    {
        Transaction t = new Transaction(accountNumber, accountType, oldSum, transactionSum, newSum, transactionType);
        storeTransactionList.add(t);
        printToTransactionFile(personalNumber, t);
    }

    public ArrayList<SavingsAccount> getStoreSavingAccountList()
    {
        return storeSavingAccountList;
    }

    public ArrayList<CreditAccount> getStoreCreditAccountList()
    {
        return storeCreditAccountList;
    }

    public ArrayList<Transaction> getStoreTransactionList()
    {
        return storeTransactionList;
    }
    
    void print()
    {
        System.out.println(this.accountList + "");
    }
    
    public String getName()
    {
        return name;
    }

    public String getAccountList()
    {
        return accountList;
    }
    
    public long getPersonalNUmber()
    {
        return personalNumber;
    }
    
    private void printToTransactionFile(long personalNumber ,Transaction t) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(personalNumber + "Transaction.txt", true));
        writer.write(t.getTransactionString() + "\n" + t.getTransactionInfo() + "\n|\n");
        writer.close();
    }

}