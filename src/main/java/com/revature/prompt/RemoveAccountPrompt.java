package com.revature.prompt;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.DatabaseDao;
import com.revature.dao.DatabaseDaoImpl;
import com.revature.dao.UserDao;
import com.revature.models.Account;

public class RemoveAccountPrompt implements Prompt {

	DatabaseDao dbDao = new DatabaseDaoImpl();
	UserDao user = UserDao.currentImplementation;
	Scanner scan = new Scanner(System.in);
	AccountDao accountDao = AccountDao.currentImplementation;

	@Override
	public Prompt run() {
		System.out.println("input which account you would like to delete: ");
		String selectionString = "";
		int selection = -1;
		while (selection != 0) {
			dbDao.getAccountInformation(user.getAccountId());
			List<Account> accounts = accountDao.getAccounts();

			System.out.println("All Accounts for " + user.getName() + ": ");
			System.out.println("Please enter a number\n0. To go back.");
			int totalAccounts = 1;
			for (Account account : accounts) {
				if (account.getAccountStatus() != 0) {
					System.out.println((totalAccounts) + ". Account type: " + account.getAccountType()
							+ ", Total Amount = $" + String.format("%.2f", account.getAccountBalance()));
				}
				totalAccounts++;
			}
			selectionString = scan.nextLine();
			selection = Integer.parseInt(selectionString);
			while (selection < 0 || selection > accounts.size()) {
				System.out.println("Sorry, that is an invalvid selection. please re-input your number");
				System.out.println("0. To go back.");
				totalAccounts = 1;
				for (Account account : accounts) {
					System.out.println((totalAccounts) + ". " + account);
					totalAccounts++;
				}
				selectionString = scan.nextLine();
				selection = Integer.parseInt(selectionString);
			}
			promptSelection(selection, accounts);
			accounts.clear();
		}
		return new UserMenuPrompt();
	}

	private void promptSelection(int selection, List<Account> account) {
		for (int i = 0; i < account.size(); i++) {
			if (i + 1 == selection) {
				if (account.get(i).getAccountBalance() != 0) {
					System.out.println("Sorry, your account must be at $0.00 before it can be closed.");
				} else {
					dbDao.updateDeleteAccount(account.get(i).getAccountId(), user.getAccountId());
				}
			}
		}
	}
}
