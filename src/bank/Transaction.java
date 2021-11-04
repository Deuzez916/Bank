package bank;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
* konto Id
* datum och klockslag
* transaktionstyp
* belopp
* saldo (efterTransaktionen)
 */
class Transaction
{

    private final int accountNumber;
    private final String accountType;
    private final double oldSum;
    private final double transactionSum;
    private final double newSum;
    private final String transactionType;
    private final String dateTime;
    private final String transactionInfo;
    private String transactionString = "";

    Transaction(int accountNumber, String accountType, double oldSum, double transactionSum,  double newSum, String transactionType)
    {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.oldSum = oldSum;
        this.transactionSum = transactionSum;
        this.newSum = newSum;
        this.transactionType = transactionType;
        this.dateTime = dateAndTime();
        this.transactionInfo = accountNumber + "," + accountType + "," 
                + oldSum + "," + transactionSum + "," + newSum + "," 
                + transactionType + "," + dateTime;

        if (transactionType.equals("+"))
        {
            this.transactionString = addingMoney();
        } else if (transactionType.equals("-"))
        {
            this.transactionString = withdrawingMoney();
        }
    }

    public String addingMoney()
    {
        String s = "";
        if (accountType.equals("s"))
        {
            s = "Savings account: ";
        } else if (accountType.equals("c"))
        {
            s = "Credit account: ";
        }
        
        s += accountNumber + "\n" + formatSum(transactionSum) + " + " + 
                formatSum(oldSum) + " = " + formatSum(newSum) + "\n" + dateTime;
        
        return s;
    }

    public String withdrawingMoney()
    {
        String s = "";
        if (accountType.equals("s"))
        {
            s = "Savings account: ";
        } else if (accountType.equals("c"))
        {
            s = "Credit account: ";
        }
        
        s += accountNumber + "\n" + formatSum(transactionSum) + " - " + 
                formatSum(oldSum) + " = " + formatSum(newSum) + "\n" + dateTime;

        return s;
    }

    private String formatSum(double sum)
    {
        return String.format("%.2f", sum);
    }

    private String dateAndTime()
    {
        return new SimpleDateFormat("yyyy/MM/dd    HH:mm").format(Calendar.getInstance().getTime());
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public double getOldSum()
    {
        return oldSum;
    }

    public double getTransactionSum()
    {
        return transactionSum;
    }

    public double getNewSum()
    {
        return newSum;
    }

    public String getTransactionType()
    {
        return transactionType;
    }

    public String getDateTime()
    {
        return dateTime;
    }

    public String getTransactionInfo()
    {
        return transactionInfo;
    }
    
    public String getTransactionString()
    {
        return transactionString;
    }
    
}
