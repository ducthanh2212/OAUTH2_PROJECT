package com.FeedService;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.Constant.BeanServiceConstant;
import com.Constant.ConfigurableBeanFactoryConstant;
import com.FeedDao.UserDao;

@Service(BeanServiceConstant.USER_MONGO_SERVICE_NAME)
@Scope(ConfigurableBeanFactoryConstant.SINGLETON)
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public Document getUser(String userName) {
		return userDao.getUser(userName);
	}
}
