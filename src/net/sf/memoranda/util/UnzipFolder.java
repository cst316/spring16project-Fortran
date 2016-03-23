package net.sf.memoranda.util;

import java.io.*;
import java.util.zip.*;

import org.apache.commons.io.FilenameUtils;

import java.util.Enumeration;
import java.io.IOException;

public class UnZipFolder{
	//constants & field names
	private final int MAXLIMIT = 2048;
	/** .memoranda location:: drive:\Users\<user>\.memoranda*/
	private String FOLDERDEST = System.getProperty("user.home") + File.separator
	     	+ ".memoranda" + File.separator;
	/** file held <p>Ex: ZipFullOfJava.zip*/
	private String file;
	/** fileName only <p>Ex: ZipFullOfJava*/
	private String zipFileName;

	//construct(s)
	/**Constructs UnzipFolder object with predefined and/or passed value(s).
	 * @param zipFile File to unzip. Assumes file has '.zip' extension.
	 */
	public UnZipFolder(File zipFile){
		extract (zipFile);
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
	/**  @param zipFile File to unzip. Assumes file has '.zip' extension.
	 */
	public void extract (File zipFile){
		//obtain zipfile's files
		file = zipFile.getName();
		zipFileName = file.substring(0,file.length() - 4);
		new File(zipFileName).mkdir();
		//unzip file and extract java files
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
	          	File destFile = new File(FOLDERDEST + zipFileName, currentEntry);
	            File destinationParent = destFile.getParentFile();
	            destinationParent.mkdir();

	            if(!entry.isDirectory()) {

	            	System.out.println("Extracting: " +entry);
	            	//Quy Ly's Code 43-51 prints filename
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

	         }
         	zipfile.close();
		 } catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + "Error in reading/extracting zip.");
			// TODO: handle exception
		}
	 }//extract method
}//UnzipFolder class
