/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package reverse;

import java.io.*;
/**
 *
 * @author Mike
 */
import java.util.Stack;
public class ReverseTest {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader stdin = new BufferedReader(reader);
        
        int decisionVal = 0;
       
        System.out.println("Enter Data seperated by a Space");
        
        String input = stdin.readLine();
        
        String[] dimen = input.split("\\s+");
       
        Stack<String> s_1 = new Stack<>();
         
        for (String dimen1 : dimen) {
            s_1.push(dimen1);
        }
       
        Reverse<String> r_1 = new Reverse<String>();
        
        r_1.ReverseOrder(s_1);
        
        System.out.println(r_1.getStack());
       
        // TODO code application logic here
    }
    
}
