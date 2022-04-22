package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection="Feeds")
@XmlRootElement(name = "feed")
@XmlAccessorType(XmlAccessType.NONE)
public class Feed {
	
	@Id
	private String id;
	
	@Field(value="lang")
	@XmlAttribute
	private String lang;
	
	@Field(value="Title")
	@XmlElement
	private String title;
	
	@Field(value="pubDate")
	@XmlElement
	private String pubDate;
	
	@Field(value="copyright")
	@XmlElement
	private String copyright;
	
	@Field(value="link")
	@XmlElement
	private List<Link> link;
	
	@Field(value="item")
	@XmlElement
	private List<Item> item;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubdate() {
		return pubDate;
	}

	public void setPubdate(String pubdate) {
		this.pubDate = pubdate;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public List<Link> getLink() {
		return link;
	}

	public void setLink(List<Link> link) {
		this.link = link;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> items) {
		this.item = items;
	}

	public Feed() {}

	public String toXmlString() {
		List<Item> i = getItem();
		String xml = "<xml>"
				+ "<feed lang='"  + getLang() +"'>"
				+ "<title>" + getTitle() + "</title>"
				+ "<pubDate>" + getPubdate() + "</pubDate>"
				+ "<copyright>" + getCopyright() + "</copyright>";
			for(Link l : getLink()) {
				xml += "<link rel='" + l.getRel() +"' type='" + l.getType() + "' href='" + l.getHref() + "'>" + l.getValue()  + "</link>";
			}
			
			for(Item it : i) {
				xml += "<item>"
						+ "<guid>" + it.getGuid() + "</guid>"
						+ "<title>" + it.getTitle() + "</title>";
				
				for(Category c : it.getCategory()) {
					xml +="<category term='" + c.getTerm() + "'/>";
				}
				if (it.getPublished() == null) {
					xml += "<updated>" + it.getUpdated() + "</updated>";
				} else {
					xml += "<published>" + it.getPublished() + "</published>";
				}
				
				if (it.getImage() != null) {
					if(it.getImage().getLength() != 0 ) {
						xml += "<image type='" + it.getImage().getType() + "' href='" + it.getImage().getHref() + "' alt='" + it.getImage().getAlt()+ "' length='" + it.getImage().getLength() + "'/>";
					} else {
						xml += "<image type='" + it.getImage().getType() + "' href='" + it.getImage().getHref() + "' alt='" + it.getImage().getAlt() + "'/>";
					}
				}
				
				if (it.getContent().getHref() == null) {
					xml += "<content type='" + it.getContent().getType() + "'>" + it.getContent().getValue() + "</content>";
				} else {
					xml += "<content type='" + it.getContent().getType() + "' href='" + it.getContent().getHref() +"'>" + it.getContent().getValue() + "</content>";
				}
				
				if(it.getAuthor() != null) {
					for(Author a : it.getAuthor()) {
						xml += "<author>" 
								+ "<name>" + a.getName() + "</name>";
						if(a.getMail() != null) {
							xml += "<email>" + a.getMail() + "</email>";
						}
						if(a.getUri() != null) {
							xml +="<uri>" + a.getUri() + "</uri>";
						}
						xml +="</author>";
					}
				}
				if(it.getContributor() != null) {
					for(Contributor c : it.getContributor()) {
						xml += "<contributor>" 
								+ "<name>" + c.getName() + "</name>";
						if(c.getMail() != null) {
							xml += "<email>" + c.getMail() + "</email>";
						}
						if(c.getUri() != null) {
							xml +="<uri>" + c.getUri() + "</uri>";
						}
						xml +="</contributor>";
					}
				}
			}
			xml +=  "</item>"
					+"</feed>" 
					+ "</xml>";
			return xml;
	}
}
