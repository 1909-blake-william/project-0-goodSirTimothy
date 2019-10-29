package com.revature.prompt;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AdminViewDao;
import com.revature.dao.DatabaseDao;
import com.revature.dao.DatabaseDaoImpl;
import com.revature.models.UsersViewAdmin;

public class AdminViewUsersPrompt implements Prompt {

	Scanner scan = new Scanner(System.in);
	DatabaseDao dbDao = new DatabaseDaoImpl();
	AdminViewDao adminView = AdminViewDao.currentImplementation;

	@Override
	public Prompt run() {
		System.out.println("All user on server: ");
		dbDao.displayAllUsers();
		List<UsersViewAdmin> users = adminView.getUserList();
		System.out.println("All Accounts: ");
		System.out.println("Please enter a number\n0. To go back.");
		int totalUsers = 1;
		for (UsersViewAdmin user : users) {
			System.out.println(
					(totalUsers) + ":\tID: " + user.getUserId() + ", Username: " + user.getUsername() + ", Admin: "
							+ user.getAdmin() + "\n\t\tFull Name: " + user.getFirstName() + ", " + user.getLastname());
			totalUsers++;
		}
		users.clear();
		System.out.print("Press 'Enter' to exit");
		scan.nextLine();
		return new UserMenuPrompt();
	}
}
