package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.DatabaseDaoImpl;

public class LoginPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);

	/**
	 * Display a prompt for the user to login or make an account.
	 */
	public Prompt run() {
		System.out.print("Enter 1 to login.\nEnter 2 to make an account: ");
		String input = scan.nextLine();
		switch (input) {
		case "1":
			System.out.println("Please enter your username");
			String username = scan.nextLine();

			System.out.println("Please enter your password");
			String password = scan.nextLine();

			DatabaseDaoImpl dbDao = new DatabaseDaoImpl();
			if (dbDao.checkUserAndPassword(username, password)) {
				dbDao.pullUserInformation(username, password);
				return new UserMenuPrompt();
			}
			
			System.out.println("\tSorry incorrect credentials\n");
			break;
		case "2":
			return new UserCreationPrompt();
		default:
			break;
		}
		// recall the login prompt
		return new LoginPrompt();
	}

}
