/**
 * 
 */

/**
 * @author Mike
 *
 */

import net.sf.memoranda.ui.LOCTable;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TestLOCTable {

	/**
	 * 
	 */
	static final Object[] COLUMNAMES = { "SourceFile", "LOC" };
	private Object[][] testData = {{"Testing.java","120"},
		 	              {"Account.java","245"},
		 	              {"Main.java","25"}};
	private Object[][] testData2 = {{"Testing.java","120"},
             {"Account.java","245"},
             {"Main.java","25"},{"Testing.java","90"},{"Account.java","190"}};
	 
	private LOCTable lt;
	
	private LOCTable lt2;
	
	private final String[] CASE1 = {"TestingTestingTesting.java","PersonalOnlinePortForlio.java"};
	
	private final String[] CASE2 = {"Testing.java","Account.java","Main.java"};
	private final String EMPTYSTRING = "";
	
	public TestLOCTable() {
		
		lt = new LOCTable(testData,COLUMNAMES);
		lt2 = new LOCTable(testData2,COLUMNAMES);
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
		**/
		
		
		assertTrue(lt2.searchTable(CASE2[0]));
		
		
	}

}
