/* Quy Ly 
Ser215
October 1, 2014
Assignment 5 New String split Method
For some odd reason a question mark will not work for my split.
*/

import java.io.*;

public class P1
{
    public static void main (String [] args)throws IOException
    {
        BufferedReader stdin = new BufferedReader(new InputStreamReader (System.in));

//get user input for whatever they wish to enter	
	System.out.println ("Enter something");
	String UserInput1 = stdin.readLine();
	String s = UserInput1;

//Now the user must enter the delimiter. To split more than one, must put []
//For example if wanting to split @ and #, must do [@#], do not have to add [] if just one delimiter.
	System.out.println ("Now enter the delimiter");
	String UserInput2 = stdin.readLine();
	String regex = UserInput2;

//Puts the inputs into an array
	String Data [] = split(s, regex);
    }
     
//Method to actually split the array     
    public static String [] split(String s, String regex)
    {
        String Temp [] = s.split(regex);
	String Result [] = new String [s.length()];

	for (int i = 0; i < Temp.length; i++)
	{
//Gets the individual inputs and prints them without the delimiter
	    Result [i*2] = Temp [i];
	    System.out.print (Temp [i]);
	    System.out.print (", ");
	    
//Gets the delimiter and prints them out after the input.
	    Result [i] = regex;
	    System.out.print (Result [i]);
            System.out.print (", ");
	}
	return Result;
    }
}