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
    private final double creditLimet = -5000.00;
    private double currentCredit = 0;
    

    ArrayList<SavingsAccount> savingAccountList = new ArrayList<SavingsAccount>();
    ArrayList<CreditAccount> creditAccountList = new ArrayList<CreditAccount>();
    ArrayList<Transaction> transactionList = new ArrayList<Transaction>();

    Customer(String name, String lastName, long personalNumber) throws FileNotFoundException, IOException
    {
        this.name = name;
        this.lastName = lastName;
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
        try ( BufferedReader fileIn = Files.newBufferedReader(Paths.get(personalNumber + "Savings.txt")))
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
        try ( BufferedReader fileIn = Files.newBufferedReader(Paths.get(personalNumber + "Credit.txt")))
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
        try ( BufferedReader fileIn = Files.newBufferedReader(Paths.get(personalNumber + "Transaction.txt")))
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

    public void addSavingAccountList() throws FileNotFoundException, IOException
    {
        SavingsAccount s = new SavingsAccount(personalNumber);
        savingAccountList.add(s);
        printToSavingsAccountList(s);

    }

    public void addCreditAccountList() throws FileNotFoundException, IOException
    {
        CreditAccount c = new CreditAccount(personalNumber);
        creditAccountList.add(c);
        printToCreditAccountList(c);
    }

    public void addTransactionList(int accountNumber, String accountType, double oldSum, double transactionSum, double newSum, String transactionType) throws IOException
    {
        Transaction t = new Transaction(accountNumber, accountType, oldSum, transactionSum, newSum, transactionType);
        transactionList.add(t);
        printToTransactionFile(personalNumber, t);
    }
    
    public void removeSavingsAccount(int accountNumber) throws IOException
    {
        int index = 0;
        for (int i = 0; i < savingAccountList.size(); i++)
        {
            if (accountNumber == savingAccountList.get(i).getAccountNumber())
            {
                index = i;
                break;
            }
        }
        
        double oldSum = savingAccountList.get(index).getAccountSum();
        double transactionSum = oldSum;
        double newSum = oldSum - transactionSum;
        addTransactionList(accountNumber, "s", oldSum, transactionSum, newSum, "r");
        savingAccountList.remove(index);
    }
    
    public void removeCreditAccount(int accountNumber) throws IOException
    {
        int index = 0;
        for (int i = 0; i < creditAccountList.size(); i++)
        {
            if (accountNumber == creditAccountList.get(i).getAccountNumber())
            {
                index = i;
                break;
            }
        }
        
        double oldSum = creditAccountList.get(index).getAccountSum();
        double transactionSum = oldSum;
        double newSum = oldSum - transactionSum;
        addTransactionList(accountNumber, "c", oldSum, transactionSum, newSum, "r");
        creditAccountList.remove(index);
    }

    public void printToSavingsAccountList(SavingsAccount s) throws IOException
    {

        try ( BufferedWriter p = new BufferedWriter(new FileWriter(personalNumber + "Savings.txt", true)))
        {
            p.write(s.getAccountNumber() + "," + s.getAccountSum() + "," + s.getAccountType() + "\n");
        }

    }

    public void updateSavingsAccountList() throws FileNotFoundException, IOException
    {
        try ( BufferedWriter p = new BufferedWriter(new FileWriter(personalNumber + "Savings.txt")))
        {
            for (SavingsAccount savingsAccount : savingAccountList)
            {
                p.write(savingsAccount.getAccountNumber() + "," + savingsAccount.getAccountSum() + "," + savingsAccount.getAccountType() + "\n");
            }
        }

    }

    public void printToCreditAccountList(CreditAccount c) throws IOException
    {

        try ( BufferedWriter p = new BufferedWriter(new FileWriter(personalNumber + "Credit.txt", true)))
        {
            p.write(c.getAccountNumber() + "," + c.getAccountSum() + "," + c.getAccountType() + "\n");
        }

    }

    public void updateCreditAccountList() throws FileNotFoundException, IOException
    {
        try ( BufferedWriter p = new BufferedWriter(new FileWriter(personalNumber + "Credit.txt")))
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
    
    public void addMoneyToAccount(int accountIndex, double transactionSum, String accountType) throws IOException
    {
        int accountNumber = 0;
        double oldSum = 0;
        double newSum = 0;
        String transactionType = "+";
        
        if (accountType.equalsIgnoreCase("s"))
        {
            accountNumber = getSavingAccountList().get(accountIndex).getAccountNumber();
            oldSum = getSavingAccountList().get(accountIndex).getAccountSum();
            getSavingAccountList().get(accountIndex).addMoney(transactionSum);
            updateSavingsAccountList();
            newSum = getSavingAccountList().get(accountIndex).getAccountSum();

        } else
        {
            accountNumber = getCreditAccountList().get(accountIndex).getAccountNumber();
            oldSum = getCreditAccountList().get(accountIndex).getAccountSum();
            getCreditAccountList().get(accountIndex).addMoney(transactionSum);
            updateCreditAccountList();
            newSum = getCreditAccountList().get(accountIndex).getAccountSum();
        }
        addTransactionList(accountNumber, accountType, oldSum, transactionSum, newSum, transactionType);
        
    }
    
    public void withdrawMoneyFromAccount(int accountNumber, double transactionSum)
    {
        for (int i = 0; i < getSavingAccountList().size(); i++)
        {
            if (getSavingAccountList().get(i).getAccountNumber() == accountNumber)
            {
                getSavingAccountList().get(i).withdrawMoney(transactionSum);
                break;
            }
        }

        for (int i = 0; i < getCreditAccountList().size(); i++)
        {
            if (getCreditAccountList().get(i).getAccountNumber() == accountNumber)
            {
                getCreditAccountList().get(i).withdrawMoney(transactionSum);
                break;
            }
        }
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

    public double getCreditLimet()
    {
        return creditLimet;
    }

    public double getCurrentCredit()
    {
        return currentCredit;
    }

    public void setCurrentCredit(double currentCredit)
    {
        this.currentCredit = currentCredit;
    }
    
    @Override
    public String toString()
    {
       int firstRepeat = 31 - name.length();
       int secondRepeat = 29 - lastName.length();
       return name + " ".repeat(firstRepeat) + lastName + " ".repeat(secondRepeat) + personalNumber;
    }
    
}
