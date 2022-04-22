package fr.univrouen.rss22.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univrouen.rss22.FeedService;
import fr.univrouen.rss22.FeedServiceImpl;
import fr.univrouen.rss22.XmlValidator;
import model.Feed;
import model.Item;

@RestController
public class PostController {
	
	@Autowired
	private FeedService fs;
	
	private XmlValidator v;
	
	public PostController() {
		fs = new FeedServiceImpl();
		v = new XmlValidator();
	}
	
	@RequestMapping(value = "/rss22/insert", method = RequestMethod.POST,
	consumes = "application/xml", produces = "application/xml")
	public ResponseEntity<String> insertFlux(@RequestBody Feed feed) {
		if(fs.findByTitleAndPubDate(feed.getTitle(), feed.getPubdate()).isPresent()) {
			return new ResponseEntity<String>("<error>" + feed.getTitle() + " : " + feed.getPubdate() + " ALREADY EXISTS" + "</error>",HttpStatus.BAD_REQUEST);
		} else {
			/*try {
				if(v.validate(feed.toXmlString())) {
					fs.insertFeed(feed);
				} else {
					return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
				}
			} catch (ParserConfigurationException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			fs.insertFeed(feed);
			Item it = null;
			for(Item i : feed.getItem()) {
				it = i;
			}
			return new ResponseEntity<String>("<item>" + "Last insert"
					+ "<guid>" + it.getGuid() + "</guid>" + "</item>", HttpStatus.CREATED);
		}
	}
}
