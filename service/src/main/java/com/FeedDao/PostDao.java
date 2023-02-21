package com.FeedDao;

import java.util.List;
import java.util.Map;

import org.bson.Document;


public interface PostDao {
	public List<Document> getLstPost();
	
	public String savePost(Map<String,Object> post);
}
