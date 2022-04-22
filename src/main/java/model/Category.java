package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.NONE)
public class Category {

	@XmlAttribute
	private String term;
	
	public Category(String t) {
		this.term = t;
	}
	
	public Category() {}
	
	public String getTerm() {
		return this.term;
	}
}
