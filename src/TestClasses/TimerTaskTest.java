package TestClasses;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.ui.TimerTask;
public class TimerTaskTest {

	private static TimerTask gui = new TimerTask();
	private static TimerTask.TimeClass tc = null;
	
	@Before
	public void setUp() throws Exception {
		tc = gui.new TimeClass();
	}
	
	
	@Test
	public void TimeLogictest() 
	{
		String result = "19 : 20 : 19";
		assertTrue(tc.logic().equals(result));//20, 19, 20
		
	}

}
