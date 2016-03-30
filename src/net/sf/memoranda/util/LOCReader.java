package net.sf.memoranda.util;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
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
 * @author Quy Ly and Saul Lopez
 */

public class LOCReader {
	//field variables and constants
	private static final int COLUMN = 2;
	private int LOC;
	private int testCount;
	private String fileLine;
	private String fileName;
	private String javaFile;
	private static String fN;
	private static int row;
	private static String[][] array;
	private String FOLDERDEST = System.getProperty("user.home") + File.separator 
	     	+ ".memoranda" + File.separator;
	private final int MAXLIMIT = 2048;
	
	private Hashtable<String,Integer> LocMap;
	private boolean ableToExtract;
	private List<File> files;
	
	
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
	            //System.out.println(currentEntry);
	            //For Unit Testing Purposes
	            String path = "test/";
	          
	           destFile = new File(FOLDERDEST + zipFileName, currentEntry);
	           // destFile = new File(path + zipFileName, currentEntry);
	             //destFile = new File(path,currentEntry);
	            File destinationParent = destFile.getParentFile();
	            
	            if(destinationParent != null){
	            	
	            	destinationParent.mkdirs();
	            }
	            
	            if(!entry.isDirectory()) {

	            	
	            	if (currentEntry.contains(".java")) {
						//If current Entry is
						//If lastindexof is ".java", does not give the full name
						//therefore, lastindexof is "/" and get anything after the "/"
						int index = currentEntry.lastIndexOf("/");
						String fN = currentEntry.substring(index + 1);
						++testCount;
						//for testing purposes
						//System.out.println(fN);
					}
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
	
	private List<File> getExtractedFiles() {
		File folder = new File(FOLDERDEST + fileName);//zipFolderName
		//File file = null;
		//File newFile; //= new File(FOLDERDEST + "name");
		File[] listOfFiles = folder.listFiles(); //Returns an array of abstract pathnames denoting the files in the directory denoted by this abstract pathname
		files = new ArrayList<File>(Arrays.asList(listOfFiles));
		for (int i = 0; i < files.size(); i++) {
			if (files.get(i).isDirectory()) {
				files.remove(i);
			}
		}
		return files;
	 }
	
	public void computeLOC(File readFile){
		FileReader file;
		LOC = 0;
		fileLine = "";
		fileName = readFile.getName();
	
		ZipFile zipFile = null;
		String fN = null;
		
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
				
				// Quy Im thinking once file is done put LOC and name to hashTable in here
				file.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
			
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public LOCReader() {
		LOC = 0;
		fileLine = "";
		fileName = "";
		LocMap = new Hashtable<String,Integer>();
	}
	
	public LOCReader(File readFile) {
		
		LocMap = new Hashtable<String,Integer>();
		LocMap.put("boi.java",12);///for testing purposes
		LocMap.put("Bush Did 9/11",190);
		LOC = 0;
		fileLine = "";
		fileName = readFile.getName();	
		//ZipFile zipFile = null;
		//String fN = null;

		if (fileName.endsWith(".java")) {
				
			computeLOC(readFile);
				
		}
		else if (fileName.contains(".zip")) {
				
		    boolean result = extract(readFile);
		    
		    if(result == false){
		    	
		    	///unable to extract nested zip
		    	 JOptionPane.showMessageDialog(null,"Nested Zip Found Aborting Import",
				    		"ZipError",JOptionPane.ERROR_MESSAGE);
		    }
		    else{
		    	
		    	//get list of files Suals method here
		    	List<File> currentList = getExtractedFiles();
				//call quy method that computes LOC on each file
		    	
		    	//we done
		    }
		    
		}
		
	}

	//methods
	private static String configPath = System.getProperty("user.home") + File.separator + ".memoranda" + File.separator;
	public static Object[][] xmlToArray() {

		String loc;

		try {
			File inputFile = new File(configPath + "SavedLOC.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("LOCFILE");

			row = nList.getLength();
			array = new String[row][COLUMN];

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
		}   catch (FileNotFoundException e) {
		    JOptionPane.showMessageDialog(null,"Cannot Find File with Saved LOC",
		    		"Error",JOptionPane.ERROR_MESSAGE);
		}
	    catch (Exception e) {
            e.printStackTrace();
        }
		return (array);
	}

	// Setters and getters
	public void setLOC(int loc) {
		LOC = loc;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	

	public Hashtable<String,Integer> getLocTable(){
		return LocMap;
		
	}

	public int getTestCount() {
		return testCount;
	}
	

}
