package com.FeedService;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.Constant.BeanServiceConstant;
import com.Constant.ConfigurableBeanFactoryConstant;
import com.FeedDao.PostDao;



@Service(BeanServiceConstant.POST_MONGO_SERVICE_NAME)
@Scope(ConfigurableBeanFactoryConstant.SINGLETON)
public class PostService {

	@Autowired
	private PostDao postDao;
	
	public List<Document> getLstPost(){
		return postDao.getLstPost();
	}
	
	public String savePost(Map<String,Object>post) {
		return postDao.savePost(post);
	}
}
