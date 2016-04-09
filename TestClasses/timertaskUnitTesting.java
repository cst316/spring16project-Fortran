

import static org.junit.Assert.*;

import org.junit.Test;

import net.sf.memoranda.ui.StopWatch;

public class timertaskUnitTesting {

	/*
	 * This method case tests for the name of the task 
	 * created by the default constructors of TimerTask
	 */
	@Test
	public void test1() {
		StopWatch task1 = new StopWatch();
		String temp1 = "Task";
		assertTrue(!(temp1 == task1.name));
	}
	
	/*
	 * This method case tests for the name of the task 
	 * created by the one string param constructors of TimerTask
	 */
	@Test
	public void test2() {
		StopWatch task2 = new StopWatch("MyFirstTask");
		String temp2 = "MyFirstTask";
		assertEquals(temp2, task2.name);
	}
	/*
	 * This method case tests for the name of the task
	 * to see how TimerTask behaves when we provide a name with spaces
	 */
	@Test
	public void test3(){
		StopWatch task3 = new StopWatch("My First Task");
		String temp3 = "My First Task";
		assertTrue(temp3 == task3.name);
		
	}
}
