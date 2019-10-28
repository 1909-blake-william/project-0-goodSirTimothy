package com.revature.dao;

import java.util.List;

import com.revature.models.Transaction;

public interface TransactionDao {
	TransactionDao currentImplementation = new TransactionDaoImpl();
	
	/**
	 * This is for setting 
	 * @param transactionId
	 * @param transactionAmount
	 */
	void setTransaction(int transactionId, float transactionAmount);
	
	/**
	 * 
	 * @return
	 */
	List<Transaction> getTransactions();
}
