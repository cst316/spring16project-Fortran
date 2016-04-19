package net.sf.memoranda.ui.htmleditor;

public class ImagePropertiesParameter {
	

	private String width_Img;
	private String height_Img;
	private String hspace_Img;
	private String vspace_Img;
	
	public ImagePropertiesParameter( String width, String height, String hspace,
			String vspace) {
		
		width_Img = width;
		height_Img = height;
		hspace_Img = hspace;
		vspace_Img = vspace;
		
	}

	public String getWidth_Img() {
		return width_Img;
	}
	public void setWidth_Img(String width_Img) {
		this.width_Img = width_Img;
	}
	public String getHeight_Img() {
		return height_Img;
	}
	public void setHeight_Img(String height_Img) {
		this.height_Img = height_Img;
	}
	public String getHspace_Img() {
		return hspace_Img;
	}
	public void setHspace_Img(String hspace_Img) {
		this.hspace_Img = hspace_Img;
	}
	public String getVspace_Img() {
		return vspace_Img;
	}
	public void setVspace_Img(String vspace_Img) {
		this.vspace_Img = vspace_Img;
	}



	
	
}
