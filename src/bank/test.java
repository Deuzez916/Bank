package bank;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class test {
    public static void main(String[] args) throws FileNotFoundException
    {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd\tHH:mm").format(Calendar.getInstance().getTime());
        System.out.println(timeStamp);
        
        
    }

    
    
}
