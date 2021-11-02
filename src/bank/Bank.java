package bank;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Bank
{

    public ArrayList<String> getCustomers()
    {
        ArrayList<String> ar = new ArrayList<String>();
        return ar;
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
