/**
 * 
 */

package net.sf.memoranda.tests;
import org.junit.Test;
import net.sf.memoranda.util.LOCReader;
import net.sf.memoranda.util.NestedZipException;
import java.io.*;
import java.util.zip.*;
import java.util.Enumeration;
import static org.junit.Assert.assertTrue;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
/**
 * @author Mike and Quy
 *
 */
public class TestLOCReader {

	/**
	 * 
	 */
	public TestLOCReader() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testExtractZip(){
		
		final String expectedPath2 = "test/t2/";
		final String expectedPath3 = "test/t3";
		final String expectedPath1 = "test/t1Nested/t1";
		
		
		File expectedDir2 = new File(expectedPath2);
		File expectedDir3 = new File(expectedPath3);
		/**
		 * Boundary Value Analysis
		 *  t1 - A zip with a nested zip containing 3 nested folders 
		 *  with 4 java source files which will return false
		    t2 - A zip with multiple folders containing java source files will return true
		    t3 - A zip with a folder containing a java source file wiull return true   
		 * 
		 * Check to see if Output Directory Exists
		 */
		File testCase1 = new File("test/t1Nested.zip");
		File testCase2 = new File("test/t2.zip");
		File testCase3 = new File("test/t3.zip");

		LOCReader lc2 = new LOCReader();
		LOCReader lc3 = new LOCReader();
		
		assertTrue(lc2.extract(testCase2));
		assertTrue(lc3.extract(testCase3));
		
		LOCReader lc = new LOCReader();		
		assertTrue(!(lc.extract(testCase1)));

	}
	
	@Test
	/**
	 * test1.zip - a zip file that contains only .java files with no folders
	 * test2.zip - a zip file that contains .java files in root, 
	   contains .java files in folders A and B
	 * test3.zip - a zip file that contains .java files in root, 
	   contains .java files in folders A and B,
	   contains .java files in nested folders A1 and B1
	 */
	public void testFindJavaFiles() {
		
		final int expectedCount1 = 4;
		final int expectedCount2 = 18;
		final int expectedCount3 = 24;
		
		File testCase1 = new File("test/test1.zip/");
		File testCase2 = new File("test/test2.zip/");
		File testCase3 = new File("test/test3.zip/");
		
		LOCReader zip1 = new LOCReader(testCase1);
		LOCReader zip2 = new LOCReader(testCase2);
		LOCReader zip3 = new LOCReader(testCase3);
		
		assertTrue(expectedCount1 == zip1.getTestCount());
		assertTrue(expectedCount2 == zip2.getTestCount());
		assertTrue(expectedCount3 == zip3.getTestCount());	
	}
}
