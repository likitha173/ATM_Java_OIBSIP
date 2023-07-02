import java.util.Scanner;

public class ATM {
	

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Bank theBank = new Bank("Bank of Drausin"); // init Bank
		User aUser = theBank.addUser("John", "Doe", "1234"); // add a user, which also creates a Savings account
		

		// add a checking account for our user
		Account newAccount = new Account("Checking", aUser, theBank);
		aUser.addAccount(newAccount);
		theBank.addAccount(newAccount);
		
		User curUser;
		
		while (true) {
			
			curUser = ATM.mainMenuPrompt(theBank, sc); // stay in login prompt until successful login
			ATM.printUserMenu(curUser, sc); // stay in main menu until user quits
			
		}

	}
	
     //Print the ATM's login menu.
	 public static User mainMenuPrompt(Bank theBank, Scanner sc) {
		
		String userID;
		String pin;
		User authUser;
		
		// prompt user for user ID/pin combo until a correct one is reached
		do {
			
			System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());		
			System.out.print("Enter user ID: ");
			userID = sc.nextLine();
			System.out.print("Enter pin: ");
			pin = sc.nextLine();
			
			// try to get user object corresponding to ID and pin combo
			authUser = theBank.userLogin(userID, pin);
			if (authUser == null) {
				System.out.println("Incorrect user ID/pin combination. " + 
						"Please try again");
			}
			
		} while(authUser == null); 	// continue looping until we have a  
									// successful login
		
		return authUser;
		
	}
	
    //  Print the ATM's menu for user actions.
	public static void printUserMenu(User theUser, Scanner sc) {
		
		theUser.printAccountsSummary(); // print a summary of the user's accounts
		
		int choice;

		do {
			
			System.out.println("What would you like to do?");
			System.out.println("  1) Show account transaction history");
			System.out.println("  2) Withdraw");
			System.out.println("  3) Deposit");
			System.out.println("  4) Transfer");
			System.out.println("  5) Quit");
			System.out.println();
			System.out.print("Enter choice: ");
			choice = sc.nextInt();
			
			if (choice < 1 || choice > 5) {
				System.out.println("Invalid choice. Please choose 1-5.");
			}
			
		} while (choice < 1 || choice > 5);
		
		// process the choice
		switch (choice) {
		
		case 1:
			ATM.showTransHistory(theUser, sc);
			break;
		case 2:
			ATM.withdrawFunds(theUser, sc);
			break;
		case 3:
			ATM.depositFunds(theUser, sc);
			break;
		case 4:
			ATM.transferFunds(theUser, sc);
			break;
		case 5:
			// gobble up rest of previous input
			sc.nextLine();
			break;
		}
		
		// redisplay this menu unless the user wants to quit
		if (choice != 5) {
			ATM.printUserMenu(theUser, sc);
		}
		
	}
	
	// Process transferring funds from one account to another.
	
	public static void transferFunds(User theUser, Scanner sc) {
		
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal;
		
		// get account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) of the account to " + 
					"transfer from: ", theUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(fromAcct);
		
		// get account to transfer to
		do {
			System.out.printf("Enter the number (1-%d) of the account to " + 
					"transfer to: ", theUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while (toAcct < 0 || toAcct >= theUser.numAccounts());
		
		// get amount to transfer
		do {
			System.out.printf("Enter the amount to transfer (max $%.02f): $", 
					acctBal);
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero.");
			} else if (amount > acctBal) {
				System.out.printf("Amount must not be greater than balance " +
						"of $.02f.\n", acctBal);
			}
		} while (amount < 0 || amount > acctBal);
		
		// finally, do the transfer 
		theUser.addAcctTransaction(fromAcct, -1*amount, String.format(
				"Transfer to account %s", theUser.getAcctUUID(toAcct)));
		theUser.addAcctTransaction(toAcct, amount, String.format(
				"Transfer from account %s", theUser.getAcctUUID(fromAcct)));
		
	}
	
	// Process a fund withdraw from an account.
	
	public static void withdrawFunds(User theUser, Scanner sc) {
		
		int fromAcct;
		double amount;
		double acctBal;
		String memo;
		
		// get account to withdraw from
		do {
			System.out.printf("Enter the number (1-%d) of the account to " + 
					"withdraw from: ", theUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(fromAcct);
		
		// get amount to transfer
		do {
			System.out.printf("Enter the amount to withdraw (max $%.02f): $", 
					acctBal);
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero.");
			} else if (amount > acctBal) {
				System.out.printf("Amount must not be greater than balance " +
						"of $%.02f.\n", acctBal);
			}
		} while (amount < 0 || amount > acctBal);
		
		// gobble up rest of previous input
		sc.nextLine();
		
		// get a memo
		System.out.print("Enter a memo: ");
		memo = sc.nextLine();
		
		// do the withdrwal
		theUser.addAcctTransaction(fromAcct, -1*amount, memo);
		
	}
	
	// Process a fund deposit to an account.
	
	public static void depositFunds(User theUser, Scanner sc) {
		
		int toAcct;
		double amount;
		String memo;
		
		// get account to withdraw from
		do {
			System.out.printf("Enter the number (1-%d) of the account to " + 
					"deposit to: ", theUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while (toAcct < 0 || toAcct >= theUser.numAccounts());
		
		// get amount to transfer
		do {
			System.out.printf("Enter the amount to deposit: $");
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero.");
			} 
		} while (amount < 0);
		
		// gobble up rest of previous input
		sc.nextLine();
		
		// get a memo
		System.out.print("Enter a memo: ");
		memo = sc.nextLine();
		
		// do the deposit
		theUser.addAcctTransaction(toAcct, amount, memo);
		
	}
	
	// Show the transaction history for an account.

	public static void showTransHistory(User theUser, Scanner sc) {
		
		int theAcct;
		
		// get account whose transactions to print
		do {
			System.out.printf("Enter the number (1-%d) of the account\nwhose " + 
					"transactions you want to see: ", theUser.numAccounts());
			theAcct = sc.nextInt()-1;
			if (theAcct < 0 || theAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while (theAcct < 0 || theAcct >= theUser.numAccounts());
		
		// print the transaction history
		theUser.printAcctTransHistory(theAcct);
		
	}

}