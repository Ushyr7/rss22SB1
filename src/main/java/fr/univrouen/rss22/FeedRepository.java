package fr.univrouen.rss22;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.Feed;

public interface FeedRepository extends MongoRepository<Feed, String>{
	
	Optional<Feed> findFeedByTitleAndPubDate(String Title, String pubDate);
	
}
