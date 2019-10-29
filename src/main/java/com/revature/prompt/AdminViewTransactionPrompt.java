package com.revature.prompt;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AdminViewDao;
import com.revature.dao.DatabaseDao;
import com.revature.dao.DatabaseDaoImpl;
import com.revature.models.TransactionViewAdmin;
import com.revature.models.UsersViewAdmin;

public class AdminViewTransactionPrompt implements Prompt {

	Scanner scan = new Scanner(System.in);
	DatabaseDao dbDao = new DatabaseDaoImpl();
	AdminViewDao adminView = AdminViewDao.currentImplementation;

	@Override
	public Prompt run() {
		System.out.println("All Transactions: ");
		dbDao.displayAllTransactions();
		List<TransactionViewAdmin> transactions = adminView.getTransactionList();
		int totalTransactions = 1;
		for (TransactionViewAdmin transaction : transactions) {
			System.out.println((totalTransactions) + ":\tID: " + transaction.getTransactionId()
					+ ", Transaction Amount: $" + String.format("%.2f", transaction.getTransactionAmount()) + ", from account: "
					+ transaction.getAccountId() + "\nBy: " + transaction.getUsername());
			totalTransactions++;
		}
		transactions.clear();
		System.out.print("Press 'Enter' to exit");
		scan.nextLine();
		return new UserMenuPrompt();
	}

}
