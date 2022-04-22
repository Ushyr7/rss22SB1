package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "content")
@XmlAccessorType(XmlAccessType.NONE)
public class Content {

	@XmlValue
	private String value;
	
	@XmlAttribute
	private String type;
	
	@XmlAttribute
	private String href;
	
	public Content() {}
	
	public Content(String val, String t) {
		value = val;
		type = t;
	}
	
	public Content(String val, String t, String h) {
		value = val;
		type = t;
		href = h;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getType() {
		return type;
	}
	
	public String getHref() {
		return href;
	}

}
