package com.example.scanner;

import java.util.Scanner;

import com.example.model.User;
import com.example.service.BankAccountService;
import com.example.service.UserService;

public class RegistrationScreen implements Screen{	
	UserService us = new UserService();
	BankAccountService bas = new BankAccountService();
	ValidationScreen vs = new ValidationScreen();
	HomeScreen hs = new HomeScreen();
	
	public RegistrationScreen() {
		
	}
	
	@Override
	public void render(Scanner scan) {		
		String f_name = fname(scan);		
		String l_name = lname(scan);		
		String user_name = userName(scan);
		String pass_word = password(scan);
		String my_role = role(scan);
		
		
		int user_id = us.createUser(user_name,  pass_word, f_name, l_name, my_role);
		if(user_id > 0) {
			System.out.println("Congratulations! You are successfully registered in our bank.\n"
					+ "Now you can log in using your username and password to open a bank account.");
			System.out.println("====================================================================");
			System.out.println("Log In Processs....");
			System.out.println("===================");			
			fUserLogin(scan);
		}else {
			System.out.println("Something went wrong. Try again!");			
			scan.close();
		}
	}	
	
	public String fname(Scanner scan) {
		boolean valid = false;
		String fname ="";
		do {
			System.out.println("Please enter the first name.");
			System.out.println("============================");
			fname =scan.next().toLowerCase();			
			valid = vs.validString(fname);
		}while(!valid);
		
		return fname;
	}
	
	public String lname(Scanner scan) {
		boolean valid = false;
		String lname ="";
		do {
			System.out.println("Please enter the last name.");
			System.out.println("===========================");
			lname =scan.next().toLowerCase();			
			valid = vs.validString(lname);
		}while(!valid);
		return lname;
	}
	
	public String role(Scanner scan) {
		boolean valid = false;
		String role ="";
		do {
			System.out.println("Please enter your role. \n'1' For Customer \n'2' For Employee and\n'3' For Admin.");
			System.out.println("==========================");
			role =scan.next();			
			valid = vs.validNumber(role);
			if(valid == true) {
				int option = Integer.parseInt(role);				
				switch(option) {
				case 1:
					role = "Customer";
					break;
				case 2:
					role = "Employee";
					break;
				case 3:
					role = "Admin";
					break;
				default:
					System.out.println("Invalid Input!");
					valid=false;
					break;
				}
			}
			
		}while(!valid);
		
		return role;
	}
	public String accountType(Scanner scan) {
		boolean valid = false;
		String role ="";
		do {
			System.out.println("Please enter an account type. \n'1' For Checking \n'2' For Saving and \n'3' For Business.");
			System.out.println("============================");
			role =scan.next();			
			valid = vs.validNumber(role);
			if(valid == true) {
				int option = Integer.parseInt(role);				
				switch(option) {
				case 1:
					role = "Checking";
					break;
				case 2:
					role = "Saving";
					break;
				case 3:
					role = "Business";
					break;
				default:
					System.out.println("Invalid Input!");
					valid=false;
					break;
				}
			}
			
		}while(!valid);
		
		return role;
	}
	
	
	public String userName(Scanner scan) {
		    String username ="";	
			boolean valid = false;			
			do {
				System.out.println("Please enter a username. Alphabets and numbers only. Both required.");
				System.out.println("====================================================---------------");
				username =scan.next().toLowerCase();			
				valid = vs.validUnamePass(username);
				if(valid) {
					User user = us.getUserByUsername(username);
					if(user !=null) {
						System.out.println("The username "+ username +" already exists. Please try a different one.");
						System.out.println("=====================================================================");
						valid = false;
					}
				}
			}while(!valid);
			
		return username;
	}
	
	public String password(Scanner scan) {
		boolean valid = false;
		String pass1 ="";
		String pass2 ="";
		do {
			System.out.println("Please enter a new password. Alphabets and numbers only. Both required.");	
			System.out.println("=======================================================================");
			pass1 =scan.next().toLowerCase();
			valid = vs.validUnamePass(pass1);
			
			if(valid == true) {
				System.out.println("Please confirm the password. Alphabets and numbers only. Both required.");
				System.out.println("=======================================================================");
				pass2 =scan.next().toLowerCase();
				valid = vs.validUnamePass(pass2);
				if(!pass1.equals(pass2)) {
					System.out.println("The passwords did not match. Try again.");
					System.out.println("=======================================");
					valid = false;
				}
			}
			
		}while(!valid);
		
		return pass1;
	}
	
	
	public void fUserLogin(Scanner scan) {
		System.out.println("Press 'y' to Log In OR any letter  to exit: ");			
		String userLogin = scan.next().toLowerCase();
		boolean valid = vs.validString(userLogin);
		if(valid == true) {
			if(userLogin.equals("y")) {
				hs.loginUtility(scan);
			}
			else {
				System.out.println("========================================");
				System.out.println("Thank you for registering! See you soon!");
				scan.close();
			}
			
		}
	}
}
