package com.revature.prompt;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.DatabaseDao;
import com.revature.dao.DatabaseDaoImpl;
import com.revature.dao.TransactionDao;
import com.revature.dao.UserDao;
import com.revature.models.Account;
import com.revature.models.Transaction;

public class ViewTransactionPrompt implements Prompt {

	DatabaseDao dbDao = new DatabaseDaoImpl();
	UserDao user = UserDao.currentImplementation;
	Scanner scan = new Scanner(System.in);
	AccountDao accountDao = AccountDao.currentImplementation;
	TransactionDao transactionDao = TransactionDao.currentImplementation;

	@Override
	public Prompt run() {
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
			accountSelectionLogic(selection, accounts);
			accounts.clear();
		}
		return new UserMenuPrompt();
	}

	private void accountSelectionLogic(int selection, List<Account> accounts) {
		for (int i = 0; i < accounts.size(); i++) {
			if (i + 1 == selection) {
				transactionHistory(accounts.get(i).getAccountId());
			}
		}
	}

	private void transactionHistory(int accountId) {
		System.out.println(dbDao.displayTransactionInformation(accountId, user.getAccountId()));
		List<Transaction> transactions = transactionDao.getTransactions();
		for(Transaction trans: transactions) {
			System.out.println("ID = " + trans.getTransactionId() + ", Amount = $" + String.format("%.2f", trans.getTransactionAmount()));
		}
	}
}
