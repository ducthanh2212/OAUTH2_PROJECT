package com.oauth2_project.configuration;

import java.util.Collection;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import com.oauth2_project.repository.UserDao;

@Component
public class CustomTokenStore implements TokenStore {
	

	private final MongoTemplate mongoTemplate;

	private final String collectionName;
	
	private final String key;

	public CustomTokenStore() {
		this.mongoTemplate = null;
		this.collectionName = "";
		this.key = "";
	}

	public CustomTokenStore(MongoTemplate mongoTemplate, String collectionName, String key) {
		this.mongoTemplate = mongoTemplate;
		this.collectionName = collectionName;
		this.key = key;
	}
	
	private MongoCollection<Document> getColl(){
		return this.mongoTemplate.getCollection(collectionName);
	}

	private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

	@Override
	public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OAuth2Authentication readAuthentication(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
		Document docToken = new Document();
		Map<String, Object> details = (Map<String, Object>) authentication.getUserAuthentication().getDetails();
		docToken.put("user", details.get("username"));
		docToken.put("token", token.getValue());
		docToken.put("refreshtoken", token.getRefreshToken().getValue());
		docToken.put("expired", token.getExpiration());
		docToken.put("key", key);
		try {
			Document update = new Document("$set", docToken);
			Document filter = new Document("user", docToken.get("user"));
			getColl().updateOne(filter, update, new UpdateOptions().upsert(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAccessToken(OAuth2AccessToken token) {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
		// TODO Auto-generated method stub

	}

	@Override
	public OAuth2RefreshToken readRefreshToken(String tokenValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeRefreshToken(OAuth2RefreshToken token) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
		// TODO Auto-generated method stub

	}

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
