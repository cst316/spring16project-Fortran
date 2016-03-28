import java.io.*;

public class p1 {
    public static void main (String[] args) throws IOException {
        
        InputStreamReader reader = new InputStreamReader (System.in);
        BufferedReader stdin = new BufferedReader (reader);
        
        String UserInput = stdin.readLine ();
        int IntegerValue = Integer.parseInt (UserInput);
        int fact = 1;
        while (IntegerValue >= 1){
            fact = fact * IntegerValue;
            IntegerValue --;
        }
        System.out.println ("fact calcuated: " + fact);
    }
}