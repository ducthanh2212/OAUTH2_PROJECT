package com.oauth2_project.configuration;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.bson.Document;
import org.bson.types.Binary;
import org.redisson.codec.SerializationCodec;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;

@Component
public class CustomTokenStore implements TokenStore {

	private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

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

	private MongoCollection<Document> getColl() {
		return this.mongoTemplate.getCollection(collectionName);
	}

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
		ObjectMapper mapper = new ObjectMapper();
		try {
			byte[] authenticationBytes = mapper.writeValueAsBytes(authentication);
			docToken.put("authentication", authenticationBytes);
			Document update = new Document("$set", docToken);
			Document filter = new Document("user", docToken.get("user"));
			getColl().updateOne(filter, update, new UpdateOptions().upsert(true));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		Document filter = new Document("refreshtoken", tokenValue);
		Document token = this.getColl().find(filter).first();
		if (!ObjectUtils.isEmpty(token)) {
			OAuth2RefreshToken refreshToken = new DefaultOAuth2RefreshToken(token.get("refreshtoken").toString());
			return refreshToken;
		}
		return null;
	}

	@Override
	public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken refreshtoken) {
		// TODO Auto-generated method stub
		Document filter = new Document("refreshtoken", refreshtoken.getValue());
		Document token = this.getColl().find(filter).first();
		if (token != null) {
		ObjectMapper mapper = new ObjectMapper();
            try {
            	byte[] authenticationBytes = ((Binary) token.get("authentication")).getData();
				return mapper.readValue(authenticationBytes, OAuth2Authentication.class);
			} catch (StreamReadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatabindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
            
		}
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
