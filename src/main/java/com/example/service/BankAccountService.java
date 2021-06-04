package com.example.service;

import java.util.List;

import org.apache.log4j.Logger;


import com.example.dao.BankAccountDAOImpl;
import com.example.model.BankAccount;


public class BankAccountService {
	BankAccountDAOImpl adi = new BankAccountDAOImpl();	
	public static final Logger log2 = Logger.getLogger(BankAccountService.class); 
	
	public BankAccountService() {
		
	}
	
	public int createBankAccount(String type, boolean joint) {
		int success = adi.createBankAccount(type, joint);
		if(success > 0) {
			log2.error("The bank account was not created.");
			
		} else {
			log2.error("The bank account was not created.");
			
		}
		return success;
	}
	
	public double getBalance(int accoutId) {
		BankAccount ba = adi.getBankAccountByAccountId(accoutId);
		return ba.getBalance();
	}
	public void createUserIdAccountIdJunction(int user_id, int bankAccountId) {
		  adi.insertUserIdAccountId(user_id, bankAccountId);
	}
	
	public boolean deleteBankAccout(int bankAccountId) {
		return adi.deleteAccount(bankAccountId);
	}
	
	public BankAccount getBankAccountByAccountId(int bankAccountId){
		return adi.getBankAccountByAccountId(bankAccountId);
	}
	
	public List<BankAccount> getAllBankAccounts(){
		return adi.getAllBankAccounts();
	}
	public List<BankAccount> getBankAccountsByUsername(String username){
		return adi.getBankAccountsByUsername(username);
	}
	public boolean deposit(int id, double amount) {
		return adi.deposit(id, amount);
	}
	
	public boolean changeBankStatusAccount(int accountId, String status) {
		return adi.updateBankAccountStatus(accountId, status);
	}
	
	public boolean withdraw(int bankAccountId, double amount) {
		return adi.withdraw(bankAccountId, amount);
	}
	
	public boolean transfer(int bankAccountId1, int bankAccountId2, double amount) {
		if(adi.withdraw(bankAccountId1, amount)) {
			if(adi.deposit(bankAccountId2, amount)){
				log2.debug("The amount "+amount+" Transferer between the accounts "+bankAccountId1 + " and " + bankAccountId2);
				return true;
				
			}
			else {
				adi.deposit(bankAccountId1, amount);
				log2.debug("The amount withdrawn was deposited back to the acccout "+ bankAccountId1);
				return false;
			}			
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		BankAccountService bs = new BankAccountService();
		List<BankAccount> accounts = bs.getBankAccountsByUsername("sg1");
		for(BankAccount ba : accounts) {
			System.out.println(ba.getAccountId());
		}
		bs.deposit(22, 5000);
//		bs.changeBankStatusAccount(25);
//		bs.changeBankStatusAccount(3);
		bs.transfer(22, 24, 500);		
//		System.out.println(ba);
//		int test = bs.createBankAccount("Checking", false);
//		System.out.println(test);
//		bs.createUsernameAccountIdJunction("suman", 11);
	}
}
