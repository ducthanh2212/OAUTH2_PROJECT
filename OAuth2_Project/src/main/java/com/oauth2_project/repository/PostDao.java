//package com.oauth2_project.repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.bson.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Repository;
//import org.springframework.util.ObjectUtils;
//
//import com.mongodb.client.MongoCollection;
//@Repository
//public class PostDao {
//	@Autowired
//	private MongoTemplate mongoTemplate;
//	
//	private MongoCollection<Document> getCollection(){
//		return mongoTemplate.getCollection("post");
//	}
//	
//	public List<Document> getLstPost(){
//		return this.getCollection().find().into(new ArrayList<>());
//	}
//	
//	public String savePost(Map<String,Object> post) {
//		Document docPost = new Document(post);
//		try {
//			String idPost = this.getCollection().insertOne(docPost).getInsertedId().asObjectId().getValue().toHexString();
//			if(!ObjectUtils.isEmpty(idPost)) {
//				return idPost;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//}
