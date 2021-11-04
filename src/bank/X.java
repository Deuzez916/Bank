package bank;

import java.util.Scanner;

public class X {

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run)
        {
            System.out.print("\nMeny"
                    + "\n1 - Bank Admin"
                    + "\n2 - Customer"
                    + "\n3 - Exit"
                    + "\n"
                    + "\nChoice - ");
            String choice = scan.nextLine();
            
            switch (choice)
            {
                case "1":
                    bankAdmin();
                    break;
                case "2":
                    customer();
                        break;
                case "3":
                    run = false;
                    System.out.println("\nExiting bank");
                    break;
                default:
                    System.out.println("\nChoose between 1 and 3.");
                    break;
            }
        }
    }
    
    public static void bankAdmin()
    {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run)
        {
            System.out.print("\nBank Admin meny"
                    + "\n1 - View customer"
                    + "\n2 - Manage customer"
                    + "\n3 - Create customer"
                    + "\n4 - Remove customer"
                    + "\n5 - Go back"
                    + "\n"
                    + "\nChoice - ");
            String choice = scan.nextLine();

            switch (choice)
            {
                case "1":
                    viewCustomers();
                    break;
                case "2":
                    manageCustomers();
                    break;
                case "3":
                    createCustomers();
                    break;
                case "4":
                    removeCustomers();
                    break;
                case "5":
                    run = false;
                    break;
                default:
                    System.out.println("\nChoose between 1 - 4.");
                    break;
            }
        }
    }
    
    private static void viewCustomers()
    {
        System.out.println("\nView customer");
    }

    private static void manageCustomers()
    {
        System.out.println("\nManage customer");
    }

    private static void createCustomers()
    {
        System.out.println("\nCreate customer");
    }

    private static void removeCustomers()
    {
        System.out.println("\nRemove customer");
    }
    
    public static void customer()
    {
        Scanner scan = new Scanner(System.in);
        boolean run = true;
        while (run)
        {
            System.out.print("\nCustomer meny"
                    + "\n1 - View Accounts"
                    + "\n2 - Add money"
                    + "\n3 - Take out money "
                    + "\n4 - Go back"
                    + "\n"
                    + "\nChoice - ");
            String choice = scan.nextLine();

            switch (choice)
            {
                case "1":
                    viewAccounts();
                    break;
                case "2":
                    addMoney();
                    break;
                case "3":
                    takeOutMoney();
                    break;
                case "4":
                    run = false;
                    break;
                default:
                    System.out.println("\nChoose between 1 - 4.");
                    break;
            }
        }
    }

    private static void viewAccounts()
    {
        System.out.println("\nView account");
    }

    private static void addMoney()
    {
        System.out.println("\nAdd money");
    }

    private static void takeOutMoney()
    {
        System.out.println("\nTake out money");
    }
}
