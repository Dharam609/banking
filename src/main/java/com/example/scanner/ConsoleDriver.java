package com.example.scanner;

import java.util.Scanner;

public class ConsoleDriver {	
	public static void main(String[] args) {
		System.out.println("* WELCOME TO REVATURE BANK *");		
		System.out.println("****************************");		
		Scanner scan = new Scanner(System.in);
		new HomeScreen().render(scan);	
	}	
}

