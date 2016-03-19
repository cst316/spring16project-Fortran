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
	/*
	 * Removes the .zip extension from fileName in order to be used 
	 * for making new Directory
	 * @param fileName File name to be usd
	 */
	public static String removeZipExtension(String fileName){
		
		//findindex of .??
		// Stringbuilder
		//remove everyting after .
		//return new string
		
		StringBuilder sb = new StringBuilder(fileName);
		int index = sb.lastIndexOf(".",0);
		System.out.println(index);
		sb = sb.delete(index,fileName.length());
		return sb.toString();

	}
	
	
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
	            is = new BufferedInputStream
	              (zipfile.getInputStream(entry));
	            int count;
	            byte data[] = new byte[MAXLIMIT];
	            FileOutputStream fos = new 
	              FileOutputStream(destFile);//needs to have the file to be written to  concat 
	            //fileNamezipFile.getPath() + File.separator + entry.toString()
	            dest = new 
	              BufferedOutputStream(fos, MAXLIMIT);
	            while ((count = is.read(data, 0, MAXLIMIT)) 
	              != -1) {
	               dest.write(data, 0, count);
	            }
	            dest.flush();
	            dest.close();
	            is.close();
	            } 
			 
	         }
	          
			 /*
			 String outputFolder = removeZipExtension(zipFile.getName());
			 //folder destination
			 String FolderName = FOLDERDEST + outputFolder;
			 File folder = new File(FolderName);
			 if(!folder.exists()){
				 
				 folder.mkdir();
				 System.out.println("made Dir");
			 }
			 
			 ZipInputStream zip = new ZipInputStream(new FileInputStream(zipFile));
			 
			 ZipEntry z = zip.getNextEntry();
			 
			 while(z != null){
				 
				 if(z.isDirectory()){
					 
					 z = zip.getNextEntry();
					 
					 
				 }
				 String fileName = z.getName();
				 File f = new File(FolderName +File.separator + fileName);
				 System.out.println("file unzip : "+ f.getAbsoluteFile());
				 new File(f.getParent()).mkdirs();
				 
				 FileOutputStream os = new FileOutputStream(f);
				 
				 int length = 0;
				 while((length = zip.read(buffer)) > 0){
					 
					 os.write(buffer,0,length);
					 
					 
				 }
				 
				 os.close();	 
				 z = zip.getNextEntry();		 
				 
			 }
			 zip.closeEntry();
			 zip.close();
			 */
			 
			
		 }catch (IOException e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}
		 
	 }
		
	
	
}
	
	
	




