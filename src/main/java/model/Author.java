package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.NONE)
public class Author {

	@XmlElement
	private String name;
	
	@XmlElement
	private String mail;
	
	@XmlElement
	private String uri;
	
	public Author() {}
	
	public Author(String n) {
		name = n;
	}
	
	public Author(String n,String m) {
		name = n;
		mail = m;
	}
	
	public Author(String n,String m, String u) {
		name = n;
		mail = m;
		uri = u;
	}
	
	public String getName() {
		return name;
	}	
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}


}
