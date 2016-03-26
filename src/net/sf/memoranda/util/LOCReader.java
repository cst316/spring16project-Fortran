package net.sf.memoranda.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.w3c.dom.*;

import javax.swing.JOptionPane;
import javax.xml.parsers.*;

import org.apache.commons.io.FilenameUtils;

/**
 * @author Quy Ly and Saul Lopez
 */

public class LOCReader {
	//field variables and constants
	private static final int COLUMN = 2;
	private int LOC;
	private String fileLine;
	private String fileName;
	private static int row;
	private static String[][] array;

	//constructor(s)
	public LOCReader() {
		LOC = 0;
		fileLine = "";
		fileName = "";
	}
	public LOCReader(File readFile) throws ExtensionException {

		FileReader file;
		LOC = 0;
		fileLine = "";
		fileName = readFile.getName();
		String extension = FilenameUtils.getExtension(readFile.getAbsolutePath());

		try {
			file = new FileReader(readFile);
			if (extension.equalsIgnoreCase("java")) {

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
					file.close();
				}
			}
			else{
				file.close();
				throw new ExtensionException();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}


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

	//methods
	private static String configPath = System.getProperty("user.home") + File.separator + ".memoranda" + File.separator;
	public static Object[][] xmlToArray() {

		String fN;
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

}//LOCReader class