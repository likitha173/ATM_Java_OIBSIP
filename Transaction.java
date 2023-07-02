import java.util.Date;

public class Transaction{

    private double amount;  //The Amount of this Transaction.
    private Date timestamp; //The time and date of this tranaction.
    private String memo;  //A memo for this transaction.
    private Account inAccount;  //The account in which the transaction was performed.

    /*
    Create a new tranaction 
    @param amount the amount tranacted
    @param inAccount the account the tranaction belongs to
    */
    public Transaction(double amount, Account inAccount){
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";
    }

    /*
    Create a new tranaction 
    @param amount the memo tranacted
    @param memo the memo for the transaction
    @param inAccount the account the tranaction belongs to
    */
    public Transaction(double amount, String memo, Account inAccount){

        // call the two-arg constructor first
        this(amount, inAccount);

        //set the memo
        this.memo = memo;
    }

}