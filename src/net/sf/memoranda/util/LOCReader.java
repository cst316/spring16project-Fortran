package net.sf.memoranda.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.w3c.dom.*;
import javax.xml.parsers.*;

/**
*
* @author Quy Ly and Saul Lopez
*/

public class LOCReader {
  
	int LOC;
	String fileLine;
	String fileName;
	
    public LOCReader () {
    	LOC = 0;
        fileLine = "";
    }
    
    public LOCReader (File readFile) {
        
        FileReader file;
        LOC = 0;
        fileLine = "";
        fileName = readFile.getName();

        try 
        {
            file = new FileReader(readFile);
            
            BufferedReader reader = new BufferedReader(file);
       
            fileLine = reader.readLine();
     
//Read until end of file
            while (fileLine != null){
               
        //Remove all spaces in the beginning
                fileLine = fileLine.replaceAll("\\s+", "");
              
        //Counts all but empty lines
                if (fileLine.length() > 0 ) {
                    LOC++;
                }
    
        //Do not count comments
                if (fileLine.startsWith("//")) {
                  
                } 
        
        //Do not count lines of multiple comments
                if (fileLine.startsWith("/*")) {
                   
                        while(!fileLine.endsWith("*/")) {
                            fileLine = reader.readLine();
                        }
                }

                fileLine = reader.readLine();
                
            }
            file.close();
        }
        
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
	private static String configPath = System.getProperty("user.home") + File.separator 
			+ ".memoranda" + File.separator;
   
    public static void xmlToArray() {

	 try {
		File inputFile = new File(configPath + "SavedLOC.xml");
        DocumentBuilderFactory dbFactory 
           = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        
        doc.getDocumentElement().normalize();
        System.out.println("Root element: " 
           + doc.getDocumentElement().getNodeName());
        
        NodeList nList = doc.getElementsByTagName("LOCFILE");//getDocumentElement().getChildNodes();
        System.out.println("----------------------------" + nList.getLength());
        
        for (int temp = 0; temp < nList.getLength(); temp++) {
           Node nNode = nList.item(temp);

           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
              Element eElement = (Element) nNode;
              System.out.println("Filename: " 
                 + eElement
                 .getElementsByTagName("SOURCEFILE")
                 .item(0)
                 .getChildNodes()
                 .item(0)
                 .getNodeValue());
              
              System.out.println("Lines of Code: "
                 + eElement
                 .getElementsByTagName("LOC")
                 .item(0)
                 .getChildNodes()
                 .item(0)
                 .getNodeValue());
           }
        } 
	  }
    
	  catch (Exception e) {
          e.printStackTrace();
      }
    }
    
//Setters and getters    
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
