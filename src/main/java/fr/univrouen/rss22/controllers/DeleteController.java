package fr.univrouen.rss22.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univrouen.rss22.FeedService;
import model.Feed;
import model.Item;

@RestController
public class DeleteController {

	@Autowired
	private FeedService fs;
	
	@RequestMapping(value = "/rss22/delete/{guid}", method = RequestMethod.DELETE, produces = "application/xml")
	public ResponseEntity<String> deleteItem(@PathVariable(value = "guid") String guid) {
		Feed f = fs.findFeedContainingItemWithGuid(guid);
		if (f == null) {
			return new ResponseEntity<String>("<status>ERROR : " + guid + " not found" + "</status>", HttpStatus.BAD_REQUEST);
		}
		List<Item> it = f.getItem();
		Item itToDelete = null;
		for (Item i : it) {
			if (i.getGuid().equals(guid)) {
				itToDelete = i;
			}
		}
		it.remove(itToDelete);
		if (it.size() == 0) {
			fs.deleteFeedById(f.getId());
		} else {
			f.setItem(it);
			fs.updateFeed(f);
		}
			return new ResponseEntity<String>("<id>" + guid + "</id>", HttpStatus.OK);
	}
}

