/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package reverse;

import java.util.Stack;
/**
 *
 * @author Mike
 */
public class Reverse<T> extends Stack<T>{
   
    private Stack<T> reversedData ;
    /** Creates the reversedData stack **/
    public Reverse() {
        
        this.reversedData = new Stack<>();
        
    }
    /** 
     * Reverses the elements in a stack. 
     * @param S The Stack to be reversed
     */
    public void ReverseOrder(Stack<T> S) {
        
        while(!S.empty()) {
            
           reversedData.push(S.pop());
            
        }
        
    }
   /**
    * Getter for the Stack
    * @return The reversed Stack
    */
    public Stack<T> getStack() {
        
        return this.reversedData;
        
    }
    
}
