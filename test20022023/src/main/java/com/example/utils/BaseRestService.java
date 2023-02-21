package com.example.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public abstract class BaseRestService {

	public BaseRestService() {

	}

	

	protected ResponseEntity<?> error(Integer code, Object message) {
		Map<String, Object> mapMessage = new LinkedHashMap<>();
		mapMessage.put("code", code);
		mapMessage.put("message", message);
		mapMessage.put("data", null);
		return ResponseEntity.ok().body(mapMessage);
	}

	protected ResponseEntity<?> success(Object obj) {
		Map<String, Object> mapMessage = new LinkedHashMap<>();
		mapMessage.put("code", 200);
		mapMessage.put("message", "success");
		mapMessage.put("data", obj);
		return ResponseEntity.ok().body(mapMessage);
	}

}
