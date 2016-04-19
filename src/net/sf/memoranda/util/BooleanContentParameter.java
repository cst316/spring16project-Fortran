package net.sf.memoranda.util;

public class BooleanContentParameter {
	private boolean IsNum;
	private boolean areTitlesAsheaders;
	private boolean copyImagesRequired;
	
	public BooleanContentParameter(boolean num,
			boolean titlesAsHeaders, boolean copyImages) {
		IsNum = num;
		areTitlesAsheaders = titlesAsHeaders;
		copyImagesRequired = copyImages;
	}
	
	public boolean isNum() {
		return IsNum;
	}

	public void setIsNum(boolean isNum) {
		IsNum = isNum;
	}

	public boolean areTitlesAsheaders() {
		return areTitlesAsheaders;
	}

	public void setAreTitlesAsheaders(boolean areTitlesAsheaders) {
		this.areTitlesAsheaders = areTitlesAsheaders;
	}

	public boolean isCopyImagesRequired() {
		return copyImagesRequired;
	}

	public void setCopyImagesRequired(boolean copyImagesRequired) {
		this.copyImagesRequired = copyImagesRequired;
	}

}
