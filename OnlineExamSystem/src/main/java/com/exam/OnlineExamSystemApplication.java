package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.service.UserService;

@SpringBootApplication
public class OnlineExamSystemApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	public static void main(String[] args)  {
		SpringApplication.run(OnlineExamSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Start to code");
		//Demo code to see the working without controller when there was no controller class
//		User user =new User();
//		
//		user.setFirstName("Suraj");
//		user.setLastName("Soni");
//		user.setUsername("surajsoni357");
//		user.setPassword(this.passwordEncoder.encode("2323"));
//		user.setEmail("suraj@soni");
//		user.setProfile("default.png");
//		
//		Role role1=new Role();
//		role1.setRoleId(1);
//		role1.setRoleName("IT");
//		
//		UserRole userRoles =new UserRole();
//		userRoles.setRole(role1);
//		userRoles.setUser(user);
//		
//		Set<UserRole> userSet=new HashSet<UserRole>();
//		userSet.add(userRoles);
//		
//		User user1=this.userService.createUser(user, userSet);
//		System.out.println(user1.getUsername());
		
	}

}
