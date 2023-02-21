package com.oauth2_project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.oauth2_project.service.UserDetailsServiceImpl;


@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	/**
	 * Các giá trị có thể được truyền vào phương thức sessionCreationPolicy bao gồm:

SessionCreationPolicy.STATELESS: Chế độ phi trạng thái (stateless), ứng dụng không sử dụng phiên hoặc trạng thái của người dùng để lưu trữ thông tin liên quan đến phiên hoặc trạng thái của người dùng.
SessionCreationPolicy.ALWAYS: Luôn luôn tạo phiên cho người dùng, bất kể có yêu cầu tạo phiên từ phía người dùng hay không.
SessionCreationPolicy.IF_REQUIRED: Chỉ tạo phiên khi cần thiết, ví dụ khi người dùng yêu cầu tạo phiên bằng cách đăng nhập hoặc thực hiện một hành động đặc biệt nào đó trong ứng dụng.
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 	
		.and().authorizeRequests().antMatchers("/oauth/token")
		.permitAll().anyRequest().authenticated()
		.and()
		.authorizeRequests();
	}

	
	/**
	 * hàm này dùng để so sánh password mà user gửi xuống provider.setPasswordEncoder( bCryptPasswordEncoder() )
	 * sau đó so sánh với tài khoản được lưu trong db qua provider.setUserDetailsService(userDetailsService) hợp lệ thì được xác thực
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder( bCryptPasswordEncoder() );
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	
	/**
	 * 
	 * mã hóa password
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	
}