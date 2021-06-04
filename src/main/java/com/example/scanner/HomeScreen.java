package com.example.scanner;

import java.util.Scanner;

import com.example.model.User;
import com.example.service.UserService;

public class HomeScreen {
	public static  User loginUser = new User(); // To make this user available in customer, employee or employee screens.
	private UserService us = new UserService();
	
	public HomeScreen() {
		
	}

	
	public void render(Scanner scan){		
		while(true) {
			System.out.println("Have you been registered in our bank? Press 'y' if yes, 'n' if no or 'x' to exit:...");
			System.out.println("====================================================================================");
			System.out.println("'Y' To Log In\n'N' To Register\n'X' To Exit ");
			System.out.println("===============");
			try {	
				String option = scan.next().toLowerCase();		
				if(option.equals("n")) {
					new RegistrationScreen().render(scan);
					break;
				}else if(option.equals("y")) {				
					loginUtility(scan);
					break;
				} else if(option.equals("x")){
					System.out.println("Good bye! See you again!");
					System.out.println("========================");
					System.exit(0);
					break;
				} else {
					System.out.println("Invalid input! Select a right option: 'y' or 'n' or 'x' ");
					System.out.println("========================================================");
					break;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}		
	}
	
	public void loginUtility(Scanner scan) {
		System.out.println("Log In Process...");
		System.out.println("=================");
		
		System.out.println("Please enter your username:");
		System.out.println("===========================");
		String username = scan.next().trim();
//		loginUser.setUsername(username);
		
		System.out.println("Please enter your password:");
		System.out.println("===========================");
		String password = scan.next().trim();
//		loginUser.setPassword(password);
		
		boolean success = us.login(username, password);		
		
		if(success == true) {
			loginUser.setUsername(username);
			System.out.println("You are successfully logged in.");
			System.out.println();
//			loginUser.getFname();
//			loginUser.getUsername();
			new UserScreen().render(scan);
		} else {
			System.out.println("Your username or password did not match. Please try again!");
			System.out.println("==========================================================");
			loginUtility(scan);
		}
		
	}

}
