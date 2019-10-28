package com.revature.prompt;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.DatabaseDao;
import com.revature.dao.DatabaseDaoImpl;
import com.revature.dao.UserDao;
import com.revature.models.Account;

public class ViewAccountsPrompt implements Prompt {

	DatabaseDao dbDao = new DatabaseDaoImpl();
	UserDao user = UserDao.currentImplementation;
	Scanner scan = new Scanner(System.in);
	AccountDao accountDao = AccountDao.currentImplementation;

	@Override
	public Prompt run() {
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
			accounts.clear();
		}
		return new UserMenuPrompt();
	}

	/**
	 * 
	 * @param selection
	 * @param accounts
	 */
	private void promptSelection(int selection, List<Account> accounts) {
		for (int i = 0; i < accounts.size(); i++) {
			if (i + 1 == selection) {
				System.out.println(accounts.get(i).getAccountBalance());
				displayWithdrawAndDeposit(i + 1, accounts.get(i).getAccountBalance(), accounts.get(i).getAccountId());
			}
		}
	}

	/**
	 * 
	 * @param id
	 * @param balance
	 * @param accountId
	 */
	private void displayWithdrawAndDeposit(int id, float balance, int accountId) {
		int selection = -1;
		while (selection != 0) {
			System.out.println("1. to deposit money into the account");
			System.out.println("2. to withdraw money from the account");
			System.out.println("0. to go back");

			selection = scan.nextInt();
			scan.nextLine();
			if (selection == 1) {
				transactionAmount(id, balance, accountId, "deposited");
				selection = 0;
			} else if (selection == 2) {
				transactionAmount(id, balance, accountId, "withdrawn");
				selection = 0;
			}
		}
	}

	/**
	 * 
	 * @param id
	 * @param balance
	 * @param accountId
	 * @param typeOfTransaction
	 */
	private void transactionAmount(int id, float balance, int accountId, String typeOfTransaction) {
		boolean keepLoopGoing = true;
		while (keepLoopGoing) {
			System.out.println("Enter the amount that is being " + typeOfTransaction + " or enter 0 exit");
			float amount = scan.nextFloat();
			scan.nextLine();

			while (amount < 0) {
				System.out.println("Please input only positive numbers");
				amount = scan.nextFloat();
				scan.nextLine();
			}

			System.out.println(
					"Is this correct amount: $" + String.format("%.2f", amount) + "\nEnter 'y' for yes, 'n' for no");
			String confirm = scan.nextLine();

			if ("y".equals(confirm)) {
				if ("withdrawn".equals(typeOfTransaction)) {
					amount = amount * -1;
				}
				System.out.println(dbDao.updateBalance(amount, balance, accountId, user.getAccountId()));
				keepLoopGoing = false;
			} else if ("0".equals(confirm)) {
				keepLoopGoing = false;
			}
		}
	}
}
