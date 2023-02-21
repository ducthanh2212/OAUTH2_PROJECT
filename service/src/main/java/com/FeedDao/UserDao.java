package com.FeedDao;

import org.bson.Document;

public interface UserDao {
	public Document getUser(String userName);
}
