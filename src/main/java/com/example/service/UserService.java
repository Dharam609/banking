package com.example.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.example.dao.UserDAOImpl;
import com.example.model.User;


public class UserService {
	
	UserDAOImpl udi = new UserDAOImpl();
	public static final Logger log2 = Logger.getLogger(UserService.class); 
	
	public UserService() {
		
	}
	
	public int createUser(String username, String password, String fname, String lname, String role) {
		return udi.insertUser(username, password, fname, lname, role);
	}
	
	public int callableCreateUser(String username, String password, String fname, String lname, String role) {
		return udi.callableInsertUser(username, password, fname, lname, role);
	}
	
	public User getUserByUsername(String username) {
		return udi.getUserByUsername(username);
	}
	
	public boolean login(String username, String password) {
		if(udi.login(username, password)){
			log2.debug("Login was successfull!");
			return true;
		}
		else {
			log2.debug("Login was not successfull with the username "+ username + " & " + password);
			return false;
		}
	}
	
	public void deleteUser(String username) {
		udi.deleteUser(username);
	}
	
	public List<User> getAllUsers(){
		return udi.getAllUsers();
	}

}
