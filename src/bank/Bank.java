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
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Bank
{

    private static ArrayList<Customer> customerList;

    public static void main(String[] args) throws IOException
    {
        customerList = createCustomerList();

        BankGUI b = new BankGUI();
        b.Customer_Screen(b.getJFrame(), getCustomerList().get(0));
    }

    private static ArrayList<Customer> createCustomerList() throws FileNotFoundException, IOException
    {
        ArrayList<Customer> customerArrayList = new ArrayList<>();

        File fileController = new File("CustomerList.txt");
        if (fileController.exists() == false)
        {
            PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream("CustomerList.txt")));
            p.close();
        } else
        {
            try ( BufferedReader fileIn = Files.newBufferedReader(Paths.get("CustomerList.txt")))
            {
                for (String s; (s = fileIn.readLine()) != null;)
                {
                    String[] sInfo = s.split(",");

                    customerArrayList.add(new Customer(sInfo[0], sInfo[1], Long.parseLong(sInfo[2]), Double.parseDouble(sInfo[3])));
                }
            }
        }

        return customerArrayList;
    }

    public static void addCustomer(String name, String lastName, long personalNumber, double currentCredit) throws FileNotFoundException, IOException
    {
        Customer customer = new Customer(name, lastName, personalNumber, currentCredit);
        customerList.add(customer);
        addCustomerToFile(name, lastName, personalNumber, currentCredit);
    }

    public static ArrayList<Customer> getCustomerList()
    {
        return customerList;
    }

    public static void addCustomerToFile(String name, String lastName, long personalNumber, double currentCredit) throws IOException
    {
        try ( BufferedWriter bfWriter = new BufferedWriter(new FileWriter("CustomerList.txt", true)))
        {
            bfWriter.write(name + "," + lastName + "," + personalNumber + "," + currentCredit + "\n");
        }
    }

    public static void updateCustomerToFile() throws IOException
    {
        try ( BufferedWriter bfWriter = new BufferedWriter(new FileWriter("CustomerList.txt")))
        {
            for (Customer customer : customerList)
            {
                bfWriter.write(customer.getName() + "," + customer.getLastName() + "," 
                        + customer.getPersonalNumber() + "," + customer.getCurrentCredit() +"\n");
            }
        }
    }
    //--------------------------------------------------------------------------

    public static void getCustomers() throws IOException
    {

        try ( BufferedReader fileIn = Files.newBufferedReader(Paths.get("CustomerList.txt")))
        {
            for (String s; (s = fileIn.readLine()) != null;)
            {
                String[] splitString = s.split(",");
                customerList.add(new Customer(splitString[0], splitString[1], Long.parseLong(splitString[2]), Double.parseDouble(splitString[3])));
            }
        }
    }

    // Returnerar true om kund skapades annars returneras false.
    public static boolean addCustomer(String name, long personalNumber)
    {
        return true;
    }

    public static ArrayList<String> getCustomer(long personalNumber)
    {
        ArrayList<String> ar = new ArrayList<String>();
        ar.add(customerList.get(getCustomerIndex(personalNumber)).toString());
        return ar;
    }

    /*
    Returnerar true om namnet ändrades annars returnerar false (om
    kunden inte fanns).
     */
    public static boolean changeCustomerName(String name, long personalNumber)
    {
        return true;
    }

    /*
    Listan som returneras ska innehålla information om alla konton som togs
    bort och saldot som kunden får tillbaka.
     */
    public static ArrayList<String> removeCustomer(long personalNumber)
    {
        ArrayList<String> ar = new ArrayList<String>();
        return ar;
    }

    // Returnerar kontonumret som det skapade kontot fick
    public static int addSavingsAccount(long personalNumber)
    {
        return 0;
    }

    /*
    Returnerar en String som innehåller presentation av kontot med
    kontonnummer accountId som tillhör kunden personalNumber
    (kontonummer, saldo, kontotyp).
     */
    public static String getAccount(long personalNumber, int accountId)
    {
        return "0";
    }

    /*    
    Gör en insättning på konto med kontonnummer accountId som tillhör
    kunden personalNumber, returnerar true om det gick bra annars false.
     */
    public static boolean deposit(String accountType, Long personalNumber, int accountIndex, double transactionSum) throws IOException
    {
        Customer customer = customerList.get(getCustomerIndex(personalNumber));
        customer.addMoneyToAccount(accountIndex, transactionSum, accountType);
        if (accountType.equalsIgnoreCase("c")) updateCustomerToFile();
        return true;
    }

    /*
    Gör ett uttag på konto med kontonnummer accountId som tillhör kunden
    personalNumber, returnerar true om det gick bra annars false.
     */
    public boolean withdraw(Customer customer, String accountType, int accountIndex, double transactionSum) throws IOException
    {
        double withdrawCheck = 0;
        if (accountType.equalsIgnoreCase("s"))
        {
            withdrawCheck = customer.getSavingAccountList().get(accountIndex).getAccountSum();
            if (withdrawCheck - transactionSum >= 0)
            {
                customer.withdrawMoneyFromAccount(accountIndex, transactionSum, accountType);
                return true;
            }
        } else
        {
            withdrawCheck = customer.getCurrentCredit();
            if (withdrawCheck - transactionSum >= customer.getCreditLimit())
            {
                customer.withdrawMoneyFromAccount(accountIndex, transactionSum, accountType);
                updateCustomerToFile();
                return true;
            }
        }
                return false;
    }

    
    public static void closeAccount(long personalNumber, int accountId) throws IOException
    {
        Customer customer = customerList.get(getCustomerIndex(personalNumber));
        for (int i = 0; i < customer.getSavingAccountList().size(); i++)
        {
            if (customer.getSavingAccountList().get(i).getAccountNumber() == accountId)
            {
                customer.removeSavingsAccount(accountId);
                break;
            }
        }

        for (int i = 0; i < customer.getCreditAccountList().size(); i++)
        {
            if (customer.getCreditAccountList().get(i).getAccountNumber() == accountId)
            {
                customer.removeCreditAccount(accountId);
                break;
            }
        }
    }

    //Detta visar transaktionshistoriken för ett visst kundkonto
    public static ArrayList<String> getTransactions(long personalNumber, int accountId)
    {
        ArrayList<String> ar = new ArrayList<String>();
        for (int i = 0; i < 10; i++)
        {
            ar.add("");
        }
        return ar;
    }
    
    private static int getCustomerIndex(Long personalNumber)
    {
        int customerIndex = 0;
        for (int i = 0; i < customerList.size(); i++)
        {
            if (customerList.get(i).getPersonalNumber() == personalNumber)
            {
                customerIndex = i;
                break;
            }
        }
        return customerIndex;
    }
}
