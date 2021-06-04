package com.example.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BankAccount implements Serializable {	
	private int accountId;
	private String type;
	private boolean joint = false;
	private double balance = 0.0;
	private String status = "pending";
	private String created_date;
	
	
	public BankAccount() {		
	}
	public BankAccount(int accountId, double balance,  String status, String type, boolean joint,  String created_date) {
		this.accountId = accountId;
		this.type = type;
		this.joint = joint;
		this.balance = balance;		
		this.status = status;
		this.created_date = created_date;
	}
	
	public int getAccountId() {
		return accountId;
	}

	public void setAccoutID(){
		System.out.println("You  are not allowed to set the account id.");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;		
	}

	public boolean isJoint() {
		return joint;
	}

	public void setJoint(boolean joint) {
		// many things to be checked and updated before changing joint value	
		this.joint = joint;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
//		if(!role.equals("Customer")){
		this.balance = balance;	
//		}else{
		System.out.println("You are not allowed to set the balance.");
//		}					
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
//		if(!role.equals("Customer")){
		this.status = status;	
//		}else{
		System.out.println("You are not allowed to change the status of an account.");
//		}
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
//		if(!role.equals("Customer")){
		this.created_date = created_date;	
//		}else{
		System.out.println("You are not allowed to modify created date.");
//		}
	}

	@Override
	public String toString() {
		return  String.format("%-20s%-20s%-20s%-20s%-20s%-20s", accountId, balance , status , type , joint , created_date);
	}
		
		
}
