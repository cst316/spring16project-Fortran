package net.sf.memoranda.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import org.w3c.dom.*;
import javax.swing.JOptionPane;
import javax.xml.parsers.*;

//import org.apache.commons.io.FilenameUtils;

/**
 * @author Quy Ly, Saul Lopez, and Michael Zaragoza
 */

public class LOCReader {
	//field variables and constants
	private static final int COLUMN = 2;
	private int LOC;
	private int testCount;
	private String fileLine;
	private String fileName;
	private static String fN;
	private static int ROW;
	private static String[][] array;
	private static String configPath = System.getProperty("user.home") + File.separator + ".memoranda" + File.separator;
	private final int MAXLIMIT = 2048;
	private final static String JAVAEXTENSION = ".java";
	private Hashtable<String,Integer> locMap;
	private boolean ableToExtract;
	private List<File> files;
	
	public LOCReader() {
		LOC = 0;
		fileLine = "";
		fileName = "";
		locMap = new Hashtable<String,Integer>();
	}
	
	public LOCReader(File readFile) {
		files = new ArrayList<File>();
		locMap = new Hashtable<String,Integer>();
		
		LOC = 0;
		fileLine = "";
		fileName = readFile.getName();	
		
		if (fileName.endsWith(".java")) {
				
			computeLOC(readFile);
			locMap.put(fileName, LOC);
			//have to call quys listToHash in here too
		}
		else if (fileName.endsWith(".zip")) {
				
		    boolean result = extract(readFile);
		    
		    if(result == false){
		    	
		    	///unable to extract nested zip
		    	 JOptionPane.showMessageDialog(null,"Nested Zip Found Aborting Import",
				    		"ZipError",JOptionPane.ERROR_MESSAGE);
		    }
		    else{
		    	
		    	String actualFolder = fileName.substring(0,fileName.length() - 4);
		    	File outputDir = new File("test" + File.separator + actualFolder);
		    	searchDirectories(outputDir);
		    	
		    	List<File> currentList = getFiles();
		    	
		    	listToHash(currentList);
		    	deleteDir(outputDir);
		    }
		}
	}
	
