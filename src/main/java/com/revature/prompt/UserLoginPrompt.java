package com.revature.prompt;

import java.util.Scanner;

public class UserLoginPrompt implements Prompt{

	Scanner scan = new Scanner(System.in);
	
	@Override
	public Prompt run() {
		System.out.print("Please enter your username: ");
		String username = scan.nextLine();
		/* 
		 * while (username.equals(_Method_For_Checking_Username){
		 * 		Sysout("Username has been already picked.\nPlease input another username:")
		 * 		username = scan.nextLine();
		 * 		// Make away to break free.
		 * }
		 */
		
		System.out.print("Please enter your password: ");
		String passwordString = scan.nextLine();
		return null;
	}

}
