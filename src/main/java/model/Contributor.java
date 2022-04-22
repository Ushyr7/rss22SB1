package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contributor")
@XmlAccessorType(XmlAccessType.NONE)
public class Contributor {

	@XmlElement
	private String name;
	
	@XmlElement
	private String mail;
	
	@XmlElement
	private String uri;
	
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

	public Contributor() {}
	
	public Contributor(String n) {
		name = n;
	}
	
	public Contributor(String n,String m) {
		name = n;
		mail = m;
	}
	
	public Contributor(String n,String m, String u) {
		name = n;
		mail = m;
		uri = u;
	}
	
	public String getName() {
		return name;
	}

	
}
