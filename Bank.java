import java.util.ArrayList;

public class Bank{

    private String name;  //The Name of the Bank.
    private ArrayList<User> users;  //The list of User
    private ArrayList<Account> accounts;  //The list of accounts

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
        boolean nonUnique;

        //continue looping untill we get a unique ID
        do{
            uuid = "";
            for(int c = 0; c < len; c++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
    
            //Check  to make sure it's unique
            nonUnique = false;
            for(Account a : this.accounts){
                if(uuid.compareTo(a.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        } while(nonUnique);

            return uuid;
    }


    // Add an account
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }
}