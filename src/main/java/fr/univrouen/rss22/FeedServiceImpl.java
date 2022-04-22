package fr.univrouen.rss22;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Feed;
import model.Item;

@Service("FeedService")
public class FeedServiceImpl implements FeedService {

	@Autowired
	private FeedRepository repository;
	
	@Override
	public List<Feed> findAll() {
		return repository.findAll();
	}

	@Override
	public void insertFeed(Feed feed) {
		repository.insert(feed);
	}

	@Override
	public Optional<Feed> findByTitleAndPubDate(String title, String pubDate) {
		return repository.findFeedByTitleAndPubDate(title, pubDate);
	}

	@Override
	public Feed findFeedContainingItemWithGuid(String guid) {
		List<Feed> feed = repository.findAll();
		for (Feed f : feed) {
			for(Item it : f.getItem()) {
				if(it.getGuid().equals(guid)) {
					return f;
				}
			}
		}
		return null;	
	}
	
	public Item findItemWithGuid(String guid) {
		List<Feed> feed = repository.findAll();
		Item result = null;
		for (Feed f : feed) {
			for(Item it : f.getItem()) {
				if(it.getGuid().equals(guid)) {
					result = it;
				}
			}
		}
		return result;	
	}

	@Override
	public void updateFeed(Feed feed) {
		repository.save(feed);
	}

	@Override
	public void deleteFeedById(String id) {
		repository.deleteById(id);
	}
}
