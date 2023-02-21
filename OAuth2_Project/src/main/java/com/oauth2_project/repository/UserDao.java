package com.oauth2_project.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
@Repository
public class UserDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	private MongoCollection<Document> getCollection(){
		return mongoTemplate.getCollection("user");
	}
	
	public Document getUser(String userName) {
		Document filter = new Document ("username", userName);
		return this.getCollection().find(filter).first();
	}
}
