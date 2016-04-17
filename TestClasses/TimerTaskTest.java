//package net.sf.memoranda.ui.resources.icons.mimetypes.text;

import static org.junit.Assert.*;

import org.junit.Test;

import net.sf.memoranda.ui.StopWatch;
import net.sf.memoranda.ui.TimerTask;

public class TimerTaskTest {
	/*
	 * This method case tests for the name of the task
	 * created by the default constructors of TimerTask
	 */
	@Test
	public void test1() {
		TimerTask task1 = new TimerTask();
		String temp1 = "Task";
		String temp1check = task1.getName(); //fix for dead store task 1 issue #26
		assertTrue(!(temp1 == temp1check));
	}

	/*
	 * This method case tests for the name of the task
	 * created by the one string param constructors of TimerTask
	 */
	@Test
	public void test2() {
		TimerTask task2 = new TimerTask("MyFirstTask");
		String temp2 = "MyFirstTask";
		String temp2check = task2.getName(); //fix for dead store task 1 issue #27
		assertTrue(temp2 ==temp2check);
	}
	/*
	 * This method case tests for the name of the task
	 * to see how TimerTask behaves when we provide a name with spaces
	 */
	@Test
	public void test3(){
		TimerTask task3 = new TimerTask("My First Task");
		String temp3 = "My First Task";
		String temp3check = task3.getName(); //fix for dead store task 1 issue #28
		assertTrue(temp3 == temp3check);

	}
}
