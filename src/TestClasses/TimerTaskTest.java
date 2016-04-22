package TestClasses;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.memoranda.ui.TimerTask;

public class TimerTaskTest {

	private static TimerTask gui;
	private static TimerTask.TimeClass tc;
	private TimerTask.TimeKeeper timeKeeper;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		gui = new TimerTask();
		tc = gui.new TimeClass();
		timeKeeper = gui.new TimeKeeper();
	}

	@After
	public void tearDown() throws Exception {
		timeKeeper = null;
		tc = null;
		gui = null;
	}

	@Test
	public void logicTest() {
		System.out.println("Time class's logic test");
		String result = "19 : 20 : 19";
		assertTrue(tc.logic(20, 19, 20).equals(result));// 20, 19, 20
		result = "19 : 20 : 19";

		// <= 0 crashes-user could only put 59 for hour, minute, and second.
		// System.out.println(tc.logic(-100, -100, -100));
		// System.out.println(tc.logic(00, 00, 00));

		// Test based on user possible inputs max 59 and min 0
		// NOTE: logic (min, hour, sec) not (hour, min, sec)
		result = "00 : 00 : 58";
		assertTrue(tc.logic(00, 00, 59).equals(result));
		result = "00 : 58 : 59";
		assertTrue(tc.logic(59, 00, 00).equals(result));
		result = "58 : 59 : 59";
		assertTrue(tc.logic(00, 59, 00).equals(result));
		// result = "Done";
		// assertTrue(tc.logic(00, 00, 01).equals(result));
	}

	@Test
	public void getHourTest() {
		System.out.println("getHourTest");
		assertTrue(timeKeeper.getHour() == 0);
	}

	@Test
	public void setHourTest() {
		System.out.println("setHourTest");
		timeKeeper.setHour(-12);
		assertTrue(timeKeeper.getHour() == -12);
		timeKeeper.setHour(0);
		assertTrue(timeKeeper.getHour() == 0);
		timeKeeper.setHour(12);
		assertTrue(timeKeeper.getHour() == 12);
		timeKeeper.setHour(24);
		assertTrue(timeKeeper.getHour() == 24);
	}

	@Test
	public void getMinTest() {
		System.out.println("getMinTest");
		assertTrue(timeKeeper.getMin() == 0);
	}

	@Test
	public void setMinTest() {
		System.out.println("setMinTest");
		timeKeeper.setMin(-10);
		assertTrue(timeKeeper.getMin() == -10);
		timeKeeper.setMin(0);
		assertTrue(timeKeeper.getMin() == 0);
		timeKeeper.setMin(60);
		assertTrue(timeKeeper.getMin() == 60);
	}

	@Test
	public void getSecTest() {
		System.out.println("getSecTest");
		assertTrue(timeKeeper.getSec() == 0);
	}

	@Test
	public void setSecTest() {
		System.out.println("setSecTest");
		timeKeeper.setSec(-10);
		assertTrue(timeKeeper.getSec() == -10);
		timeKeeper.setSec(0);
		assertTrue(timeKeeper.getSec() == 0);
		timeKeeper.setSec(60);
		assertTrue(timeKeeper.getSec() == 60);
	}

}
