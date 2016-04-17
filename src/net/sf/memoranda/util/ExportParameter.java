package net.sf.memoranda.util;

import java.io.File;

import net.sf.memoranda.Project;

public class ExportParameter {

	private Project prj;
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

	public ExportParameter(Project prj, File f, String charset, 
			boolean xhtml, boolean chunked, boolean navigation,
			boolean num, boolean titlesAsHeaders, boolean copyImages) {
		
		
		
		// TODO Auto-generated constructor stub
	}

	public Project getPrj() {
		return prj;
	}

	public void setPrj(Project prj) {
		this.prj = prj;
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

	public boolean isXHTML() {
		return isXHTML;
	}

	public void setXHTML(boolean isXHTML) {
		this.isXHTML = isXHTML;
	}

	public boolean isChunked() {
		return isChunked;
	}

	public void setChunked(boolean isChunked) {
		this.isChunked = isChunked;
	}

	public boolean isNum() {
		return isNum;
	}

	public void setNum(boolean isNum) {
		this.isNum = isNum;
	}

	public boolean areTitlesAsheaders() {
		return areTitlesAsheaders;
	}

	public void setAreTitlesAsheaders(boolean areTitlesAsheaders) {
		this.areTitlesAsheaders = areTitlesAsheaders;
	}

	public boolean isImageCopied() {
		return isImageCopied;
	}

	public void setImageCopied(boolean isImageCopied) {
		this.isImageCopied = isImageCopied;
	}

}
