package net.sf.memoranda.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
*
* @author Quy Ly and Saul Lopez
*/

public class LOCreader {
  
	int LOC;
	String fileName;
	
    public LOCreader () {
    	LOC = 0;
        fileName = "";
    }
    
    public LOCreader (File readFile) {
        
        FileReader file;
        LOC = 0;
        fileName = "";

        try 
        {
            file = new FileReader(readFile);
            
            BufferedReader reader = new BufferedReader(file);
            
            fileName = reader.readLine();
     
//Read until end of file
            while (fileName != null){
               
        //Remove all spaces in the beginning
                fileName = fileName.replaceAll("\\s+", "");
              
        //Counts all but empty lines
                if (fileName.length() > 0 ) {
                    LOC++;
                }
    
        //Do not count comments
                if (fileName.startsWith("//")) {
                  
                } 
        
        //Do not count lines of multiple comments
                if (fileName.startsWith("/*")) {
                   
                        while(!fileName.endsWith("*/")) {
                            fileName = reader.readLine();
                        }
                }

                fileName = reader.readLine();
                
            }
            System.out.println("LOC Called");
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
		this.fileName = fileName;
	}
    
	public int getLOC() {
		return LOC;
	}

	public String getFileName() {
		return fileName;
	}
}
