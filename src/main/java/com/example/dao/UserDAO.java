package com.example.dao;

import java.util.List;

import com.example.model.User;

public interface UserDAO {	
	int insertUser(String username, String password, String fname, String lname, String role);
	int callableInsertUser(String username, String password, String fname, String lname, String role);
	void updateUser(String username);
	void deleteUser(String username);	
	List<User> getAllUsers();
	User getUserByUsername(String username);
	boolean login(String username, String password);
	

}
