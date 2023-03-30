package com.oauth2_project.configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.oauth2_project.repository.UserDao;
//@Component
public class CustomAuthorizationServerTokenServices implements AuthorizationServerTokenServices {

	private final TokenStore tokenStore;
	@Autowired
	private UserDao userDao;

	public CustomAuthorizationServerTokenServices(TokenStore tokenStore) {
		this.tokenStore = tokenStore;
	}

	@Override
	public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
		// Tạo access token và refresh token
		OAuth2AccessToken accessToken = new DefaultOAuth2AccessToken(UUID.randomUUID().toString());
		OAuth2RefreshToken refreshToken = new DefaultOAuth2RefreshToken(UUID.randomUUID().toString());

		// Lưu token vào token store
		tokenStore.storeAccessToken(accessToken, authentication);
		tokenStore.storeRefreshToken(refreshToken, authentication);

		// Đặt hạn sử dụng của token
		int accessTokenValiditySeconds = 60 * 60 * 24; // 1 ngày
		int refreshTokenValiditySeconds = 60 * 60 * 24 * 30; // 30 ngày
		((DefaultOAuth2AccessToken) accessToken)
				.setExpiration(new Date(System.currentTimeMillis() + accessTokenValiditySeconds * 1000L));

		((DefaultOAuth2AccessToken) refreshToken)
				.setExpiration(new Date(System.currentTimeMillis() + refreshTokenValiditySeconds * 1000L));

		// Đặt thông tin về scope, client id, v.v.
		((DefaultOAuth2AccessToken) accessToken).setScope(authentication.getOAuth2Request().getScope());
		Document user = userDao.getUser(authentication.getName());
		Map<String, Object> additionalInfo = new HashMap<>();
		additionalInfo.put("user_name", user.get("username"));
		additionalInfo.put("full_name", user.get("role"));
		((DefaultOAuth2AccessToken) accessToken)
				.setAdditionalInformation(additionalInfo);
		((DefaultOAuth2AccessToken) accessToken).setTokenType("Bearer");

		// Trả về access token và refresh token
		DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(accessToken);
		token.setRefreshToken(refreshToken);
		return token;
	}

	@Override
	public OAuth2AccessToken refreshAccessToken(String refreshToken, TokenRequest tokenRequest)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		// TODO Auto-generated method stub
		return null;
	}

}
