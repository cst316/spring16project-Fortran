package net.sf.memoranda.util;

public class BooleanXHTMLParameter {

	private boolean isXHTML;
	private boolean isChunked;
	private boolean isNavigatable;
	
	public BooleanXHTMLParameter(boolean xhtml, 
			boolean chunked, boolean navigation) {
		
		isXHTML = xhtml;
		isChunked = chunked;
		isNavigatable = navigation;
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

	public boolean isNavigatable() {
		return isNavigatable;
	}

	public void setNavigatable(boolean isNavigatable) {
		this.isNavigatable = isNavigatable;
	}

	

}
