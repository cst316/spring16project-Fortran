package net.sf.memoranda.util;

import java.util.zip.*;
import java.io.*;
import java.util.List;
import java.util.Enumeration;

import java.io.IOException;

public class UnzipFolder {

	private final int MAXLIMIT = 2048;
	private String FOLDERDEST = System.getProperty("user.home") + File.separator 
	     	+ ".memoranda" + File.separator;
	
	public UnzipFolder(File zipFile){
		
		String s = zipFile.getName();
		String folderName = s.substring(0,s.length() - 4);
		new File(folderName).mkdir();
	
		 try {
			 System.out.println(zipFile.getPath());
			 BufferedOutputStream dest = null;
	         BufferedInputStream is = null;
	         ZipEntry entry;
	         ZipFile zipfile = new ZipFile(zipFile);
	         Enumeration e = zipfile.entries();
	         while(e.hasMoreElements()) {
	        	 
	            entry = (ZipEntry) e.nextElement();
	         
	            String currentEntry = entry.getName();
	            System.out.print(currentEntry);
	          	File destFile = new File(FOLDERDEST + folderName, currentEntry);
	            File destinationParent = destFile.getParentFile();
	            destinationParent.mkdir();
	            
	            if(!entry.isDirectory()) {
	            
	            	System.out.println("Extracting: " +entry);
	            	//Quy Ly's Code 43-51
	            	if (currentEntry.contains(".java")) {
						//If current Entry is 
						//If lastindexof is ".java", does not give the full name
						//therefore, lastindexof is "/" and get anything after the "/" 
						int index = currentEntry.lastIndexOf("/");
						 String fN = currentEntry.substring(index + 1);
						//for testing purposes
						System.out.println(fN);
					}
	            	
	            	is = new BufferedInputStream
	            			(zipfile.getInputStream(entry));
	            	int count;
	            	byte data[] = new byte[MAXLIMIT];
	            	FileOutputStream fos = new 
	                FileOutputStream(destFile);
	           
	            	dest = new 
	            	BufferedOutputStream(fos, MAXLIMIT);
	            	while ((count = is.read(data, 0, MAXLIMIT)) != -1) 
	            	{
	            		dest.write(data, 0, count);
	            	}
	            	dest.flush();
	            	dest.close();
	            	is.close();
	            } 
			 
	         }
	          
		 } catch (IOException e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}
		 
	 }
		
}
	