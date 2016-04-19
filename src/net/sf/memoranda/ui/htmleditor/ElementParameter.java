package net.sf.memoranda.ui.htmleditor;

import javax.swing.text.Element;

public class ElementParameter {

	private Element element;
	private String source;
	private String alternative;
	
	public ElementParameter(Element el, String src, String alt) {
		element = el;
		source = src;
		alternative = alt;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAlternative() {
		return alternative;
	}

	public void setAlternative(String alternative) {
		this.alternative = alternative;
	}

}
