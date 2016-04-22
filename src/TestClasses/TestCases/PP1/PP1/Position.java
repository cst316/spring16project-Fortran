/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package maze;

/**
 *
 * @author Mike
 */
/**
 * Represents a single position in a maze of characters.
 *
 * @author Lewis and Chase
 * @version 4.0
 */
public class Position
{
    private int x; 
    private int y;

    /**
     * Constructs a position and sets the x & y coordinates to 0,0.
     */
    Position ()
    {
        x = 0;
        y = 0;
    }
    Position (int newX,int newY) {
        
        x = newX;
        y = newY;
        
    }
    /**
     * Returns the x-coordinate value of this position.
     * @return the x-coordinate of this position
     */
    public int getx()
    {
        return x;
    }

    /**
     * Returns the y-coordinate value of this position.
     * @return the y-coordinate of this position
     */
    public int gety()
    {
        return y;
    }

    /**
     * Sets the value of the current position's x-coordinate.
     * @param a value of x-coordinate
     */
    public void setx(int a)
    {
        x = a;
    }

    /**
     * Sets the value of the current position's x-coordinate.
     * @param a value of y-coordinate
     */ 
    public void sety(int a)
    {
        y = a;
    }
}
