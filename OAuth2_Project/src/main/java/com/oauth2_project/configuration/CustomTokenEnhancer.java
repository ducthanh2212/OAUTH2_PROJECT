package com.oauth2_project.configuration;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.oauth2_project.repository.UserDao;
@Component
public class CustomTokenEnhancer implements TokenEnhancer {
	
	@Autowired
	private UserDao userDao;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Document user = userDao.getUser(authentication.getName());
		Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("user_name", user.get("username"));
        additionalInfo.put("full_name", user.get("role"));
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
	}

}
