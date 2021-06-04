package com.example.dao;

import java.util.List;

import com.example.model.BankAccount;

public interface BankAccountDAO {
	int createBankAccount(String type, boolean joint);	
	
	void insertUserIdAccountId(int user_id, int bankAccountId);
	boolean deleteAccount(int bankAccountId);
	
	boolean deposit(int bankAccountId, double amount);
	boolean updateBankAccountStatus(int bankAccountId, String status);	
	boolean withdraw(int bankAccountId, double amount);	
	
	List<BankAccount> getAllBankAccounts();	
	List<BankAccount> getBankAccountsByUsername(String username);
	
	BankAccount getBankAccountByAccountId(int bankAccountId);
}
