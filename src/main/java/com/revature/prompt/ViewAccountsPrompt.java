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
		dbDao.getAccountInformation(user.getAccountId());
		List<Account> accounts = accountDao.getAccounts();
		int selection = -1;
		while (selection != 0) {
			System.out.println("All Accounts for " + user.getName() + ": ");
			System.out.println("Please enter a number\n0. To go back.");
			int totalAccounts = 1;
			for (Account account : accounts) {
				System.out.println((totalAccounts) + ". " + account);
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
	 */
	private void displayWithdrawAndDeposit(int id, float balance, int accountId) {
		int selection = -1;
		while (selection != 0) {
			System.out.println("1. to deposit money into the account");
			System.out.println("2. to withdraw money from the account");
			System.out.println("0. to go back");

			selection = scan.nextInt();
			scan.nextLine();
			if(selection == 1) {
				depositMoney(id, balance, accountId);
			} else if(selection == 2) {
				
			}
		}
	}

	/**
	 * If the user selects to deposit money, all them to input how much money would
	 * would like to deposit.
	 */
	private void depositMoney(int id, float balance, int accountId) {
		boolean keepLoopGoing = true;
		while (keepLoopGoing) {
			System.out.println("Enter the amount that is being deposited");
			float amount = scan.nextFloat();
			scan.nextLine();
			System.out.println("Is this correct ammount: " + amount + "\nEnter 'y' for yes, 'n' for no");
			String confirm = scan.nextLine();
			if("y".contentEquals(confirm)) {
				System.out.println(dbDao.updateBalance(amount, balance, accountId, user.getAccountId()));
			}
		}
	}

	/**
	 * If the user selects to withdraw money, allow them to input the amount they
	 * would like to withdraw
	 */
	private void withdrawMoney() {
		boolean keepLoopGoing = true;
		while (keepLoopGoing) {
			System.out.println("Enter the amount that is being withdrawn");
			int amount = scan.nextInt();
			scan.nextLine();
			System.out.println("Is this correct ammount: " + amount + "\nEnter 'y' for yes, 'n' for no");
			String confirm = scan.nextLine();
			if("y".contentEquals(confirm)) {
				
			}
		}
	}
}
