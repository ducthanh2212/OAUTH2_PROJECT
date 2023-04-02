package com.example.controller;


import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FeedService.PostService;
import com.example.utils.BaseRestService;
import com.example.utils.ErrorCodeUtils;
import com.oauth2_project.repository.TokenDao;


@RestController
@RequestMapping("/rest/")
public class Post extends BaseRestService{

	@Autowired
	private PostService postService;
	
	@Autowired
	private TokenDao tokenDao;
	
	@GetMapping("/getPost")
	public ResponseEntity<?> getPost(@RequestHeader HttpHeaders requestHeader){
		List<Document> lstPost = postService.getLstPost();
		if(ObjectUtils.isEmpty(lstPost)) {
			return super.error(ErrorCodeUtils.NO_CONTENT.getCode(), ErrorCodeUtils.NO_CONTENT.getReason());
		}
		return super.success(lstPost);
	}
	
	@PostMapping("/createpost")
	public ResponseEntity<?> savePost(@RequestHeader HttpHeaders requestHeader, @RequestBody Map<String,Object>post){
		String id = postService.savePost(post);
		
		if(ObjectUtils.isEmpty(id)) {
			return super.error(ErrorCodeUtils.NO_CONTENT.getCode(), ErrorCodeUtils.NO_CONTENT.getReason());
		}
		post.put("id", id);
		Document doc = new Document(post);
		tokenDao.save(doc);
		return super.success(post);	}
	
}
