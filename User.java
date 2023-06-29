import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User{

    private String firstName;  //The first Name of the User
    private String lastName;  //The Last Name of the User
    private String uuid;  //The ID number of the User
    private byte pinHash[]; //The MD5 hash of User's pin number

    private ArrayList<Account> accounts;  //The list of accounts of this User.

    public User(String firstName, String lastName, String pin, Bank thebank){
        // set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        // Store this pin's MDS hash, rather then the original value, for securiny reasons.
        try {
            MessageDigest md = MessageDigest.getInstance("MDS");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e){
            System.err.println("Error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);

            // get a new, unique universal ID for the user
            this.uuid = theBank.getNewUserUUID();

            // Create empty list of accounts
            this.accounts = new ArrayList<Account>();

            //print log message
            System.out.printf("New user %s, %s with ID %s create.\n", lastName, firstName, this.uuid);
        }
    }



}