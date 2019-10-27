package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.DatabaseDaoImpl;
import com.revature.dao.UserDao;

public class AddAccountPrompt implements Prompt {

	Scanner scan = new Scanner(System.in);
	UserDao user = UserDao.currentImplementation;
	DatabaseDaoImpl dbDao = new DatabaseDaoImpl();

	@Override
	public Prompt run() {
		int selection = -1;
		while (selection != 0) {
			System.out.println("What kind of banking account would you like to add:");
			System.out.println("1. Savings");
			System.out.println("2. Money Market");
			System.out.println("3. Checking");
			System.out.println("0. To go back");
			selection = scan.nextInt();
			scan.nextLine();
			while (selection < 0 || selection > 3) {
				System.out.println("Incorrect input.");
				System.out.println("1. Savings");
				System.out.println("2. Money Market");
				System.out.println("3. Checking");
				System.out.println("0. To go back");
				selection = scan.nextInt();
				scan.nextLine();
			}
			selection(selection);
		}
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
