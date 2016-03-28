import java.io.*;
import java.util.*;
public class arraysandstrings
{
    public static void main (String[] args)throws IOException{
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    
    System.out.println("Enter quiz number and then the answer key");
    String input = stdin.readLine();
    String [] dimensions = input.split("\\s+");
    List key = Arrays.asList(dimensions);
    key = key.subList(1,key.size());
   
    
    double sumaverage = 0.00;
   double averagediv = 0.00;
   String newinput = " ";
   while (!newinput.equalsIgnoreCase("ZZZZ")){
        System.out.println("Enter student name-last name first-and ID followed by student answer sheet(terminate loop by inputting ZZZZ)");
        System.out.println("If the answer is skipped then please place N");
        newinput = stdin.readLine();
        if (newinput.equalsIgnoreCase("ZZZZ")){
        double average = (sumaverage/averagediv);
        System.out.println("The average is:" + average);
        System.exit(0);
        }
        
        String splitter [] = newinput.split("\\s+");
        List answer = Arrays.asList(splitter);
        answer = answer.subList(3,answer.size());
        
        //String Buffer
        String blankstring = " ";
        StringBuffer buffer = new StringBuffer(blankstring);
        buffer.append(splitter[2].charAt(0));
        buffer.append(splitter[2].charAt(1));
        buffer.append(splitter[2].charAt(2));
        buffer.append("-");
        buffer.append(splitter[2].charAt(3));
        buffer.append(splitter[2].charAt(4));
        buffer.append("-");
        buffer.append(splitter[2].charAt(5));
        buffer.append(splitter[2].charAt(6));
        buffer.append(splitter[2].charAt(7));
        buffer.append(splitter[2].charAt(8));
        //variables
        int sum = 0;
        int n = 0;
       for (int i = 0; i < answer.size(); ++i){
       
           if (answer.get(n).toString().equalsIgnoreCase(key.get(n).toString())){
            
             sum = sum+1;
            
            } 
           else {
            sum = sum;
            }
            n = n+1;
         } 
         sumaverage = sumaverage + sum;
         System.out.println(buffer +" "+ splitter[1]+" "+splitter[0]+" "+ sum);
         averagediv = averagediv + 1;
        }
    }
    }
    
  
    
 
