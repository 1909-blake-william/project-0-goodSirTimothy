package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.DatabaseDaoImpl;
import com.revature.dao.UserDao;

public class AddAccountPrompt implements Prompt {

	Scanner scan = new Scanner(System.in);
	UserDao user = UserDao.currentImplementation;
	DatabaseDaoImpl dbDao = new DatabaseDaoImpl();
	private int selection = -1;

	/**
	 * Prompt for adding new account's that are linked to the user.
	 */
	@Override
	public Prompt run() {
		String selectionString = "";
		while (selection != 0) {
			System.out.println("What kind of banking account would you like to add:");
			System.out.println("1. Savings" + "\n2. Money Market"
					+ "\n3. Checking" + "\n0. To go back");
			selectionString = scan.nextLine();
			selection = Integer.parseInt(selectionString);
			
			while (selection < 0 || selection > 3) {
				System.out.println("Incorrect input.");
				System.out.println("1. Savings" + "\n2. Money Market"
						+ "\n3. Checking" + "\n0. To go back");
				selectionString = scan.nextLine();
				selection = Integer.parseInt(selectionString);
			}
			
			selection(selection);
		}
		// when done
		return new UserMenuPrompt();
	}
	
	private void selection(int selection) {
		switch (selection) {
		case 1:
			dbDao.saveAccountToDatabase("Savings", 0, 1, user.getAccountId());
			break;
		case 2:
			dbDao.saveAccountToDatabase("Money Market", 0, 1, user.getAccountId());
			break;
		case 3:
			dbDao.saveAccountToDatabase("Checking", 0, 1, user.getAccountId());
			break;
		default:
			break;
		}
	}
}
