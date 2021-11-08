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
    private double oldSum;
    private double transactionSum;
    private double newSum;
    private final String transactionType;
    private final String dateTime;
    private final String transactionInfo;
    private String transactionString = "";

    //Creating new transaction
    Transaction(int accountNumber, String accountType, double oldSum, double transactionSum, double newSum, String transactionType)
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
            this.transactionString = addMoney();
        } else if (transactionType.equals("-"))
        {
            this.transactionString = withdrawMoney();
        } else if (transactionType.equalsIgnoreCase("r"))
        {
            this.transactionString = removeAccount();
        }
    }

    //Loading transaction from file
    Transaction(int accountNumber, String accountType, double oldSum, double transactionSum, double newSum, String transactionType, String dateTime)
    {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.oldSum = oldSum;
        this.transactionSum = transactionSum;
        this.newSum = newSum;
        this.transactionType = transactionType;
        this.dateTime = dateTime;
        this.transactionInfo = accountNumber + "," + accountType + ","
                + oldSum + "," + transactionSum + "," + newSum + ","
                + transactionType + "," + dateTime;

        if (transactionType.equals("+"))
        {
            this.transactionString = addMoney();
        } else if (transactionType.equals("-"))
        {
            this.transactionString = withdrawMoney();
        }else if (transactionType.equalsIgnoreCase("r"))
        {
            this.transactionString = removeAccount();
        }
    }

    public String addMoney()
    {
        String addMoneyString = "";
        if (accountType.equals("s"))
        {
            addMoneyString = "Savings account: ";
        } else if (accountType.equals("c"))
        {
            addMoneyString = "Credit account: ";
        }

        addMoneyString += accountNumber + "\n" + formatSum(oldSum) + " + "
                + formatSum(transactionSum) + " = " + formatSum(newSum)
                + "\n" + dateTime;
        return addMoneyString;
    }

    public String withdrawMoney()
    {
        String withdrawMoneyString = "";
        if (accountType.equals("s"))
        {
            withdrawMoneyString = "Savings account: ";
        } else if (accountType.equals("c"))
        {
            withdrawMoneyString = "Credit account: ";
        }

        withdrawMoneyString += accountNumber + "\n" + formatSum(oldSum) + " - "
                + formatSum(transactionSum) + " = " + formatSum(newSum)
                + "\n" + dateTime;

        return withdrawMoneyString;
    }

    public String removeAccount()
    {
        String removeAccountString = "";
        if (accountType.equals("s"))
        {
            removeAccountString = "Closing Savings account: ";
        } else if (accountType.equals("c"))
        {
            removeAccountString = "Closing Credit account: ";
        }

        removeAccountString += accountNumber + "\n" + formatSum(oldSum) + " - "
                + formatSum(transactionSum) + " = " + formatSum(newSum)
                + "\n" + dateTime;

        return removeAccountString;
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
