package com.revature.prompt;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AdminViewDao;
import com.revature.dao.DatabaseDao;
import com.revature.dao.DatabaseDaoImpl;
import com.revature.models.UsersViewAdmin;

public class AdminViewUsersPrompt implements Prompt{
	
	Scanner scan = new Scanner(System.in);
	DatabaseDao dbDao = new DatabaseDaoImpl();
	AdminViewDao adminView = AdminViewDao.currentImplementation;

	@Override
	public Prompt run() {
		System.out.println("All user on server: ");
		int selection = -1;
		//while (selection != 0) {
			dbDao.displayAllUsers();
			List<UsersViewAdmin> users = adminView.getUserList();
			System.out.println("All Accounts: ");
			System.out.println("Please enter a number\n0. To go back.");
			int totalUsers = 1;
			for (UsersViewAdmin user : users) {
					System.out.println((totalUsers) + ":\tID: "+user.getUserId() + ", Username: " + user.getUsername() + ", Admin: " + user.getAdmin()
							+ "\n\t\tFull Name: " + user.getFirstName() + ", " + user.getLastname());
					totalUsers++;
			}
			/*
			selection = scan.nextInt();
			scan.nextLine();
			while (selection < 0 || selection > accounts.size()) {
				System.out.println("Sorry, that is an invalvid selection. please re-input your number");
				System.out.println("0. To go back.");
				totalAccounts = 1;
				for (Account account : accounts) {
					System.out.println((totalAccounts) + ". " + account);
					totalAccounts++;
				}
				selection = scan.nextInt();
				scan.nextLine();
			}
			promptSelection(selection, accounts);
			*/
			users.clear();
			System.out.print("Enter anything to exit:");
			scan.nextLine();
		//}
		return new UserMenuPrompt();
	}
}
