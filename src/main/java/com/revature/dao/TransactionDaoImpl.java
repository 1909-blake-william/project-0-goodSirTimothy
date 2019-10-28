package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Transaction;

public class TransactionDaoImpl implements TransactionDao{

	List<Transaction> transaction = new ArrayList<Transaction>();
	
	@Override
	public void setTransaction(int transactionId, float transactionAmount) {
		transaction.add(new Transaction(transactionId, transactionAmount));
	}

	@Override
	public List<Transaction> getAccounts() {
		return transaction;
	}
	
}
