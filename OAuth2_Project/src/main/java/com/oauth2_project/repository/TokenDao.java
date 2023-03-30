package com.oauth2_project.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;

@Repository
public class TokenDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	private MongoCollection<Document> getCollection() {
		return mongoTemplate.getCollection("token");
	}


	public void save(Document docToken) {
		try {
			this.getCollection().insertOne(docToken);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
