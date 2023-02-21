package com.example.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/")
public class test {
	@GetMapping("/get")
	public ResponseEntity<?> getPost(@RequestHeader HttpHeaders requestHeader){
		return ResponseEntity.ok("hello");
	}
}
