package com.FeedDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import com.Constant.BeanServiceConstant;
import com.Constant.CollectionConstant;
import com.Constant.ConfigurableBeanFactoryConstant;
import com.FeedDao.PostDao;
import com.mongodb.client.MongoCollection;
@Repository(BeanServiceConstant.POST_MONGO_DAO_NAME)
@Scope(ConfigurableBeanFactoryConstant.SINGLETON)
public class PostImpl implements PostDao{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	private MongoCollection<Document> getCollection(){
		return this.mongoTemplate.getCollection(CollectionConstant.POST);
	}
	
	@Override
	public List<Document> getLstPost() {
		return this.getCollection().find().into(new ArrayList<>());
	}

	@Override
	public String savePost(Map<String, Object> post) {
		Document docPost = new Document(post);
		try {
			String idPost = this.getCollection().insertOne(docPost).getInsertedId().asObjectId().getValue().toHexString();
			if(!ObjectUtils.isEmpty(idPost)) {
				return idPost;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
