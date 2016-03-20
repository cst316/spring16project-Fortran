package net.sf.memoranda.util;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.*;

import org.w3c.dom.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.*;

/**
 * @author Quy Ly and Saul Lopez
 */

public class LOCReader {

	private static final int COLUMN = 2;
	private int LOC;
	private String fileLine;
	private String fileName;
	private static String fN;
	private static int row;
	private static String[][] array;

	public LOCReader() {
		LOC = 0;
		fileLine = "";
		fileName = "";
	}

	public LOCReader(File readFile) {

		FileReader file;
		LOC = 0;
		fileLine = "";
		fileName = readFile.getName();
		ZipFile zipFile = null;
		
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
			
			//Read zip file and search for .java files
			if (fileName.contains(".zip")) {
				zipFile = new ZipFile(fileName);
		        
				Enumeration<? extends ZipEntry> e = zipFile.entries();
				
				//Loop zip if it has folders, it checks the folders too
				while (e.hasMoreElements()) {
					ZipEntry entry = e.nextElement();
					
					//Get the full path name
					String entryName = entry.getName();
					if (entryName.contains(".java")) {
						
						//If lastindexof is ".java", does not give the full name
						//therefore, lastindexof is "/" and get anything after the "/" 
						int index = entryName.lastIndexOf("/");
						fN = entryName.substring(index + 1);
						//for testing purposes
						System.out.println(fN);
					}
				}
		        zipFile.close();
			}
		}

		catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

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
			JFrame frame = new JFrame();
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

	public int getLOC() {
		return LOC;
	}

	public String getFileName() {
		return fileName;
	}
}