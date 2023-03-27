package com.oauth2_project.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

//@Component
public class CustomTokenResponseFilter  extends OncePerRequestFilter{
	@Autowired
    private TokenStore tokenStore;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

    @Autowired
    private ObjectMapper objectMapper;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 if ("/oauth/token".equals(request.getServletPath()) && "POST".equals(request.getMethod())) {
	            try {
	                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("someone", "123"));
	                OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
	                OAuth2AccessToken accessToken = tokenStore.getAccessToken(oAuth2Authentication);
	                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

	                Map<String, Object> tokenMap = new HashMap<>();
	                tokenMap.put("access_token", accessToken.getValue());
	                tokenMap.put("token_type", accessToken.getTokenType());
	                tokenMap.put("refresh_token", accessToken.getRefreshToken().getValue());
	                tokenMap.put("expires_in", accessToken.getExpiresIn());
	                UserDetails userDetails = (UserDetails) oAuth2Authentication.getUserAuthentication().getPrincipal();
	                tokenMap.put("username", userDetails.getUsername());

	                objectMapper.writeValue(response.getWriter(), tokenMap);
	            } catch (Exception e) {
	                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                Map<String, String> errorMap = new HashMap<>();
	                errorMap.put("error", "invalid_request");
	                errorMap.put("error_description", "Invalid client credentials");
	                objectMapper.writeValue(response.getWriter(), errorMap);
	            }
	        } else {
	            filterChain.doFilter(request, response);
	        }
	}

}
