package com.oauth2_project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.oauth2_project.service.UserDetailsServiceImpl;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	
	@Value("${jwtsecretkey}")
	private String jwtsecretkey;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private CustomTokenEnhancer customTokenEnhancer;
	
	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("someone").secret(passwordEncoder.encode("123"))
		.authorizedGrantTypes("password", "authorization_code", "refresh_token").scopes("read","write")
		.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "USER","ADMIN")
		.autoApprove(true)
		.accessTokenValiditySeconds(20)
        .refreshTokenValiditySeconds(600);
	}

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager).accessTokenConverter(defaultAccessTokenConverter())
    	.userDetailsService(userDetailsService).tokenEnhancer(customTokenEnhancer);
//    	endpoints.tokenEnhancer(new CustomTokenEnhancer());
    	
    }

	@Bean
	public TokenStore tokenStore(){
		return new JwtTokenStore(defaultAccessTokenConverter());	
	}

	@Bean
	public JwtAccessTokenConverter defaultAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(jwtsecretkey);
		System.err.println();
		System.err.println("converter: "+ converter.getAccessTokenConverter());
		return converter;
	}
}
