/**
 * 
 */

package net.sf.memoranda.tests;
import org.junit.Test;
import net.sf.memoranda.util.UnzipFolder;
import net.sf.memoranda.util.NestedZipException;
import java.io.*;
import java.util.zip.*;
import java.util.Enumeration;
import static org.junit.Assert.assertTrue;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
/**
 * @author Mike
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
	public void TestExtractZip(){
		
		final String expectedPath2 = "test/t2/";
		final String expectedPath3 = "test/t3";
		final String expectedPath1 = "test/t1Nested/t1";
		
		File expectedDir1 = new File(expectedPath1);
		File expectedDir2 = new File(expectedPath2);
		File expectedDir3 = new File(expectedPath3);
		/**
		 * Boundary Value Analysis
		 *  t1 - A zip with a nested zip containing 3 nested folders 
		 *  with 4 java source files which will return false
		    t2 - A zip with multiple folders containing java source files
		    t3 - A zip with a folder containing a java source file    
		 * 
		 * Check to see if Ouput Directory Exists
		 */
		File testCase1 = new File("test/t1Nested.zip");
		File testCase2 = new File("test/t2.zip");
		File testCase3 = new File("test/t3.zip");

		
		UnzipFolder zip2 = new UnzipFolder(testCase2);
		UnzipFolder zip3 = new UnzipFolder(testCase3);
		
		//verify output folders existence
	
		assertTrue(expectedDir2.exists());
		assertTrue(expectedDir3.exists());
		
		//insert nested zip which will return false;	
		UnzipFolder zip1 = new UnzipFolder(testCase1);
		assertTrue(zip1.extract(testCase1));
		
		
	}
	
		
		
	

}
