package com.example.scanner;

public class ValidationScreen {
	
	public boolean validString(String str) {		
		String validString = str.trim().replaceAll("[^a-zA-B]", "");
		if(validString.length() != str.length()) {
			System.out.println("Invalid input!");
			return false;
		}
		return true;
	}
	
	public boolean validUnamePass(String str) {		
		String validString = str.trim().replaceAll("[^a-zA-B0-9]", "");
		String alphaCheck = validString.replaceAll("[0-9]", "");
		String numCheck = validString.replaceAll("[a-zA-Z]", "");
		if(validString.length() != str.length() || (alphaCheck.length() < 1) || (numCheck.length() < 1)) {
			System.out.println("Invalid input!");
			return false;
		}
		return true;
	}
	
	public boolean validNumber(String str) {
		String validString = str.trim().replaceAll("[^0-9]", "");
		if(validString.length() != str.length()) {
			System.out.println("Invalid input!");
			return false;
		}
		return true;

	}
}
