package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.model.BankAccount;
import com.example.model.User;
import com.example.utility.DAOUtility;

public class BankAccountDAOImpl implements BankAccountDAO{
	
	public BankAccountDAOImpl() {
	}
	 
	@Override
	public int createBankAccount(String type, boolean joint) {
		int bankAccountId = -1;
		try(Connection con = DAOUtility.getConnection()) {
			String sql = "insert into bankaccount(type, joint) values(?,?)";			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, type);
			ps.setBoolean(2,joint);
			ps.executeUpdate();			
			
			sql = "select max(account_id) from bankaccount";
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				bankAccountId = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		

		return bankAccountId;
		
	}
	
	@Override
	public void insertUserIdAccountId(int user_id, int bankAccountId) {
		try (Connection con = DAOUtility.getConnection()){
			String sql = "insert into user_junction_account(user_id, account_id) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setInt(2, bankAccountId);
			
			ps.executeUpdate();		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public boolean deleteAccount(int bankAccountId) {		
		try(Connection con = DAOUtility.getConnection()) {
			String sql = "delete from bankaccount where account_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bankAccountId);
			
			int success = ps.executeUpdate();
			if(success == 1) {
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public List<BankAccount> getAllBankAccounts() {
		List<BankAccount> bankAccounts = new ArrayList<>();
		
		try(Connection con = DAOUtility.getConnection()) {
			String sql = "select * from bankaccount";
			
			PreparedStatement ps = con.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				bankAccounts.add(new BankAccount(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getBoolean(5),rs.getString(6)));				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
		return bankAccounts;	
	}

	@Override
	public BankAccount getBankAccountByAccountId(int bankAccountId) {
		BankAccount bankAccount = null;
		try(Connection con = DAOUtility.getConnection()) {
			String sql = "select * from bankaccount where account_id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bankAccountId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				bankAccount = new BankAccount(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getBoolean(5),rs.getString(6));				
			}
			
			return bankAccount;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
		return bankAccount;
	}

	@Override
	public List<BankAccount> getBankAccountsByUsername(String username) {
		UserDAOImpl udi = new UserDAOImpl();
		User user = udi.getUserByUsername(username);
		List<BankAccount> bankAccounts = new ArrayList<>();
		try(Connection con = DAOUtility.getConnection()) {
			String sql = "select * from user_junction_account where user_id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUser_id());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {				
				bankAccounts.add(getBankAccountByAccountId(rs.getInt(2)));
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return bankAccounts;
	}	

	@Override
	public boolean updateBankAccountStatus(int bankAccountId, String status) {
		BankAccount account = getBankAccountByAccountId(bankAccountId);
		status = status.trim().toLowerCase();
		if(account != null) {
			try(Connection con = DAOUtility.getConnection()){
				String sql = "update bankaccount set status = ? where account_id = ? ";
				PreparedStatement ps = con.prepareStatement(sql);
				
				if(status.equals("approved") || status.equals("denied")) {
					ps.setString(1, status);
					ps.setInt(2, bankAccountId);
					ps.executeUpdate();
					return true;
				} else {
					return false;
				}			
				
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				
			}
		}
		return false;
	}
	
	@Override
	public boolean deposit(int bankAccountId, double amount) {
		BankAccount bankAccount = getBankAccountByAccountId(bankAccountId);
		if(bankAccount != null) {
			if(bankAccount.getStatus().equals("pending")) {
				System.out.println("Sorry, the bank accout with the bank account id : "+bankAccountId + " is either pending or denied.");
				return false;
			} else {
				if(amount <= 0) {
					System.out.println("Invalid amount entered.");
					return false;
				}
				else {
					try(Connection con = DAOUtility.getConnection()) {					
						String sql = "update bankaccount set balance = ? where account_id = ? ";
						
						PreparedStatement ps = con.prepareStatement(sql);

						double currentBalance =  bankAccount.getBalance();
						double newBalance = currentBalance + amount;
						
						ps.setDouble(1, newBalance);					
						ps.setInt(2, bankAccountId);
						
						ps.executeUpdate();	
						
						return true;
						
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Ammount was not deposited properly.");
						// TODO: handle exception
					}
					
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean withdraw(int bankAccountId, double amount) {	
		BankAccount bankAccount = getBankAccountByAccountId(bankAccountId);
		if(bankAccount != null ) {
			if(bankAccount.getStatus().equals("pending")) {
				System.out.println("Sorry, the bank accout with the bank account id : "+bankAccountId + " is waiting to get apporved.");
			} else {
				if(amount <= 0 || bankAccount.getBalance() < amount) {
					System.out.println("Invalid amount entered.");
				}
				else {
					try(Connection con = DAOUtility.getConnection()) {					
						String sql = "update bankaccount set balance = ? where account_id = ? ";
						
						PreparedStatement ps = con.prepareStatement(sql);

						double currentBalance =  bankAccount.getBalance();						
						double newBalance = currentBalance - amount;
						
						ps.setDouble(1, newBalance);					
						ps.setInt(2, bankAccountId);
						
						ps.executeUpdate();
						
						return true;	
						
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Ammount was not deposited properly.");
						// TODO: handle exception
					}
					
				}
			}
			
		}		
		return false;
	}
	
}
