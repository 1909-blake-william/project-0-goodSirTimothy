package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.UserDao;

public class UserMenuPrompt implements Prompt {

	UserDao user = UserDao.currentImplementation;
	
	Scanner scan = new Scanner(System.in);
	
	public Prompt run() {
		System.out.println("Welcome: " + user.getName());
		System.out.println("Please input one of the options: ");
		System.out.println("Press 1 to add bank account: ");
		System.out.println("Press 2 to remove bank account: ");
		System.out.println("Press 3 to view your bank accounts: ");
		System.out.println("Press 4 to view transaction history: ");
		if(user.getAdmin()) {
			System.out.println("Press 7 to view all users: ");
			System.out.println("Press 8 to view all accounts: ");
		}
		System.out.println("Press 0 to logout: ");
		
		String input = scan.nextLine();
		
		switch (input) {
		case "1":
			return new AddAccountPrompt();
		case "2":
			return new RemoveAccountPrompt();
		case "3":
			return new ViewAccountsPrompt();
		case "4":
			return new TransactionPrompt();
		case "7":
			if(user.getAdmin()) {
				return new AdminViewUsersPrompt();
			}
			break;
		case "8":
			if(user.getAdmin()) {
				return new AdminViewAccountsPrompt();
			}
			break;
		case "0":
			return new LoginPrompt();

		default:
			break;
		}
		
		return new UserMenuPrompt();
	}

}
