package com.example.scanner;

import java.util.List;
import java.util.Scanner;

import com.example.model.BankAccount;
import com.example.model.User;

public class EmployeeScreen extends CustomerScreen implements Screen{
//	ValidationScreen vs = new ValidationScreen();
	CustomerScreen cs = new CustomerScreen();	
	
	public EmployeeScreen() {		
		
	}

	@Override
	public void render(Scanner scan) {		
		
	}
	
	public void employeeOperation(Scanner scan) {		
		System.out.println("\nPlease choose an option:");
		System.out.println("************************");
		boolean valid = true;
		int optInt = -1;
		while(true) {
			System.out.println("'1' To Deposit\n'2' To Withdraw\n'3' To Transfer\n'4' To Open a Bank Account"
					+ "\n'5' To View Users\n'6' To View Bank Accounts\n'7' To Update Account Status"
					+ "\\n'8' To View Your Bank Accounts\n'0' to Exit.");
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
			deposit();
			employeeOperation(scan);
			break;
		case 2:			
			withdraw();
			employeeOperation(scan);
			break;
		case 3:			
			transfer();
			employeeOperation(scan);
			break;
		case 4:
			openAccount();
			employeeOperation(scan);
			break;
		case 5:
			viewUsers();
			employeeOperation(scan);
			break;
		case 6:
			viewBankAccounts();
			employeeOperation(scan);
			break;
		case 7:
			viewBankAccounts();
			updateAccountStatus();
			employeeOperation(scan);
			break;
		default:
			employeeOperation(scan);
			break;	
		}		
	}	
	
	public void viewUsers() {
		List<User> users = us.getAllUsers();
		int countAccount = 1;
		System.out.println(String.format("\t\t"+"%-20s%-20s%-20s%-20s", "First Name", "Last Name" , "User Name" , "Role"));
		System.out.println(String.format("\t\t"+"%-20s%-20s%-20s%-20s", "**********", "*********" , "**********" , "****"));
		for(User user : users) {
			System.out.println("User : "+countAccount++ +"\t"+user);
		}
	}
	
	public void viewBankAccounts() {
		List<BankAccount> accounts = bas.getAllBankAccounts();
		int countAccount = 1;
		System.out.println(String.format("\t\t"+"%-20s%-20s%-20s%-20s%-20s%-20s", "Account ID", "Balance" , "Status" , "Type" , "Joint" , "Created Date"));
		System.out.println(String.format("\t\t"+"%-20s%-20s%-20s%-20s%-20s%-20s", "----------", "-------" , "------" , "----" , "-----" , "------------"));
		for(BankAccount ba: accounts) {
			System.out.println("Account: "+countAccount++ +"\t"+ ba);
	}
}
	public void updateAccountStatus() {
		viewBankAccounts();
		System.out.println("Please enter an bank account id from the list above:");
		String accountId = scan.next();
		boolean validId = vs.validNumber(accountId);
		
		System.out.println("Please enter 1 to approve or 0 to deny or anything not to change the status."
				+ "\n'1' To Approve\n'0' To Deny\n Press anyting not to change the status.");
		String status = scan.next();
		boolean validStatus = vs.validNumber(accountId);		
		if(validId == true && validStatus == true) {
			int accId = Integer.parseInt(accountId);
			int intStatus = Integer.parseInt(status);			
					if(intStatus == 1) {
						if(bas.changeBankStatusAccount(accId, "approved")) {
							System.out.println("The status of the bank account "+ accId  +" has been changed to 'approved'.");														
						}						
					}
					else if(intStatus == 0){
						if(bas.changeBankStatusAccount(accId, "denied")){
							System.out.println("The status of the bank account "+ accId  +" has been changed to 'denied'.");							
						}
						
					} else {
						System.out.println("The status of the bank acoount "+ accId + " was not changed.");
			}
			
		}
		
	}
}
