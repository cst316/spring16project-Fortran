package net.sf.memoranda.util;

import java.io.File;

import net.sf.memoranda.Project;

public class ExportParameter {

	private Project prject;
	private File file;
	private String charSet;
	private boolean isXHTML;
	private boolean isChunked;
	private boolean isNum;
	private boolean areTitlesAsheaders;
	private boolean isImageCopied;
	private boolean isNavigatable;
	
	public boolean isNavigatable() {
		return isNavigatable;
	}

	public void setNavigatable(boolean isNavigatable) {
		this.isNavigatable = isNavigatable;
	}

	public ExportParameter(Project prj, File f, String charset) {
		
		prject = prj;
		file = f;
		charSet = charset;
		// TODO Auto-generated constructor stub
	}

	public Project getPrj() {
		return prject;
	}

	public void setPrj(Project prj) {
		this.prject = prj;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
}

	