package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("serial")
public class User implements Serializable{
	private int user_id;
	private String fname;
	private String lname;	
	private String username;
	private String password;
	private String role;
	
	public User() {
		
	}
	
	public User(int user_id, String username, String password, String fname, String lname, String role){
		this.setUser_id(user_id);
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.lname = lname;				
		this.role = role;
		}
	
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {		
		this.fname = fname;		
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}	

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	@Override
	public String toString() {
//		return "First Name: " + fname + "\nLast Name: " + lname + "\nUsername: " + username ;
		return  String.format("%-20s%-20s%-20s%-20s", fname, lname , username , role);
	}
	
}

