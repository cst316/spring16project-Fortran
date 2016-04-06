package TestClasses;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.ui.StopWatch;
public class StopWatchTest {

	private static StopWatch gui = new StopWatch();
	private static StopWatch.TimeClass tc = null;
	
	@Before
	public void setUp() throws Exception {
		tc = gui.new TimeClass();
	}
	
	
	@Test
	public void TimeLogictest() 
	{
		String result = "19 : 20 : 19";
		assertTrue(tc.logic(20, 19, 20).equals(result));
		
	}

}
