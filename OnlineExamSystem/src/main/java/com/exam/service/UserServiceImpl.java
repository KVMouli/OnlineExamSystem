package com.exam.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dao.RoleDao;
import com.exam.dao.UserDao;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.exceptions.UserAlreadyPresent;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao u_dao;

	@Autowired
	private RoleDao r_dao;

	// User Creation
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws UserAlreadyPresent {

		User local = this.u_dao.findByUsername(user.getUsername());// findByusername is take care off by the dao layer
																	// it provides the implementation
		if (local != null) {
			System.out.println("User is already there!!");
			throw new UserAlreadyPresent("User is aready Present");
		} else {
			for (UserRole ur : userRoles) {// taking out role from user roles nd saving it
				r_dao.save(ur.getRole());// this is to save in RoleDao
			}//this for loop is for role table 
			user.getUserRoles().addAll(userRoles);// then adding all userroles
			local = this.u_dao.save(user);// then adding user
		}
		return local;

	}

	@Override
	public User getUser(String username) {
		User local = this.u_dao.findByUsername(username);
		return local;
	}

	@Override
	public void deleteUser(int userId) {
		this.u_dao.deleteById(userId);

	}

}
