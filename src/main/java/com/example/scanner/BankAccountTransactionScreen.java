package com.example.scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.model.BankAccount;
import com.example.model.User;
import com.example.service.BankAccountService;
import com.example.service.UserService;


public class BankAccountTransactionScreen extends ValidationScreen {
	
Scanner scan = new Scanner(System.in);
	
	ValidationScreen vs =  new ValidationScreen();
	
	BankAccountService bas = new BankAccountService();
	RegistrationScreen rs = new RegistrationScreen();
	UserService us = new UserService();
	
	private String username = HomeScreen.loginUser.getUsername();
	private User user = us.getUserByUsername(username);
	private int user_id = user.getUser_id();
	private String role = user.getRole();
	private List<BankAccount> bankAccounts = bas.getBankAccountsByUsername(username);
	private int size = bankAccounts.size();
	private List<BankAccount> allBankAccounts = bas.getAllBankAccounts();
	String fname = user.getFname().toUpperCase();	
	
	public BankAccountTransactionScreen() {
		
	}
	public void deposit() {
		if(size > 0 || role.equals("Admin")) {
			boolean check = true;
			while(check) {
//				userBankAccountInfo();
				System.out.println("Please select a valid bank account Id from the list of bank accounts above.");
				System.out.println("==========================================================================.");
				String accountId = scan.next();
				boolean validNum = vs.validNumber(accountId);
				System.out.println("Please enater an amount you would like to deposit into the account.");
				System.out.println("===================================================================");
				String amount = scan.next();
				boolean validAmt = vs.validNumber(amount);
				if(validNum && validAmt) {
					double doubleAmount = Integer.parseInt(amount);
					int intAccountId = Integer.parseInt(accountId);
					if(doubleAmount > 0) {
						List<Integer> accountIds = new ArrayList<>();
						if(!role.equals("Admin")) {					
							accountIds = oneUserAccountIds();
							if(accountIds.size() > 0) {
								for(int id : accountIds) {
									if(intAccountId == id) {
										if(bas.deposit(id, doubleAmount)) {
											System.out.println("The amount "+ doubleAmount + " has successfully been deposited into the account # "+ id);
											System.out.println("==================================================================================");
											System.out.println("New Balance after the deposit: "+ bas.getBalance(id));
											check = false;
											break;
										} else {
			//								userBankAccountInfo();
											System.out.println("You many not have privileges to access to this account.");
											check = false;
											break;
										}
										
									}
									
								}
							} else {
								System.out.println("You many not have privileges to access to this account.");
								check = false;
								break;
							}
							
									
						
						} else {
							if(bas.deposit(intAccountId, doubleAmount)) {
								System.out.println("The amount "+ doubleAmount + " has successfully been deposited into the account # "+ intAccountId);
								System.out.println("==================================================================================");
								System.out.println("New Balance after the deposit: "+ bas.getBalance(intAccountId));
								check = false;
								break;
							} else {
//									userBankAccountInfo();
								System.out.println("The account might have pending or denied status.");
								check = false;
								break;
							}
						}
							
						
					}
						
				}
				System.out.println("Sorry, no deposit was done.Invalid account id or no previliges to access this account.");
				check = false;
			}
			
		} else {
			System.out.println("Sorry!You do not have any bank accounts in our bank.");
		}
		
		
	}
	
