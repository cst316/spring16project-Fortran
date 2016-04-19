package net.sf.memoranda.ui.htmleditor;

public class ImageInfoParameter {

	private String border_Img;
	private String align_Img;
	
	public String getBorder() {
		return border_Img;
	}

	public void setBorder(String border) {
		this.border_Img = border;
	}

	public String getAlign() {
		return align_Img;
	}

	public void setAlign(String align) {
		this.align_Img = align;
	}

	public ImageInfoParameter(String border, String align) {
		border_Img = border;
		align_Img = align;
	}

}
