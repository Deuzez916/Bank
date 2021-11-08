package bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Bank
{
    Bank() throws FileNotFoundException
    {
        BankGUI bankGUI = new BankGUI();
        
    }
    
    ArrayList<Customer> customerList = new ArrayList<>();

    public void addCustomer(String name, String lastName, long personalNumber) throws FileNotFoundException, IOException
    {
       Customer customer = new Customer(name, lastName, personalNumber);
       customerList.add(customer);
       addCustomerToFile(name, lastName, personalNumber);
    }

    public  ArrayList<Customer> getCustomerList()
    {
        return customerList;
    }
    
    public static void addCustomerToFile(String name, String lastName, long personalNumber) throws IOException
    {
        BufferedWriter bfWriter = new BufferedWriter(new FileWriter("CustomerList.txt", true));
        bfWriter.write(name + "," + lastName + "," + personalNumber + "\n");
        bfWriter.close();
    }
    
    public void uppdateCustomerToFile() throws IOException
    {
        BufferedWriter bfWriter = new BufferedWriter(new FileWriter("CustomerList.txt"));
        
        for (Customer customer : customerList)
        {
            bfWriter.write(customer.getName() + "," + customer.getLastName()+ "," + customer.getPersonalNumber() + "\n");
        }
        bfWriter.close();
    }
    //--------------------------------------------------------------------------
    void getCustomers() throws IOException
    {
        
        try (BufferedReader fileIn = Files.newBufferedReader(Paths.get("CustomerList.txt")))
        {
            for(String s;(s = fileIn.readLine()) != null;)
            {
                String [] splitString = s.split(",");
                customerList.add(new Customer(splitString[0], splitString[1], Long.parseLong(splitString[2])));
            }
        }
    }

    // Returnerar true om kund skapades annars returneras false.
    public boolean addCustomer(String name, long personalNumber)
    {
        return true;
    }

    public ArrayList<String> getCustomer(long personalNumber)
    {
        ArrayList<String> ar = new ArrayList<String>();
        return ar;
    }

    /*
    Returnerar true om namnet ändrades annars returnerar false (om
    kunden inte fanns).
    */
    public boolean changeCustomerName(String name, long personalNumber)
    {
        return true;
    }

    /*
    Listan som returneras ska innehålla information om alla konton som togs
    bort och saldot som kunden får tillbaka.
     */
    public ArrayList<String> removeCustomer(long personalNumber)
    {
        ArrayList<String> ar = new ArrayList<String>();
        return ar;
    }

    // Returnerar kontonumret som det skapade kontot fick
    public int addSavingsAccount(long personalNumber)
    {
        return 0;
    }

    /*
    Returnerar en String som innehåller presentation av kontot med
    kontonnummer accountId som tillhör kunden personalNumber
    (kontonummer, saldo, kontotyp).
     */
    public String getAccount(long personalNumber, int accountId)
    {
        return "0";
    }

    /*    
    Gör en insättning på konto med kontonnummer accountId som tillhör
    kunden personalNumber, returnerar true om det gick bra annars false.
    */
    public boolean deposit(long personalNumber, int accountId, BigDecimal amount)
    {
        return true;
    }

    /*
    Gör ett uttag på konto med kontonnummer accountId som tillhör kunden
    personalNumber, returnerar true om det gick bra annars false.
     */
    public boolean withdraw(long personalNumber, int accountId, BigDecimal amount)
    {
        return true;
    }
    
    /*
    Stänger ett konto med kontonnummer accountId som tillhör kunden personalNumber
    . Returnerar information om det konto som stängdes.
     */
    public String closeAccount(long personalNumber, int accountId)
    {
        return "0";
    }
    
    //Detta visar transaktionshistoriken för ett visst kundkonto
    public ArrayList<String> getTransactions(long personalNumber, int accountId)
    {
        ArrayList<String> ar = new ArrayList<String>();
        return ar;
    }
}
