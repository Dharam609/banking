package com.example.scanner;

import java.util.Scanner;

import com.example.service.BankAccountService;

public class AdminScreen extends EmployeeScreen implements Screen {	
	
	 BankAccountService bas = new BankAccountService();
	 
	public AdminScreen() {
		
	}

	@Override
	public void render(Scanner scan) {
		System.out.println("I am in Admin page.");
		
	}
	
	public void adminOperation(Scanner scan) {		
		System.out.println("\nPlease choose an option:");
		System.out.println("************************");
		boolean valid = true;
		int optInt = -1;
		while(true) {
			System.out.println("'1' To Deposit\n'2' To Withdraw\n'3' To Transfer\n'4' To Open a Bank Account"
					+ "\n'5' To View Users\n'6' To View Bank Accounts\n'7' To Update Account Status"
					+ "\n'8' To Cancel Bank Account\n'9' To View Your Bank Accounts\n'0' to Exit.");
			System.out.println("*************");
			String option = scan.next();
			valid = validNumber(option);
			if(valid == true) {
				optInt = Integer.parseInt(option);
				if(optInt >= 0 && optInt <=9) {
					break;
				}
			}
			System.out.println("Please enter an integer between 0 and 4 inclusive .");
		}
		
		switch(optInt) {
		case 0:
			System.out.println("Thank you for using our app. See you again!");
			System.exit(0);
			break;
		case 1:
			viewBankAccounts();
			deposit();
			adminOperation(scan);
			break;
		case 2:
			viewBankAccounts();
			withdraw();
			adminOperation(scan);
			break;
		case 3:
			viewBankAccounts();
			transfer();
			adminOperation(scan);
			break;
		case 4:
			 openAccount();
			 adminOperation(scan);
			break;
		case 5:
			viewUsers();
			adminOperation(scan);
			break;
		case 6:
			viewBankAccounts();
			adminOperation(scan);
			break;
		case 7:
			viewBankAccounts();
			updateAccountStatus();
			adminOperation(scan);
			break;
		case 8:
			viewBankAccounts();
			cancelBankAccount();
			adminOperation(scan);
			break;
		case 9:
			userBankAccountInfo();
			adminOperation(scan);
			break;		
		default:
			break;	
		}		
	}
	
	public void cancelBankAccount() {
		System.out.println("\nPlease enter a bank account id to cancel.");
		System.out.println("*****************************************");
		String bankAccountId = scan.next();
		boolean valid = vs.validNumber(bankAccountId);
		if(valid == true) {
			int validBankAccountId = Integer.parseInt(bankAccountId);
			if(bas.deleteBankAccout(validBankAccountId)) {
				System.out.println("The bank accout wiht the account id "+ validBankAccountId + "has been cancelled.");
			} else {
				System.out.println("The bank account was not cancelled. Please try again!");
			}
			
		} else {
			System.out.println("The bank account was not cancelled. Please try again!");
		}
	}
}
