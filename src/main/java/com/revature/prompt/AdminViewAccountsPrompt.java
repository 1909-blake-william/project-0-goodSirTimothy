package com.revature.prompt;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AdminViewDao;
import com.revature.dao.DatabaseDao;
import com.revature.dao.DatabaseDaoImpl;
import com.revature.models.Account;
import com.revature.models.AccountViewAdmin;

public class AdminViewAccountsPrompt implements Prompt {

	Scanner scan = new Scanner(System.in);
	DatabaseDao dbDao = new DatabaseDaoImpl();
	AdminViewDao adminView = AdminViewDao.currentImplementation;

	@Override
	public Prompt run() {
		System.out.println("All banking accounts: ");
		dbDao.displayAllAccounts();
		List<AccountViewAdmin> accounts = adminView.getAccountList();
		System.out.println("All Accounts: ");
		System.out.println("Please enter a number\n0. To go back.");
		int totalAccounts = 1;
		for (AccountViewAdmin account : accounts) {
			System.out.println((totalAccounts) + ". Account status: " + account.getAccountStatus() + ". Account type: "
					+ account.getAccountType() + ", Total Amount = $"
					+ String.format("%.2f", account.getAccountBalance()) + "\n Username: " + account.getUsername());
			totalAccounts++;
		}
		accounts.clear();
		System.out.print("Press 'Enter' to exit");
		scan.nextLine();
		return new UserMenuPrompt();
	}
}
