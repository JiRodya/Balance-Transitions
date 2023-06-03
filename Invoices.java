

/**
 * Title:       Invoices
 * Semester:    COP3337-Summer2022
 * @author:     Dianelys Rocha
 * PantherID:   6272943
 *
 * I affirm that this program is entirely my own work. Nothing, but the
 * classes/methods given by the professor, is the work of any other person.
 *
 * This class is a helper for BalanceTransactions.java. It allows to store the
 * information about the transactions as an object of type Invoice.
 */



public class Invoices
{
    private int ID;
    private double transaction;
    private char tType;

    /**
     *Constructor
     *@param transaction: amount
     *@param tType stores paid or received
     *@param id stores  the transaction ID
     */

    public Invoices(int id, double Transaction, char type)
    {
        ID=id;
        transaction = Transaction;
        tType = Character.toUpperCase(type);
    }


    /**
     * @return the Transaction ID
     */
    public int getID()
    {
        return ID;
    }

    /**
     * @return the transaction amount
     */
    public double getTransaction()
    {
        return transaction;
    }

    /**
     * @return the transaction type
     */
    public char gettType()
    {
        return tType;
    }

}
