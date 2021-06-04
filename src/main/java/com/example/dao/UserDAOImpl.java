package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.model.BankAccount;
import com.example.model.User;
import com.example.utility.DAOUtility;

/*
 * This class implements methods from UserDAO interface. The methods in this class use getConnection method from DAOUtility class, and
 * implement logics while having access to the database specified in the url supplied to DriverManager.getConnection() method inside
 * the body of DAOUtility.getConnection() method. DAOUtility class is defined static so that its object instantiation is not required to
 * call getConnection() method.
 */

public class UserDAOImpl implements UserDAO{
	private BankAccountDAOImpl badi = new BankAccountDAOImpl();
	public UserDAOImpl() {
		
	}	

	/*
	 * The insertUser() method consists of logic to insert values into bankuser table of bank_database. It returns integer value -1 if it fails
	 * to insert the new values into the specified table, but the the current user's user_id if it succeeds to insert.
	 */
	@Override
	public int insertUser(String username, String password, String fname, String lname, String role) {
		int user_id = -1;
		User user = getUserByUsername(username);
		if(user == null) {
			try(Connection con = DAOUtility.getConnection()) {
				String sql = "insert into bankuser(username, password, fname, lname, role) values(?,?,?,?,?)";
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				ps.setString(3, fname);
				ps.setString(4, lname);
				ps.setString(5, role);
				
				ps.executeUpdate();
				
				sql = "select max(user_id) from bankuser";
				ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					user_id = rs.getInt(1);
				}
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		
		}
		
		return user_id;
	}

	@Override
	public void updateUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String username) {
		User user = getUserByUsername(username);
		if(user != null) {
			List<BankAccount> bAccounts = badi.getBankAccountsByUsername(username);			
			try(Connection con = DAOUtility.getConnection()) {
				String sql = "delete from bankuser where username = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
				
				int success = ps.executeUpdate();				
		/*
		 * 	Checking if the user has a joint bank account. If yes, the joint value of the relevant bank account will turn into false.
		 *  Assumption: A joint account in this bank will have max two account holders. */
				if(success == 1) {
					sql = "update bankaccount set joint = ? where account_id = ?";
					ps = con.prepareStatement(sql);
					
					for(BankAccount id : bAccounts) {
							if(id.isJoint()){
								ps.setBoolean(1, false);
								ps.setInt(2, id.getAccountId());
								ps.executeUpdate();							
							} else {
								badi.deleteAccount(id.getAccountId());
								System.out.println("The relevant single bank account(s) has/have been deleted as well.");	
							}							
					}
					System.out.println("All relevant tables are successfully updated after deleting the user: "+ username);
				}
				ps.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}			
			
		}
		
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		try(Connection con = DAOUtility.getConnection()) {
			String sql = "select * from bankuser";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				users.add(new User(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6)));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return  users;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = null;
		try(Connection con = DAOUtility.getConnection()) {
			String sql = "select * from bankuser where username = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No user with the username "+ username + " found.");
		} finally {
			
		}
		return user;
	}


	@Override
	public boolean login(String username, String password) {
		try {
			User user = getUserByUsername(username);
			if(user.getPassword().trim().equals(password)){
				return true;			
			}
		} catch (Exception e) {
			
		} finally {
			
		}
		return false;
	}

	@Override
	public int callableInsertUser(String username, String password, String fname, String lname, String role) {
		User user = getUserByUsername(username);
		int user_id = -1;
		if(user == null) {
			try(Connection con = DAOUtility.getConnection()){
				String sql = "{? = call insert_user(?,?,?,?,?)}";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				ps.setString(3, fname);
				ps.setString(4, lname);
				ps.setString(5, role);
				
				ps.execute();
				
				sql = "select max(user_id) from bankuser";
				ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					user_id = rs.getInt(1);
				}
				ps.close();
				rs.close();
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		
		}
		
		return user_id;
	}
	

	
}
