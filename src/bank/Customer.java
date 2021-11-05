package bank;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Customer
{

    private final String name;
    private final String lastName;
    private final long personalNumber;

    ArrayList<SavingsAccount> savingAccountList = new ArrayList<SavingsAccount>();
    ArrayList<CreditAccount> creditAccountList = new ArrayList<CreditAccount>();
    ArrayList<Transaction> transactionList = new ArrayList<Transaction>();

    Customer(String name, String accountList, long personalNumber) throws FileNotFoundException, IOException
    {
        this.name = name;
        this.lastName = accountList;
        this.personalNumber = personalNumber;
        createAccountsList(personalNumber);
    }

    void createAccountsList(long personalNumber) throws FileNotFoundException, IOException
    {
        File fileController = new File(personalNumber + "Transaction.txt");
        if (fileController.exists() == false)
        {
            PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream(personalNumber + "Savings.txt")));
            p = new PrintStream(new BufferedOutputStream(new FileOutputStream(personalNumber + "Credit.txt")));
            p = new PrintStream(new BufferedOutputStream(new FileOutputStream(personalNumber + "Transaction.txt")));
            p.close();
        } else
        {
            loadSavingAccountList();
            loadCreditAccountList();
            loadTransactionList();
        }
    }

    private void loadSavingAccountList() throws FileNotFoundException, IOException
    {
        try ( BufferedReader fileIn = Files.newBufferedReader(Paths.get("CustomerList.txt")))
        {
            for (String s; (s = fileIn.readLine()) != null;)
            {
                String[] sInfo = s.split(",");

                savingAccountList.add(new SavingsAccount(Integer.parseInt(sInfo[0]), Double.parseDouble(sInfo[1])));
            }
        }
    }

    private void loadCreditAccountList() throws FileNotFoundException, IOException
    {
        try ( BufferedReader fileIn = Files.newBufferedReader(Paths.get("CustomerList.txt")))
        {
            for (String s; (s = fileIn.readLine()) != null;)
            {
                String[] sInfo = s.split(",");

                creditAccountList.add(new CreditAccount(Integer.parseInt(sInfo[0]), Double.parseDouble(sInfo[1])));
            }
        }
    }

    private void loadTransactionList() throws FileNotFoundException, IOException
    {
        try ( BufferedReader fileIn = Files.newBufferedReader(Paths.get("CustomerList.txt")))
        {
            for (String s; (s = fileIn.readLine()) != null;)
            {
                String[] sInfo = s.split(",");

                transactionList.add(new Transaction(Integer.parseInt(sInfo[0]), sInfo[1],
                        Double.parseDouble(sInfo[2]),
                        Double.parseDouble(sInfo[3]),
                        Double.parseDouble(sInfo[4]),
                        sInfo[5], sInfo[6]));
            }
        }
    }

    public void addStoreSavingAccountList() throws FileNotFoundException, IOException
    {
        SavingsAccount s = new SavingsAccount(personalNumber);
        savingAccountList.add(s);
        SavingsAccountInfo(s);

    }

    public void addStoreCreditAccountList() throws FileNotFoundException, IOException
    {
        CreditAccount c = new CreditAccount(personalNumber);
        creditAccountList.add(c);
        CreditAccountInfo(c);
    }

    public void addStoreTransactionList(int accountNumber, String accountType, double oldSum, double transactionSum, double newSum, String transactionType) throws IOException
    {
        Transaction t = new Transaction(accountNumber, accountType, oldSum, transactionSum, newSum, transactionType);
        transactionList.add(t);
        printToTransactionFile(personalNumber, t);
    }

    public void SavingsAccountInfo(SavingsAccount s) throws IOException
    {

        try ( BufferedWriter p = new BufferedWriter(new FileWriter(personalNumber + "Savings.txt", true)))
        {
            p.write(s.getAccountNumber() + "," + s.getAccountSum() + "," + s.getAccountType() + "\n");
        }

    }

    public void UpdateSavingsAccountInfo() throws FileNotFoundException, IOException
    {
        try ( BufferedWriter p = new BufferedWriter(new FileWriter(personalNumber + "Savings.txt")))
        {
            for (SavingsAccount savingsAccount : savingAccountList)
            {
                p.write(savingsAccount.getAccountNumber() + "," + savingsAccount.getAccountSum() + "," + savingsAccount.getAccountType() + "\n");
            }
        }

    }

    public void CreditAccountInfo(CreditAccount c) throws IOException
    {

        try ( BufferedWriter p = new BufferedWriter(new FileWriter(personalNumber + "Credit.txt", true)))
        {
            p.write(c.getAccountNumber() + "," + c.getAccountSum() + "," + c.getAccountType() + "\n");
        }

    }

    public void UpdateCreditAccountInfo() throws FileNotFoundException, IOException
    {
        try ( BufferedWriter p = new BufferedWriter(new FileWriter(personalNumber + "Savings.txt")))
        {
            for (CreditAccount creditAccount : creditAccountList)
            {
                p.write(creditAccount.getAccountNumber() + "," + creditAccount.getAccountSum() + "," + creditAccount.getAccountType() + "\n");
            }
        }

    }

    private void printToTransactionFile(long personalNumber, Transaction t) throws IOException
    {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(personalNumber + "Transaction.txt", true)))
        {
            writer.write(t.getTransactionInfo() + "\n");
        }
    }

    public ArrayList<SavingsAccount> getSavingAccountList()
    {
        return savingAccountList;
    }

    public ArrayList<CreditAccount> getCreditAccountList()
    {
        return creditAccountList;
    }

    public ArrayList<Transaction> getTransactionList()
    {
        return transactionList;
    }

    public String getName()
    {
        return name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public long getPersonalNumber()
    {
        return personalNumber;
    }

}
