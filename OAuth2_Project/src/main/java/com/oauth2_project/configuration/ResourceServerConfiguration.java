package com.oauth2_project.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource-server-rest-api";
    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
    private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
    private static final String SECURED_PATTERN = "/secured/**";
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
    	.antMatchers(HttpMethod.GET, "/rest/**").hasAnyAuthority("ADMIN","USER")
    	.antMatchers(HttpMethod.POST, "/rest/**").hasAuthority("ADMIN")
    	.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
  
    }
}

