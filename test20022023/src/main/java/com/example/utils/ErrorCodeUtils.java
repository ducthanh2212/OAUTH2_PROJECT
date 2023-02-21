package com.example.utils;

public enum ErrorCodeUtils {
	NO_CONTENT(2004, "NO_CONTENT");


	private final int code;
	
	private final String reason;
	
	ErrorCodeUtils(int code, String reason) {
		this.code = code;
		this.reason = reason;
	}

	public int getCode() {
		return code;
	}

	public String getReason() {
		return reason;
	}
	
	
}
