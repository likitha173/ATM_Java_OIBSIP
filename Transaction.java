import java.util.Date;

public class Transaction{

    private double amount;  //The Amount of this Transaction.
    private Date timestamp; //The time and date of this tranaction.
    private String memo;  //A memo for this transaction.
    private Account inAccount;  //The account in which the transaction was performed.
}