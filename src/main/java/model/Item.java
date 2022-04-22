package model;


import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;


@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.NONE)
public class Item  {
	
	@XmlElement
	private String guid;
	
	@XmlElement
	private String title;

	@XmlElement
	private String published;
	
	@XmlElement
	private String updated;
	
	@XmlElement
	private List<Category> category;
	
	@XmlElement
	private Image image;
	
	@XmlElement
	private Content content;
	
	@XmlElement
	private List<Author> author;
	
	@XmlElement
	private List<Contributor> contributor;
	
	public Item() {}
	
	public Item(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return ("Article : " + title + "\n(" + guid 
				+ ") Le = " + published );
	}
	
	public String getGuid() {
		return guid;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getPublished() {
		return published;
	}
	
	
	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}
	
	public List<Category> getCategory() {
		return category;
	}
	
	public Content getContent() {
		return content;
	}
	
	public List<Author> getAuthor() {
		return author;
	}
	
	public List<Contributor> getContributor() {
		return contributor;
	}

	public void setContributor(List<Contributor> contributor) {
		this.contributor = contributor;
	}
	
	public Image getImage() {
		return image;
	}
	
}


