import java.io.*;

public class p1 {
    public static void main (String[] args) throws IOException {
        
        InputStreamReader reader = new InputStreamReader (System.in);
        BufferedReader stdin = new BufferedReader (reader);
        
        String UserInput1 = stdin.readLine ();
        int SmallValue = Integer.parseInt (UserInput1);
        
        String UserInput2 = stdin.readLine ();
        int BigValue = Integer.parseInt (UserInput2);
       
        int sum = SmallValue + 1;
        
        if (SmallValue < BigValue) {
            sum = sum + SmallValue;
            sum ++;
        }
        else {
            System.err.println("The first input should be smaller than the second.");
            
        
        System.out.println ("Value read: ");
    }
}
}