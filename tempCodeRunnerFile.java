import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    private String firstName;  // The first Name of the User
    private String lastName;  // The Last Name of the User
    private String uuid;  // The ID number of the User
    private byte pinHash[]; // The MD5 hash of User's pin number

    private ArrayList<Account> accounts;  // The list of accounts of this User.

    public User(String firstName, String lastName, String pin, Bank theBank) {
        // set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        // Store this pin's MDS hash, rather than the original value, for security reasons.
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // get a new, unique universal ID for the user
        this.uuid = theBank.getNewUserUUID();

        // Create an empty list of accounts
        this.accounts = new ArrayList<Account>();

        // print log message
        System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);
    }

    // get the user ID number
    public String getUUID(){
        return this.uuid;
    }
   
   
    // Add an account for the user
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    // get the number of accounts the user has 
    public int numAccounts(){
        return this.accounts.size();
    }

    // get the balance of a particular account 
    public double getAcctBalance(int acctIdx){
        return this.accounts.get(acctIdx).getBalance();
    }

    //Get the UUID of a particular account.
    public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID();
	}
    
    // Print transaction history for a particular account.
    public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
	}

    // Add a transaction to a particular account.
    public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
	}
	
	
	// Check whether a given pin matches the true User pin
	public boolean validatePin(String aPin) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), 
					this.pinHash);
		} catch (Exception e) {
			System.err.println("error, caught exeption : " + e.getMessage());
			System.exit(1);
		}
		
		return false;
	}
	
	
	// Print summaries for the accounts of this user.

	public void printAccountsSummary() {
		
		System.out.printf("\n\n%s's accounts summary\n", this.firstName);
		for (int a = 0; a < this.accounts.size(); a++) {
			System.out.printf("%d) %s\n", a+1, 
					this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
		
	}


}
