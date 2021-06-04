package com.example.scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.model.BankAccount;
import com.example.model.User;
import com.example.service.BankAccountService;
import com.example.service.UserService;

public class UserScreen implements Screen{
	
	Scanner scan = new Scanner(System.in);
	
	ValidationScreen vs =  new ValidationScreen();
	UserService us = new UserService();
	BankAccountService bas = new BankAccountService();
	RegistrationScreen rs = new RegistrationScreen();
	CustomerScreen cs = new CustomerScreen();
	AdminScreen as = new AdminScreen();
	EmployeeScreen es = new EmployeeScreen();
	
	private String username = HomeScreen.loginUser.getUsername();
	private User user = us.getUserByUsername(username);
	private List<BankAccount> bankAccounts = bas.getBankAccountsByUsername(username);
	private int size = bankAccounts.size();
	String fname = user.getFname().toUpperCase();
	
	public UserScreen() {
		
	}

	@Override
	public void render(Scanner scan) {
		System.out.println("Hi "+ fname +", Welcome to Revature Bank! You are in "+ user.getRole().toUpperCase()+ " page.");
		System.out.println("==============================================================");
		System.out.println("Your profile:");
		System.out.println("=============");
		System.out.println(String.format("%-20s%-20s%-20s%-20s", "First Name", "Last Name" , "User Name" , "Role"));
		System.out.println(String.format("%-20s%-20s%-20s%-20s", "----------", "---------" , "---------" , "----"));
		System.out.println(user);
		
		System.out.println("Your Bank Account Detail:");
		System.out.println("=========================");
		
		userBankAccountInfo();
		String role = user.getRole().trim().toLowerCase();
		if(role.equals("customer")) {
			cs.customerOperation(scan);
		} else if(role.equals("employee")) {
			es.employeeOperation(scan);
		}else if(role.equals("admin")) {
			as.adminOperation(scan);
		}
		
	}
	public void userBankAccountInfo() {
		if(size > 0) {
			System.out.println("List of Your Bank Accounts");
			System.out.println("==========================");
			int countAccount = 1;
			System.out.println(String.format("\t\t"+"%-20s%-20s%-20s%-20s%-20s%-20s", "Account ID", "Balance" , "Status" , "Type" , "Joint" , "Created Date"));
			System.out.println(String.format("\t\t"+"%-20s%-20s%-20s%-20s%-20s%-20s", "----------", "-------" , "------" , "----" , "-----" , "------------"));
			for(BankAccount ba: bankAccounts) {
				System.out.println("Account: "+countAccount++ +"\t"+ ba);
			}
		} else {
			System.out.println("There is not your bank account in our bank.");
		}
	}

}
