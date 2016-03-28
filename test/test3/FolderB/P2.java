/* Quy Ly
Ser215
October 1, 2014
Assignment 5 P2 Class
*/

import java.io.*;

class P2
{
    public static void main (String [] args)throws IOException
    {
        BufferedReader stdin = new BufferedReader(new InputStreamReader (System.in));

//Gets the ID from the user
	System.out.println ("Enter your identification number.");
	String InputID = stdin.readLine();
	int ID = Integer.parseInt (InputID);

//Gets the Balance from the user
	System.out.println ("How much money do you have in your account?");
	String InputBal = stdin.readLine();
	double Bal = Double.parseDouble (InputBal);

//Gets the annual interest rate from the user
	System.out.println ("Enter your annual interest rate.");
	String InputAIR = stdin.readLine();
	double AnnualIR = Double.parseDouble (InputAIR);	

//Sets them in the Account
	Account account1 = new Account (ID, Bal, AnnualIR);

//Gets how much the user wants to withdraw and calculates the new balance
	System.out.println ("How much do you wish to withdraw?");
	String WithAmount = stdin.readLine ();
	double WithDrawal = Double.parseDouble (WithAmount);
	account1.withdraw (WithDrawal);

//Gets how much the user wants to deposit and calculates the new balance
	System.out.println ("How much do you wish to deposit?");
	String DepAmount = stdin.readLine ();
	double Depositing = Double.parseDouble (DepAmount);
	account1.deposit (Depositing);

//Prints out the ID, new Balance after withdrawal and depositing, the monthly interest, and the date it was created
	System.out.println ();
	System.out.println ("Your ID number is " + account1.getID());
	System.out.println ("After withdrawal and depositing, your new balance is $" + account1.getBal());
	System.out.println ("Your monthly interest is " + account1.getMonthlyInterest() + "%");
	System.out.println ("This account was created on " + account1.getDateCreated());
    }
}