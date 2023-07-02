import java.util.Date;

public class Transaction{

    private double amount;  //The Amount of this Transaction.
    private Date timestamp; //The time and date of this tranaction.
    private String memo;  //A memo for this transaction.
    private Account inAccount;  //The account in which the transaction was performed.

    
    // Create a new tranaction
    public Transaction(double amount, Account inAccount){
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";
    }

    
    // Create a new tranaction
    public Transaction(double amount, String memo, Account inAccount){

        // call the two-arg constructor first
        this(amount, inAccount);

        //set the memo
        this.memo = memo;
    }

    // Get the transaction amount.
	 
	public double getAmount() {
		return this.amount;
	}
	

    // Get a string summarizing the transaction
	 
	public String getSummaryLine() {
		
		if (this.amount >= 0) {
			return String.format("%s, $%.02f : %s", 
					this.timestamp.toString(), this.amount, this.memo);
		} else {
			return String.format("%s, $(%.02f) : %s", 
					this.timestamp.toString(), -this.amount, this.memo);
		}
	}

}