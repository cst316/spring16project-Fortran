package net.sf.memoranda.util;

import java.io.*;
import java.util.zip.*;

//import org.apache.commons.io.FilenameUtils;

import java.util.Enumeration;
import java.io.IOException;

public class UnzipFolder{
	//constants & field names
	private final int MAXLIMIT = 2048;
	/** .memoranda location:: drive:\Users\<user>\.memoranda*/
	private String FOLDERDEST = System.getProperty("user.home") + File.separator
	     	+ ".memoranda" + File.separator;
	/** file held <p>Ex: ZipFullOfJava.zip*/
	private String file;
	/** fileName only <p>Ex: ZipFullOfJava*/
	private String zipFileName;
	private boolean isZipNested = false;
	public boolean isNestedZipThrown() {
		return isZipNested;
	}
	public void setNestedZipThrown(boolean nestedZipThrown) {
		isZipNested = nestedZipThrown;
	}
	//construct(s)
	/**Constructs UnzipFolder object with predefined and/or passed value(s).
	 * @param zipFile File to unzip. Assumes file has '.zip' extension.
	 */
	public UnzipFolder(File zipFile){
		file = zipFile.getName();
		isZipNested = false;
	}
	//getters & setters
	/**  @return FOLDERDEST folder destination
	 */
	public String getFOLDERDEST() {
		return FOLDERDEST;
	}

	/**  @return file name of zip file Example:'ZipFile.zip'
	 */
	public String getFile() {
		return file;
	}

	/**  @return zipFileName name of zip file 'ZipFile', not extensions included
	 */
	public String getZipFileName() {
		return zipFileName;
	}

	//method(s)
	/**  @param zipFile File to unzip. 
	 */
	public boolean extract (File zipFile){
		//obtain zipfile's files
		zipFileName = file.substring(0,file.length() - 4);
		new File(zipFileName).mkdir();
		//unzip file and extract java files
		 try {
			 //System.out.println(zipFile.getPath());
			 BufferedOutputStream dest = null;
	         BufferedInputStream is = null;
	         ZipEntry entry;
	         ZipFile zipfile = new ZipFile(zipFile);
	         Enumeration e = zipfile.entries();
	         File destFile;
	         while(e.hasMoreElements() && isZipNested == false) {
	        	 
	            entry = (ZipEntry) e.nextElement();

	            String currentEntry = entry.getName();
	            //System.out.println(currentEntry);
	            //For Unit Testing Purposes
	            String path = "test/";//GOMaDIIIT ITS IN HERERERE MODIFY FOR boi
	          
	          	//File destFile = new File(FOLDERDEST + zipFileName, currentEntry);
	            destFile = new File(path + zipFileName, currentEntry);
	             //destFile = new File(path,currentEntry);
	            File destinationParent = destFile.getParentFile();
	            
	            if(destinationParent != null){
	            	destinationParent.mkdirs();
	            }
	           
	            if(!entry.isDirectory()) {

	            	//System.out.println("Extracting: " +entry);
	            	//Quy Ly's Code 
	            	if (currentEntry.contains(".java")) {
						//If current Entry is
						//If lastindexof is ".java", does not give the full name
						//therefore, lastindexof is "/" and get anything after the "/"
						int index = currentEntry.lastIndexOf("/");
						String fN = currentEntry.substring(index + 1);
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
	            	isZipNested = true;
	            		
	            }
	             
	         }
         	zipfile.close();
		 } 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + "Error in reading/extracting zip.");
			// TODO: handle exception
		}
		 finally {
			
			 return isZipNested; 
			 
		}
		 
	 }
}
