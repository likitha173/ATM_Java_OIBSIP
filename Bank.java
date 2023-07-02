import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Bank{

    private String name;  //The Name of the Bank.
    private ArrayList<User> users;  //The list of User
    private ArrayList<Account> accounts;  //The list of accounts


    // Create a new Bank object with empty lists of users and accounts
    public Bank(String name){
        this.name = name;

        users = new ArrayList<User>();
        accounts = new ArrayList<Account>();
    }



    public String getNewUserUUID(){
        //Generate a new universally unique ID for a User

        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        //continue looping untill we get a unique ID
        do{
            uuid = "";
            for(int c = 0; c < len; c++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
    
            //Check  to make sure it's unique
            nonUnique = false;
            for(User u : this.users){
                if(uuid.compareTo(u.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        } while(nonUnique);

            return uuid;
    }


    public String getNewAccountUUID(){
        //Generate a new universally unique ID for a Account

        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique = false;

        //continue looping untill we get a unique ID
        do{
            uuid = "";
            for(int c = 0; c < len; c++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
    
            //Check  to make sure it's unique
            for(Account a : this.accounts){
                if(uuid.compareTo(a.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        } while(nonUnique);

            return uuid;
    }


    // Create a new user of the bank

    public User addUser(String firstName, String lastName, String pin){

        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }

    // Add an existing account for a particular User
    public void addAccount(Account newAccount){
        this.accounts.add(newAccount);
    }

    //  Get the User object associated with a particular userID and pin, if they are valid.
    public User userLogin(String userID, String pin){
        for(User u : this.users){
            if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)){  
                return u;
            }
        }
        return null;
    }

    // get the name of the bank
    public String getName(){
        return this.name;
    }
}