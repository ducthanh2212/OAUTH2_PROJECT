package com.oauth2_project.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.oauth2_project.repository.UserDao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDao userDao;
	

	@Override
	public UserDetails loadUserByUsername(String idUser) throws UsernameNotFoundException {
		Document user = userDao.getUser(idUser);
		 List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
			GrantedAuthority authority = new SimpleGrantedAuthority(user.getString("role"));
			grantList.add(authority);
	        if(!ObjectUtils.isEmpty(user)) {
	        	UserDetails userdetial = new User(user.get("username").toString(), user.getString("password"), grantList);
	        	return userdetial;
	        }
	        throw new UsernameNotFoundException("LOI");
	}
}
