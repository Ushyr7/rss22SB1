package fr.univrouen.rss22;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import model.Feed;
import model.Item;

@Service
public interface FeedService {
	
	List<Feed> findAll();
	
	Optional<Feed> findByTitleAndPubDate(String Title, String pubDate);
		
	void insertFeed(Feed feed);
	
	Feed findFeedContainingItemWithGuid(String guid);
	
	Item findItemWithGuid(String guid);
	
	void updateFeed(Feed feed);
	
	void deleteFeedById(String id);
	
}
