import java.util.ArrayList;

public class Bank{

    private String name;  //The Name of the Bank.
    private ArrayList<User> users;  //The list of User
    private ArrayList<Account> accounts;  //The list of accounts

    public String getNewUserUUID(){
        
    }
    public String getNewAcountUUID(){
        
    }
    // Add an account
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }
}