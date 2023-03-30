package com.oauth2_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oauth2_project.configuration.CustomAuthorizationServerTokenServices;
//@RestController
public class oauth {
	@Autowired
	private TokenStore tokenStore;

	@Autowired
	@Qualifier("customAuthorizationServerTokenServices")
	private AuthorizationServerTokenServices authorizationServerTokenServices;
	
	@RequestMapping(value = "/oauth/token", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getAccessToken(@RequestBody OAuth2Authentication authentication) {
	    // Tạo token
//	    OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(tokenRequest);
System.err.println(authentication);
//	    // Lưu token vào MongoDB
//	    OAuth2Authentication authentication = authorizationServerTokenServices.loadAuthentication(tokenRequest.getAuthorizationRequest().getClientId(), token.getValue());
//	    tokenStore.storeAccessToken(token, authentication);

	    // Trả về token
//	    return new ResponseEntity<>(token, HttpStatus.OK);
return null;
	}
}
