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

class Transaction {

    private String accountType; 
    
    Transaction (long ssn, int accountNumber, String accountType, double oldSum, double transactionSum, double newSum, String transactionType)
    {
        this.accountType = accountType;
        
        if (transactionType.equals("+"))
        {
            addingMoney(accountNumber, transactionType, oldSum, transactionSum, newSum);
        } else if (transactionType.equals("-"))
        {
            withdrawingMoney(accountNumber, transactionType, oldSum, transactionSum, newSum);
        }
    }
    
    private String addingMoney(int accountNumber, String accountType, double oldSum, double transactionSum, double newSum)
    {
        String s = "";
        if (accountType.equals("s"))
        {
        s = "Savings account: " + accountNumber 
                + "\n" + formatSum(transactionSum) + " + " 
                + formatSum(oldSum) + " = " 
                + formatSum(newSum) 
                + "\n" + dateAndTime();
        } else if (accountType.equals("c"))
        {
        s = "Credit account: " + accountNumber 
                + "\n" + formatSum(transactionSum) + " + " 
                + formatSum(oldSum) + " = " 
                + formatSum(newSum) 
                + "\n" + dateAndTime();
        }
        return s;
    }
    
    private String withdrawingMoney(int accountNumber, String accountType, double oldSum, double transactionSum, double newSum)
    {
        String s = "";
        if (accountType.equals("s"))
        {
        s = "Savings account: " + accountNumber 
                + "\n" + formatSum(transactionSum) + " - " 
                + formatSum(oldSum) + " = " 
                + formatSum(newSum) 
                + "\n" + dateAndTime();
        } else if (accountType.equals("c"))
        {
        s = "Credit account: " + accountNumber 
                + "\n" + formatSum(transactionSum) + " - " 
                + formatSum(oldSum) + " = " 
                + formatSum(newSum) 
                + "\n" + dateAndTime();
        }
        return s;
    }
    
    private String formatSum(double sum)
    {
        return String.format("%.2f", sum);
    }
    
    private String dateAndTime()
    {
        return new SimpleDateFormat("yyyy/MM/dd\tHH:mm").format(Calendar.getInstance().getTime());
    }
    
}
