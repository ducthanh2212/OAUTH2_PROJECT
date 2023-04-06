package com.oauth2_project.repository;

import org.bson.Document;
import org.bson.types.ObjectId;
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
	
	public Document getUser(String idUser) {
		Document filter = new Document ("username", idUser);
		return this.getCollection().find(filter).first();
	}
	
	public void save (Document token) {
		try {
			
			this.getCollection().insertOne(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
