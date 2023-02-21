package com.FeedDaoImpl;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.Constant.BeanServiceConstant;
import com.Constant.CollectionConstant;
import com.Constant.ConfigurableBeanFactoryConstant;
import com.FeedDao.PostDao;
import com.FeedDao.UserDao;
import com.mongodb.client.MongoCollection;
@Repository(BeanServiceConstant.USER_MONGO_DAO_NAME)
@Scope(ConfigurableBeanFactoryConstant.SINGLETON)
public class UserDaoImpl implements UserDao{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	private MongoCollection<Document> getCollection(){
		return mongoTemplate.getCollection(CollectionConstant.USER);
	}
	
	@Override
	public Document getUser(String userName) {
		Document filter = new Document ("user", userName);
		return this.getCollection().find(filter).first();
	}

}
