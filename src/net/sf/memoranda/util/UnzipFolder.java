package net.sf.memoranda.util;

import java.io.*;
import java.util.zip.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class UnzipFolder{
	//constants & field names
	private final int MAXLIMIT = 2048;
		///** zip folder destination <p>Example: drive:\Users\<user>\*/
	/** extraction destination <p>.memoranda location:: drive:\Users\<user>\.memoranda*/
	private String FOLDERDEST = System.getProperty("user.home") + File.separator
	     	+ ".memoranda" + File.separator;
	/** name of file with extension <p>Example: ZipFullOfJava.zip*/
	private String zipFolder;
	/** name of file only, no extension <p>Example: ZipFullOfJava*/
	private String zipFolderName;
	/** */
	private List<File> files;

	//constructor(s)
	/**Constructs UnzipFolder object with predefined and/or passed value(s).
	 * @param file File to unzip. Assumes file has '.zip' extension.
	 */
	public UnZipFolder(File file) throws ExtensionException{
		zipFolder = file.getName();

		StringBuilder builder = new StringBuilder(zipFolder);
		int index = builder.lastIndexOf(".");
		String extension = builder.substring(index,zipFolder.length());
		if (extension.equalsIgnoreCase("zip")){
			throw new ExtensionException();
		}

		zipFolderName = zipFolder.substring(0,zipFolder.length() - 4);
		//FOLDERDEST = file.getAbsolutePath(); //C:\temp\..\\..\file.txt

		extract (file);
		files = getFiles();
	}

	//Getters & Setters
	/** gets the folder destination of zipFile
	 * @return FOLDERDEST folder destination
	 */
	public String getFOLDERDEST() {
		return FOLDERDEST;
	}
	/** gets the zip file
	 * <p> Example: 'ZipName.zip'
	 * @return zipFile name of zip file <p> Example:'ZipFile.zip'
	 */
	public String getZipFile() {
		return zipFolder;
	}
	/** gets the zip file name
	 * <p> Example: 'ZipName'
	 * @return zipFileName name of zip file, no extensions included
	 */
	public String getZipFileName() {
		return zipFolderName;
	}
	public List<File> getFiles() {
		return files;
	}

	//method(s)
	/** extracts all contents of zip file.
	 * @param file zip file to unzip. Assumes file has '.zip' extension.
	 */
	public void extract (File file){
		new File(zipFolderName).mkdir();
		//unzip file and extract java files
		try {
			 BufferedOutputStream dest = null;
	         BufferedInputStream is = null;
	         ZipEntry entry;
	         ZipFile zipfile = new ZipFile(file);
	         Enumeration<? extends ZipEntry> e = zipfile.entries();
	         while(e.hasMoreElements()) {

	            entry = (ZipEntry) e.nextElement();

	            String currentEntry = entry.getName();
	          	File destFile = new File(FOLDERDEST + zipFolderName, currentEntry);
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
	 }//extract method

	public List<File> getExtractedFiles() {
		File folder = new File(FOLDERDEST + zipFolderName);
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
	 }//extract method
}//UnzipFolder class
