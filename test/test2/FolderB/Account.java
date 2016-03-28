/* Quy Ly
Ser215
October 1, 2014
Assignment 5 Account Class
*/

import java.io.*;
import java.util.Date;

class Account
{
    private int id;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated = new Date();

//Default constructor
    public Account ()
    {
        id = 0;
        balance = 0.0;
	annualInterestRate = 0.0;
    }

//Constructor with three arguments
    public Account (int id, double balance, double annualInterestRate)
    {
	this.id = id;
	this.balance = balance;
	this.annualInterestRate = annualInterestRate;
    }

//Mutators for ID, Balance, and Annual Interest Rate
    public void setID ()
    {
	this.id = id;
    }

    public void setBal (int balance)
    {
	this.balance = balance;
    }

    public void setAnnualIR (int annualInterestRate)
    {
	this.annualInterestRate = annualInterestRate;
    }

//Accessors for ID, Balance, Annual Interest Rate, Date, and Monthly Interest
    public int getID ()
    {
	return id;
    }

    public double getBal ()
    {
	return balance;
    }

    public double getAnnualIR ()
    {
	return annualInterestRate;
    }

    public Date getDateCreated ()
    {
	return dateCreated;
    }

//Monthly Interest also rounds to 2 decimal places after dividing by 12.
    public double getMonthlyInterest ()
    {
	double Month = (annualInterestRate / 12);
	double RoundMonth = (double) Math.round (Month * 100) / 100;
	return RoundMonth;
    }

//Calculates the balance when user enters the withdrawal amount and returnts the new balance
    double withdraw (double Amount)
    {
	return (balance -= Amount);
    }

//Calculates the balance when user enters the depositing amount and returns the new balance
    double deposit (double Amount)
    {
	return (balance += Amount);
    }
}