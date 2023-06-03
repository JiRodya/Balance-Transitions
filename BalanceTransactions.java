
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Title:       BalanceTransactions
 * Semester:    COP3337-Summer2022
 * @author:     Dianelys Rocha
 *
 * This program reads a .txt file holding the information for different
 * transactions. The information is given in this order: ID, amount, type of
 * transaction. Then, the program asks the user for initial and final balance for
 * that day, and checks if the transactions in the .txt agree with that
 * information.
 */


public class BalanceTransactions
{

    /**
     * @param inputFile to access the .txt
     * @param fileName to store the name of the file given by the user
     *
     * @throws FileNotFoundException
     *if file is not found, it will ask the user for a different name
     */
    public static Scanner InitialSetup()
    {
        File inputFile;
        String fileName;
        Scanner userInput = new Scanner(System.in);
        Scanner fileScanner = null;
        boolean validFile = false;// to check if file was read or not

        do
        {
            fileName = userInput.next();
            try
            {
                inputFile = new File(fileName);
                fileScanner = new Scanner(inputFile);
                validFile = true;
            }
            catch (FileNotFoundException e)
            {
                System.out.println("File not found, please try a different name: ");
            }

        } while (!validFile);

        userInput.close();

        return fileScanner;
    }

    /**
     *@param fileScanner to read the values in the .txt
     *@return the ArrayList of Invoices @param inv
     */

    public static ArrayList<Invoices> processInvoices(Scanner fileScanner)
    {
        int id, invCounter = 0;
        double transaction;
        char type;
        ArrayList<Invoices> inv = new ArrayList<Invoices>();

        while (fileScanner.hasNext())
        {
            id = Integer.parseInt(fileScanner.next());
            transaction = Double.parseDouble(fileScanner.next());
            type = fileScanner.nextLine().charAt(1);

            Invoices invoice = new Invoices(id, transaction, type);
            inv.add(invoice);
        }

        return inv;
    }

    /**
     *@param inv the ArrayList of Invoices
     *this method checks wether the final Balance corresponds to the Expected
     *value after doing the indicated transactions in the .txt file
     *if expected value corresponds with Final Balance, a message to indicate that
     *there were no errors will prompt.
     *if expected value does not correspond with final Balance, an error message
     *will appear including the expected value.
     */

    public static void checkBalances(ArrayList<Invoices> inv)
    {
        double initialBalance = 0, finalBalance = 0,expected=0;
        Scanner userInput = new Scanner(System.in);

        System.out.print("Enter the initial balance for the day: ");
        initialBalance = userInput.nextDouble();
        expected=initialBalance;

        System.out.print("Enter the final balance for the day: ");
        finalBalance = userInput.nextDouble();

        for (Invoices i : inv)
        {

            if (i.gettType() == 'R')
            {
                expected += i.getTransaction();
            }
            else if (i.gettType() == 'P')
            {
                expected -= i.getTransaction();
            }
        }

        if (expected == finalBalance)
        {
            System.out.println("\n\nDone. No errors found");
        }
        else
        {
            System.out.println("\n\nError. Final Balance does not match the expected value");
            System.out.println("Expected value: "+expected);
        }
    }


    /**
     *main method to organize the sequence of actions
     */

    public static void main(String[] args)
    {

        Scanner fileScanner;
        ArrayList<Invoices> inv;

        System.out.print("Please enter the name of the file containing the transactions(include extension file): ");

        fileScanner = InitialSetup();
        inv = processInvoices(fileScanner);

        checkBalances(inv);
    }

}