	/**
	 * extracts zip folder and makes a new directory with all the content in test folder
	 * @param zipFile - Zip File to be extracted
	 * @return <ul><li>true -if method is able to extract folder succesfully </li> 
	 * 		  <li> false - if nested zip folder is found</li>	
	 * 		  </ul>
	 */
	public boolean extract (File zipFile){
		ableToExtract = true;
		
		//obtain zipfile's files
		String zipFileName = zipFile.getName();
		zipFileName = zipFileName.substring(0,zipFileName.length() - 4);
		new File(zipFileName).mkdir();
		
		//unzip file and extract java files
		 try {
			
			 BufferedOutputStream dest = null;
	         BufferedInputStream is = null;
	         ZipEntry entry;
	         ZipFile zipfile = new ZipFile(zipFile);
	         Enumeration<? extends ZipEntry> e = zipfile.entries();
	         File destFile;
	         
	         while(e.hasMoreElements() && ableToExtract) {
	        	
	            entry = (ZipEntry) e.nextElement();
	            
	            String currentEntry = entry.getName();
	            String path = "test/";
	          
	            destFile = new File(path + zipFileName, currentEntry);
	            File destinationParent = destFile.getParentFile();
	            
	            if(destinationParent != null){
	            	
	            	destinationParent.mkdirs();
	            }
	            
	            if(!entry.isDirectory()) {

	            	is = new BufferedInputStream
	            			(zipfile.getInputStream(entry));
	            	int count;
	            	byte data[] = new byte[MAXLIMIT];
	            	FileOutputStream fos = new FileOutputStream(destFile);

	            	dest = new
	            	BufferedOutputStream(fos, MAXLIMIT);
	            	while ((count = is.read(data, 0, MAXLIMIT)) != -1){
	            		dest.write(data, 0, count);
	            	}
	            	dest.flush();
	            	dest.close();
	            	is.close();
	            }
	            if(currentEntry.endsWith(".zip")){
	            	//set to true exit loop and abort import
	            	ableToExtract = false;
	            }
	         }
         	zipfile.close();
		} 
		 
		catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Something went wrong trying to extraxt zip please try again",
		    		"Error",JOptionPane.ERROR_MESSAGE);
		}
		 
		finally {			
			return ableToExtract; 	 
		} 
	 }
	
	public static boolean deleteDir(File folder){
		
		if(folder.isDirectory()){
			
			File[] dirContents = folder.listFiles();
			for(int x = 0; x <= dirContents.length - 1; x++){
				
				boolean result = deleteDir(dirContents[x]);
				if(!result){
					return false;
				}	
			}
		}
		return folder.delete();
	}

	/* NOTE
	 * The following method was written by Yong Mook Kim 
	 * some minor modifications made by Michael Zaragoza
	 * Original Source Code can be found 
	 * @ http://www.mkyong.com/java/search-directories-recursively-for-file-in-java/
	 * @param folder Folder to be Searched
	 */
	
	public void searchDirectories(File folder){
		
		if(folder.isDirectory()){
			search(folder);
		}
	}
	
	/* 	NOTE
	 * The following helper method was written by Yong Mook Kim 
	 * some minor modifications made by Michael Zaragoza
	 * Original Source Code can be found 
	 * @ http://www.mkyong.com/java/search-directories-recursively-for-file-in-java/
	 * @param folder Folder to be Searched
	 */
	public boolean search(File file){
		boolean result = false;
		
		if(file.isDirectory() && file.canRead()){	
			for(File f : file.listFiles()){
					
				if(f.isDirectory()){
					search(f);
				}
				else{
					String name = f.getName();
					if(name.endsWith(JAVAEXTENSION)){
						result =  true;
						files.add(f);
					}
				}
			}
		}	
		
		return result;
	}
	
	public void computeLOC(File readFile){
		FileReader file;
		LOC = 0;
		fileLine = "";
		fileName = readFile.getName();
		
		try {
			
			file = new FileReader(readFile);
			if (fileName.contains(".java")) { 
			
				BufferedReader reader = new BufferedReader(file);
	
				fileLine = reader.readLine();
	
				// Read until end of file
				while (fileLine != null) {
	
					// Remove all spaces in the beginning
					fileLine = fileLine.replaceAll("\\s+", "");
	
					// Counts all but empty lines
					if (fileLine.length() > 0) {
						LOC++;
					}
	
					// Do not count comments
					if (fileLine.startsWith("//")) {
					}
	
					// Do not count lines of multiple comments
					if (fileLine.startsWith("/*")) {
	
						while (!fileLine.endsWith("*/")) {
							fileLine = reader.readLine();
						}
					}
					
					fileLine = reader.readLine();
				}
				
				file.close();
			}
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* Grabs the file name and LOC from the XML file 
	 * then puts it into an array.
	 */
	public static Object[][] xmlToArray() {

		String loc;

		try {
			File inputFile = new File(configPath + "SavedLOC.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("LOCFILE");

			ROW = nList.getLength();
			array = new String[ROW][COLUMN];

	        for (int temp = 0; temp < nList.getLength(); temp++) {
		           Node nNode = nList.item(temp);

		           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		              Element eElement = (Element) nNode;

		              fN = (eElement
				              .getElementsByTagName("SOURCEFILE")
				              .item(0)
				              .getChildNodes()
				              .item(0)
				              .getNodeValue());

		              loc = (eElement
		    	              .getElementsByTagName("LOC")
		    	              .item(0)
		    	              .getChildNodes()
		    	              .item(0)
		    	              .getNodeValue());

	            	  array [temp][0] = fN;
	            	  array [temp][1] = loc;
		          }
		    }
		}   
		
		catch (FileNotFoundException e) {
		    JOptionPane.showMessageDialog(null,"Cannot Find File with Saved LOC",
		    		"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Sorry, something went wrong, try importing again.",
		    		"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		return (array);
	}
	
	private void listToHash (List<File> currentList) {
		File listFileName = null;
		
		for (int i = 0; i < currentList.size(); i++) {
			listFileName = currentList.get(i);
			computeLOC(listFileName);
			locMap.put(fileName , LOC);
		}
	}

	// Setters and getters
	public void setLOC(int loc) {
		LOC = loc;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setFiles(List<File> files) {
		this.files = files;
	}
	
	public void setTestCount(int testCount) {
		this.testCount = testCount;
	}
 
	public int getLOC() {
		return LOC;
	}

	public String getFileName() {
		return fileName;
	}
	
	public List<File> getFiles() {
		return files;
	}

	public Hashtable<String,Integer> getLocTable(){
		return locMap;
		
	}

	public int getTestCount() {
		return testCount;
	}
}
