package TestClasses;
/**
 * 
 */

/**
 * @author Mike
 *
 */

import net.sf.memoranda.ui.LOCTable;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class TestLOCTable {

	/**
	 * 
	 */
	static final Object[] COLUMNAMES = { "SourceFile", "LOC" };
	private Object[][] testData = {{"Testing.java","120"},
		 	              {"Account.java","245"},
		 	              {"Main.java","25"}};
	private Object[][] testData2 = {{"Source.java","120"},
             {"Foo.java","245"},
             {"Main.java","25"},{"Testing.java","90"},{"Account.java","190"}};
	 
	private LOCTable lt;
	private LOCTable lt2;	
	private LOCTable rm1;
	private LOCTable rm2;
	private LOCTable rm3;
	private LOCTable rm4;
	
	private final String[] CASE1 = {"TestingTestingTesting.java","PersonalOnlinePortForlio.java"};
	
	private final String[] CASE2 = {"Testing.java","Account.java","Main.java"};
	private final String EMPTYSTRING = "";
	
	
	private final Object[][] CASE1_rm = {{"Account.java","13"},{"Account.java","14"},{"Account.java","78"}};
	private final Object[][] CASE2_rm =  {{"Main.java",14},{"Server.java",25},{"Foo.java",78},{"Main.java",34}};
	private final Object[][] CASE3_rm = {{"Server.java",23},{"Main.java",45},{"Main.java",789},{"Foo.java",43}};

	private final Object[][] expected_1rm = {{"Account.java","13"},{"-","-"},{"-","-"}};
	private final Object[][] expected_2rm = {{"Main.java",14},{"Server.java",25},{"Foo.java",78},{"-","-"}};;
	private final Object[][] expected_3rm = {{"Server.java",23},{"Main.java",45},{"-","-"},{"Foo.java",43}};
	private final Object[][] expected_4rm ={{"Source.java","23"},{"Main.java","56"},{"Test.java","90"}};
	public TestLOCTable() {
		
		lt = new LOCTable(testData,COLUMNAMES);
		lt2 = new LOCTable(testData2,COLUMNAMES);
		
		rm1 = new LOCTable(CASE1_rm,COLUMNAMES);
		rm2 = new LOCTable(CASE2_rm,COLUMNAMES);
		rm3 = new LOCTable(CASE3_rm,COLUMNAMES);
		rm4 = new LOCTable(expected_4rm,COLUMNAMES);
	}
	
	@Test
	public void testSearchTable(){
		
		/**
		 * Boundary Value Analysis
		 * 
		 *  Case 1:   “TestingTestingTesting.java”,”PersonalOnlinePortForlio.java” Should return false

			Case 2:   “Testing.java”,”Account.java”,”Main.java” should return true

			Case 3:    “” should return false
		 * 
		 */
		for(int x = 0 ; x <= CASE1.length - 1; x++){
			
			assertTrue(!lt.searchTable(CASE1[x]));
		}
		
		for(int y = 0; y <= CASE2.length - 1; y++){
			
			assertTrue(lt.searchTable(CASE2[y]));
		}
		
		assertTrue(!lt.searchTable(EMPTYSTRING));
		
		/**
		Basis:

			Case 1: Multiple Copies of the search entry are found

			Case 2: Position of Entry
			As of 4/12 we no longer accept duplicates in the table
			
		**/
	}
	@Test
	public void testRemoveDuplicates(){
		
		/**
		 * Boundary Analysis/Expected Results
		 * 
		 * Case 1: {"Account.java","13"},{"Account.java","14"},{"Account.java","78"} return True
		 * 
		 * Case 2: {"Main.java",14},{"Server.java",25},{"Foo.java",78},{"Main.java",34} return True
		 * 
		 * Case 3: {"Server.java",23},{"Main.java",45},{"Main.java",789},{"Foo.java",43} return True
		 * 
		 * Case 4:{{"Source.java","23"},{"Main.java","56"},{"Test.java","'90}} return True
		 */

		boolean result = Arrays.deepEquals(rm1.markSecondOcc(CASE1_rm), expected_1rm);
		assertTrue(result);
		boolean result2 = Arrays.deepEquals(rm2.markSecondOcc(CASE2_rm),expected_2rm);
		assertTrue(result2);
		boolean result3 = Arrays.deepEquals(rm3.markSecondOcc(CASE3_rm),expected_3rm);
		assertTrue(result3);
		
		boolean result4 = Arrays.deepEquals(rm4.markSecondOcc(expected_4rm),expected_4rm);//No Duplicates
		assertTrue(result4);
	}
}
