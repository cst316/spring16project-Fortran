package net.sf.memoranda.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
            //Quy Im going to refactor this to fileContent for line 
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
            System.out.println("FileName " + fileName  + " " + LOC);
            file.close();
        }
        
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
   
//Setters and getters    
	public void setLOC(int loc) {
		LOC = loc;
	}

	public void setFileName(String fileName) {
		this.fileLine = fileName;
	}
    
	public int getLOC() {
		return LOC;
	}

	public String getFileName() {
		return fileName;
	}
}
