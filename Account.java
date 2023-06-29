import java.util.ArrayList;

public class Account{

    private String name;  //The name of Account
    private double balance;  //The current balance of the Account
    private String uuid;   //The account ID number
    private User holders;  //The User object that owns this account
    private ArrayList<Transaction> transactions; //The list of transactions for this account
}