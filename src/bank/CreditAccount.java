package bank;

public class CreditAccount {
    private final int accountNumber;
    private final String accountType = "c";
    private double accountSum = 0;
    
    CreditAccount ()
    {
        this.accountNumber = getAccountNumberFromFile();
    }
    
    private int getAccountNumberFromFile()
    {
        
        return 0;
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
}
