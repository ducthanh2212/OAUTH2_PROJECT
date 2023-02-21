//package com.oauth2_project.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import org.bson.Document;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.oauth2_project.service.PostService;
//
//@RestController
//@RequestMapping("/rest/")
//public class PostController {
//
//	@Autowired
//	private PostService postService;
//	
//	@GetMapping("/getpost")
//	public ResponseEntity<?> getPost(@RequestHeader HttpHeaders requestHeader){
//		List<Document> lstPost = postService.getLstPost();
//		if(ObjectUtils.isEmpty(lstPost)) {
//			return ResponseEntity.noContent().build();	
//		}
//		lstPost.forEach(item -> {
//			ObjectId id = item.getObjectId("_id");
//			item.put("_id", id.toString());
//		});
//		return ResponseEntity.ok().body(lstPost);
//	}
//	
//	@PostMapping("/createpost")
//	public ResponseEntity<?> savePost(@RequestHeader HttpHeaders requestHeader, @RequestBody Map<String,Object>post){
//		String id = postService.savePost(post);
//		if(ObjectUtils.isEmpty(id)) {
//			return ResponseEntity.noContent().build();
//		}
//		post.put("id", id);
//		return ResponseEntity.ok().body(post);
//	}
//}
