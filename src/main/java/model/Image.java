package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "image")
@XmlAccessorType(XmlAccessType.NONE)
public class Image {
	
	@XmlAttribute
	private String type;

	@XmlAttribute
	private String href;
	
	@XmlAttribute
	private String alt;
	
	@XmlAttribute
	private int length;

	public Image(String t, String h, String a) {
		type = t;
		href = h;
		alt = a;
	}
	
	public Image(String t, String h, String a, int l) {
		type = t;
		href = h;
		alt = a;
		length = l;
	}

	public Image() {}
	
	public String getType() {
		return type;
	}
	
	public String getHref() {
		return href;
	}
	
	public String getAlt() {
		return alt;
	}
	
	public int getLength() {
		return length;
	}
	
}
