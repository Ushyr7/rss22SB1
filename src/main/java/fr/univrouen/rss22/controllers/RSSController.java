package fr.univrouen.rss22.controllers;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univrouen.rss22.FeedService;
import fr.univrouen.rss22.FeedServiceImpl;
import fr.univrouen.rss22.TransformXML;
import model.Author;
import model.Category;
import model.Contributor;
import model.Feed;
import model.Item;
import model.Link;
	
@RestController
public class RSSController {
	
	@Autowired
	private FeedService fs;
	
	private TransformXML transformer;
	
	public RSSController() {
		fs = new FeedServiceImpl();
		transformer = new TransformXML();
	}
	
	@GetMapping(value = "/rss22/resume/xml", produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public String getListRSSinXML() {
		List<Feed> feed = fs.findAll();
		List<Item> item = new ArrayList<Item>();
		for (Feed f : feed) {
			item.addAll(f.getItem());
		}
		String xml = "<items>";
		for(Item it : item) {
			if (it.getPublished() != null) {
			xml +=  "<item>"
					+ "<title>" + it.getTitle() + "</title>"
					+ "<published>" + it.getPublished() + "</published>"
					+ "<guid>" + it.getGuid() + "</guid>"
					+"</item>";
			} else {
				xml +=  "<item>"
						+ "<title>" + it.getTitle() + "</title>"
						+ "<updated>" + it.getUpdated() + "</updated>"
						+ "<guid>" + it.getGuid() + "</guid>"
						+"</item>";
			}
		}
		xml += "</items>";
		return xml;
	}
	
	@GetMapping(value = "/rss22/resume/html")
	@ResponseBody
	public String getListRSSinHTML() {
		List<Feed> feeds = this.fs.findAll();
		List<Item> item = new ArrayList<Item>();
		for (Feed f : feeds) {
			item.addAll(f.getItem());
		}
		String html = "<!DOCTYPE html>" 
				+ "<html lang='fr'>"
				+ "<head>"
				+ "<title> Listes des articles </title>"
				+ "</head>"
				+ "<body>";
		html += "<table>"
				+ "<thead>"
				+ "<tr>"
				+ "<th>Titre</th>"
				+ "<th>Date de publication/mise à jour</th>"
				+ "<th>guid</th>"
				+ "</tr>"
				+ "</thead>"
				+ "<tbody>";
		for(Item it : item) {
			if (it.getPublished() != null) {
				html += "<tr>"
						+ "<td>" + it.getTitle() + "</td>"
						+ "<td>" + it.getPublished() + "</td>"
						+ "<td>" + it.getGuid() + "</td>"
						+ "</tr>";
			} else {
				html += "<tr>"
						+ "<td>" + it.getTitle() + "</td>"
						+ "<td>" + it.getUpdated() + "</td>"
						+ "<td>" + it.getGuid() + "</td>"
						+ "</tr>";
			}
		}
		html += "</tbody>"
				+ "</table>"
				+"</body>"
				+"</html>";
		return html;
	}
	
	@GetMapping(value = "/rss22/resume/xml/{guid}", produces = "application/xml")
	public ResponseEntity<String> getItemInXML(@PathVariable(value = "guid") String guid) {
		Feed f = fs.findFeedContainingItemWithGuid(guid);
		Item i = fs.findItemWithGuid(guid);
		if(i == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} else {
			String xml = "<xml>"
					+ "<feed lang='" + f.getLang() +"'>"
					+ "<title>" + f.getTitle() + "</title>"
					+ "<pubDate>" + f.getPubdate() + "</pubDate>"
					+ "<copyright>" + f.getCopyright() + "</copyright>";
			for(Link l : f.getLink()) {
				xml += "<link rel='" + l.getRel() +"' type='" + l.getType() + "' href='" + l.getHref() + "'>" + l.getValue()  + "</link>";
			}
			
			xml += "<item>"
					+ "<guid>" + i.getGuid() + "</guid>"
					+ "<title>" + i.getTitle() + "</title>";
			
			for(Category c : i.getCategory()) {
				xml +="<category term='" + c.getTerm() + "'/>";
			}
			if (i.getPublished() == null) {
				xml += "<updated>" + i.getUpdated() + "</updated>";
			} else {
				xml += "<published>" + i.getPublished() + "</published>";
			}
			
			if (i.getImage() != null) {
				if(i.getImage().getLength() != 0 ) {
					xml += "<image type='" + i.getImage().getType() + "' href='" + i.getImage().getHref() + "' alt='" + i.getImage().getAlt()+ "' length='" + i.getImage().getLength() + "'/>";
				} else {
					xml += "<image type='" + i.getImage().getType() + "' href='" + i.getImage().getHref() + "' alt='" + i.getImage().getAlt() + "'/>";
				}
			}
			
			if (i.getContent().getHref() == null) {
				xml += "<content type='" + i.getContent().getType() + "'>" + i.getContent().getValue() + "</content>";
			} else {
				xml += "<content type='" + i.getContent().getType() + "' href='" + i.getContent().getHref() +"'>" + i.getContent().getValue() + "</content>";
			}
			
			if(i.getAuthor() != null) {
				for(Author a : i.getAuthor()) {
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
			if(i.getContributor() != null) {
				for(Contributor c : i.getContributor()) {
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
			xml +=  "</item>"
					+"</feed>" 
					+ "</xml>";
			return new ResponseEntity<String>(xml, HttpStatus.OK);
		}
	}
	

	
	/*
	@GetMapping(value = "/rss22/resume/html/{guid}")
	public ResponseEntity<String> getItemInHtml(@PathVariable(value = "guid") String guid) {
		ResponseEntity<String> xml = this.getItemInXML(guid);
		try {
			return new ResponseEntity<String>(transformer.transform(xml.getBody()), HttpStatus.OK);
		} catch (TransformerException e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	 */		
	
	@GetMapping(value = "/rss22/resume/html/{guid}")
	public ResponseEntity<String> getItemInHtml(@PathVariable(value = "guid") String guid) {
		Feed f = fs.findFeedContainingItemWithGuid(guid);
		Item i = fs.findItemWithGuid(guid);
		if(i == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} else {
			String html = "<!DOCTYPE html>"
					+ "<html lang='" + f.getLang() +"'>"
					+ "<head>"
					+ "<title>" + f.getTitle() + f.getPubdate() + f.getCopyright() + "</title>"
					+ "</head>"
					+"<body>"
					+"<div>";
			for(Link l : f.getLink()) {
				html += "<a rel='" + l.getRel() +"' type='" + l.getType() + "' href='" + l.getHref() + "'>" + l.getValue()  + "</a>";
			}
			
			html += "<h1>" + i.getTitle() + "</h1>";
			
			for(Category c : i.getCategory()) {
				html +="<h4>"  + "Catégorie : " + c.getTerm() + "</h4>";
			}
			if (i.getPublished() == null) {
				html += "<div>" + "Mise à jour : " +i.getUpdated() + "</div>";
			} else {
				html += "<div>" + "Publication : " + i.getPublished() + "</div>";
			}
			
			if (i.getImage() != null) {
					html += "<img " + "src='" + i.getImage().getHref() + "' alt='" + i.getImage().getAlt() + "'/>";
			}
			
			if (i.getContent().getHref() == null) {
				html += "<div>" + i.getContent().getValue() + "</div>";
			} else {
				html += "<div>" + i.getContent().getValue() + "</div> <br>"
						+"<div> Source : " + i.getContent().getHref() + "</div>";
			}
			
			if(i.getAuthor() != null) {
				for(Author a : i.getAuthor()) {
					html += "<p> Auteur : " + a.getName();
					if(a.getMail() != null) {
						html += " - " + a.getMail();
					}
					if(a.getUri() != null) {
						html += " - " + a.getUri();
					}
					html +="</p>";
				}
			}
			if(i.getContributor() != null) {
				for(Contributor c : i.getContributor()) {
					html += "<p>" + c.getName();
					if(c.getMail() != null) {
						html += " - " + c.getMail();
					}
					if(c.getUri() != null) {
						html +=" - " + c.getUri();
					}
					html +="</p>";
				}
			}
			html +=  "</div>"
					+"</body>" 
					+ "</html>";
			return new ResponseEntity<String>(html, HttpStatus.OK);
		}
	}
}
	
