package TestClasses;


import static org.junit.Assert.*;

import org.junit.Test;

import net.sf.memoranda.ui.StopWatch;

public class StopWatchUnitTesting {

	/*
	 * This method case tests for the name of the task 
	 * created by the default constructors of TimerTask
	 */
	@Test
	public void test1() {
		StopWatch task1 = new StopWatch();
		String temp1 = "Task";
		String temp1check = task1.getName();
		assertTrue(!(temp1 == task1.getName()));
	}
	
	/*
	 * This method case tests for the name of the task 
	 * created by the one string param constructors of TimerTask
	 */
	@Test
	public void test2() {
		StopWatch task2 = new StopWatch("MyFirstTask");
		String temp2 = "MyFirstTask";
		String temp2check = task2.getName();
		assertEquals(temp2, task2.getName());
	}
	/*
	 * This method case tests for the name of the task
	 * to see how TimerTask behaves when we provide a name with spaces
	 */
	@Test
	public void test3(){
		StopWatch task3 = new StopWatch("My First Task");
		String temp3 = "My First Task";
		String temp3check = task3.getName();
		assertTrue(temp3 == task3.getName());
		
	}
}