	public void withdraw() {
		if(size > 0 || role.equals("Admin")) {
			boolean check = true;		
			while(check) {
//				userBankAccountInfo();
				System.out.println("Please select a valid bank account Id from the list of bank accounts above.");
				System.out.println("==========================================================================.");
				String accountId = scan.next();
				boolean validNum = vs.validNumber(accountId);
				System.out.println("Please enter an amount you would like to withdraw from the account.");
				System.out.println("===================================================================");
				String amount = scan.next();
				boolean validAmt = vs.validNumber(amount);
				if(validNum && validAmt) {
					double doubleAmount = Integer.parseInt(amount);
					int intAccountId = Integer.parseInt(accountId);
					if(doubleAmount > 0) {
						List<Integer> accountIds = new ArrayList<>();
						if(!role.equals("Admin")) {					
							accountIds = oneUserAccountIds();
							if(accountIds.size() > 0) {
								for(int id : accountIds) {
									if(intAccountId == id)  {
									if(bas.withdraw(id, doubleAmount)) {
										System.out.println("The amount "+ doubleAmount + " has successfully been withdrawn from the account # "+ id);
										System.out.println("==================================================================================");
										System.out.println("New Balance after the withdrawal: "+ bas.getBalance(id));
										check = false;
										break;
									} else {
//										userBankAccountInfo();
										System.out.println("You many not have privileges to access to this account.");
										check = false;
										break;
									}
								}
									
							}
							} else {
								System.out.println("You many not have privileges to access to this account.");
								check = false;
							}
							
						} else {
							if(bas.withdraw(intAccountId, doubleAmount)) {
								System.out.println("The amount "+ doubleAmount + " has successfully been withdrawn from the account # "+ intAccountId);
								System.out.println("==================================================================================");
								System.out.println("New Balance after the withdrawal: "+ bas.getBalance(intAccountId));
								check = false;
								break;
							} else {
//								userBankAccountInfo();
								System.out.println("The account may have pending or denied status.");
								check = false;
								break;
							}
						}
						
					}
					
				}
				System.out.println("Sorry, no withdrawl was done.Invalid account id or no previliges to access this account.");
				check = false;
			}
		} else {
			System.out.println("Sorry!You do not have any bank accounts in our bank.");
		}
		
		
	}
	
	public void transfer() {
		if(size > 0 || role.equals("Admin")) {
			boolean check = true;
//			userBankAccountInfo();
			while(check) {				
				System.out.println("Please select a valid bank account Id to transfer from.");
				System.out.println("======================================================.");
				String accountId1 = scan.next();
				boolean validNum1 = vs.validNumber(accountId1);
				
				System.out.println("Please select a valid bank account Id to transfer to.");
				System.out.println("======================================================.");
				String accountId2 = scan.next();
				boolean validNum2 = vs.validNumber(accountId2);
				
				System.out.println("Please enter an amount you would like to transfer.");
				System.out.println("==================================================");
				String amount = scan.next();
				boolean validAmt = vs.validNumber(amount);
				
				if(validNum1 && validNum2 && validAmt) {
					double doubleAmount = Integer.parseInt(amount);
					int intAccountId1 = Integer.parseInt(accountId1);
					int intAccountId2 = Integer.parseInt(accountId2);
					int accId1 = -1;
					int accId2 = -1;
					if(doubleAmount > 0) {
						List<Integer> accountIds = new ArrayList<>();
						if(!role.equals("Admin")) {					
							accountIds = oneUserAccountIds();						
							if(accountIds.size() > 1) {
								for(int id : accountIds) {						
									if(intAccountId1 == id) {
										accId1 = id;
									} else if(intAccountId2 == id) {
										accId2 = id;
									}							
								}
								
								if(bas.transfer(accId1, accId2 , doubleAmount)) {
									System.out.println("The amount "+ doubleAmount + " has successfully been \nWithdrawn from "
											+ "the account # " + intAccountId1 + " and \nTransfered to the account # "+ intAccountId2);
									System.out.println("==============================================");
									System.out.println("The new balance of the account #  "+ intAccountId1+" : " + bas.getBalance(intAccountId1));
									System.out.println("The new balance of the account #  "+ intAccountId2+" : " + bas.getBalance(intAccountId2));
									System.out.println("==============================================");
									check = false;						
								} 
							} else {
								System.out.println("You many not have privileges to access to one or both accounts.");
								check = false;
							}
						
					}
						else {
							if(bas.transfer(intAccountId1, intAccountId2 , doubleAmount)) {
								System.out.println("The amount "+ doubleAmount + " has successfully been \nWithdrawn from "
										+ "the account # " + intAccountId1 + " and \nTransfered to the account # "+ intAccountId2);
								System.out.println("==============================================");
								System.out.println("The new balance of the account #  "+ intAccountId1+" : " + bas.getBalance(intAccountId1));
								System.out.println("The new balance of the account #  "+ intAccountId2+" : " + bas.getBalance(intAccountId2));
								System.out.println("==============================================");
								check = false;						
							} 
					
					} 
				}
			}
				System.out.println("Sorry, no transfer was done.Invalid account ids or no previliges to access one or both accounts.");
				check = false;
			}
		}else {
			System.out.println("Sorry!You do not have any bank accounts in our bank.");
		}
		
	}
	
