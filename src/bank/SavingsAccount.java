package bank;

public class SavingsAccount {

    private final int accountNumber;
    private final String accountType = "s";
    private double accountSum = 0;
    
    SavingsAccount ()
    {
        this.accountNumber = getAccountNumberFromFile();
    }
    
    private int getAccountNumberFromFile()
    {
        int i = 1;
        return i;
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
