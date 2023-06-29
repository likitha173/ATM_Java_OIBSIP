import java.util.ArrayList;

public class Account{

    private String name;  //The name of Account
    // private double balance;  //The current balance of the Account
    private String uuid;   //The account ID number
    private User holders;  //The User object that owns this account
    private ArrayList<Transaction> transactions; //The list of transactions for this account

    public Account(String name, user holder, Bank theBank){
        //set the account name and hodler
        this.name = name;
        this.holder = holder;

        //get new account UUID
        this.uuid = theBank.getNewAccountUUID();

        //init transactions
        this.transactions = new ArrayList<Transaction>();

        //add to holder and bank lists
        hodler.addAccount(this);
        theBank.addAccount(this);
    }
}