	public void openAccount() {
		String accType = rs.accountType(scan);
		int bankAccountId = -1;
		int user_id = user.getUser_id();
		openJointAccount(accType, bankAccountId, user_id);		
	}
	
	public void openJointAccount(String accType, int bankAccountId, int user_id) {
		boolean check = true;
		while(check) {
			System.out.println("Is this a joint account? (y/n)?");
			System.out.println("===============================");
			
			String joint = scan.next().toLowerCase();
			if(joint.equals("y")) {				
				System.out.println("Please enter the details of the second account holder.");
				System.out.println("======================================================");
				String f_name2 = rs.fname(scan);
				String l_name2 = rs.lname(scan);
				String user_name2 = rs.userName(scan);
				String pass_word2 = rs.password(scan);
				String my_role2 = rs.role(scan);
				
				int user_id2 = us.createUser(user_name2, pass_word2,  f_name2, l_name2,  my_role2);
				
				bankAccountId = bas.createBankAccount(accType, true);
				
				bas.createUserIdAccountIdJunction(user_id, bankAccountId);
				bas.createUserIdAccountIdJunction(user_id2, bankAccountId);
				System.out.println("Thank you for opening a bank account in our bank!"
						+ "\nPlease wait for some time to get your bank account approved.");
				System.out.println("=====================================================");
				
				check = false;
			}else if(joint.equals("n")){
				bankAccountId = bas.createBankAccount(accType, false);				
				bas.createUserIdAccountIdJunction(user_id, bankAccountId);
				System.out.println("Thank you for opening a bank account in our bank!"
						+ "\nPlease wait for some time to get your bank account approved.");
				System.out.println("==============================================================");
				check = false;
			} else {
				System.out.println("Invalid input. Please press 'y' or 'n'");
				System.out.println("======================================");
			}
		}
		
	}
	
	public List<Integer> oneUserAccountIds() {
		List<Integer> accountIds = new ArrayList<>();
		if(size > 0) {
			for(BankAccount ba : bankAccounts) {
				accountIds.add(ba.getAccountId());
			}
		}
		return accountIds;
	}
	
		
	public void userBankAccountInfo() {
		if(size > 0 || role.equals("Admin")) {
			List<BankAccount> allBankAccounts = new ArrayList<>();
			if(role.equals("Admin")) {
				allBankAccounts = bas.getAllBankAccounts();
			} else{
				allBankAccounts = bas.getBankAccountsByUsername(username);
			}
			int countAccount = 1;
			System.out.println(String.format("\t\t"+"%-20s%-20s%-20s%-20s%-20s%-20s", "Account ID", "Balance" , "Status" , "Type" , "Joint" , "Created Date"));
			System.out.println(String.format("\t\t"+"%-20s%-20s%-20s%-20s%-20s%-20s", "**********", "*******" , "******" , "****" , "*****" , "*************"));
			for(BankAccount ba: allBankAccounts) {
				System.out.println("Account: "+countAccount++ +"\t"+ ba);
		}
		} else {
			System.out.println("There is not your bank account in our bank.");
		}
	}
	
}
