package com.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.dao.UserDao;
import com.exam.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	//spring security will use this class to load by username if data is already present there 
	//we just need to pass the object of this class
	@Autowired
	private UserDao u_dao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user=this.u_dao.findByUsername(username);
	if(user==null) {
		System.out.println("User Not Found");
		throw new UsernameNotFoundException("No user found with this credential");
	}
		return user;
	}
	

}
